package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.fixy.R;
import com.app.fixy.adapter.RecommendedServicesAdapter;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class HomeFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static HomeFragment fragment;

    LinearLayoutManager mLayoutManager;

    @BindView(R.id.rv_recommended_services)
    RecyclerView rvRecommendedServices;

    RecommendedServicesAdapter mAdapter;

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
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvRecommendedServices.setLayoutManager(mLayoutManager);
        rvRecommendedServices.setNestedScrollingEnabled(false);
        mAdapter = new RecommendedServicesAdapter(getActivity(),mHeight);
        rvRecommendedServices.setAdapter(mAdapter);
    }

    @Override
    protected void initListeners() {

    }
}
