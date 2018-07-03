package my.norxiva.micromys.order;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

@Slf4j
@Component
public class OrderEventStoreHandler {

    private MongoTemplate mongoTemplate;
    private Repository<OrderAggregate> repository;

    @Autowired
    public OrderEventStoreHandler(Repository<OrderAggregate> repository,
                                  MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public Map<String, Object> get(String id) {
        try {
            return mongoTemplate.snapshotCollection().find(eq("aggregateIdentifier", id)).first();
        } catch (AggregateNotFoundException ex) {
            log.warn("aggregate not found, repository '{}' id '{}'", OrderAggregate.class.getSimpleName(), id);
            return null;
        }

    }
}
