package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.fixy_worker.fragments.CoinsFragment;
import com.app.fixy_worker.fragments.CreateProfileFragment;
import com.app.fixy_worker.fragments.HomeFragment;
import com.app.fixy_worker.fragments.MyRequestFragment;
import com.app.fixy_worker.fragments.ProfileFragment;
import com.app.fixy_worker.fragments.SelectServiceFragment;
import com.app.fixy_worker.fragments.VerificationFragment;

public class LandingPagerAdapter  extends FragmentPagerAdapter {

    int mNumOfTabs;
    Context mContext;
    HomeFragment homeFragment;
    MyRequestFragment myRequestFragment;
    CoinsFragment coinsFragment;
    ProfileFragment profileFragment;

    public LandingPagerAdapter(FragmentManager fm, Context con) {
        super(fm);
        mContext = con;
        homeFragment = HomeFragment.newInstance(mContext);
        myRequestFragment = MyRequestFragment.newInstance(mContext);
        coinsFragment = CoinsFragment.newInstance(mContext);
        profileFragment = ProfileFragment.newInstance(mContext);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return homeFragment;
            case 1:
                return myRequestFragment;

            case 2:
                return coinsFragment;

            default:
                return profileFragment;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
