package com.app.fixy.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.app.fixy.R;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateProfileActivity extends BaseActivity {

    @BindView(R.id.rv_main)
    RelativeLayout rvMain;

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_email)
    EditText edEmail;
    @BindView(R.id.ed_referral_code)
    EditText edReferralCode;

    @Override
    protected int getContentView() {
        return R.layout.activity_create_profile;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {
        edName.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
        edEmail.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
        edReferralCode.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.img_referral)
    void imgReferral() {
        showCustomSnackBar(rvMain, getString(R.string.referral_code), getString(R.string.enter_referral_code_detail));
    }

    @Override
    public void onClick(View view) {

    }
}
