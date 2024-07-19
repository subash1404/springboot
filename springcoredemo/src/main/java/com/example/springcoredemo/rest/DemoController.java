package com.example.springcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springcoredemo.common.Coach;

@RestController
public class DemoController {
    private Coach myCoach;
    
    // Constructor injection
    // @Autowired
    // public DemoController(Coach theCoach) {
    //     myCoach = theCoach;
    // }

    // Use of Qualifiers
    // @Autowired
    // public DemoController(@Qualifier("tennisCoach") Coach theCoach) {
    //     myCoach = theCoach;
    // }

    //Setter injection
    @Autowired
    public void setCoach(@Qualifier("swimCoach") Coach theCoach){
        myCoach = theCoach;
    }
    
   
    @GetMapping("/")
    public String defaultRoute(){
        return "Default Route";
    }

    @GetMapping("/getDailyWorkOut")
    public String getDailyWorkOut(){
        return myCoach.getDailyWorkOut();
    }

    
}
