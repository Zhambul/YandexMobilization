package com.example.yandexmobilization.data.mapper;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by Жамбыл on 4/1/2016.
 */
@Table
public class ArtistMapper extends SugarRecord {

    private Long id;
    private String name;
    private int tracks;
    private int albumsAmount;
    private String description;
    private List<String> genres;
    //todo change to url
    private String link;
    private String smallCover;
    private String bigCover;

    public ArtistMapper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getAlbumsAmount() {
        return albumsAmount;
    }

    public void setAlbumsAmount(int albumsAmount) {
        this.albumsAmount = albumsAmount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallCover() {
        return smallCover;
    }

    public void setSmallCover(String smallCover) {
        this.smallCover = smallCover;
    }

    public String getBigCover() {
        return bigCover;
    }

    public void setBigCover(String bigCover) {
        this.bigCover = bigCover;
    }

    @Override
    public String toString() {
        return "ArtistMapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tracks=" + tracks +
                ", albumsAmount=" + albumsAmount +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                ", link='" + link + '\'' +
                ", smallCover='" + smallCover + '\'' +
                ", bigCover='" + bigCover + '\'' +
                '}';
    }
}
