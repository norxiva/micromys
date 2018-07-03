package my.norxiva.micromys.order.transaction.autoconfig;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.transaction.TransactionAggregate;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TransactionAutoConfiguration {

    @Bean
    public AggregateFactory<TransactionAggregate> transactionAggregateFactory() {
        SpringPrototypeAggregateFactory<TransactionAggregate> factory = new SpringPrototypeAggregateFactory<>();
        factory.setPrototypeBeanName(StringUtils.uncapitalize(TransactionAggregate.class.getSimpleName()));
        return factory;
    }

    @Bean
    public Repository<TransactionAggregate> transactionAggregateRepository(Snapshotter snapshotter,
                                                                           EventStore eventStore,
                                                                           AggregateFactory<TransactionAggregate> aggregateFactory) {
        SnapshotTriggerDefinition definition = new EventCountSnapshotTriggerDefinition(snapshotter, 1);
        return new EventSourcingRepository<>(aggregateFactory, eventStore, definition);
    }

}
