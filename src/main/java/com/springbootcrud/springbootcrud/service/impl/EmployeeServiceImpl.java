package com.springbootcrud.springbootcrud.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcrud.springbootcrud.exception.ResourceNotFoundException;
import com.springbootcrud.springbootcrud.model.Component;
import com.springbootcrud.springbootcrud.model.Employee;
import com.springbootcrud.springbootcrud.model.Measure;
import com.springbootcrud.springbootcrud.model.Root;
import com.springbootcrud.springbootcrud.repository.EmployeeRepository;
import com.springbootcrud.springbootcrud.service.EmployeeService;
import org.sonar.wsclient.SonarClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Create
    @Override
    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    // Get all Employee records
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();

    }

    //Get a single Employee record by ID
    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "ID", id);
        }

    }

    //Update
    @Override
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
    @Override
    public void deleteEmployee(long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "ID", id));

        employeeRepository.deleteById(id);
    }

    @Override
    public String getBugs() throws JsonProcessingException {
        String sonarEndpoint = "api/issues/search";
        Map<String, Object> sonarEndParams = new HashMap<>();
        sonarEndParams.put("componentKeys", "com.springbootcrud:springbootCRUD");
        sonarEndParams.put("types", "BUG");
        String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);
//        return sonarResponse;
        //Extracting the required data from JSON String
        JsonNode parent= new ObjectMapper().readTree(sonarResponse);
        String total = parent.path("total").asText();
        return total;

    }

    @Override
    public String getVulnerability() throws JsonProcessingException {
        String sonarEndpoint = "api/issues/search";
        Map<String, Object> sonarEndParams = new HashMap<>();
        sonarEndParams.put("componentKeys", "com.springbootcrud:springbootCRUD");
        sonarEndParams.put("types", "VULNERABILITY");
        String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);

        //Extracting the required data from JSON String
        JsonNode parent= new ObjectMapper().readTree(sonarResponse);
        String total = parent.path("total").asText();
        return total;
    }

    @Override
    public String getCodeSmells() throws JsonProcessingException {
        String sonarEndpoint = "api/issues/search";
        Map<String, Object> sonarEndParams = new HashMap<>();
        sonarEndParams.put("componentKeys", "com.springbootcrud:springbootCRUD");
        sonarEndParams.put("types", "CODE_SMELL");
        String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);

        //Extracting the required data from JSON String
        JsonNode parent= new ObjectMapper().readTree(sonarResponse);
        String total = parent.path("total").asText();
        return total;
    }

    @Override
    public String getDuplications() throws JsonProcessingException {

        String sonarEndpoint = "api/measures/component";
        Map<String, Object> sonarEndParams = new HashMap<>();
        sonarEndParams.put("metricKeys", "duplicated_lines_density");
        sonarEndParams.put("component", "com.springbootcrud:springbootCRUD");
        String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);

        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(sonarResponse, Root.class);
        return root.getComponent().getMeasures().get(0).getValue();
    }

    @Override
    public String getCoverage() throws JsonProcessingException {
        String sonarEndpoint = "api/measures/component";
        Map<String, Object> sonarEndParams = new HashMap<>();
        sonarEndParams.put("metricKeys", "coverage");
        sonarEndParams.put("component", "com.springbootcrud:springbootCRUD");
        String sonarResponse = sonarClient.get(sonarEndpoint, sonarEndParams);

        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(sonarResponse, Root.class);
        return root.getComponent().getMeasures().get(0).getValue();
    }
}
