package com.example.yandexmobilization.presentation.presenter;

import com.example.yandexmobilization.presentation.view.MvpView;

/**
 * Created by Жамбыл on 4/2/2016.
 */
public abstract class BasePresenter<T extends MvpView> {

    private T view;

    public void attachView(T view) {
        this.view = view;
    }
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

    abstract void resume();

    abstract void pause();

    abstract void destroy();
}
