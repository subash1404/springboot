package com.example.springboot.empcrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springboot.empcrud.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> empList = query.getResultList();
        return empList;
    }

    @Override
    public List<Employee> findEmployee(int id) {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee where id = :id", Employee.class);
        query.setParameter("id", id);
        List<Employee> empList = query.getResultList();
        return empList;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        Employee dbEmp = entityManager.merge(employee);
        return dbEmp;
    }
    
    @Override
    public Employee deleteEmployee(Employee employee){
        Employee emp = entityManager.find(Employee.class,employee.getId());
        entityManager.remove(emp);
        return emp;
    }
}
