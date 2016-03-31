package com.example.domainandroid.repository;

import com.example.domainandroid.entity.Artist;
import com.example.domainandroid.entity.Entity;
import java.util.List;

import rx.Observable;

/**
 * Created by Жамбыл on 3/30/2016.
 */
public interface Repository<T extends Entity> {

    Observable<List<T>> getAll();
    Observable<T> getById(int id);
}
