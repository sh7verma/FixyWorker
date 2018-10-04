package com.app.fixy_worker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.MyServicesAdapter;
import com.app.fixy_worker.adapters.PostServiceAdapter;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.AdsModel;
import com.app.fixy_worker.models.LoginModel;

import java.util.ArrayList;

import butterknife.BindView;

public class PostServiceFragment extends BaseFragment {


    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    public static PostServiceFragment fragment;

    public static ArrayList<LoginModel.ResponseBean.SelectedServicesBean> serviceList;
    private PostServiceAdapter mAdapterServices;

    AdsModel model;

    public static PostServiceFragment newInstance(Context mcon, ArrayList<LoginModel.ResponseBean.SelectedServicesBean> eList) {
        if (fragment == null){
            fragment = new PostServiceFragment();
            serviceList = eList;
        }
        return fragment;
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_post;
    }

    @Override
    protected void onCreateStuff() {
        model = AdsModel.getInstance();
        updateServiceAdapter();
    }
    private void updateServiceAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(), calculateNoOfColumns(getContext()), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapterServices = new PostServiceAdapter(getActivity(),serviceList, mHeight,interfaces);
        recyclerView.setAdapter(mAdapterServices);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {

    }
    InterfacesCall.ServiceOffer interfaces = new InterfacesCall.ServiceOffer() {
        @Override
        public void serviceClick(int pos) {
            model.setSubcategoriesBean(serviceList.get(pos));
        }

        @Override
        public void adsClick(int pos) {

        }

        @Override
        public void recomendClick(int pos) {

        }
    };

    public boolean validate(){
        if (model.getSubcategoriesBean() == null){
            showValidationSnackBar(txtTitle,getString(R.string.select_service_validation));
            return false;
        }
        AdsModel.setInstance(model);
        return true;
    }
}
