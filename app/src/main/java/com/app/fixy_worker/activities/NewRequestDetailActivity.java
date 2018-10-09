package com.app.fixy_worker.activities;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.RoundedTransformation;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.RequestModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class NewRequestDetailActivity extends BaseActivity {

    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.txt_title)
    TextView txtTitle; 
    
    @BindView(R.id.img_service)
    ImageView imgService;
    @BindView(R.id.img_user_img)
    ImageView imgUserImg;
    @BindView(R.id.txt_service_name)
    TextView txtServiceName;
    @BindView(R.id.txt_service_charges)
    TextView txtServiceCharges;
    @BindView(R.id.txt_service_id)
    TextView txtServiceId;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.txt_user_rating)
    RatingBar userRating;
    @BindView(R.id.txt_user_location)
    TextView txtUserLocation;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.txt_time_counter)
    TextView txtTimeCounter;
    @BindView(R.id.txt_description)
    TextView txtDescription;
    @BindView(R.id.txt_otp)
    TextView txtOtp;
    RequestModel.ResponseBean mData;

    int serWidth, serHeight, radius;
    int userWidth, userHeight;
    @Override
    protected int getContentView() {
        return R.layout.activity_new_request_detail;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {
        mData = getIntent().getParcelableExtra(InterConst.EXTRA);

        serWidth = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        serHeight = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        userWidth = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        userHeight = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        radius = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        txtTitle.setText(R.string.booking_detail);
    }

    @Override
    protected void initListener() {
        txtServiceName.setText(mData.getCategory_name());
        txtServiceCharges.setText(mData.getRequest_price());
        txtServiceId.setText(mData.getId());
        txtUserName.setText(mData.getFullname());
        txtTime.setText(mData.getCreated_at());
        txtUserLocation.setText(mData.getAddress());
        txtOtp.setText(mData.getRequest_otp());
        txtDescription.setText(mData.getOffer_description());
//        userRating.setProgress(Integer.parseInt(mData.getAverage_rating()));
        if (!TextUtils.isEmpty(mData.getCategory_pic())) {
            Picasso.get()
                    .load(mData.getCategory_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(serWidth, serHeight)
                    .into(imgService);
        }
        if (!TextUtils.isEmpty(mData.getProfile_pic())) {
            Picasso.get()
                    .load(mData.getCategory_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(userWidth, userHeight)
                    .into(imgUserImg);
        }
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {

        finish();
        overridePendingTransition(R.anim.slide_right, R.anim.slide_out_right);
    }

    @OnClick(R.id.ic_back)
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.ic_info)
    void infoClick() {

        showCustomSnackBar(llMain, getString(R.string.otp), getString(R.string.provide_otp_to_worker));
    }
}
