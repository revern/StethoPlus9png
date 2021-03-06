package com.example.almaziskhakov.conductortest;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by almaziskhakov on 09/02/2017.
 */

public abstract class BaseController extends Controller {
    private Unbinder unbinder;

    @NonNull @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected abstract View inflateView(@NonNull LayoutInflater inflater,
                                        @NonNull ViewGroup container);

    protected void onViewBound(@NonNull View view) { }

    @Override protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        unbinder.unbind();
        unbinder = null;
    }
}
