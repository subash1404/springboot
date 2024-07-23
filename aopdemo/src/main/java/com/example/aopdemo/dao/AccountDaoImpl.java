package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
    
    @Override
    public void addAccount(){
        System.out.println("Adding account");
    }
}
