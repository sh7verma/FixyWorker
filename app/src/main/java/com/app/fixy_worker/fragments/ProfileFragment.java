package com.app.fixy_worker.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.SettingsActivity;
import com.app.fixy_worker.activities.ShowAddressActivity;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class ProfileFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static ProfileFragment fragment;

    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_settings)
    LinearLayout llSettings;

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
        llAddress.setOnClickListener(this);
        llSettings.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_address:
                intent = new Intent(getActivity(), ShowAddressActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
                break;
            case R.id.ll_settings:
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
                break;
        }

    }
}
