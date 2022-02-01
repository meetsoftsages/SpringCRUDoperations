package com.springbootcrud.springbootcrud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootcrud.springbootcrud.model.Component;
import com.springbootcrud.springbootcrud.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee); //method of return type Employee, used to create an employee record
    List<Employee> getAllEmployees(); //to get all employee records
    Employee getEmployeeById(long id); //to get an employee record by id
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
    String getBugs() throws JsonProcessingException;
    String getVulnerability() throws JsonProcessingException;
    String getCodeSmells() throws JsonProcessingException;
    String getDuplications() throws JsonProcessingException;
    String getCoverage() throws JsonProcessingException;
}
