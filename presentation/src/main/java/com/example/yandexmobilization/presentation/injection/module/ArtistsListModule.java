package com.example.yandexmobilization.presentation.injection.module;

import android.content.Context;

import com.example.yandexmobilization.domain.interactor.GetAllArtists;
import com.example.yandexmobilization.domain.repository.ArtistRepository;
import com.example.yandexmobilization.presentation.adapter.ArtistsListAdapter;
import com.example.yandexmobilization.presentation.injection.scope.PerActivity;
import com.example.yandexmobilization.presentation.presenter.ArtistsListPresenter;
import com.example.yandexmobilization.presentation.ui.activity.ArtistsListActivity;
import com.example.yandexmobilization.presentation.view.ArtistsListMvpView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Жамбыл on 4/1/2016.
 */
@Module
public class ArtistsListModule {

    private ArtistsListMvpView artistsListActivity;

    public ArtistsListModule(ArtistsListMvpView artistsListActivity) {
        this.artistsListActivity = artistsListActivity;
    }

    @PerActivity
    @Provides
    GetAllArtists provideAllArtists(ArtistRepository artistRepository) {
        return new GetAllArtists(artistRepository);
    }

    @Provides
    @PerActivity
    ArtistsListPresenter provideArtistsListPresenter(GetAllArtists getAllArtists) {
        return new ArtistsListPresenter(getAllArtists);
    }

    @Provides
    @PerActivity
    ArtistsListAdapter provideArtistsListAdapter(Context context) {
        return new ArtistsListAdapter(context, artistsListActivity);
    }
}
