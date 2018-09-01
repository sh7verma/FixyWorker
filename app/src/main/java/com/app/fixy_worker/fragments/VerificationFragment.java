package com.app.fixy_worker.fragments;

import android.content.Context;
import android.view.View;

import com.app.fixy_worker.R;

public class VerificationFragment  extends BaseFragment {

    public  static VerificationFragment fragment;

    public static Context mContext;
    public VerificationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VerificationFragment newInstance(Context context) {
        fragment = new VerificationFragment();
        mContext = context;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_verification;
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


