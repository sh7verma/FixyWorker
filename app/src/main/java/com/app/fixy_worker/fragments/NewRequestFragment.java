package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import com.app.fixy_worker.utils.Consts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewRequestFragment extends BaseFragment   {

    static NewRequestFragment fragment;
    private static Context mContext;

    @BindView(R.id.recycleview)
    RecyclerView rvPast;


    private Handler timeHandler = new Handler();
    private Runnable timeRunnable;

    NewRequestAdapter mAdapter;
    private List<RequestModel.ResponseBean> mList  = new ArrayList<>();

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

        mAdapter = new NewRequestAdapter(mContext,click,mList);
        mAdapter.setHasStableIds(true);
        rvPast.setAdapter(mAdapter);
        rvPast.setHasFixedSize(true);
    }

    long timeMili;
    private void setHandler() {
        timeHandler.removeCallbacks(timeRunnable);
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                for (int pos = 0;pos<mList.size();pos++) {

//                    timeMili = Consts.differnceServerToCurrentTime(mList.get(pos).getCreated_at());
                    timeMili = Consts.differnceServerToCurrentTime("2018-10-12 07:10:59");
                    mList.get(pos).setRemainingTime(Consts.convertMilisecondtoTime(timeMili));
                    mAdapter.notifyDataSetChanged();
                }
                timeHandler.postDelayed(timeRunnable,1000);
            }
        };
        timeHandler.postDelayed(timeRunnable,1000);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    protected void initListeners() {

    }

    InterfacesCall.NewRequest click = new InterfacesCall.NewRequest() {
        @Override
        public void clickIndex(int pos) {
            Intent intent = new Intent(getActivity(), NewRequestDetailActivity.class);
            intent.putExtra(InterConst.EXTRA,mList.get(pos));
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in,R.anim.out);
        }

        @Override
        public void Accept(int pos) {

        }

        @Override
        public void Decline(int pos) {

        }
    };

    public void updateAdater(){

//        mAdapter = new NewRequestAdapter(mContext,click, mList);
//        rvPast.setAdapter(mAdapter);
        hitIncomingRequest();
    }
    public void hitIncomingRequest() {
        ApiInterface apiInterface = RetrofitClient.getInstance();

        Call<RequestModel> call = apiInterface.incomming_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""));
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                if (response.body().getResponse().size()>0 && response.body().getCode() == InterConst.SUCCESS_RESULT){

                    mList = response.body().getResponse();
                    mAdapter.updateAdapter(mList);
//                    mAdapter = new NewRequestAdapter(mContext, click, mList);
//                    rvPast.setAdapter(mAdapter);

                    setHandler();
                }
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
