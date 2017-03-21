package com.example.almaziskhakov.conductortest.network;

import com.example.almaziskhakov.conductortest.models.Providers;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by almaziskhakov on 17/03/2017.
 */

public interface Api {
    String BASE_URL = "https://api.pairup.im/";
    String V1       = "v1/";

    @GET(V1 + "providers") Observable<Providers> getProviders();

}
