package my.norxiva.micromys.order;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.api.OrderCreatedEvent;
import my.norxiva.micromys.order.api.OrderExecutedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventListener {

    @EventHandler
    public void handle(OrderCreatedEvent event) {
        log.info("handle order created: {}", event);
    }


    @EventHandler
    public void handle(OrderExecutedEvent event) {
        log.info("handle order executed: {}", event);
    }


}
