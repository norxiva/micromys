package my.norxiva.micromys.order.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.order.OrderEventStoreHandler;
import my.norxiva.micromys.order.api.*;
import my.norxiva.micromys.order.transaction.api.CreateTransactionCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Component
@Path("order")
public class OrderEndpoint {

    private CommandGateway commandGateway;
    private OrderEventStoreHandler orderEventStoreHandler;

    @Autowired
    public OrderEndpoint(CommandGateway commandGateway,
                         OrderEventStoreHandler orderEventStoreHandler) {
        this.commandGateway = commandGateway;
        this.orderEventStoreHandler = orderEventStoreHandler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@QueryParam("merchantNo") @NotBlank String merchantNo,
                           @QueryParam("message") @NotBlank String message,
                           @QueryParam("key") @NotBlank String key,
                           @QueryParam("signature") @NotBlank String signature) {
//        CreateOrderCommand command = new CreateOrderCommand();
//        log.info("create order:{}", command);
//        command.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
//
//        log.info("send create command.");
//        CompletableFuture<String> future = commandGateway.send(command);
//        log.info("create command sent.");
//        try {
//            String result = future.get();
//            log.info("create command received.");
//
//            log.info("result: {}", result);
//            ExecuteOrderCommand executeOrderCommand = new ExecuteOrderCommand();
//            executeOrderCommand.setId(command.getId());
//
//            commandGateway.sendAndWait(executeOrderCommand);
//            log.info("execute command sent.");
//
////            String executeResult = commandGateway.sendAndWait(executeOrderCommand, 5, TimeUnit.SECONDS);
////            log.info("execute result: {}", executeResult);
//        } catch (InterruptedException | ExecutionException ex) {
//            log.error(ex.getMessage(), ex);
//            throw new RuntimeException(ex);
//        }

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
        Map<String, Object> result = orderEventStoreHandler.get(id);
        return Response.ok(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("transaction")
    public Response createTransaction(CreateTransactionCommand command) {
        log.info("create transaction:{}", command);
        command.setId(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));

        commandGateway.send(command);
        return Response.ok("SUCCESS").build();
    }
}
