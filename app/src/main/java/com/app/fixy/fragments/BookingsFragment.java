package com.app.fixy.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;

import com.app.fixy.R;
import com.app.fixy.adapters.MyPagerAdapter;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class BookingsFragment extends BaseFragment {
    @SuppressLint("StaticFieldLeak")
    static BookingsFragment fragment;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Context mContext;

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
        mContext = getActivity();
        setviewpager();
    }

    private void setviewpager() {
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager(), 2,mContext));
    }
    @Override
    protected void initListeners() {

    }
}
