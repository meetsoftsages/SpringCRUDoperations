package com.springbootcrud.springbootcrud.service.impl;

import com.springbootcrud.springbootcrud.model.Employee;
import com.springbootcrud.springbootcrud.repository.EmployeeRepository;
import com.springbootcrud.springbootcrud.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
