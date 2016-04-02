package com.example.yandexmobilization.presentation.injection.module;

import com.example.yandexmobilization.presentation.injection.scope.PerActivity;
import com.example.yandexmobilization.presentation.presenter.ArtistDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Жамбыл on 4/2/2016.
 */
@Module
public class ArtistDetailsModule {

    @Provides
    @PerActivity
    ArtistDetailPresenter provideArtistDetailPresenter() {
        return new ArtistDetailPresenter();
    }
}
