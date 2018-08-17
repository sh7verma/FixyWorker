package com.app.fixy.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.fixy.fragments.BookedFragment;
import com.app.fixy.fragments.PendingFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    Context mContext;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs, Context con) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        mContext = con;
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return BookedFragment.newInstance(mContext);

            default:
                return PendingFragment.newInstance(mContext);
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
