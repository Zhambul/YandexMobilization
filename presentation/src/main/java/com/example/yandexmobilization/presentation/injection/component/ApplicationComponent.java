package com.example.yandexmobilization.presentation.injection.component;

import android.content.Context;

import com.example.yandexmobilization.domain.repository.ArtistRepository;
import com.example.yandexmobilization.presentation.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Жамбыл on 4/1/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent extends BaseComponent{

    ArtistRepository getArtistRepository();
    Context getContext();
}
