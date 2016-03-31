package com.example.yandexmobilization.data.database;

import com.example.domainandroid.entity.Artist;

import java.util.List;

import rx.Observable;

/**
 * Created by Жамбыл on 3/31/2016.
 */
public class DataBaseHelper {

    private boolean hasData;
    private Observable<List<Artist>> cache;

    public boolean hasData() {
        return hasData;
    }
    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public Observable<List<Artist>> getArtists() {
        return cache;
    }

    public Observable<List<Artist>>  saveArtists(List<Artist> artists) {
        this.cache = Observable.just(artists);
        return cache;
    }
}
