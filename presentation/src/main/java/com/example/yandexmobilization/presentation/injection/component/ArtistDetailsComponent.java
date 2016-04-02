package com.example.yandexmobilization.presentation.injection.component;

import com.example.yandexmobilization.presentation.injection.module.ArtistDetailsModule;
import com.example.yandexmobilization.presentation.injection.module.ArtistsListModule;
import com.example.yandexmobilization.presentation.injection.scope.PerActivity;
import com.example.yandexmobilization.presentation.ui.activity.ArtistsDetailActivity;

import dagger.Component;

/**
 * Created by Жамбыл on 4/2/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ArtistDetailsModule.class)
public interface ArtistDetailsComponent extends BaseComponent {

    void inject(ArtistsDetailActivity artistsDetailActivity);
}
