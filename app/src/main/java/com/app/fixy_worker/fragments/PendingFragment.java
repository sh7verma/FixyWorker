package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.BookingDetailActivity;
import com.app.fixy_worker.activities.PendingDetailActivity;
import com.app.fixy_worker.adapters.PendingAdapter;
import com.app.fixy_worker.interfaces.InterfacesCall;


public class PendingFragment extends Fragment {

    static PendingFragment fragment;
    private static Context mContext;

    RecyclerView rvPast;



    PendingAdapter mAdapter;

    public static PendingFragment newInstance(Context context) {
        fragment = new PendingFragment();
        mContext = context;
//        textView.setText("PAST");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        rvPast =   view.findViewById(R.id.recycleview);
        onCreateStuff();
        return view;
    }
    protected void onCreateStuff() {
        rvPast.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPast.setNestedScrollingEnabled(false);

        mAdapter = new PendingAdapter(mContext,click);
        rvPast.setAdapter(mAdapter);
    }

    protected void initListeners() {

    }

    InterfacesCall.IndexClick click = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {
            Intent intent = new Intent(getActivity(), PendingDetailActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in,R.anim.out);
        }
    };
}
