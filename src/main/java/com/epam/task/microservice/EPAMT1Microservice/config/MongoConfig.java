package com.epam.task.microservice.EPAMT1Microservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.epam.task.microservice.EPAMT1Microservice.repo")
public class MongoConfig {



    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://dbUser:dbUser@testclaster.ve3w3.mongodb.net/GymDB?retryWrites=true&w=majority&appName=TestClaster");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "GymDB");
    }


}
