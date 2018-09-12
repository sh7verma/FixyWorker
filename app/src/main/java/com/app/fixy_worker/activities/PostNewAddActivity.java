package com.app.fixy_worker.activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;
import com.app.fixy_worker.adapters.PostAdAdapter;
import com.app.fixy_worker.customviews.CirclePageIndicator;
import com.app.fixy_worker.customviews.CustomViewPager;

import butterknife.BindView;

public class PostNewAddActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_done)
    Button txtDone;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    private PostAdAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_post_new_add;
    }

    @Override
    protected void onCreateStuff() {
        txtTitle.setText(getString(R.string.post_new_add));
        adapter = new PostAdAdapter(getSupportFragmentManager(),mContext);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        viewPager.disableScroll(false);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {
        txtDone.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_done:
                viewPager.setCurrentItem(1,true);
                break;
        }
    }
}
