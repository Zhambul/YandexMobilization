package com.example.yandexmobilization.presentation.view;

import android.widget.ImageView;

import com.example.yandexmobilization.domain.model.Artist;

import java.util.List;

/**
 * Created by Жамбыл on 4/2/2016.
 */
public interface ArtistsListMvpView extends MvpView {
    void showArtists(List<Artist> artists);
    void showArtistDetails(Artist artist, ImageView imageView);
}
