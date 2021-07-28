package com.example.rsapidemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.rsapidemo.model.Employee;
import com.example.rsapidemo.repository.EmployeeRepository;

@RestController
public class EmployeeController
{
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }

    @GetMapping("/")
    public String homepage() {
        return "<a href='/init'>Load data</a><br/>"
                + "<a href='/employees'>Data</a><br/>";
    }

    @GetMapping("/init")
    public void init()
    {
        for (int i = 1; i <= 10; i++) {
            Employee e = new Employee(""+i+"_"+System.currentTimeMillis(),
                "Alex_" + System.currentTimeMillis() % 100,
                "Vasseur_" + i,
                "alex.vasseur@foo.com"
            );
            employeeRepository.save(e);
        }
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees()
    {
        Iterable<Employee> result = employeeRepository.findAll();
        List<Employee> employeesList = new ArrayList<Employee>();
        result.forEach(employeesList::add);
        return employeesList;
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable String id)
    {
        Optional<Employee> emp = employeeRepository.findById(id);
        return emp;
    }

    @PutMapping("/employee/{id}")
    public Optional<Employee> updateEmployee(@RequestBody Employee newEmployee, @PathVariable String id)
    {
        Optional<Employee> optionalEmp = employeeRepository.findById(id);
        if (optionalEmp.isPresent()) {
            Employee emp = optionalEmp.get();
            emp.setFirstName(newEmployee.getFirstName());
            emp.setLastName(newEmployee.getLastName());
            emp.setEmail(newEmployee.getEmail());
            employeeRepository.save(emp);
        }
        return optionalEmp;
    }

    @DeleteMapping(value = "/employee/{id}", produces = "application/json; charset=utf-8")
    public String deleteEmployee(@PathVariable String id) {
        Boolean result = employeeRepository.existsById(id);
        employeeRepository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee newEmployee)
    {
        String id = String.valueOf(new Random().nextInt());
        Employee emp = new Employee(id, newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getEmail());
        employeeRepository.save(emp);
        return emp;
    }
}