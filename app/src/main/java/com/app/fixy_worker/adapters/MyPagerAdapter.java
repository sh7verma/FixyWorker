package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.fixy_worker.fragments.NewRequestFragment;
import com.app.fixy_worker.fragments.ScheduledFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    Context mContext;
    NewRequestFragment newRequestFragment;
    ScheduledFragment scheduledFragment;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs, Context con) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        mContext = con;
        newRequestFragment =  NewRequestFragment.newInstance(mContext);
        scheduledFragment =  ScheduledFragment.newInstance(mContext);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return newRequestFragment;
            case 1:
                return scheduledFragment;

            default:
                return scheduledFragment;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
