package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.ScheduleDetailActivity;
import com.app.fixy_worker.adapters.ScheduledAdapter;
import com.app.fixy_worker.interfaces.InterfacesCall;

import butterknife.BindView;


public class ScheduledFragment extends BaseFragment {

    static ScheduledFragment fragment;
    private static Context mContext;

    @BindView(R.id.recycleview)
    RecyclerView rvPast;



    ScheduledAdapter mAdapter;

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

        mAdapter = new ScheduledAdapter(mContext,click);
        rvPast.setAdapter(mAdapter);
    }

    protected void initListeners() {

    }

    InterfacesCall.IndexClick click = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {
            Intent intent = new Intent(getActivity(), ScheduleDetailActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in,R.anim.out);
        }
    };

    @Override
    public void onClick(View v) {

    }
}
