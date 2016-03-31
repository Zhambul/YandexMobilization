package com.example.yandexmobilization.data.net;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import javax.inject.Singleton;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Жамбыл on 3/28/2016.
 */
@Singleton
public interface RestAPI {
    String ENDPOINT = "http://download.cdn.yandex.net/mobilization-2016/";

    @GET("artists.json")
    Observable<JsonArray> loadArtistsJson();

    class Creator {

        public static RestAPI newService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestAPI.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RestAPI.class);
        }
    }
}
