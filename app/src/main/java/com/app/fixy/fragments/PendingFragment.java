package com.app.fixy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fixy.R;
import com.app.fixy.adapters.PendingAdapter;


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

        mAdapter = new PendingAdapter(mContext);
        rvPast.setAdapter(mAdapter);
    }

    protected void initListeners() {

    }
}
