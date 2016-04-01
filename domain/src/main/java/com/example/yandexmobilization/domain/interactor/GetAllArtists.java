package com.example.yandexmobilization.domain.interactor;

import com.example.yandexmobilization.domain.repository.ArtistRepository;

import rx.Observable;

/**
 * Created by Жамбыл on 4/1/2016.
 */
public class GetAllArtists extends UseCase {

    private final ArtistRepository artistRepository;

    public GetAllArtists(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return artistRepository.getAll();
    }
}
