package com.example.yandexmobilization.data.net;

import com.example.domainandroid.entity.Artist;
import com.example.yandexmobilization.data.deserialization.ArtistsDeserializer;
import com.google.gson.JsonArray;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Жамбыл on 3/31/2016.
 */
@Singleton
public class ServerHelper {

    private RestAPI restAPI;
    private ArtistsDeserializer artistsDeserializer;

    @Inject
    public ServerHelper(RestAPI restAPI, ArtistsDeserializer artistsDeserializer) {
        this.restAPI = restAPI;
        this.artistsDeserializer = artistsDeserializer;
    }

    public Observable<List<Artist>> loadArtists() {
        return Observable.create((subscriber)-> {
            JsonArray artists = restAPI.loadArtistsJson().toBlocking().first();
            subscriber.onNext(artistsDeserializer.deserialize(artists));
        });
    }


}
