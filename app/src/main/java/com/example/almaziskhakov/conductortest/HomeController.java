package com.example.almaziskhakov.conductortest;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by almaziskhakov on 09/02/2017.
 */

public class HomeController extends BaseController {

    @BindView(R.id.red_btn)   TextView uiRedBtn;
    @BindView(R.id.blue_btn)  TextView uiBlueBtn;
    @BindView(R.id.green_btn) TextView uiGreenBtn;

    @OnClick(R.id.blue_btn) void onBlueClick() {
        uiGreenBtn.setText("HALO");
    }

    @OnClick(R.id.green_btn) void onGreenClick() {
        getRouter().pushController(RouterTransaction.with(new OneController())
            .pushChangeHandler(new FadeChangeHandler())
            .popChangeHandler(new FadeChangeHandler()));
    }

    @NonNull @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return super.onCreateView(inflater, container);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        ButterKnife.bind(view);
        uiRedBtn.setText("RED");
    }

    @Override protected void onDetach(@NonNull View view) {
        super.onDetach(view);
    }

    @Override protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
    }

}
