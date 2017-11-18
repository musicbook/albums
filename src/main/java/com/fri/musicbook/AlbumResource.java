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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Path("/albums")
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

    // http req

    private List<Album> getAlbums(List<Integer> albumIds){
        Optional<String> albumURL= Optional.of("http://192.168.99.100:1000");
        Client httpClient = ClientBuilder.newClient();
        List<Album> albums=new ArrayList<Album>();

        if (albumURL.isPresent()) {
            for (Integer albumId : albumIds) {
                try {
                    albums.add(httpClient
                            .target(albumURL.get() + "/v1/albums/query?id=" + albumId)
                            .request().get(new GenericType<Album>() {
                            })
                    );
                } catch (WebApplicationException | ProcessingException e) {
                    System.out.println(e);
                    throw new InternalServerErrorException(e);
                }
            }
        } else {
            return null;
        }
        return albums;

    }

}
