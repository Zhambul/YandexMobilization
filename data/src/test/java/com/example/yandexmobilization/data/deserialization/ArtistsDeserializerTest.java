package com.example.yandexmobilization.data.deserialization;

import com.example.domainandroid.entity.Artist;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Жамбыл on 3/30/2016.
 */
public class ArtistsDeserializerTest {

    JsonArray dummyJson;

    @Before
    public void initDummyJsonArray() {
        JsonParser jsonParser = new JsonParser();
        dummyJson = jsonParser.parse("[\n" +
                "  {\n" +
                "    \"id\": 1080505,\n" +
                "    \"name\": \"Tove Lo\",\n" +
                "    \"genres\": [\n" +
                "      \"pop\",\n" +
                "      \"dance\",\n" +
                "      \"electronics\"\n" +
                "    ],\n" +
                "    \"tracks\": 81,\n" +
                "    \"albums\": 22,\n" +
                "    \"link\": \"http://www.tove-lo.com/\",\n" +
                "    \"description\": \"шведская певица и автор песен. Она привлекла к себе внимание в 2013 году с выпуском сингла «Habits», но настоящего успеха добилась с ремиксом хип-хоп продюсера Hippie Sabotage на эту песню, который получил название «Stay High». 4 марта 2014 года вышел её дебютный мини-альбом Truth Serum, а 24 сентября этого же года дебютный студийный альбом Queen of the Clouds. Туве Лу является автором песен таких артистов, как Icona Pop, Girls Aloud и Шер Ллойд.\",\n" +
                "    \"cover\": {\n" +
                "      \"small\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\",\n" +
                "      \"big\": \"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"\n" +
                "    }\n" +
                "  }]").getAsJsonArray();
    }

    @Test
    public void shouldDeserializeProperly(){
        List<Artist> artists = new ArtistsDeserializer().deserialize(dummyJson);
        if(artists.size() == 0) {
            fail();
        }
    }

    @Test
    public void shouldNotFailOnNullJson() {
        new ArtistsDeserializer().deserialize(null);
    }
}