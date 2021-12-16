package com.springbootcrud.springbootcrud.service;

import com.springbootcrud.springbootcrud.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee); //method of return type Employee, used to create a employee record
    List<Employee> getAllEmployees(); //to get all employee records
    Employee getEmployeeById(long id); //to get a employee record by id
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
}
