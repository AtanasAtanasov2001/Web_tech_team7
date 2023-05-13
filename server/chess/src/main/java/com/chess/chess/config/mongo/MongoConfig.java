//package com.chess.chess.config.mongo;
//
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@Configuration
//@EnableMongoRepositories(basePackages = "com.chess")
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Value("${mongodb.name}")
//    private String databaseName;
//
//    @Value("${mongodb.url}")
//    private String ulr;
//
//    @Override
//    protected String getDatabaseName() {
//        return databaseName;
//    }
//
//    @Override
//    @Bean
//    public MongoClient mongoClient() {
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(ulr))
//                .build();
//        return MongoClients.create(settings);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), getDatabaseName());
//    }
//}
