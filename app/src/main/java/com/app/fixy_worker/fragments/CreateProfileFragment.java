package com.app.fixy_worker.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;
import com.app.fixy_worker.adapters.MyPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;


public class CreateProfileFragment extends BaseFragment {


    @BindView(R.id.rv_main)
    RelativeLayout rvMain;

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_email)
    EditText edEmail;
    @BindView(R.id.ed_referral_code)
    EditText edReferralCode;

    public  static CreateProfileFragment fragment;

    public static Context mContext;
    public CreateProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CreateProfileFragment newInstance(Context context) {
          fragment = new CreateProfileFragment();
        mContext = context;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_create_profile;
    }

    @Override
    protected void onCreateStuff() {

        edName.setTypeface(typefaceMedium);
        edEmail.setTypeface(typefaceMedium);
        edReferralCode.setTypeface(typefaceMedium);
    }

    @OnClick(R.id.img_referral)
    void imgReferral() {
         showCustomSnackBar(rvMain, getString(R.string.referral_code), getString(R.string.enter_referral_code_detail));
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClick(View view) {

    }

}
