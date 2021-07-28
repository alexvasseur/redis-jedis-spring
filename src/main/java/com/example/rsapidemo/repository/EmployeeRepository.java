package com.example.rsapidemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.rsapidemo.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}