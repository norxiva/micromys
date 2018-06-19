package my.norxiva.micromys.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.OrderStatus;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import my.norxiva.micromys.order.api.CreateOrderCommand;
import my.norxiva.micromys.order.api.ExecuteOrderCommand;
import my.norxiva.micromys.order.api.OrderCreatedEvent;
import my.norxiva.micromys.order.api.OrderExecutedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


// use Aggregate instead of AggregateRoot
@Slf4j
@Getter
@NoArgsConstructor
@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition")
public class OrderAggregate {

    @AggregateIdentifier
    private String id;
    private String no;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
    private OrderStatus status;

    // command handler must define in aggregate class
    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        log.info("create order command: {}", command);
        apply(new OrderCreatedEvent(
                command.getId(),
                command.getNo(),
                command.getAmount(),
                command.getTransactionCategory(),
                command.getTransactionType(),
                command.getChannelType(),
                null,
                null,
                null)
        );
    }


    @CommandHandler
    public void execute(ExecuteOrderCommand command) {
        log.info("execute order command: {}", command);
        apply(new OrderExecutedEvent(
                command.getId(),
                command.getNo(),
                command.getAmount(),
                command.getTransactionCategory(),
                command.getTransactionType(),
                command.getChannelType(),
                null,
                null,
                null));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        log.info("on order created: {}", event);
        this.id = event.getId();
        this.no = event.getNo();
        this.amount = event.getAmount();
        this.channelType = event.getChannelType();
        this.transactionCategory = event.getTransactionCategory();
        this.transactionType = event.getTransactionType();
        this.status = OrderStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(OrderExecutedEvent event) {
        log.info("on order executed: {}", event);
        this.status = OrderStatus.PROCESSING;
    }

}
