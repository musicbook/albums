package com.fri.musicbook.info;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
@Path("/info")
public class InfoResource {
    @GET
    public Response returnInfo(){


        String opis_projekta = null;
        List<String> mikrostoritve = new ArrayList<String>() ;
        List<String> github = new ArrayList<String>();
        List<String> dockerhub = new ArrayList<String>();
        List<String> travis = new ArrayList<String>();
        List<String> clani = new ArrayList<String>();

        clani.add("js3445");
        clani.add("jb4634");

        opis_projekta="Projekt Musicbook je socialno omrežje, ki vsebuje glasbene skupine in njihove sledilce.";

        mikrostoritve.add("http://169.51.16.117:31896/v1/albums");
        mikrostoritve.add("http://169.51.16.117:32280/v1/songs");

        mikrostoritve.add("http://169.51.16.117:30370/v1/artists");



        github.add("https://github.com/musicbook/albums");
        github.add("https://github.com/musicbook/songs");

        github.add("https://github.com/musicbook/artist");
        github.add("https://github.com/musicbook/genre");



        travis.add("https://travis-ci.org/musicbook/albums");
        travis.add("https://travis-ci.org/musicbook/songs");



        dockerhub.add("https://hub.docker.com/r/cleptes/albums/");
        dockerhub.add("https://hub.docker.com/r/cleptes/songs/");

        dockerhub.add("https://hub.docker.com/r/blajan/artists-api/");
        dockerhub.add("https://hub.docker.com/r/blajan/genres-api/");


        Entity entity=new Entity(opis_projekta,mikrostoritve,github,dockerhub,travis,clani);

        return Response.ok(entity).build();
    }

}
