package com.example.yandexmobilization.data.deserialization;

import android.support.annotation.NonNull;

import com.example.domainandroid.entity.Artist;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

/**
 * Created by Жамбыл on 3/30/2016.
 */
@Singleton
public class ArtistsDeserializer {

    public List<Artist> deserialize(JsonArray jsonElements){
        if (isInvalidInput(jsonElements)) {
            return new ArrayList<>();
        }

        return getArtists(jsonElements);
    }

    private boolean isInvalidInput(JsonArray jsonElements) {
        return jsonElements==null || jsonElements.size()  == 0 || jsonElements.isJsonNull() ;
    }

    @NonNull
    private List<Artist> getArtists(JsonArray artistsJsonArray) {
        List<Artist> artists = new ArrayList<>();
        for (JsonElement artistJsonElement : artistsJsonArray) {
            Artist artist = new Artist();
            JsonObject artistJsonObject = artistJsonElement.getAsJsonObject();
            setMainData(artist, artistJsonObject);
            setCovers(artist, artistJsonObject);
            setLink(artist, artistJsonObject);
            setGenres(artist, artistJsonObject);
            artists.add(artist);
        }
        return artists;
    }

    private void setMainData(Artist artist, JsonObject artistJsonObject) {
        artist.setId(artistJsonObject.get("id").getAsInt());
        artist.setName(artistJsonObject.get("name").getAsString());
        artist.setTracks(artistJsonObject.get("tracks").getAsInt());
        artist.setAlbumsAmount(artistJsonObject.get("albums").getAsInt());
        artist.setDescription(artistJsonObject.get("description").getAsString());
    }

    private void setCovers(Artist artist, JsonObject artistJsonObject) {
        JsonObject covers = artistJsonObject.get("cover").getAsJsonObject();
        artist.setSmallCover(covers.get("small").getAsString());
        artist.setBigCover(covers.get("big").getAsString());
    }

    private void setGenres(Artist artist, JsonObject artistJsonObject) {
        List<String> genres = new ArrayList<>();
        for (JsonElement genre : artistJsonObject.get("genres").getAsJsonArray()) {
            genres.add(genre.getAsString());
        }

        artist.setGenres(genres);
    }

    private void setLink(Artist artist, JsonObject artistJsonObject) {
        JsonElement link = artistJsonObject.get("link");
        if(link!=null){
            artist.setLink(link.getAsString());
        }
    }
}
