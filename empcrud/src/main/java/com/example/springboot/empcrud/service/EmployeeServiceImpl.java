package com.example.springboot.empcrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot.empcrud.dao.EmployeeDao;
import com.example.springboot.empcrud.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll(){
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findEmployee(int id){
        return employeeDao.findEmployee(id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee){
        return employeeDao.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Employee employee){
        return employeeDao.deleteEmployee(employee);
    }



}
