package com.app.fixy_worker.activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.LandingPagerAdapter;
import com.app.fixy_worker.customviews.CustomViewPager;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.receivers.IncomingRequestReceiver;
import com.app.fixy_worker.service.JobDispatcherService;
import com.app.fixy_worker.service.NewRequestService;
import com.app.fixy_worker.utils.Consts;

import butterknife.BindView;

public class LandingActivity extends BaseActivity {

    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.txt_home)
    TextView txtHome;
    @BindView(R.id.view_home)
    View viewHome;

    @BindView(R.id.ll_bookings)
    LinearLayout llBookings;
    @BindView(R.id.img_bookings)
    ImageView imgBookings;
    @BindView(R.id.txt_bookings)
    TextView txtBookings;
    @BindView(R.id.view_bookings)
    View viewBookings;

    @BindView(R.id.ll_coins)
    LinearLayout llCoins;
    @BindView(R.id.img_coins)
    ImageView imgCoins;
    @BindView(R.id.txt_coins)
    TextView txtCoins;
    @BindView(R.id.view_coins)
    View viewCoins;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;

    @BindView(R.id.ll_profile)
    LinearLayout llProfile;
    @BindView(R.id.img_profile)
    ImageView imgProfile;
    @BindView(R.id.txt_profile)
    TextView txtProfile;
    @BindView(R.id.view_profile)
    View viewProfile;

    // Current fragment selected
    int mViewSelection = Consts.FRAG_NULL;
    private LandingPagerAdapter adapter;
    IncomingRequestReceiver requestReceiver = new IncomingRequestReceiver();

    @Override
    protected int getContentView() {
        return R.layout.activity_landing;
    }

    @Override
    protected void onCreateStuff() {

        adapter = new LandingPagerAdapter(getSupportFragmentManager(), mContext);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
        viewPager.disableScroll(true);
        viewPager.setCurrentItem(0, true);
        loadFragment(Consts.FRAG_HOME);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        loadFragment(Consts.FRAG_HOME);
                        utils.setInt(InterConst.ON_BOOKING, InterConst.ZERO);
                        break;
                    case 1:
                        loadFragment(Consts.FRAG_BOOKINGS);
                        utils.setInt(InterConst.ON_BOOKING, InterConst.ONE);
                        break;
                    case 2:
                        loadFragment(Consts.FRAG_COINS);
                        utils.setInt(InterConst.ON_BOOKING, InterConst.ZERO);
                        break;
                    case 3:
                        loadFragment(Consts.FRAG_PROFILE);
                        utils.setInt(InterConst.ON_BOOKING, InterConst.ZERO);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        callService();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getStringExtra(InterConst.EXTRA).equals(InterConst.INCOMING_BROADCAST_OPEN_POPUP)) {
            viewPager.setCurrentItem(1);
            // call in myRequestFragment
            sendBroadcast(new Intent(InterConst.NEW_REQUEST_HIT_API_BROADCAST));
        }
    }

    @Override
    protected void initUI() {


    }

    @Override
    protected void initListener() {
        llHome.setOnClickListener(this);
        llBookings.setOnClickListener(this);
        llCoins.setOnClickListener(this);
        llProfile.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }


    public void loadFragment(int selected) {

        hideKeyboard(this);

        imgHome.setImageResource(R.mipmap.ic_home);
        txtHome.setTextColor(getContext().getResources().getColor(R.color.grey_text));
        viewHome.setBackgroundColor(getContext().getResources().getColor(R.color.white));


        imgBookings.setImageResource(R.mipmap.ic_booking);
        txtBookings.setTextColor(getContext().getResources().getColor(R.color.grey_text));
        viewBookings.setBackgroundColor(getContext().getResources().getColor(R.color.white));

        imgCoins.setImageResource(R.mipmap.ic_coins);
        txtCoins.setTextColor(getContext().getResources().getColor(R.color.grey_text));
        viewCoins.setBackgroundColor(getContext().getResources().getColor(R.color.white));

        imgProfile.setImageResource(R.mipmap.ic_profile);
        txtProfile.setTextColor(getContext().getResources().getColor(R.color.grey_text));
        viewProfile.setBackgroundColor(getContext().getResources().getColor(R.color.white));

        if (selected == Consts.FRAG_HOME) {
            imgHome.setImageResource(R.mipmap.ic_home_a);
            txtHome.setTextColor(getContext().getResources().getColor(R.color.app_color));
            viewHome.setBackgroundColor(getContext().getResources().getColor(R.color.app_color));
        } else if (selected == Consts.FRAG_BOOKINGS) {
            imgBookings.setImageResource(R.mipmap.ic_booking_a);
            txtBookings.setTextColor(getContext().getResources().getColor(R.color.app_color));
            viewBookings.setBackgroundColor(getContext().getResources().getColor(R.color.app_color));
        } else if (selected == Consts.FRAG_COINS) {
            imgCoins.setImageResource(R.mipmap.ic_coins_a);
            txtCoins.setTextColor(getContext().getResources().getColor(R.color.app_color));
            viewCoins.setBackgroundColor(getContext().getResources().getColor(R.color.app_color));
        } else if (selected == Consts.FRAG_PROFILE) {
            imgProfile.setImageResource(R.mipmap.ic_profile_s);
            txtProfile.setTextColor(getContext().getResources().getColor(R.color.app_color));
            viewProfile.setBackgroundColor(getContext().getResources().getColor(R.color.app_color));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                viewPager.setCurrentItem(0, true);

                break;
            case R.id.ll_bookings:
                viewPager.setCurrentItem(1, true);
                sendBroadcast(new Intent(InterConst.NEW_REQUEST_HIT_API_BROADCAST));


                break;
            case R.id.ll_coins:
                viewPager.setCurrentItem(2, true);

                break;
            case R.id.ll_profile:
                viewPager.setCurrentItem(3, true);

                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(requestReceiver,new IntentFilter(InterConst.INCOMING_BROADCAST_OPEN_POPUP));
    }

    void callService() {
// get job api service
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            ComponentName jobService = new ComponentName(getPackageName(), JobDispatcherService.class.getName());
            JobInfo jobInfo;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                jobInfo = new JobInfo.Builder(1, jobService).setMinimumLatency(5000).build();
            } else {
                jobInfo = new JobInfo.Builder(1, jobService).setPeriodic(5000).build();
            }
            jobScheduler.schedule(jobInfo);
        } else {
                startService(new Intent(getApplicationContext(), NewRequestService.class));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(requestReceiver);
    }
}
