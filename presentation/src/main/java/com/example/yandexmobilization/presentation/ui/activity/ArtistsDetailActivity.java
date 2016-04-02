package com.example.yandexmobilization.presentation.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.yandexmobilization.R;
import com.example.yandexmobilization.presentation.injection.App;
import com.example.yandexmobilization.presentation.injection.component.ArtistDetailsComponent;
import com.example.yandexmobilization.presentation.injection.component.DaggerArtistDetailsComponent;
import com.example.yandexmobilization.presentation.injection.module.ArtistDetailsModule;
import com.example.yandexmobilization.presentation.presenter.ArtistDetailPresenter;
import com.example.yandexmobilization.presentation.view.ArtistDetailsMvpView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Жамбыл on 3/28/2016.
 */
public class ArtistsDetailActivity extends BaseActivity<ArtistDetailsComponent>
        implements ArtistDetailsMvpView {

    @Bind(R.id.image) ImageView imageView;
    @Inject ArtistDetailPresenter presenter;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        getComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);

        if(!imageView.getTransitionName().equals("image")) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void createComponent() {
        component = DaggerArtistDetailsComponent.builder()
            .applicationComponent(App.getInstance().getApplicationComponent())
            .artistDetailsModule(new ArtistDetailsModule())
            .build();
    }
}
