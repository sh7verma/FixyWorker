package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.app.fixy.R;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class ProfileFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static ProfileFragment fragment;

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

    }
}
