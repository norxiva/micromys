package my.norxiva.micromys.order;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public AggregateFactory<OrderAggregate> orderAggregateFactory() {
        SpringPrototypeAggregateFactory<OrderAggregate> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName(StringUtils.uncapitalize(OrderAggregate.class.getSimpleName()));
        return aggregateFactory;
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        // threshold' value should not be too lower
        return new EventCountSnapshotTriggerDefinition(snapshotter, 3);
    }

    @Bean
    public Repository<OrderAggregate> orderAggregateRepository(Snapshotter snapshotter,
                                                               EventStore eventStore,
                                                               AggregateFactory<OrderAggregate> aggregateFactory) {
        SnapshotTriggerDefinition definition = new EventCountSnapshotTriggerDefinition(snapshotter, 3);
        return new EventSourcingRepository<>(aggregateFactory, eventStore, definition);

    }

}
