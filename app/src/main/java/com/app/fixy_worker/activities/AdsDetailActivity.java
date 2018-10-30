package com.app.fixy_worker.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AdsDetailActivity extends BaseActivity {

    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_service)
    ImageView imgService;
    @BindView(R.id.txt_service_name)
    TextView txtServiceName;
    @BindView(R.id.txt_description)
    TextView txtDesription;
    @BindView(R.id.txt_percentage)
    TextView txtPercentage;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_time_counter)
    TextView txtTimeCounter;
    @BindView(R.id.ic_info_time)
    ImageView icInfoTime;


    @Override
    protected int getContentView() {
        return R.layout.activity_ads_detail;
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
        overridePendingTransition(R.anim.stay, R.anim.slide_down_out);
    }

    @OnClick(R.id.ic_back)
    void back() {
        onBackPressed();
    }

}
