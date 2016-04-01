package com.example.yandexmobilization.domain.interactor;

import com.example.yandexmobilization.domain.repository.ArtistRepository;


import rx.Observable;

/**
 * Created by Жамбыл on 4/1/2016.
 */
public class GetArtistDetails extends UseCase {

    private final ArtistRepository artistRepository;
    private final Long artistId;

    public GetArtistDetails(ArtistRepository artistRepository, Long artistId) {
        this.artistRepository = artistRepository;
        this.artistId = artistId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return artistRepository.getById(artistId);
    }
}
