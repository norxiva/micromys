package my.norxiva.micromys.order.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.*;
import my.norxiva.micromys.order.transaction.api.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Getter
@NoArgsConstructor
@Aggregate
public class TransactionAggregate {

    @AggregateIdentifier
    private String id;
    private String orderNo;
    private String serial;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
    private TransactionStatus status;
    private TransactionEventStatus eventStatus;

    @CommandHandler
    public TransactionAggregate(CreateTransactionCommand command) {
        log.info("create transaction command: {}", command);

        apply(new TransactionCreatedEvent(
                command.getId(),
                command.getOrderNo(),
                command.getSerial(),
                command.getAmount(),
                command.getTransactionCategory(),
                command.getTransactionType(),
                command.getChannelType(),
                null,
                null,
                null
        ));
    }

    @CommandHandler
    public void execute(ExecuteTransactionCommand command) {
        log.info("execute transaction command: {}", command);

        apply(new TransactionExecutedEvent(
                command.getId(),
                command.getOrderNo(),
                command.getSerial(),
                command.getAmount(),
                command.getTransactionCategory(),
                command.getTransactionType(),
                command.getChannelType(),
                null,
                null,
                null
        ));
    }

    @CommandHandler
    public void execute(CompleteTransactionCommand command) {
        log.info("complete transaction command: {}", command);

        apply(new TransactionCompletedEvent(
                command.getId(),
                command.getOrderNo(),
                command.getSerial(),
                command.getAmount(),
                command.getTransactionCategory(),
                command.getTransactionType(),
                command.getChannelType(),
                null,
                null,
                null
        ));
    }

    @EventSourcingHandler
    public void on(TransactionCreatedEvent event) {
        log.info("on transaction created: {}", event);
        this.id = event.getId();
        this.orderNo = event.getOrderNo();
        this.serial = event.getSerial();
        this.amount = event.getAmount();
        this.channelType = event.getChannelType();
        this.transactionCategory = event.getTransactionCategory();
        this.transactionType = event.getTransactionType();
        this.status = TransactionStatus.CREATED;
        this.eventStatus = TransactionEventStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(TransactionExecutedEvent event) {
        log.info("on transaction executed: {}", event);
        this.status = TransactionStatus.PROCESSING;
        this.eventStatus = TransactionEventStatus.EXECUTED;
    }

    @EventSourcingHandler
    public void on(TransactionCompletedEvent event) {
        log.info("on transaction completed: {}", event);
        this.status = TransactionStatus.SUCCEED;
        this.eventStatus = TransactionEventStatus.SUCCEED;
    }


}
