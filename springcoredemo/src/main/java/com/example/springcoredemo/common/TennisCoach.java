package com.example.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
// @Primary
public class TennisCoach implements Coach {
    @Override
    public String getDailyWorkOut(){
        return "Practice tennis for 20 mins";
    }
}
