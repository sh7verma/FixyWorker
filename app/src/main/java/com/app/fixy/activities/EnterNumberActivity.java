package com.app.fixy.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.app.fixy.R;

import butterknife.BindView;
import butterknife.OnClick;

public class EnterNumberActivity extends BaseActivity {

    @BindView(R.id.ll_next)
    LinearLayout llNext;


    @Override
    protected int getContentView() {
        return R.layout.activity_enter_number;
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

    @OnClick(R.id.ll_next)
    void next() {
        Intent intent = new Intent(this, OtpActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }



    @Override
    public void onClick(View view) {
    }
}
