package com.example.springboot.empcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.empcrud.dao.EmployeeRepository;
import com.example.springboot.empcrud.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployee(int id){
        Optional<Employee> emp = employeeRepository.findById(id);
        Employee employee = null;
        if(emp.isPresent()) employee = emp.get();
        return employee;
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id){
        employeeRepository.deleteById(id);
    }



}
