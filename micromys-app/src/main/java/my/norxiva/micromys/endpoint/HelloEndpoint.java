package my.norxiva.micromys.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Path("hello")
public class HelloEndpoint {

    @GET
    public Response hi() {
        return Response.ok("Hello World!").type(MediaType.TEXT_PLAIN_TYPE).build();
    }

}
