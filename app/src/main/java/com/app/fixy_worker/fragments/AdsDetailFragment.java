package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.dialogs.ListDialog;
import com.app.fixy_worker.dialogs.UpdateDialog;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.AdsModel;
import com.app.fixy_worker.utils.Validations;

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
    @BindView(R.id.ed_description)
    MaterialEditText edDescription;
    @BindView(R.id.ed_price)
    MaterialEditText edPrice;

    ArrayList<String> daysList = new ArrayList<>();
    ArrayList<String> percentageList = new ArrayList<>();
    public static AdsDetailFragment fragment;
    static Context mContext;
    AdsModel model;
    String percentNo, dayNo;


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
        model = AdsModel.getInstance();
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
        percentageList.add("45%");
        percentageList.add("50%");
    }


    @Override
    protected void initListeners() {

        icInfo.setOnClickListener(this);
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
        ListDialog dialog;
        switch (view.getId()) {
            case R.id.ic_info_per:
                showCustomSnackBar(icInfo, getString(R.string.offer_percentage), getString(R.string.applicable_on_original_price));
                break;
            case R.id.txt_percentage:
                  dialog = new ListDialog(getActivity(), R.style.DialogSlideAnim, percentageList, new ListDialog.dialogClick() {
                    @Override
                    public void click(int pos) {
                        txtPercentage.setText(percentageList.get(pos));
                        model.setPercentage(String.valueOf(pos*5));
                    }
                });
                dialog.show();
                break;
            case R.id.txt_period:
                  dialog = new ListDialog(getActivity(), R.style.DialogSlideAnim, daysList, new ListDialog.dialogClick() {
                    @Override
                    public void click(int pos) {
                        txtPeriod.setText(daysList.get(pos));
                        model.setPeriod(String.valueOf(pos+1));
                    }
                });
                dialog.show();
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
//                    pos = data.getIntExtra(InterConst.EXTRA, 0);
//                    txtPercentage.setText(percentageList.get(pos));
                    break;
                case DAY_RESULT_CODE:
//                    pos = data.getIntExtra(InterConst.EXTRA, 0);
//                    txtPeriod.setText(daysList.get(pos));
                    break;
            }
        }
    }

    public boolean validate() {
        if (!TextUtils.isEmpty(Validations.checkPriceValidation(fragment.getContext(),edPrice))){
            showValidationSnackBar(txtPercentage,Validations.checkPriceValidation(fragment.getContext(),edPrice));
        }
        else if (TextUtils.isEmpty(model.getPercentage())){ 
            showValidationSnackBar(txtPercentage,getString(R.string.select_percentage));
        }
        else if (TextUtils.isEmpty(model.getPeriod())){ 
            showValidationSnackBar(txtPercentage,getString(R.string.select_period_validation));
        }
        else {
            model.setOriginal_price(edPrice.getText().toString());
            model.setDescription(edDescription.getText().toString());
            AdsModel.setInstance(model);
            return true;
        }

        return false;
    }
}
