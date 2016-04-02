package com.example.yandexmobilization.presentation.injection.module;

import android.content.Context;

import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.deserialization.ArtistsDeserializer;
import com.example.yandexmobilization.data.mapper.ArtistConverter;
import com.example.yandexmobilization.data.net.RestAPI;
import com.example.yandexmobilization.data.net.ServerHelper;
import com.example.yandexmobilization.data.repository.ArtistRepositoryImpl;
import com.example.yandexmobilization.domain.interactor.GetAllArtists;
import com.example.yandexmobilization.domain.interactor.UseCase;
import com.example.yandexmobilization.domain.repository.ArtistRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Жамбыл on 4/1/2016.
 */
@Module
public class ApplicationModule {

    Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    ArtistRepository provideArtistRepository(ServerHelper serverHelper,
                                             DataBaseHelper dataBaseHelper, ArtistConverter artistConverter) {
        return new ArtistRepositoryImpl(serverHelper,dataBaseHelper,artistConverter);
    }

    @Singleton
    @Provides
    ServerHelper provideServerHelper(RestAPI restAPI, ArtistsDeserializer artistsDeserializer) {
        return new ServerHelper(restAPI,artistsDeserializer);
    }

    @Singleton
    @Provides
    RestAPI provideRestApi() {
        return RestAPI.Creator.newService();
    }

    @Singleton
    @Provides
    ArtistsDeserializer provideArtistDeserializer() {
        return new ArtistsDeserializer();
    }

    @Provides
    @Singleton
    DataBaseHelper provideDataBaseHelper() {
        return new DataBaseHelper();
    }

    @Provides
    ArtistConverter provideArtistConverter() {
        return new ArtistConverter();
    }
}
