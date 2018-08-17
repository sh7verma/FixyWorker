package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.fixy.R;
import com.app.fixy.adapters.RecommendedServicesAdapter;
import com.app.fixy.adapters.WorkersAdsAdapter;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class HomeFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static HomeFragment fragment;

    LinearLayoutManager mLayoutManagerServices;
    LinearLayoutManager mLayoutManagerAds;

    @BindView(R.id.rv_recommended_services)
    RecyclerView rvRecommendedServices;

    @BindView(R.id.rv_workers_ads)
    RecyclerView rvWorkersAds;

    RecommendedServicesAdapter mAdapterServices;
    WorkersAdsAdapter mAdapterAds;

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

    }

    @Override
    public void onClick(View view) {

    }
}
