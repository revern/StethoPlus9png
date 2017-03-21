package com.example.almaziskhakov.conductortest;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by almaziskhakov on 14/03/2017.
 */

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
