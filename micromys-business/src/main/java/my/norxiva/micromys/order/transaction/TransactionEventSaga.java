package my.norxiva.micromys.order.transaction;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.transaction.api.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.annotation.Resource;

@Slf4j
@Saga
public class TransactionEventSaga {

    @Resource
    private CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "serial")
    public void handle(TransactionCreatedEvent event) {
        log.info("handle transaction created: {}", event);
        ExecuteTransactionCommand command = new ExecuteTransactionCommand();
        command.setId(event.getId());
        command.setSerial(event.getSerial());
        commandGateway.send(command);
    }

    @SagaEventHandler(associationProperty = "serial")
    public void handle(TransactionExecutedEvent event) {
        log.info("handle transaction executed: {}", event);
        CompleteTransactionCommand command = new CompleteTransactionCommand();
        command.setId(event.getId());
        command.setSerial(event.getSerial());
        commandGateway.send(command);

    }

    @EndSaga
    @SagaEventHandler(associationProperty = "serial")
    public void handle(TransactionCompletedEvent event) {
        log.info("handle transaction completed: {}", event);
    }


}
