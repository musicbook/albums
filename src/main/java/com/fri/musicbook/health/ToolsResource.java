package com.fri.musicbook.health;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Path("/tools")
@Metered
@Log(LogParams.METRICS)
public class ToolsResource{

    private static final Logger LOG = LogManager.getLogger(ToolsResource.class.getName());

    @GET
    @Path("/health_up")
    public Response setHeath(){
        Tools.health=true;
        return Response.ok().build();
    }

    @GET
    @Path("/health_down")
    public Response clearHeath(){
        Tools.health=false;
        return Response.ok().build();
    }

    @GET
    @Path("/start_load/{no}")
    @Timed(name="fib")
    public Response startLoad(@PathParam("no") Integer no){
        Tools tool=new Tools();
        LOG.info("Starting fibload with "+no);
        return Response.ok(tool.fibNo(no)).build();

    }

}

