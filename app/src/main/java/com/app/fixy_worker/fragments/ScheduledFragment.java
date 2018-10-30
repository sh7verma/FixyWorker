package com.app.fixy_worker.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.ScheduleDetailActivity;
import com.app.fixy_worker.adapters.ScheduledAdapter;
import com.app.fixy_worker.dialogs.AcceptedTimeDialog;
import com.app.fixy_worker.dialogs.CancelConfirmDialog;
import com.app.fixy_worker.dialogs.ConfirmOtpDialog;
import com.app.fixy_worker.dialogs.ListDialog;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduledFragment extends BaseFragment {

    private static final int DETAIL_RESULT = 1;
    static ScheduledFragment fragment;
    private static Context mContext;

    @BindView(R.id.recycleview)
    RecyclerView rvPast;
    @BindView(R.id.txt_no_request)
    TextView txtNoRequest;


    ScheduledAdapter mAdapter;
    private List<RequestModel.ResponseBean> mList = new ArrayList<>();

    public static ScheduledFragment newInstance(Context context) {
        fragment = new ScheduledFragment();
        mContext = context;
//        textView.setText("PAST");
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_scheduled;
    }

    protected void onCreateStuff() {
        rvPast.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPast.setNestedScrollingEnabled(false);

        mAdapter = new ScheduledAdapter(mContext, click, mList);
        rvPast.setAdapter(mAdapter);
    }

    protected void initListeners() {

    }
// interface click from adapter
    InterfacesCall.ScheduleRequest click = new InterfacesCall.ScheduleRequest() {
        @Override
        public void clickIndex(int pos) {
            if (pos == InterConst.SHOW_ADAPTER_SNACKBAR){
                showCustomSnackBar(txtNoRequest,getString(R.string.arrival_time),getString(R.string.eta_time_mess));
            }
            else {

                Intent intent = new Intent(getActivity(), ScheduleDetailActivity.class);
                intent.putExtra(InterConst.EXTRA,mList.get(pos));
                startActivityForResult(intent,DETAIL_RESULT);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }
        }

        @Override
        public void Confirm(int pos) {
            String status = mList.get(pos).getRequest_status();
            if (status.equalsIgnoreCase(InterConst.ACCEPT_REQUEST)) {
                hitUpdateStatusApi(pos, InterConst.ON_THE_WAY);
            } else if (status.equalsIgnoreCase(InterConst.ON_THE_WAY)) {
                enterOtp();
//                hitUpdateStatusApi(pos, InterConst.CONFRIM);
            } else if (status.equalsIgnoreCase(InterConst.CONFRIM)) {
                confirmComplete();
            }
        }
    };

    private void confirmComplete() {
        CancelConfirmDialog confirmDialog = new CancelConfirmDialog(getContext(), R.style.scale_out_dowm, new InterfacesCall.Callback() {
            @Override
            public void selected(int pos) {
                switch (pos){
                    case InterConst.CANCEL:

                        break;
                    case InterConst.CONFIRM:
                        hitUpdateStatusApi(pos, InterConst.COMPLETE);
                        break;
                }
            }
        });
        confirmDialog.show();
    }

    private void enterOtp() {
        ConfirmOtpDialog acceptedTimeDialog = new ConfirmOtpDialog(getContext(), R.style.DialogSlideAnim, new ListDialog.CallBack() {

            @Override
            public void click(String time) {
                Log.e("time",""+time);
            }
        });
        acceptedTimeDialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case DETAIL_RESULT:
                    updateAdater();
                    break;
            }
        }
    }
    private void hitUpdateStatusApi(int pos, String status) {
        ApiInterface apiInterface = RetrofitClient.getInstance();
//    request_status:(1 for accept, -1 for decline, 2 for on the way, 3 for confirm)
        showProgress();
        Call<RequestModel> call = apiInterface.update_request_status(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""), mList.get(pos).getId(), mList.get(pos).getRequest_price(), "", status);
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                hideProgress();
                if (response.body().getResponse() != null && response.body().getCode() == InterConst.SUCCESS_RESULT) {

                    mList = response.body().getResponse();
                    if (mList.size() > 0) {

                        rvPast.setVisibility(View.VISIBLE);
                        txtNoRequest.setVisibility(View.GONE);
                        mAdapter.updateAdapter(mList);
                    } else {
                        rvPast.setVisibility(View.GONE);
                        txtNoRequest.setVisibility(View.VISIBLE);

                    }
//                    mAdapter = new NewRequestAdapter(mContext, click, mList);
//                    rvPast.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                t.printStackTrace();
                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public void updateAdater() {
        hitScheduledRequest();

    }

    public void hitScheduledRequest() {
        ApiInterface apiInterface = RetrofitClient.getInstance();

        Call<RequestModel> call = apiInterface.schedule_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""));
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                if (response.body().getResponse() != null && response.body().getCode() == InterConst.SUCCESS_RESULT) {

                    mList = response.body().getResponse();
                    if (mList.size() > 0) {

                        rvPast.setVisibility(View.VISIBLE);
                        txtNoRequest.setVisibility(View.GONE);
                        mAdapter.updateAdapter(mList);
                    } else {
                        rvPast.setVisibility(View.GONE);
                        txtNoRequest.setVisibility(View.VISIBLE);

                    }
//                    mAdapter = new NewRequestAdapter(mContext, click, mList);
//                    rvPast.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
