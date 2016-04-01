package com.example.yandexmobilization.domain.repository;

import java.util.List;

import rx.Observable;

/**
 * Created by Жамбыл on 3/30/2016.
 */
public interface Repository<T> {

    Observable<List<T>> getAll();
    Observable<T> getById(Long id);
}
