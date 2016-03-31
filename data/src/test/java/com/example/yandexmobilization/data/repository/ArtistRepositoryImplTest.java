package com.example.yandexmobilization.data.repository;

import com.example.domainandroid.entity.Artist;
import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.deserialization.ArtistsDeserializer;
import com.example.yandexmobilization.data.net.RestAPI;
import com.example.yandexmobilization.data.net.ServerHelper;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Жамбыл on 3/30/2016.
 */
public class ArtistRepositoryImplTest {

    private ArtistRepositoryImpl artistRepository;
    private ServerHelper serverHelper;
    private DataBaseHelper dataBaseHelper;

    @Before
    public void init() {
        RestAPI restAPI = RestAPI.Creator.newService();
        ArtistsDeserializer artistsDeserializer = new ArtistsDeserializer();
        serverHelper = new ServerHelper(restAPI, artistsDeserializer);
        dataBaseHelper = new DataBaseHelper();
        artistRepository = new ArtistRepositoryImpl(serverHelper, dataBaseHelper);
    }

    @Test
    public void shouldGetArtists() {
        List<Artist> artists = artistRepository.getAll().toBlocking().first();
        Assert.assertNotNull(artists);
    }

    @Test
    public void shouldSaveArtists() {
        List<Artist> artists = artistRepository.getAll().toBlocking().first();

        assertEquals(artists,dataBaseHelper.getArtists().toBlocking().first());
    }

    @Test
    public void shouldGiveDataFromDatabaseIsHas() {
        dataBaseHelper = mock(DataBaseHelper.class);
        serverHelper = mock(ServerHelper.class);
        when(dataBaseHelper.hasData()).thenReturn(true);
        ArtistRepositoryImpl artistRepository = new ArtistRepositoryImpl(serverHelper, dataBaseHelper);

        artistRepository.getAll();

        verify(dataBaseHelper).getArtists();
        verify(serverHelper,never()).loadArtists();
    }

    @Test
    public void shouldGetDataFromDatabaseAfterGettingFromInternet() {
        dataBaseHelper = mock(DataBaseHelper.class);
        when(dataBaseHelper.saveArtists(any())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return  Observable.just(args[0]);
        });

        ArtistRepositoryImpl artistRepository = new ArtistRepositoryImpl(serverHelper, dataBaseHelper);

        List<Artist> artists = artistRepository.getAll().toBlocking().first();
        verify(dataBaseHelper).hasData();
        verify(dataBaseHelper).saveArtists(artists);
    }

    @Test
    public void shouldGetArtistById() {
        int id = 1080505;
        Artist artist = artistRepository.getById(id).toBlocking().first();
        assertNotNull(artist);
        assertEquals(id,artist.getId());
    }
}