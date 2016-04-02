package com.example.yandexmobilization.presentation.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yandexmobilization.R;
import com.example.yandexmobilization.domain.model.Artist;
import com.example.yandexmobilization.presentation.adapter.ArtistsListAdapter;
import com.example.yandexmobilization.presentation.injection.App;
import com.example.yandexmobilization.presentation.injection.component.ArtistsListComponent;
import com.example.yandexmobilization.presentation.injection.component.DaggerArtistsListComponent;
import com.example.yandexmobilization.presentation.injection.module.ArtistsListModule;
import com.example.yandexmobilization.presentation.presenter.ArtistsListPresenter;
import com.example.yandexmobilization.presentation.util.MarginDecoration;
import com.example.yandexmobilization.presentation.view.ArtistsListMvpView;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArtistsListActivity extends BaseActivity<ArtistsListComponent> implements ArtistsListMvpView {

    @Bind(R.id.artists_recycler_view) RecyclerView artistRecyclerView;
    @Inject ArtistsListPresenter presenter;
    @Inject ArtistsListAdapter artistsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        ButterKnife.bind(this);
        initRecyclerView();
        presenter.attachView(this);
        presenter.resume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void createComponent() {
        component = DaggerArtistsListComponent.builder()
            .applicationComponent(App.getInstance().getApplicationComponent())
            .artistsListModule(new ArtistsListModule(this))
            .build();
    }

    @Override
    public void showArtists(List<Artist> artists) {
        List<Artist> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add(artists.get(i));
        }

        artistsListAdapter.setArtists(result);
        artistRecyclerView.setAdapter(artistsListAdapter);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void showArtistDetails(Artist artist, ImageView imageView) {
        if(!imageView.getTransitionName().equals("image")) {
            throw  new RuntimeException();
        }
        View sharedView = imageView;
        Intent intent = new Intent(this,ArtistsDetailActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedView, "image");
        startActivity(intent,options.toBundle());
    }

    private void initRecyclerView() {
        artistRecyclerView.addItemDecoration(new MarginDecoration(this));
        artistRecyclerView.setHasFixedSize(true);
        artistRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
