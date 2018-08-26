package com.app.fixy.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.fixy.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class BookingDetailActivity extends BaseActivity {

    @BindView(R.id.ll_main)
    LinearLayout llMain;

    @Override
    protected int getContentView() {
        return R.layout.activity_booking_detail;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {

        finish();
        overridePendingTransition(R.anim.slide_right, R.anim.slide_out_right);
    }

    @OnClick(R.id.ic_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.ic_info)
    void infoClick() {

        showCustomSnackBar(llMain, getString(R.string.otp), getString(R.string.provide_otp_to_worker));
    }
}
