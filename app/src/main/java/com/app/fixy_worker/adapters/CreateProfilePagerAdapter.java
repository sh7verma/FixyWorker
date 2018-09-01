package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.app.fixy_worker.fragments.BookedFragment;
import com.app.fixy_worker.fragments.CreateProfileFragment;
import com.app.fixy_worker.fragments.PendingFragment;
import com.app.fixy_worker.fragments.SelectServiceFragment;
import com.app.fixy_worker.fragments.VerificationFragment;

public class CreateProfilePagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    Context mContext;

    public CreateProfilePagerAdapter(FragmentManager fm, int NumOfTabs, Context con) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        mContext = con;
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return CreateProfileFragment.newInstance(mContext);
            case 1:
                return SelectServiceFragment.newInstance(mContext);

            default:
                return VerificationFragment.newInstance(mContext);
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
