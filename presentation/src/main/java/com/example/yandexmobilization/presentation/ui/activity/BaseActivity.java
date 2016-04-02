package com.example.yandexmobilization.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yandexmobilization.presentation.injection.component.BaseComponent;

/**
 * Created by Жамбыл on 4/1/2016.
 */
public abstract class BaseActivity<T extends BaseComponent> extends AppCompatActivity {

    protected T component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void createComponent();

    protected T getComponent() {
        if(component == null) {
            createComponent();
        }
        return component;
    }

    protected  void setComponent(T component) {
        this.component = component;
    }
}
