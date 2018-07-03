package my.norxiva.micromys.channel.endpoint;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.DefaultResponse;
import my.norxiva.micromys.channel.ChannelConfigManager;
import my.norxiva.micromys.channel.ChannelSettingManager;
import my.norxiva.micromys.channel.query.ChannelConfig;
import my.norxiva.micromys.channel.query.ChannelSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Path("channel")
public class ChannelEndpoint {

    private ChannelConfigManager channelConfigManager;
    private ChannelSettingManager channelSettingManager;

    @Autowired
    public ChannelEndpoint(ChannelConfigManager channelConfigManager, ChannelSettingManager channelSettingManager) {
        this.channelConfigManager = channelConfigManager;
        this.channelSettingManager = channelSettingManager;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid ChannelConfig channelConfig) {
        ChannelConfig response = channelConfigManager.save(channelConfig);
        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        return Response.ok(channelConfigManager.get(id)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        channelConfigManager.delete(id);
        return Response
                .ok(DefaultResponse
                        .builder()
                        .code(DefaultResponse.SUCCEED)
                        .build())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("settings")
    public Response createSetting(@Valid ChannelSetting setting) {
        ChannelSetting response = channelSettingManager.save(setting);
        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("settings/{id}")
    public Response getSetting(@PathParam("id") String id) {
        return Response.ok(channelSettingManager.get(id)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("settings/{id}")
    public Response deleteSetting(@PathParam("id") String id) {
        channelSettingManager.delete(id);
        return Response
                .ok(DefaultResponse
                        .builder()
                        .code(DefaultResponse.SUCCEED)
                        .build())
                .build();
    }


}
