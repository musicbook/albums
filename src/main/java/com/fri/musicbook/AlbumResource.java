package com.fri.musicbook;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.*;

import com.fri.musicbook.*;
import com.fri.musicbook.AlbumsBean;
import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;
import org.eclipse.microprofile.metrics.annotation.Metered;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Path("/albums")
@Metered(displayName="albums")
@Log
public class AlbumResource {


    @Context
    protected UriInfo uriInfo;

    @Inject
    private AlbumsBean albumsBean;

    @GET
    public Response getAllAlbums(){
        List<Album> albums=albumsBean.getAlbums();
        return Response.ok(albums).build();
    }

    @POST
    public Response addNewAlbum(Album album) {
        Album message=albumsBean.createAlbum(album);
        if(message!=null)  return Response.status(Response.Status.CREATED).entity(album).build();
        return Response.status(Response.Status.CONFLICT).entity(album).build();
    }

    @GET
    @Path("/query")
    public Response getAlbum(@QueryParam("id") Integer id){
        Album album=albumsBean.getAlbumById(id);
        if(album == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(album).build();
    }

    @GET
    @Path("/artist/{artist}")
    public Response getAlbumArtist(@PathParam("artist") Integer artistId){
        List<Album> album=albumsBean.getAlbumsByArtist(artistId);
        if(album == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(album).build();
    }

}
