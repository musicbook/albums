package com.fri.musicbook;


import java.util.ArrayList;
import java.util.List;

public class AlbumDB {
    private static List<Album> albums = new ArrayList<>();

    public static List<Album> getAlbums() {
        return albums;
    }

    public static Album getByAlbumId(String albumId) {
        for (Album album : albums) {
            if (album.getId().equals(albumId))
                return album;
        }

        return null;
    }

    public static List<Album> getByAlbumArtist(String albumArtist) {
        List<Album> albums_by_artist= new ArrayList<>();
        for (Album album : albums) {
            if (album.getName().equals(albumArtist))
                albums_by_artist.add(album);
        }

        if(albums_by_artist.isEmpty()) return null;

        return albums_by_artist;
    }

    public static void addAlbum(Album album) {
        albums.add(album);
    }

    public static void deleteAlbum(String albumId) {
        for (Album album : albums) {
            if (album.getId().equals(albumId)) {
                albums.remove(album);
                break;
            }
        }
    }
}

