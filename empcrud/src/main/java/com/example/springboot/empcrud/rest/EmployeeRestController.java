package com.example.springboot.empcrud.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.empcrud.entity.Employee;
import com.example.springboot.empcrud.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public List<Employee> findEmployee(@PathVariable int employeeId){
        return employeeService.findEmployee(employeeId);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        employee.setId(0);

        Employee dbEmp = employeeService.saveEmployee(employee);
        return dbEmp;
    }

    @PutMapping("/employees")
    public Employee updatEmployee(@RequestBody Employee employee){
        Employee dbEmp = employeeService.saveEmployee(employee);
        return dbEmp;
    }

    @DeleteMapping("/employees")
    public Employee deletEmployee(@RequestBody Employee employee){
        return employeeService.deleteEmployee(employee);
    }

}
