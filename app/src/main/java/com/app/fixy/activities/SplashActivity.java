package com.app.fixy.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.app.fixy.R;


public class SplashActivity extends BaseActivity {

    private static final long TIME_OUT = 2000;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {
        openNextActivity();
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

    void openNextActivity() {
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent in = new Intent(SplashActivity.this, EnterNumberActivity.class);
                startActivity(in);
                finish();
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            }
        }, TIME_OUT);
    }
}