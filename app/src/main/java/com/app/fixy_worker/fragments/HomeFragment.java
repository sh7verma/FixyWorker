package com.app.fixy_worker.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.AdsDetailActivity;
import com.app.fixy_worker.activities.PostNewAddActivity;
import com.app.fixy_worker.activities.ServicesListActivity;
import com.app.fixy_worker.activities.WorkersAdsListActivity;
import com.app.fixy_worker.adapters.MyServicesAdapter;
import com.app.fixy_worker.adapters.WorkersAdsAdapter;
import com.app.fixy_worker.dialogs.SelectCityDialog;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.CItyModel;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Dialogs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class HomeFragment extends BaseFragment{

    private static final int CALLBACK = 1;
    @SuppressLint("StaticFieldLeak")
    static HomeFragment fragment;

    @BindView(R.id.rv_my_services)
    RecyclerView rvMyServices;
    @BindView(R.id.rv_workers_ads)
    RecyclerView rvWorkersAds;
    @BindView(R.id.txt_view_all_ads)
    TextView txtViewAllAds;
    @BindView(R.id.txt_view_all_services)
    TextView txtViewAllServices;
    @BindView(R.id.txt_city)
    TextView txtCity;
    @BindView(R.id.txt_referral_code)
    TextView txtReferralCode;
    @BindView(R.id.txt_referral_code_benefit)
    TextView txtReferralCodeBenefit;

    MyServicesAdapter mAdapterServices;
    WorkersAdsAdapter mAdapterAds;

    LinearLayoutManager mLayoutManagerServices;
    LinearLayoutManager mLayoutManagerAds;
    private ArrayList<CItyModel.ResponseBean> cityList;
    private List<LoginModel.ResponseBean.SelectedServicesBean> servicesList;

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



        mLayoutManagerAds = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvWorkersAds.setLayoutManager(mLayoutManagerAds);
        rvWorkersAds.setNestedScrollingEnabled(false);
        mAdapterAds = new WorkersAdsAdapter(getActivity(), mHeight, interfaces);
        rvWorkersAds.setAdapter(mAdapterAds);

        if (TextUtils.isEmpty(utils.getString(InterConst.CITY_ID, ""))) {
            hitCityApi();
        } else {
            txtCity.setText(utils.getString(InterConst.CITY_NAME, ""));
            hitMyServices();
        }
        txtReferralCode.setText(utils.getString(InterConst.REFFERAL_CODE, ""));
    }

    @Override
    protected void initListeners() {
        txtViewAllServices.setOnClickListener(this);
        txtViewAllAds.setOnClickListener(this);
        txtCity.setOnClickListener(this);
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
            case R.id.txt_city:
                if (cityList == null) {
                    hitCityApi();
                } else {
                    openAvailableCity();
                }
                break;
        }
    }

    public void hitCityApi() {

        if (connectedToInternet()) {
            showProgress();
            Call<CItyModel> call = RetrofitClient.getInstance().city(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<CItyModel>() {
                @Override
                public void onResponse(Call<CItyModel> call, Response<CItyModel> response) {
                    hideProgress();
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {
                        cityList = response.body().getResponse();
                        openAvailableCity();
                    } else if (response.body().getError().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext, txtCity, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<CItyModel> call, Throwable t) {
                    hideProgress();
                    t.printStackTrace();
                    showSnackBar(txtCity, t.getMessage());
                }
            });
        } else {
            showInternetAlert(txtCity);
        }

    }

    private void openAvailableCity() {
        SelectCityDialog dialog = new SelectCityDialog(getContext(), false, R.style.pullBottomfromTop, cityList, new InterfacesCall.Callback() {
            @Override
            public void selected(int pos) {
                hitUpdateCityApi(pos);
            }
        });
        dialog.show();
    }

    public void hitUpdateCityApi(int pos) {

        if (connectedToInternet()) {
            showProgress();
            Call<LoginModel> call = RetrofitClient.getInstance().update_city(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""),
                    cityList.get(pos).getCity_name(),
                    cityList.get(pos).getId());
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    hideProgress();
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {

                        txtCity.setText(response.body().getResponse().getCity());
                        utils.setString(InterConst.CITY_NAME, response.body().getResponse().getCity());
                        utils.setString(InterConst.CITY_ID, response.body().getResponse().getCity_id());
                    } else if (response.body().getError().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext, txtCity, response.body().getError().getMessage());
                    } else if (response.body().getError().getCode() == InterConst.INVALID_ACCESS_TOKEN) {
                        moveToSplash();
                    } else {
                        showSnackBar(txtViewAllAds, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    hideProgress();
                    t.printStackTrace();
                    showSnackBar(txtCity, t.getMessage());
                }
            });
        } else {
            showInternetAlert(txtCity);
        }
    }

    private void hitMyServices() {

        if (connectedToInternet()) {
            showProgress();
            Call<LoginModel> call = RetrofitClient.getInstance().fetch_current_service(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    hideProgress();
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {
                        servicesList = response.body().getResponse().getSelected_services();
                        updateServiceAdapter();
                    } else if (response.body().getError().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext, txtCity, response.body().getError().getMessage());
                    } else if (response.body().getError().getCode() == InterConst.INVALID_ACCESS_TOKEN) {
                        moveToSplash();
                    } else {
                        showSnackBar(txtViewAllAds, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    hideProgress();
                    t.printStackTrace();
                    showSnackBar(txtCity, t.getMessage());
                }
            });
        } else {
            showInternetAlert(txtCity);
        }
    }

    private void updateServiceAdapter() {
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(), calculateNoOfColumns(getContext()), LinearLayoutManager.VERTICAL, false);
        rvMyServices.setLayoutManager(mLayoutManager);
        rvMyServices.setItemAnimator(new DefaultItemAnimator());
        mAdapterServices = new MyServicesAdapter(getActivity(),servicesList, mHeight,interfaces);
        rvMyServices.setAdapter(mAdapterServices);
    }

    InterfacesCall.ServiceOffer interfaces = new InterfacesCall.ServiceOffer() {
        @Override
        public void serviceClick(int pos) {

        }

        @Override
        public void adsClick(int pos) {
            if (pos == 0){

                Intent intent = new Intent(getContext(), PostNewAddActivity.class);
                intent.putParcelableArrayListExtra(InterConst.EXTRA, (ArrayList<LoginModel.ResponseBean.SelectedServicesBean>) servicesList);
                startActivityForResult(intent,CALLBACK);
                getActivity().overridePendingTransition(R.anim.trans_enter,R.anim.stay);
            }
            else{

                Intent intent = new Intent(getContext(), AdsDetailActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_up,R.anim.stay);
            }
        }

        @Override
        public void recomendClick(int pos) {

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case CALLBACK:
                    break;
            }
        }
    }



      /*  Bundle myExtrasBundle = new Bundle();
        myExtrasBundle.putString("some_key", "some_value");

        Job myJob = dispatcher.newJobBuilder()
                // the JobServiceSchedule that will be called
                .setService(JobDispatcherService.class)
                // uniquely identifies the job
                .setTag("incoming-request")
                // one-off job
                .setRecurring(false)
                // don't persist past a device reboot
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(0, 60))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        // only run on an unmetered network
                        Constraint.ON_UNMETERED_NETWORK,
                        // only run when the device is charging
                        Constraint.DEVICE_CHARGING
                )
                .setExtras(myExtrasBundle)
                .build();
        dispatcher.mustSchedule(myJob);
*/

}
