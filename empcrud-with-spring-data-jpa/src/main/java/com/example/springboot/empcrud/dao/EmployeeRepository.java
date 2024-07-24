package com.example.springboot.empcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.empcrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    
}
