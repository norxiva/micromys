package my.norxiva.micromys.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.*;
import my.norxiva.micromys.order.api.*;
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
@Aggregate(repository = "orderAggregateRepository")
public class OrderAggregate {

    @AggregateIdentifier
    private String id;
    private String no;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
    private OrderStatus status;
    private OrderEventStatus eventStatus;

    @CommandHandler
    public OrderAggregate(PrepareOrderCommand command) {
        log.info("prepare order command: {}", command);
        apply(new OrderPreparedEvent(
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

    @CommandHandler
    public void execute(ConfirmOrderCommand command) {
        log.info("execute order command: {}", command);
        apply(new OrderConfirmedEvent(
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
    public void on(OrderPreparedEvent event) {
        log.info("on order prepared: {}", event);
        this.id = event.getId();
        this.no = event.getNo();
        this.amount = event.getAmount();
        this.channelType = event.getChannelType();
        this.transactionCategory = event.getTransactionCategory();
        this.transactionType = event.getTransactionType();
        this.status = OrderStatus.PREPARED;
        this.eventStatus = OrderEventStatus.PREPARED;
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
        this.eventStatus = OrderEventStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(OrderExecutedEvent event) {
        log.info("on order executed: {}", event);
        this.status = OrderStatus.PROCESSING;
        this.eventStatus = OrderEventStatus.EXECUTED;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        log.info("on order confirmed: {}", event);
        this.eventStatus = OrderEventStatus.CONFIRMED;
    }


}
