package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateProfileActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @Override
    protected int getContentView() {
        return R.layout.activity_create_profile;
    }

    @Override
    protected void onCreateStuff() {

        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new CreateProfilePagerAdapter(getSupportFragmentManager(), 3,mContext));
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

    @OnClick(R.id.txt_done)
    void done() {
        Intent intent = new Intent(mContext, CongratulationActivity.class);
        finish();
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {

    }
}
