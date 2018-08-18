package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.fixy.R;
import com.app.fixy.activities.ViewAllServicesActivity;
import com.app.fixy.adapters.RecommendedServicesAdapter;
import com.app.fixy.adapters.WorkersAdsAdapter;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class HomeFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static HomeFragment fragment;

    @BindView(R.id.rv_recommended_services)
    RecyclerView rvRecommendedServices;

    @BindView(R.id.rv_workers_ads)
    RecyclerView rvWorkersAds;

    @BindView(R.id.txt_view_all_services)
    TextView txtViewAllServices;

    RecommendedServicesAdapter mAdapterServices;
    WorkersAdsAdapter mAdapterAds;

    LinearLayoutManager mLayoutManagerServices;
    LinearLayoutManager mLayoutManagerAds;

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
        mLayoutManagerServices = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvRecommendedServices.setLayoutManager(mLayoutManagerServices);
        rvRecommendedServices.setNestedScrollingEnabled(false);
        mAdapterServices = new RecommendedServicesAdapter(getActivity(), mHeight);
        rvRecommendedServices.setAdapter(mAdapterServices);

        mLayoutManagerAds = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvWorkersAds.setLayoutManager(mLayoutManagerAds);
        rvWorkersAds.setNestedScrollingEnabled(false);
        mAdapterAds = new WorkersAdsAdapter(getActivity(), mHeight);
        rvWorkersAds.setAdapter(mAdapterAds);
    }

    @Override
    protected void initListeners() {
        txtViewAllServices.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_view_all_services:
                Intent intent = new Intent(mContext, ViewAllServicesActivity.class);
                startActivity(intent);

                break;
        }
    }
}
