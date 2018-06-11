package my.norxiva.micromys;

import my.norxiva.micromys.endpoint.HelloEndpoint;
import my.norxiva.micromys.endpoint.OrderEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(HelloEndpoint.class);
        register(OrderEndpoint.class);
    }
}
