package my.norxiva.micromys.order;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.api.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventHandler {

    @EventHandler
    public void handle(OrderPreparedEvent event) {
        log.info("handle order prepared: {}", event);
    }

    @EventHandler
    public void handle(OrderCreatedEvent event) {
        log.info("handle order created: {}", event);
    }

    @EventHandler
    public void handle(OrderExecutedEvent event) {
        log.info("handle order executed: {}", event);
    }

    @EventHandler
    public void handle(OrderConfirmedEvent event) {
        log.info("handle order confirmed: {}", event);
    }

}
