package my.norxiva.micromys.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.api.CreateOrderCommand;
import my.norxiva.micromys.order.api.ExecuteOrderCommand;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
@Path("order")
public class OrderEndpoint {

    private CommandBus commandBus;

    @Autowired
    public OrderEndpoint(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(CreateOrderCommand command) {
        log.info("create order:{}", command);
        command.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        commandBus.dispatch(new GenericCommandMessage<>(command),
                new CommandCallback<CreateOrderCommand, Map<String, String>>() {
                    @Override
                    public void onSuccess(CommandMessage<? extends CreateOrderCommand> commandMessage,
                                          Map<String, String> result) {
                        log.info("on success: {}, {}", commandMessage, result);
                        ExecuteOrderCommand orderCommand = new ExecuteOrderCommand();
                        orderCommand.setId(command.getId());
                        commandBus.dispatch(new GenericCommandMessage<>(orderCommand));
                    }

                    @Override
                    public void onFailure(CommandMessage<? extends CreateOrderCommand> commandMessage,
                                          Throwable cause) {
                        log.info("on failure: {}, {}", commandMessage, cause);
                    }
                });

        return Response.ok("SUCCESS").build();

    }
}
