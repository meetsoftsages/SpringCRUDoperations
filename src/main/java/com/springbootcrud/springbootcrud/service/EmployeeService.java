package com.springbootcrud.springbootcrud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcrud.springbootcrud.exception.ResourceNotFoundException;
import com.springbootcrud.springbootcrud.model.Employee;
import com.springbootcrud.springbootcrud.model.quality.Root;
import com.springbootcrud.springbootcrud.repository.EmployeeRepository;
import org.sonar.wsclient.SonarClient;
import org.springframework.stereotype.Service;

import java.util.*;

enum CodeQuality{
    BUG,
    VULNERABILITY,
    CODE_SMELL;
}

enum CodeReview{
    duplicated_lines_density,
    coverage,
    security_hotspots;
}
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private SonarClient sonarClient = sonar();

    public SonarClient sonar() {
        SonarClient sonarClient = SonarClient.builder()
                .url("http://localhost:9000")
                .login("admin")
                .password("Interns@1234")
                .build();
        return sonarClient;
    }

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    //Create
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    // Get all Employee record
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    //Get a single Employee record by ID
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "ID", id);
        }
    }
    //Update
    public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "ID", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    //Delete
    public void deleteEmployee(long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));
        employeeRepository.deleteById(id);
    }

    public String getProjects(){
        String sonarEndpoint = "api/projects/search";
        String sonarResponse = sonarClient.get(sonarEndpoint);
        return sonarResponse;
    }

    public ArrayList<String> getTypes() throws JsonProcessingException{
        ArrayList<String> arrayList=new ArrayList<>();
        Map<String, Object> sonarEndParams = new HashMap<>();
        String sonarEndpoint = "api/issues/search";
        sonarEndParams.put("componentKeys", "com.springbootcrud:springbootCRUD");
        for (CodeQuality type: CodeQuality.values()){
            sonarEndParams.put("types",type);
            String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);
            JsonNode parent = new ObjectMapper().readTree(sonarResponse);
            String total = parent.path("total").asText();
            arrayList.add(total);
        }
        return arrayList;
    }

    public ArrayList<String> getQualityReview() throws JsonProcessingException{
        ArrayList<String> arrayList=new ArrayList<>();
        Map<String, Object> sonarEndParams = new HashMap<>();
        String sonarEndpoint = "api/measures/component";
        sonarEndParams.put("component", "com.springbootcrud:springbootCRUD");
        for (CodeReview review: CodeReview.values()){
            sonarEndParams.put("metricKeys",review);
            String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);
            ObjectMapper objectMapper = new ObjectMapper();
            Root root = objectMapper.readValue(sonarResponse, Root.class);
            arrayList.add(root.getComponent().getMeasures().get(0).getValue());
        }
        return arrayList;
    }
}
