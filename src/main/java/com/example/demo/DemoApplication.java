package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

//Main class
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);	// uses this to run the demo application
	}

//	@Bean
//	CommandLineRunner runner(SignUpInformationRepository repository, MongoTemplate mongoTemplate) {
//		return args -> {
//
//			SignUpInformation signUpInformation = new SignUpInformation(
//					"Kartik",
//					"Jain",
//					"knjain100@gmail.com",
//					"9810779949",
//					new Date(),
//					"password",
//					true
//			);
//
//			repository.insert(signUpInformation);	// insert into repository
//		};
//	}

}
