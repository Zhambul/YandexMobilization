package com.example.yandexmobilization.data.repository;


import com.example.domainandroid.entity.Artist;
import com.example.domainandroid.repository.ArtistRepository;
import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.deserialization.ArtistsDeserializer;
import com.example.yandexmobilization.data.net.RestAPI;
import com.example.yandexmobilization.data.net.ServerHelper;
import com.google.gson.JsonArray;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Жамбыл on 3/28/2016.
 */
@Singleton
public class ArtistRepositoryImpl implements ArtistRepository {

    private ServerHelper serverHelper;
    private DataBaseHelper dataBaseHelper;

    @Inject
    public ArtistRepositoryImpl(ServerHelper serverHelper, DataBaseHelper dataBaseHelper) {
        this.serverHelper = serverHelper;
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public Observable<List<Artist>> getAll() {
        if(dataBaseHelper.hasData()) {
           return dataBaseHelper.getArtists();
        } else {
           return serverHelper.loadArtists().concatMap(dataBaseHelper::saveArtists);
        }
    }

    @Override
    public Observable<Artist> getById(int id) {
        return Observable.create((Observable.OnSubscribe<Artist>) subscriber -> {
            List<Artist> artists = getAll().toBlocking().first();
            for (Artist artist : artists) {
                if(artist.getId() == id) {
                    subscriber.onNext(artist);
                    subscriber.onCompleted();
                }
            }
            subscriber.onCompleted();
        });
    }
}
