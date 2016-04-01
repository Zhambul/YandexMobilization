package com.example.yandexmobilization.data;

import android.app.Application;
import android.content.Context;
import android.test.ActivityTestCase;
import android.test.ApplicationTestCase;
import android.test.mock.MockContext;

import com.example.yandexmobilization.data.database.DataBaseHelper;
import com.example.yandexmobilization.data.mapper.ArtistMapper;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ActivityTestCaseTest  extends ActivityTestCase {

    public void testDataBase() {
        Context context = getInstrumentation().getContext();
        SugarContext.init(context);
        ArtistMapper artistMapper = new ArtistMapper();
        artistMapper.setId(12L);
        artistMapper.setName("Name");
        artistMapper.setTracks(1);
        artistMapper.setDescription("qwe");
        artistMapper.setAlbumsAmount(1);
        artistMapper.setBigCover("");
        artistMapper.setSmallCover("qwe");
        artistMapper.setLink("qwe");
        List<String> genres = new ArrayList<>();
        genres.add("qweqw");
        artistMapper.setGenres(genres);
        artistMapper.save();

        List<ArtistMapper> artistMappers = ArtistMapper.listAll(ArtistMapper.class);
        assertEquals("Name", artistMappers.get(0).getName());
    }

}