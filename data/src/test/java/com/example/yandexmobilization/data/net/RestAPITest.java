package com.example.yandexmobilization.data.net;


import com.google.gson.JsonArray;

import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * Created by Жамбыл on 3/30/2016.
 */
public class RestAPITest {

    @Test
    public void shouldLoadJsonFromServer() {
        RestAPI restAPI = RestAPI.Creator.newService();
        JsonArray result  = restAPI.loadArtistsJson().toBlocking().first();
        if(result==null || result.size()==0) {
            fail();
        }
    }

}