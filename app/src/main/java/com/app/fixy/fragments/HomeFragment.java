package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;

import com.app.fixy.R;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class HomeFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static HomeFragment fragment;

    public static HomeFragment newInstance(Context mContext) {
        fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initListeners() {

    }
}
