package com.springbootcrud.springbootcrud.service.impl;

import com.springbootcrud.springbootcrud.exception.ResourceNotFoundException;
import com.springbootcrud.springbootcrud.model.Employee;
import com.springbootcrud.springbootcrud.repository.EmployeeRepository;
import com.springbootcrud.springbootcrud.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

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
        Optional<Employee> employee= employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new ResourceNotFoundException("Employee","ID",id);
        }

    }

    //Update
    @Override
    public Employee updateEmployee(Employee employee, long id) {
//        Optional<Employee> existingEmployee = employeeRepository.findById(id);
//        if(existingEmployee.isPresent()){
//
//        }else{
//            throw new ResourceNotFoundException("Employee","ID",id);
//        }
        Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","ID",id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    //Delete
    @Override
    public void deleteEmployee(long id) {
        Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","ID",id));

        employeeRepository.deleteById(id);
    }

}
