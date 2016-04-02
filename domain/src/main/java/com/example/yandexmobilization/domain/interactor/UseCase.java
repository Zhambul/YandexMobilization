package com.example.yandexmobilization.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Жамбыл on 4/1/2016.
 */
public abstract class UseCase<T> {

    private Subscription subscription = Subscriptions.empty();

    protected abstract Observable<T> buildUseCaseObservable();

    public void execute(Subscriber<T> UseCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(UseCaseSubscriber);
    }


    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
