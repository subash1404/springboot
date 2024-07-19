package com.example.springboot.empcrud.dao;

import java.util.List;

import com.example.springboot.empcrud.entity.Employee;

public interface EmployeeDao {
    List<Employee> findAll();
    List<Employee> findEmployee(int id);
    Employee saveEmployee(Employee employee);
    Employee deleteEmployee(Employee employee);
}
