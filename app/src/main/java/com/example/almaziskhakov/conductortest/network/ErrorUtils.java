package com.example.almaziskhakov.conductortest.network;

import android.content.Context;
import android.util.Log;


import com.example.almaziskhakov.conductortest.network.models.RetrofitException;

import rx.functions.Action1;

/**
 * Created by Ilya Eremin on 12.08.2016.
 */
public class ErrorUtils {

    private static final String TAG = "Errors";

    public static Action1<Throwable> onError() {
        return ErrorUtils::logError;
    }

    public static void logError(Throwable throwable) {
        Log.d(TAG, "oh shit error occured", throwable);
    }

    public static void handle(Context context, Throwable error) {
        if (error instanceof RetrofitException) {
        } else {
            ErrorUtils.logError(error);
        }
    }

    public static Action1<Throwable> handle(Context context) {
        return error -> ErrorUtils.handle(context, error);
    }
}
