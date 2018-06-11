package my.norxiva.micromys.order;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.api.CreateOrderCommand;
import my.norxiva.micromys.order.api.ExecuteOrderCommand;
import my.norxiva.micromys.order.api.OrderCreatedEvent;
import my.norxiva.micromys.order.api.OrderExecutedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Component
public class OrderCommandHandler {

    @CommandHandler
    public void handle(CreateOrderCommand command) {
        log.info("create order command: {}", command);
        apply(OrderCreatedEvent
                .builder()
                .id(command.getId())
                .no(command.getNo())
                .amount(command.getAmount())
                .transactionCategory(command.getTransactionCategory())
                .transactionType(command.getTransactionType())
                .channelType(command.getChannelType())
                .build());
    }


    @CommandHandler
    public void handle(ExecuteOrderCommand command) {
        log.info("execute order command: {}", command);
        apply(OrderExecutedEvent
                .builder()
                .id(command.getId())
                .no(command.getNo())
                .amount(command.getAmount())
                .transactionCategory(command.getTransactionCategory())
                .transactionType(command.getTransactionType())
                .channelType(command.getChannelType())
                .build());
    }

}
