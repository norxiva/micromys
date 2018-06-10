package my.norxiva.micromys.autoconfig;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonMongoConfig {


    @Bean
    public MongoClient mongoClient(@Value("${spring.data.mongo.uri}") String uri){
        return new MongoClient(new MongoClientURI(uri));
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient){
        return new DefaultMongoTemplate(mongoClient);
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine(MongoTemplate mongoTemplate) {
        return new MongoEventStorageEngine(mongoTemplate);
    }

    @Bean
    public MongoSagaStore mongoSagaStore(MongoTemplate mongoTemplate){
        return new MongoSagaStore(mongoTemplate);
    }

}
