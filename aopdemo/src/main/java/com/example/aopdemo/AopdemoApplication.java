package com.example.aopdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aopdemo.dao.AccountDao;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunne(AccountDao accountDao){
		return runner -> {
			demoBeforeAdvice(accountDao);
		};
	}

	private void demoBeforeAdvice(AccountDao accountDao){
		accountDao.addAccount();
	}
}
