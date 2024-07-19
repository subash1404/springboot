package com.example.springcoredemo.common;

public class SwimCoach implements Coach {
    public SwimCoach(){
        System.out.println("Inside Constructor :"+getClass().getSimpleName());
    }
    
    @Override
    public String getDailyWorkOut(){
        return "Swim 1000 metres as a warmup";
    }
}
