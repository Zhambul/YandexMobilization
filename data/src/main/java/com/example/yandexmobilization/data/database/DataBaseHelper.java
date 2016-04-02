package com.example.yandexmobilization.data.database;

import com.example.yandexmobilization.data.mapper.ArtistMapper;
import com.orm.SugarContext;

import java.util.List;

import rx.Observable;

/**
 * Created by Жамбыл on 3/31/2016.
 */
public class DataBaseHelper {

    public boolean hasData() {
        return false;
//        return ArtistMapper.listAll(ArtistMapper.class).size() != 0;
    }

    public Observable<List<ArtistMapper>> getArtists() {
        return Observable.just(ArtistMapper.listAll(ArtistMapper.class));
    }

    public void saveArtists(List<ArtistMapper> artists) {
//        cleanDataBase();
//        for (ArtistMapper artist : artists) {
//            artist.save();
//        }
    }

    public void cleanDataBase() {
        ArtistMapper.deleteAll(ArtistMapper.class);
    }
}
