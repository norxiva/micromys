package my.norxiva.micromys.order.autoconfig;

import my.norxiva.micromys.order.OrderAggregate;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAutoConfiguration {

    @Bean
    public AggregateFactory<OrderAggregate> orderAggregateFactory() {
        SpringPrototypeAggregateFactory<OrderAggregate> factory = new SpringPrototypeAggregateFactory<>();
        factory.setPrototypeBeanName(StringUtils.uncapitalize(OrderAggregate.class.getSimpleName()));
        return factory;
    }

    @Bean
    public Repository<OrderAggregate> orderAggregateRepository(Snapshotter snapshotter,
                                                               EventStore eventStore,
                                                               AggregateFactory<OrderAggregate> aggregateFactory) {
        SnapshotTriggerDefinition definition = new EventCountSnapshotTriggerDefinition(snapshotter, 1);
        return new EventSourcingRepository<>(aggregateFactory, eventStore, definition);
    }

}
