package com.example.yandexmobilization.data.repository;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.deserialization.ArtistsDeserializer;
import com.example.yandexmobilization.data.mapper.ArtistConverter;
import com.example.yandexmobilization.data.mapper.ArtistMapper;
import com.example.yandexmobilization.data.net.RestAPI;
import com.example.yandexmobilization.data.net.ServerHelper;
import com.example.yandexmobilization.domain.model.Artist;
import com.orm.SugarContext;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Жамбыл on 4/1/2016.
 */
public class ArtistRepositoryAImplTest extends InstrumentationTestCase {

    private Context context;
    private ArtistRepositoryImpl artistRepository;
    private ServerHelper serverHelper;
    private DataBaseHelper dataBaseHelper;
    private ArtistConverter artistConverter;

    @Override
    protected void setUp() throws Exception {
        System.setProperty(
                "dexmaker.dexcache",
                getInstrumentation().getTargetContext().getCacheDir().getPath());

        context = getInstrumentation().getContext();
        SugarContext.init(context);
        RestAPI restAPI = RestAPI.Creator.newService();
        ArtistsDeserializer artistsDeserializer = new ArtistsDeserializer();
        serverHelper = new ServerHelper(restAPI, artistsDeserializer);
        dataBaseHelper = new DataBaseHelper();
        dataBaseHelper.cleanDataBase();
        artistConverter = new ArtistConverter();
        artistRepository = new ArtistRepositoryImpl(serverHelper, dataBaseHelper, artistConverter);
    }

    public void test_shouldGetArtists() {
        List<Artist> artists = artistRepository.getAll().toBlocking().first();
        Assert.assertNotNull(artists);
    }

    public void test_shouldGiveDataFromDatabaseIsHas() {
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

    public void test_shouldGetDataFromDatabaseAfterGettingFromInternet() {
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

    public void test_shouldGetArtistById() {
        Long id = 1080505L;
        Artist artist = artistRepository.getById(id).toBlocking().first();
        assertNotNull(artist);
        assertEquals(id,artist.getId());
    }
}