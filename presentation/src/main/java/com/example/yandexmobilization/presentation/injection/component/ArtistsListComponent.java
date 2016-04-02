package com.example.yandexmobilization.presentation.injection.component;

import com.example.yandexmobilization.presentation.injection.module.ArtistsListModule;
import com.example.yandexmobilization.presentation.injection.scope.PerActivity;
import com.example.yandexmobilization.presentation.ui.activity.ArtistsListActivity;

import dagger.Component;

/**
 * Created by Жамбыл on 4/1/2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ArtistsListModule.class)
public interface ArtistsListComponent extends BaseComponent {
    void inject(ArtistsListActivity artistsListActivity);

}
