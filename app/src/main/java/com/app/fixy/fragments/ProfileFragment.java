package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.app.fixy.R;
import com.app.fixy.activities.SettingsActivity;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class ProfileFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static ProfileFragment fragment;

    @BindView(R.id.ll_settings)
    LinearLayout llSettings;

    public static ProfileFragment newInstance(Context mContext) {
        fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.ll_settings:
                intent = new Intent(mContext, SettingsActivity.class);
                startActivity(intent);
                break;

        }
    }
}
