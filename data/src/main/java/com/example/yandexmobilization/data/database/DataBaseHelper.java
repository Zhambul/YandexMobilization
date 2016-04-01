package com.example.yandexmobilization.data.database;

import com.example.yandexmobilization.data.mapper.ArtistMapper;

import java.util.List;

import rx.Observable;

/**
 * Created by Жамбыл on 3/31/2016.
 */
public class DataBaseHelper {

    public boolean hasData() {
        return ArtistMapper.listAll(ArtistMapper.class).size() != 0;
    }

    public Observable<List<ArtistMapper>> getArtists() {
        return Observable.just(ArtistMapper.listAll(ArtistMapper.class));
    }

    public void saveArtists(List<ArtistMapper> artists) {
        for (ArtistMapper artist : artists) {
            artist.save();
        }
    }
}
