package com.example.yandexmobilization.data.mapper;

import com.example.yandexmobilization.domain.model.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Жамбыл on 4/1/2016.
 */
public class ArtistConverter {

    public List<Artist> convertToListOfArtists(List<ArtistMapper> artistMappers) {
        List<Artist> artists = new ArrayList<>();
        Artist artist = null;
        for (ArtistMapper artistMapper : artistMappers) {
            artist = new Artist();
            artist.setId(artistMapper.getId());
            artist.setName(artistMapper.getName());
            artist.setTracks(artistMapper.getTracks());
            artist.setDescription(artistMapper.getDescription());
            artist.setAlbumsAmount(artistMapper.getAlbumsAmount());
            artist.setLink(artistMapper.getLink());
            artist.setGenres(artistMapper.getGenres());
            artist.setBigCover(artistMapper.getBigCover());
            artist.setSmallCover(artistMapper.getSmallCover());
            artists.add(artist);
        }
        return artists;
    }

}
