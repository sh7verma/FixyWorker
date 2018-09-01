package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.app.fixy_worker.R;

import butterknife.BindView;
import butterknife.OnClick;

public class EnterNumberActivity extends BaseActivity {

    @BindView(R.id.ll_next)
    LinearLayout llNext;

    @BindView(R.id.ed_number)
    EditText edNumber;

    @Override
    protected int getContentView() {
        return R.layout.activity_enter_number;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {
        edNumber.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));

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
