package com.example.springboot.empcrud.service;

import java.util.List;

import com.example.springboot.empcrud.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findEmployee(int id);
    Employee saveEmployee(Employee employee);
    void deleteById(int id);
}

