package com.app.fixy_worker.fragments;

import android.app.Activity;
import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.CreateProfileActivity;
import com.app.fixy_worker.activities.OtpActivity;
import com.app.fixy_worker.activities.SelectServiceActivity;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;
import com.app.fixy_worker.adapters.SelectServiceAdapter;
import com.app.fixy_worker.customviews.FlowLayout;
import com.app.fixy_worker.dialogs.ListDialog;
import com.app.fixy_worker.dialogs.SelectCityDialog;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.CItyModel;
import com.app.fixy_worker.models.CreateActivityModel;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.models.SelectServiceModel;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectServiceFragment extends BaseFragment {

    private static final int SERVICE_RESULT = 12;
    @BindView(R.id.ll_main_city)
    LinearLayout llMainCity;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.flow_layout)
    FlowLayout flowlayout;
    @BindView(R.id.txt_city)
    TextView txtCity;

    HashMap<Integer, String> selectedList = new HashMap<>();
    public static SelectServiceFragment fragment;

    CreateActivityModel model;
    public static Context mContext;
    private ArrayList<CItyModel.ResponseBean> cityList;

    public SelectServiceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SelectServiceFragment newInstance(Context context) {
        if (fragment == null) {
            fragment = new SelectServiceFragment();
        }
        mContext = context;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_select_service;
    }

    @Override
    protected void onCreateStuff() {
        model = CreateActivityModel.getInstance();
    }

    @Override
    protected void initListeners() {
        llMainCity.setOnClickListener(this);
        llMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_main:
                intent = new Intent(getActivity(), SelectServiceActivity.class);
                intent.putExtra(InterConst.EXTRA,selectedList);
                startActivityForResult(intent, SERVICE_RESULT);
                getActivity().overridePendingTransition(R.anim.slide_up, R.anim.stay);
                break;
            case R.id.ll_main_city:
                if (cityList == null) {
                    hitCityApi();
                }
                else {
                    openAvailableCity();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SERVICE_RESULT:
                    selectedList = (HashMap<Integer, String>) data.getSerializableExtra(InterConst.RESULT_DATA_KEY);
                    createChips();
                    break;
            }
        }
    }

    public  boolean checkValidation() {
        if (TextUtils.isEmpty(model.getServices())) {
            showValidationSnackBar(llMain, getString(R.string.add_service_validation));

        } else {
            return true;
        }
        return false;
    }

    void createChips() {
        flowlayout.removeAllViews();
        List<String> Ids = new ArrayList<>();
        for (final Map.Entry<Integer, String> entry : selectedList.entrySet()) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.select_service_item, null, false);

            final TextView txtView = view.findViewById(R.id.txt_name);
            final ImageView icDelete = view.findViewById(R.id.ic_delete);
            txtView.setText(entry.getValue());
            Ids.add(String.valueOf(entry.getKey()));

            icDelete.setTag(entry.getKey());
            flowlayout.addView(view);
            icDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("text", "" + icDelete.getTag());
                    selectedList.remove(icDelete.getTag());
                    createChips();
                }
            });
        }
        String ids = TextUtils.join(",",Ids);
        model.setServices(ids);
        CreateActivityModel.setInstance(model);
    }
    public void hitCityApi() {

        if (connectedToInternet()) {
            Call<CItyModel> call = RetrofitClient.getInstance().city(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<CItyModel>() {
                @Override
                public void onResponse(Call<CItyModel> call, Response<CItyModel> response) {
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {
                        cityList = response.body().getResponse();
                        openAvailableCity();
                    } else if (response.body().getError().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext, llMain, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<CItyModel> call, Throwable t) {
                    t.printStackTrace();
                    showSnackBar(llMain,t.getMessage());
                }
            });
        }
        else {
            showInternetAlert(llMain);
        }

    }

    private void openAvailableCity() {
        SelectCityDialog dialog = new SelectCityDialog(getContext(), R.style.pullBottomfromTop, cityList, new InterfacesCall.Callback() {
            @Override
            public void selected(int pos) {
                utils.setString(InterConst.CITY_NAME,cityList.get(pos).getCity_name());
                utils.setString(InterConst.CITY_ID,cityList.get(pos).getId());
                txtCity.setText(cityList.get(pos).getCity_name());
            }
        });
        dialog.show();
    }
}

