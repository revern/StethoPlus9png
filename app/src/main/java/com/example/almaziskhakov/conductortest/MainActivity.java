package com.example.almaziskhakov.conductortest;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import com.example.almaziskhakov.conductortest.network.Api;
import com.example.almaziskhakov.conductortest.network.RxErrorHandlingCallAdapterFactory;
import com.example.almaziskhakov.conductortest.network.StringConverterFactory;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_SOME_STRING = "some_string";
    Button            uiRequest;
    SharedPreferences sharedPreferences;
    //    private Router router;
    Api               api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = buildApi(buildOkHttpClient(), buildGsonMapper());

        sharedPreferences = getPreferences(MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_SOME_STRING, "hello everybody").apply();

        uiRequest = (Button) findViewById(R.id.request_btn);
        uiRequest.setOnClickListener(view -> {
            api.getProviders()
                .subscribeOn(Schedulers.io())
                .subscribe(providers -> Toast.makeText(this,
                    providers.getProviders().get(0).getName(), Toast.LENGTH_LONG).show(),
                    error -> {});
        });

//        ViewGroup container = (ViewGroup) findViewById(R.id.controller_container);
//
//        router = Conductor.attachRouter(this, container, savedInstanceState);
//        if (!router.hasRootController()) {
//            router.setRoot(RouterTransaction.with(new HomeController()));
//        }
    }

//    @Override public void onBackPressed() {
//        if (!router.handleBack()) {
//            super.onBackPressed();
//        }
//    }

    @Nullable
    @Contract(value = "null -> null", pure = true)
    public List<String> getStr(@Nullable List<String> str) {
        if (str == null) return null;
        str.add("hello");
        return str;
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (sharedPreferences.getString(KEY_SOME_STRING, "").equals("asd")) {
            Toast.makeText(this, "YELI MAKAROLLI", Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode, event);
    }


    private OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(interceptor);
        }

        httpClientBuilder.addInterceptor(chain -> {
            return chain.proceed(chain.request());
        });

        httpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        return httpClientBuilder.build();
    }

    private Gson buildGsonMapper() {
        return new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    }

    private Api buildApi(@NonNull OkHttpClient httpClient, @NonNull Gson mapper) {
        return new Retrofit.Builder()
            .client(httpClient)
            .baseUrl(Api.BASE_URL)
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(mapper))
            .build()
            .create(Api.class);
    }
}
