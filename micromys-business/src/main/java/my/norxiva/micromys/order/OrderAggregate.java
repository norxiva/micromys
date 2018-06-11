package my.norxiva.micromys.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.OrderStatus;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import my.norxiva.micromys.order.api.OrderCreatedEvent;
import my.norxiva.micromys.order.api.OrderExecutedEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventsourcing.EventSourcingHandler;

import java.math.BigDecimal;

@Slf4j
@Getter
@NoArgsConstructor
@AggregateRoot
public class OrderAggregate {

    @AggregateIdentifier
    private String id;
    private String no;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
    private OrderStatus status;

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
