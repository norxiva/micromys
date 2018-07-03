package my.norxiva.micromys;

import my.norxiva.micromys.channel.endpoint.ChannelEndpoint;
import my.norxiva.micromys.merchant.endpoint.MerchantEndpoint;
import my.norxiva.micromys.order.endpoint.OrderEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(OrderEndpoint.class);
        register(MerchantEndpoint.class);
        register(ChannelEndpoint.class);
    }
}
