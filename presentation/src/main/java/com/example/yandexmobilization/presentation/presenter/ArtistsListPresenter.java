package com.example.yandexmobilization.presentation.presenter;

import com.example.yandexmobilization.domain.interactor.GetAllArtists;
import com.example.yandexmobilization.domain.model.Artist;
import com.example.yandexmobilization.presentation.injection.scope.PerActivity;
import com.example.yandexmobilization.presentation.view.ArtistsListMvpView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Жамбыл on 4/2/2016.
 */
@PerActivity
public class ArtistsListPresenter extends BasePresenter<ArtistsListMvpView> {

    private GetAllArtists getAllArtistsUseCase;

    @Inject
    public ArtistsListPresenter(GetAllArtists getAllArtistsUseCase) {
        this.getAllArtistsUseCase = getAllArtistsUseCase;
    }

    @Override
    public void resume() {
        getAllArtistsUseCase.execute(new Subscriber<List<Artist>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Artist> artists) {
                getView().showArtists(artists);
            }
        });
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

}
