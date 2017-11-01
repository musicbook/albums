package com.fri.musicbook;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("albums")
public class AlbumREST {

    @GET
    public Response getAllAlbums(){
        List<Album> albums=AlbumDB.getAlbums();
        return Response.ok(albums).build();
    }

    @POST
    public Response addNewAlbum(Album album) {
        AlbumDB.addAlbum(album);
        return Response.noContent().build();
    }

    @GET
    @Path("/query")
    public Response getAlbum(@QueryParam("id") String id){
        Album album=AlbumDB.getByAlbumId(id);
        if(album == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(album).build();
    }

    @GET
    @Path("/artist/{artist}")
    public Response getAlbumArtist(@PathParam("artist") String artist){
        List<Album> album=AlbumDB.getByAlbumArtist(artist);
        if(album == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(album).build();
    }
}
