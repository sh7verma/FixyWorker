package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;

import com.app.fixy.R;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class BookingsFragment extends BaseFragment {
    @SuppressLint("StaticFieldLeak")
    static BookingsFragment fragment;

    public static BookingsFragment newInstance(Context mContext) {
        fragment = new BookingsFragment();
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_bookings;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initListeners() {

    }
}
