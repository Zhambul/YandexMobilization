package com.example.yandexmobilization.presentation.injection;

import android.app.Application;

import com.example.yandexmobilization.data.database.SugarContextManager;
import com.example.yandexmobilization.presentation.injection.component.ApplicationComponent;
import com.example.yandexmobilization.presentation.injection.component.DaggerApplicationComponent;
import com.example.yandexmobilization.presentation.injection.module.ApplicationModule;

/**
 * Created by Жамбыл on 4/1/2016.
 */
public class App extends Application {

    private ApplicationComponent applicationComponent;
    private static App instance;
    private SugarContextManager sugarContextManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sugarContextManager = new SugarContextManager();
        sugarContextManager.onCreate(this);
        instance = this;

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        sugarContextManager.onTermination();
    }

    public static App getInstance() {
        return instance;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
