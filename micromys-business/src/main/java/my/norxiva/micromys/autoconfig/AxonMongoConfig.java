package my.norxiva.micromys.autoconfig;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonMongoConfig {

    @Bean
    public MongoClient mongoClient(@Value("${spring.data.mongodb.uri}") String uri) {
        return new MongoClient(new MongoClientURI(uri));
    }

    @Qualifier("axonMongoTemplate")
    @Bean
    // mongoTemplate method will conflict with MongoDataAutoConfiguration.mongoTemplate method,
    // rename to 'axonMongoTemplate
    public MongoTemplate axonMongoTemplate(MongoClient mongoClient) {
        return new DefaultMongoTemplate(mongoClient);
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine(Serializer serializer,
                                                      @Qualifier("axonMongoTemplate") MongoTemplate mongoTemplate,
                                                      AxonConfiguration configuration) {
        return new MongoEventStorageEngine(serializer, configuration.getComponent(EventUpcaster.class),
                mongoTemplate, new DocumentPerEventStorageStrategy());
    }

    @Bean
    public MongoSagaStore mongoSagaStore(@Qualifier("axonMongoTemplate") MongoTemplate mongoTemplate,
                                         Serializer serializer) {
        return new MongoSagaStore(mongoTemplate, serializer);
    }

}
