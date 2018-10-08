package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.NewRequestDetailActivity;
import com.app.fixy_worker.adapters.NewRequestAdapter;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewRequestFragment extends BaseFragment   {

    static NewRequestFragment fragment;
    private static Context mContext;

    @BindView(R.id.recycleview)
    RecyclerView rvPast;



    NewRequestAdapter mAdapter;

    public static NewRequestFragment newInstance(Context context) {
        fragment = new NewRequestFragment();
        mContext = context;
//        textView.setText("PAST");
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragement_new_request;
    }

    @Override
    protected void onCreateStuff() {
        rvPast.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPast.setNestedScrollingEnabled(false);

        mAdapter = new NewRequestAdapter(mContext,click);
        rvPast.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    protected void initListeners() {

    }

    InterfacesCall.IndexClick click = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {
            Intent intent = new Intent(getActivity(), NewRequestDetailActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in,R.anim.out);
        }
    };

    public void updateAdater(){

        mAdapter = new NewRequestAdapter(mContext,click);
        rvPast.setAdapter(mAdapter);
        hitIncomingRequest();
    }
    public void hitIncomingRequest() {
        ApiInterface apiInterface = RetrofitClient.getInstance();

        Call<RequestModel> call = apiInterface.incomming_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""));
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {

            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
