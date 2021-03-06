package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.fixy_worker.fragments.AdsDetailFormFragment;
import com.app.fixy_worker.fragments.PostServiceFragment;
import com.app.fixy_worker.models.LoginModel;

import java.util.ArrayList;

public class PostAdAdapter extends FragmentPagerAdapter {

    public static PostServiceFragment postServiceFragment;
    public static AdsDetailFormFragment adsDetailFragment;
    public PostAdAdapter(FragmentManager fm, Context mcon, ArrayList<LoginModel.ResponseBean.SelectedServicesBean> serviceList) {
        super(fm);
        postServiceFragment = PostServiceFragment.newInstance(mcon,serviceList);
        adsDetailFragment =   AdsDetailFormFragment.newInstance(mcon);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return postServiceFragment;
            default:
                return adsDetailFragment;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
