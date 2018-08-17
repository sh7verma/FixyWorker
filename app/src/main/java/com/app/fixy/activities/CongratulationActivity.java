package com.app.fixy.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.fixy.R;

import butterknife.OnClick;

public class CongratulationActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_congratulation;
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
    @OnClick(R.id.txt_browse_service)
    void browseService() {
        Intent intent = new Intent(mContext, LandingActivity.class);
        finish();
        startActivity(intent);
    }
}
