package com.app.fixy_worker.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.BookingDetailActivity;
import com.app.fixy_worker.adapters.BookingAdapter;
import com.app.fixy_worker.interfaces.InterfacesCall;

public class BookedFragment extends Fragment   {

    static BookedFragment fragment;
    private static Context mContext;

    RecyclerView rvPast;



    BookingAdapter mAdapter;

    public static BookedFragment newInstance(Context context) {
        fragment = new BookedFragment();
        mContext = context;
//        textView.setText("PAST");
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_booked, container, false);
        rvPast =   view.findViewById(R.id.recycleview);
        onCreateStuff(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected void onCreateStuff(View view) {
        rvPast.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvPast.setNestedScrollingEnabled(false);

        mAdapter = new BookingAdapter(mContext,click);
        rvPast.setAdapter(mAdapter);
    }

    protected void initListeners() {

    }

    InterfacesCall.IndexClick click = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {
            Intent intent = new Intent(getActivity(), BookingDetailActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in,R.anim.out);
        }
    };

    public void updateAdater(){

        mAdapter = new BookingAdapter(mContext,click);
        rvPast.setAdapter(mAdapter);
    }
}
