package com.example.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BoxingCoach implements Coach {
    @Override
    public String getDailyWorkOut(){
        return "Practice boxing for 30 mins";
    }
}
