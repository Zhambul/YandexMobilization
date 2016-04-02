package com.example.yandexmobilization.data.database;

import android.content.Context;

import com.orm.SugarContext;

/**
 * Created by Жамбыл on 4/2/2016.
 */
public class SugarContextManager {

    public void onCreate(Context context) {
        SugarContext.init(context);
    }

    public void onTermination() {
        SugarContext.terminate();
    }
}
