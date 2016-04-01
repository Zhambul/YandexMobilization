package com.example.yandexmobilization.data.repository;

import android.content.Context;
import android.test.mock.MockContext;

import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.deserialization.ArtistsDeserializer;
import com.example.yandexmobilization.data.mapper.ArtistConverter;
import com.example.yandexmobilization.data.mapper.ArtistMapper;
import com.example.yandexmobilization.data.net.RestAPI;
import com.example.yandexmobilization.data.net.ServerHelper;
import com.example.yandexmobilization.domain.model.Artist;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Жамбыл on 3/30/2016.
 */
public class ArtistRepositoryImplTest {

    private ArtistRepositoryImpl artistRepository;
    private ServerHelper serverHelper;
    private DataBaseHelper dataBaseHelper;
    private ArtistConverter artistConverter;

    @Before
    public void init() {
        RestAPI restAPI = RestAPI.Creator.newService();
        ArtistsDeserializer artistsDeserializer = new ArtistsDeserializer();
        serverHelper = new ServerHelper(restAPI, artistsDeserializer);
        dataBaseHelper = new DataBaseHelper();
        artistConverter = new ArtistConverter();
        artistRepository = new ArtistRepositoryImpl(serverHelper, dataBaseHelper, artistConverter);
    }

    @Test
    public void shouldGetArtists() {
        List<Artist> artists = artistRepository.getAll().toBlocking().first();
        Assert.assertNotNull(artists);
    }

    @Test
    public void shouldGiveDataFromDatabaseIsHas() {
        dataBaseHelper = mock(DataBaseHelper.class);
        serverHelper = mock(ServerHelper.class);
        when(dataBaseHelper.hasData()).thenReturn(true);
        List<ArtistMapper> artists = new ArrayList<>();
        artists.add(new ArtistMapper());
        when(dataBaseHelper.getArtists()).thenReturn(Observable.just(artists));

        ArtistRepositoryImpl artistRepository = new ArtistRepositoryImpl(serverHelper, dataBaseHelper, artistConverter);

        artistRepository.getAll().toBlocking().first();

        verify(dataBaseHelper).getArtists();
        verify(serverHelper,never()).loadArtists();
    }

    @Test
    public void shouldGetDataFromDatabaseAfterGettingFromInternet() {
        serverHelper = mock(ServerHelper.class);
        artistRepository = new ArtistRepositoryImpl(serverHelper,dataBaseHelper, artistConverter);
        List<ArtistMapper> artists = new ArrayList<>();
        artists.add(new ArtistMapper());
        when(serverHelper.loadArtists()).thenReturn(Observable.just(artists));

        //database is empty
        assertEquals(false,dataBaseHelper.hasData());

        //from internet
        artistRepository.getAll().toBlocking().first();
        verify(serverHelper,only()).loadArtists();
        assertEquals(true,dataBaseHelper.hasData());

        //from database
        artistRepository.getAll().toBlocking().first();
        verify(serverHelper,only()).loadArtists();
    }

    @Test
    public void shouldGetArtistById() {
        Long id = 1080505l;
        Artist artist = artistRepository.getById(id).toBlocking().first();
        assertNotNull(artist);
        assertEquals(id,artist.getId());
    }
}