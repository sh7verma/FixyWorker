package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;
import com.app.fixy_worker.customviews.CustomViewPager;
import com.app.fixy_worker.fragments.CreateProfileFragment;
import com.app.fixy_worker.fragments.VerificationFragment;
import com.app.fixy_worker.utils.Animations;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateProfileActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_done)
    TextView txtDone;
    @BindView(R.id.txt_step_one)
    TextView txtStepOne;
    @BindView(R.id.txt_step_two)
    TextView txtStepTwo;
    @BindView(R.id.txt_step_three)
    TextView txtStepThree;
    @BindView(R.id.vprogressbar)
    ProgressBar vprogressbar;
    private int currentPosition;
    private CreateProfilePagerAdapter adapter;
    private Runnable runnable;
    private int start=0;
    private int end=0;
    private Handler handler =new Handler();
    private boolean isFilledFirstForm = false;
    private boolean isFilledSecondForm = false;
    private boolean isFilledThirdForm = false;

    @Override
    protected int getContentView() {
        return R.layout.activity_create_profile;
    }

    @Override
    protected void onCreateStuff() {
        txtTitle.setText(getString(R.string.create_profile));
        txtStepOne.setBackground(getResources().getDrawable(R.drawable.green_oval));
        adapter = new CreateProfilePagerAdapter(getSupportFragmentManager(), 3,mContext);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        viewPager.disableScroll(true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            currentPosition = position;
            switch (position){
                case 0:
                    viewPager.setCurrentItem(0);
                    txtDone.setText(getString(R.string.next));
                    break;
                case 1:
                    viewPager.setCurrentItem(1);
                    txtDone.setText(getString(R.string.next));
                    break;
                case 2:
                    viewPager.setCurrentItem(2);
                    txtDone.setText(getString(R.string.done));
                    break;
            }
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

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.ic_back)
    void back() {
        if (currentPosition == 2 ){
            clickSecond(false);
        }
        else if (currentPosition == 1){
            clickFirst(false);
        }
        else if (currentPosition == 0){
            finish();
        }
    }
    @OnClick(R.id.txt_step_one)
    void stepOne() {
        clickFirst(false);
        Animations.AnimatedClick(this,txtStepOne);
    }

    @OnClick(R.id.txt_step_two)
    void stepTwo() {
        if (isFilledFirstForm) {
            clickSecond(false);
            Animations.AnimatedClick(this,txtStepTwo);
        }

    }

    @OnClick(R.id.txt_step_three)
    void stepThree() {
        if (isFilledSecondForm) {
            clickThird(false);
            Animations.AnimatedClick(this,txtStepThree);
        }

    }
    @OnClick(R.id.txt_done)
    void done() {
        if (currentPosition == 0 &&  ((CreateProfileFragment)adapter.getItem(0)).checkValidation()){
//        if (currentPosition == 0 ){//next
            clickSecond(true);
            isFilledFirstForm = true;
        }
        else if (currentPosition == 1 ){//next
            clickThird(true);
            isFilledSecondForm = true;
        }
        else if (currentPosition == 2 &&  ((VerificationFragment)adapter.getItem(2)).checkValidation()){//done
            showToast("done");
        }
    }


    @Override
    public void onClick(View view) {

    }

void clickFirst(boolean animate){

    viewPager.setCurrentItem(0);
        if (animate){
            start =0;
            end = 2;
            runProgressbar();
        }
        else {
            vprogressbar.setProgress(0);
            txtStepOne.setBackground(getResources().getDrawable(R.drawable.green_oval));
            txtStepTwo.setBackground(getResources().getDrawable(R.drawable.grey_oval));
            txtStepThree.setBackground(getResources().getDrawable(R.drawable.grey_oval));
        }
}
void clickSecond(boolean animate){

    viewPager.setCurrentItem(1);
        if (animate){
            start =0;
            end = 25;
            runProgressbar();
        }
        else {
            vprogressbar.setProgress(25);
            txtStepOne.setBackground(getResources().getDrawable(R.drawable.green_oval));
            txtStepTwo.setBackground(getResources().getDrawable(R.drawable.green_oval));
            txtStepThree.setBackground(getResources().getDrawable(R.drawable.grey_oval));
        }
}
void clickThird(boolean animate){

    viewPager.setCurrentItem(2);
        if (animate){
            start =25;
            end = 50;
            runProgressbar();
        }
        else {
            vprogressbar.setProgress(50);
            txtStepOne.setBackground(getResources().getDrawable(R.drawable.green_oval));
            txtStepTwo.setBackground(getResources().getDrawable(R.drawable.green_oval));
            txtStepThree.setBackground(getResources().getDrawable(R.drawable.green_oval));
        }
}
    private void runProgressbar() {
        runnable = new Runnable() {
            @Override
            public void run() {
                start++;

                vprogressbar.setProgress(start);

                if (start > 0 && start < 2){
                    txtStepOne.setBackground(getResources().getDrawable(R.drawable.green_oval));
                    txtStepTwo.setBackground(getResources().getDrawable(R.drawable.grey_oval));
                    txtStepThree.setBackground(getResources().getDrawable(R.drawable.grey_oval));
                }
                else if (start > 24 && start < 27){
                    txtStepTwo.setBackground(getResources().getDrawable(R.drawable.green_oval));
                    txtStepTwo.setBackground(getResources().getDrawable(R.drawable.green_oval));
                    txtStepThree.setBackground(getResources().getDrawable(R.drawable.grey_oval));
                }
                else  if (start > 49 && start <= end){
                    txtStepThree.setBackground(getResources().getDrawable(R.drawable.green_oval));
                    txtStepTwo.setBackground(getResources().getDrawable(R.drawable.green_oval));
                    txtStepThree.setBackground(getResources().getDrawable(R.drawable.green_oval));
                }
                if (start < end) {

                    handler.postDelayed(runnable, 10);
                } else {
                    handler.removeCallbacks(runnable);
                }
            }
        };
        handler.postDelayed(runnable, 10);
    }
}
