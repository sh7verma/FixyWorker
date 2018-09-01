package com.app.fixy_worker.fragments;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;

import butterknife.BindView;

public class SelectServiceFragment  extends BaseFragment {

    public  static SelectServiceFragment fragment;

    public static Context mContext;
    public SelectServiceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectServiceFragment newInstance(Context context) {
        fragment = new SelectServiceFragment();
        mContext = context;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_select_service;
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

