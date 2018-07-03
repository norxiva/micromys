package my.norxiva.micromys.merchant.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.merchant.MerchantManager;
import my.norxiva.micromys.merchant.query.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Slf4j
@Component
@Path("merchant")
public class MerchantEndpoint {

    private MerchantManager merchantManager;

    @Autowired
    public MerchantEndpoint(MerchantManager merchantManager) {
        this.merchantManager = merchantManager;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Merchant merchant) {
        Merchant response = merchantManager.save(merchant);
        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{no}")
    public Response get(@PathParam("no") String no) {
        return Response.ok(merchantManager.get(no)).build();
    }
}
