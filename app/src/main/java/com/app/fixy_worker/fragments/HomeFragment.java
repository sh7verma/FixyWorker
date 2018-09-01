package com.app.fixy_worker.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.BookingDetailActivity;
import com.app.fixy_worker.activities.SearchServiceActivity;
import com.app.fixy_worker.activities.ServicesListActivity;
import com.app.fixy_worker.activities.WorkersAdsListActivity;
import com.app.fixy_worker.adapters.RecommendedServicesAdapter;
import com.app.fixy_worker.adapters.WorkersAdsAdapter;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.txt_view_all_ads)
    TextView txtViewAllAds;

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
        txtViewAllAds.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.txt_view_all_services:
                intent = new Intent(mContext, ServicesListActivity.class);
                startActivity(intent);
                break;

            case R.id.txt_view_all_ads:
                intent = new Intent(mContext, WorkersAdsListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.ic_search)
    public void searchService(){

        Intent intent = new Intent(getActivity(), SearchServiceActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in,R.anim.out);
    }
}
