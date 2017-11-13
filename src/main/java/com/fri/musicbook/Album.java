package com.fri.musicbook;

import javax.persistence.*;
import org.eclipse.persistence.annotations.UuidGenerator;


@Entity(name = "albums")
@NamedQueries(value =
        {
                @NamedQuery(name = "albums.getAll", query = "SELECT o FROM albums o"),
                @NamedQuery(name = "albums.getAlbumsByArtist", query = "SELECT o FROM albums o WHERE o.artistId = :artistId")
        })
@UuidGenerator(name = "idGenerator")
public class Album {
    //private String year_of_relese;
    @Column(name = "album_name")
    private String album_name;

    @Column(name="artist_id")
    private Integer artistId;

    //private String genre;
    @Id
    @Column(name = "album_id")
    private Integer albumId;

    // Sets/Gets

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer AlbumId) {
        this.albumId = AlbumId;
    }

    public String getAlbumName() {
        return album_name;
    }

    public void setAlbumName(String name) {
        this.album_name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artist) {
        this.artistId = artist;
    }
/*
    public int getYear_of_relese(){
        return year_of_relese;
    }
    public void setYear_of_relese(String year_of_relese){
        this.year_of_relese=year_of_relese;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

*/

}
