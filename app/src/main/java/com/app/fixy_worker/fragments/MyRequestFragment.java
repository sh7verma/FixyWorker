package com.app.fixy_worker.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.MyPagerAdapter;
import com.app.fixy_worker.interfaces.InterConst;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class MyRequestFragment extends BaseFragment {
    public static MyRequestFragment fragment;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.txt_booked)
    TextView txtBooked;
    @BindView(R.id.txt_pending)
    TextView txtPending;
    public static Context mContext;
    public static MyPagerAdapter myPagerAdapter;

    public static MyRequestFragment newInstance(Context mCont) {
        fragment = new MyRequestFragment();
        mContext = mCont;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_my_request;
    }

    @Override
    protected void onCreateStuff() {
        setviewpager();
    }

    private void setviewpager() {
        viewPager.setOffscreenPageLimit(0);
        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), 2,mContext);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    txtBooked.setBackground(mContext.getResources().getDrawable(R.drawable.black_round));
                    txtPending.setBackground(mContext.getResources().getDrawable(R.drawable.grey_round_stroke));
                }
                else {
                    txtBooked.setBackground(mContext.getResources().getDrawable(R.drawable.grey_round_stroke));
                    txtPending.setBackground(mContext.getResources().getDrawable(R.drawable.black_round));

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        getContext().registerReceiver(requestBroadcast,new IntentFilter(InterConst.NEW_REQUEST_BROADCAST));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(requestBroadcast);
    }

    @Override
    protected void initListeners() {
        txtBooked.setOnClickListener(this);
        txtPending.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.txt_booked:
               viewPager.setCurrentItem(0);
               break;
           case R.id.txt_pending:
               viewPager.setCurrentItem(1);
               break;
       }
    }
    NewRequestBroadcast requestBroadcast = new NewRequestBroadcast();
    public static class NewRequestBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("myRequest","fragment hit");
            ((NewRequestFragment)myPagerAdapter.getItem(0)).updateAdater();

        }
    }
}
