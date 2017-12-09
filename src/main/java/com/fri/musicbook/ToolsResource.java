package com.fri.musicbook;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Path("/tools")
public class ToolsResource{

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


}

