package my.norxiva.micromys.order;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.api.OrderConfirmedEvent;
import my.norxiva.micromys.order.api.OrderCreatedEvent;
import my.norxiva.micromys.order.api.OrderExecutedEvent;
import my.norxiva.micromys.order.api.OrderPreparedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener {

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
