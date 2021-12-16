package com.springbootcrud.springbootcrud.repository;

import com.springbootcrud.springbootcrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
