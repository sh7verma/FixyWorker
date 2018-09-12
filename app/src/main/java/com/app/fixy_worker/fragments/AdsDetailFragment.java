package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.dialogs.ListDialog;
import com.app.fixy_worker.interfaces.InterConst;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class AdsDetailFragment extends BaseFragment {

    private static final int DAY_RESULT_CODE = 3;
    private static final int PERCENTAGE_RESULT = 2;
    @BindView(R.id.ic_info_per)
    ImageView icInfo;
    @BindView(R.id.txt_percentage)
    TextView txtPercentage;
    @BindView(R.id.txt_period)
    TextView txtPeriod;
    @BindView(R.id.txt_description)
    TextView txtDescription;
    @BindView(R.id.ed_price)
    MaterialEditText edPrice;

    ArrayList<String> daysList = new ArrayList<>();
    ArrayList<String> percentageList = new ArrayList<>();
    public static AdsDetailFragment fragment;
    static Context mContext;


    public static AdsDetailFragment newInstance(Context mcon) {
        if (fragment == null) {
            fragment = new AdsDetailFragment();
        }
        mContext = mcon;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_ads_detail;
    }

    @Override
    protected void onCreateStuff() {
        daysList.add("1 day");
        daysList.add("2 days");
        daysList.add("3 days");
        daysList.add("4 days");
        daysList.add("5 days");
        daysList.add("6 days");
        daysList.add("7 days");

        percentageList.add("5%");
        percentageList.add("10%");
        percentageList.add("15%");
        percentageList.add("20%");
        percentageList.add("25%");
        percentageList.add("30%");
        percentageList.add("35%");
        percentageList.add("40%");
        percentageList.add("50%");
    }


    @Override
    protected void initListeners() {

        icInfo.setOnClickListener(this);
        txtDescription.setOnClickListener(this);
        txtPercentage.setOnClickListener(this);
        txtPeriod.setOnClickListener(this);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ic_info_per:
                showCustomSnackBar(icInfo, getString(R.string.offer_percentage), getString(R.string.applicable_on_original_price));
                break;
            case R.id.txt_percentage:
                intent = new Intent(getActivity(), ListDialog.class);
                intent.putStringArrayListExtra(InterConst.EXTRA, percentageList);
                startActivityForResult(intent, PERCENTAGE_RESULT);
                break;
            case R.id.txt_period:
                intent = new Intent(getActivity(), ListDialog.class);
                intent.putStringArrayListExtra(InterConst.EXTRA, daysList);
                startActivityForResult(intent, DAY_RESULT_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int pos;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PERCENTAGE_RESULT:
                    pos = data.getIntExtra(InterConst.EXTRA, 0);
                    txtPercentage.setText(percentageList.get(pos));
                    break;
                case DAY_RESULT_CODE:
                    pos = data.getIntExtra(InterConst.EXTRA, 0);
                    txtPeriod.setText(daysList.get(pos));
                    break;
            }
        }
    }
}
