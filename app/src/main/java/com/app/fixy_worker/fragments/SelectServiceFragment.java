package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.SelectServiceActivity;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;
import com.app.fixy_worker.adapters.SelectServiceAdapter;

import butterknife.BindView;

public class SelectServiceFragment  extends BaseFragment {

    @BindView(R.id.ll_main)
    LinearLayout llMain;

    public  static SelectServiceFragment fragment;

    public static Context mContext;
    public SelectServiceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectServiceFragment newInstance(Context context) {
        if (fragment == null) {
            fragment = new SelectServiceFragment();
        }
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
        llMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.ll_main:
                intent = new Intent(getActivity(), SelectServiceActivity.class);
                startActivity(intent);
                break;
        }
    }

}

