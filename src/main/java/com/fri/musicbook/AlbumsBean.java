package com.fri.musicbook;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;

import javax.enterprise.context.RequestScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fri.musicbook.Album;
import com.kumuluz.ee.discovery.annotations.DiscoverService;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;


@ApplicationScoped
public class AlbumsBean {
    @PersistenceContext(unitName = "albums-jpa")
    private EntityManager em;

    private ObjectMapper objectMapper;

    private Client httpClient;

    @Inject
    @DiscoverService("songs-service")
    private String basePath;

    //private Optional<String> basePath;

    @Inject
    private configProperties configProperties;


    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();

    }

    public List<Song> getSongsByAlbum(Integer albumId){
            //if(configProperties.getIsSongsRunning()) {
                try {
                    List<Song> songs=httpClient
                            .target(basePath + "/v1/songs?filter=albumId:EQ:" + albumId.toString())
                            .request().get(new GenericType<List<Song>>() {
                            });
                    if (songs.isEmpty()) return null;
                    else return songs;
                } catch (WebApplicationException | ProcessingException e) {
                    throw new InternalServerErrorException(e);
                }
            }
          //  return null;
        //}



    public List<Album> getAlbums(){
        Query query=em.createNamedQuery("albums.getAll",Album.class);
        return query.getResultList();
    }

    public List<Album> getAlbumsByArtist(Integer artistId){
        Query query=em.createNamedQuery("albums.getAlbumsByArtist",Album.class).setParameter("artistId",artistId);
        return query.getResultList();
    }

    public Album getAlbumById(Integer albumId){

        Album album=em.find(Album.class,albumId);
        album.setSongs(getSongsByAlbum(albumId));
        return album;
    }

    public Album createAlbum(Album album){
        try {
            beginTx();
            em.persist(album);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            return null;
        }

        return album;
    }

    public Album putAlbum(Integer albumId, Album album) {
        // Updates album
        Album a=em.find(Album.class,albumId);
        if(a== null) return null;

        try {
            beginTx();
            album.setAlbumId(a.getAlbumId());
            album = em.merge(album);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            return null;
        }

        return album;
    }

    public boolean deleteAlbum(Integer albumId){
        Album album=em.find(Album.class,albumId);
        if(album!=null) {
            try {
                beginTx();
                em.remove(album);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
                return false;
            }
        }else return false;

        return true;
    }


    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }


}
