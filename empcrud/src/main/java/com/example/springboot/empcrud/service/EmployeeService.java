package com.example.springboot.empcrud.service;

import java.util.List;

import com.example.springboot.empcrud.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    List<Employee> findEmployee(int id);
    Employee saveEmployee(Employee employee);
    Employee deleteEmployee(Employee employee);
}

