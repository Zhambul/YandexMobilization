package com.example.yandexmobilization.data.repository;

import com.example.yandexmobilization.data.mapper.ArtistMapper;
import com.example.yandexmobilization.domain.model.Artist;
import com.example.yandexmobilization.domain.repository.ArtistRepository;
import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.mapper.ArtistConverter;
import com.example.yandexmobilization.data.net.ServerHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Жамбыл on 3/28/2016.
 */
@Singleton
public class ArtistRepositoryImpl implements ArtistRepository {

    private ServerHelper serverHelper;
    private DataBaseHelper dataBaseHelper;
    private ArtistConverter artistConverter;

    @Inject
    public ArtistRepositoryImpl(ServerHelper serverHelper, DataBaseHelper dataBaseHelper, ArtistConverter artistConverter) {
        this.serverHelper = serverHelper;
        this.dataBaseHelper = dataBaseHelper;
        this.artistConverter = artistConverter;
    }

    //todo refactor
    @Override
    public Observable<List<Artist>> getAll() {
        if(dataBaseHelper.hasData()) {
           return dataBaseHelper.getArtists().concatMap(artistMappers ->
               Observable.just(artistConverter.convertToListOfArtists(artistMappers)));
        } else {
           return serverHelper.loadArtists().concatMap(artistMappers -> {
               dataBaseHelper.saveArtists(artistMappers);
               return Observable.just(artistConverter.convertToListOfArtists(artistMappers));
           });
        }
    }

    @Override
    public Observable<Artist> getById(Long id) {
        return getAll().map(artists -> {
            for (Artist artist : artists) {
                if(artist.getId().equals(id)) {
                    return artist;
                }
            }
            return null;
        });
    }
}
