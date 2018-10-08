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
    NewRequestFragment bookedFragment;
    ScheduledFragment pendingFragment;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs, Context con) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        mContext = con;
        bookedFragment =  NewRequestFragment.newInstance(mContext);
        pendingFragment =  ScheduledFragment.newInstance(mContext);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return bookedFragment;
            case 1:
                return pendingFragment;

            default:
                return pendingFragment;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
