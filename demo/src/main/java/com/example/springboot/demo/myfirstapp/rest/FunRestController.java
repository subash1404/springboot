package com.example.springboot.demo.myfirstapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController{

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teaminfo")
    public String teaminfo(){
        return "TeamName :"+teamName;
    }
    @GetMapping("/")
    public String sayHello(){
        return "Hello Maven!";
    }
}
