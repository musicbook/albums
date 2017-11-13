package com.fri.musicbook;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import javax.enterprise.context.RequestScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import com.fri.musicbook.Album;


@ApplicationScoped
public class AlbumsBean {
    @PersistenceContext(unitName = "albums-jpa")
    private EntityManager em;

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
