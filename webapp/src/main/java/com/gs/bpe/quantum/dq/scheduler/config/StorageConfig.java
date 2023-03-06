package com.gs.bpe.quantum.dq.scheduler.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class StorageConfig {

//    @Bean
    public InMemoryStorageProvider initStorageProvider() {
        return new InMemoryStorageProvider();
    }

    @Bean(destroyMethod = "close")
    public MongoDBStorageProvider initMongoDBStorageProvider() {
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
//                .applyToConnectionPoolSettings(builder -> builder.maxWaitTime(10, SECONDS).maxSize(3))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        MongoClient mongoClient = MongoClients.create(mongoClientSettings, null);
        return new MongoDBStorageProvider(mongoClient);
    }
}
