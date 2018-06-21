package my.norxiva.micromys.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.OrderRepositoryHandler;
import my.norxiva.micromys.order.api.ConfirmOrderCommand;
import my.norxiva.micromys.order.api.CreateOrderCommand;
import my.norxiva.micromys.order.api.ExecuteOrderCommand;
import my.norxiva.micromys.order.api.PrepareOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@Path("order")
public class OrderEndpoint {

    private CommandGateway commandGateway;
    private OrderRepositoryHandler orderRepositoryHandler;

    @Autowired
    public OrderEndpoint(CommandGateway commandGateway,
                         OrderRepositoryHandler orderRepositoryHandler) {
        this.commandGateway = commandGateway;
        this.orderRepositoryHandler = orderRepositoryHandler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(CreateOrderCommand command) {
        log.info("create order:{}", command);
        command.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));

        CompletableFuture<String> future = commandGateway.send(command);
        try {
            String result = future.get();
            log.info("result: {}", result);
            ExecuteOrderCommand executeOrderCommand = new ExecuteOrderCommand();
            executeOrderCommand.setId(command.getId());

            commandGateway.sendAndWait(executeOrderCommand);
//            String executeResult = commandGateway.sendAndWait(executeOrderCommand, 5, TimeUnit.SECONDS);
//            log.info("execute result: {}", executeResult);
        } catch (InterruptedException | ExecutionException ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }

        return Response.ok("SUCCESS").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("prepare")
    public Response prepare(PrepareOrderCommand command) {
        return Response.ok("SUCCESS").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("confirm")
    public Response confirm(ConfirmOrderCommand command) {
        commandGateway.sendAndWait(command);
        return Response.ok("SUCCESS").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        Map<String, Object> result = orderRepositoryHandler.get(id);
        return Response.ok(result).build();
    }
}
