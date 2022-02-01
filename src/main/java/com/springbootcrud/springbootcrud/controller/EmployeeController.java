package com.springbootcrud.springbootcrud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootcrud.springbootcrud.model.Component;
import com.springbootcrud.springbootcrud.model.Employee;
import com.springbootcrud.springbootcrud.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // Create employee REST Api
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // Get all employee records REST Api
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // Gets a single employee record by ID REST Api
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    //Update a record REST Api
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id")long employeeid){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,employeeid), HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted",HttpStatus.OK);
    }

    @GetMapping("/bug")
    public String getBugs() throws JsonProcessingException {
        return employeeService.getBugs();
    }

    @GetMapping("/vulnerability")
    public String getVulnerability() throws JsonProcessingException {
        return employeeService.getVulnerability();
    }

    @GetMapping("/codesmell")
    public String getCodeSmell() throws JsonProcessingException {
        return employeeService.getCodeSmells();
    }

    @GetMapping("/duplication")
    public String getDuplications() throws JsonProcessingException{
        return employeeService.getDuplications();
    }

    @GetMapping("/coverage")
    public String getCoverage() throws JsonProcessingException{
        return employeeService.getCoverage();
    }

}
