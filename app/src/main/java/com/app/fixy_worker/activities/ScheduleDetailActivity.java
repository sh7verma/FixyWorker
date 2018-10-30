package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.RoundedTransformation;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Consts;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleDetailActivity extends BaseActivity {


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
    @BindView(R.id.txt_notes)
    TextView txtNotes;
    @BindView(R.id.txt_requested_time)
    TextView txtRequestedTime;
    @BindView(R.id.txt_accepted_time)
    TextView txtAcceptedTime;
    @BindView(R.id.txt_eta_time)
    TextView txtEtaTime;
    @BindView(R.id.txton_the_way_time)
    TextView txtonTheWayTime;
    @BindView(R.id.txt_confirm_time)
    TextView txtConfirmTime;

    @BindView(R.id.vprogressbar)
    ProgressBar vprogressbar; 
    @BindView(R.id.ll_request)
    LinearLayout llRequest; 
    @BindView(R.id.ll_accepted)
    LinearLayout llAccepted; 
    @BindView(R.id.ll_on_way)
    LinearLayout llOnWay; 
    @BindView(R.id.ll_confirmed)
    LinearLayout llConfirmed; 
    
    @BindView(R.id.txt_confirm)
    TextView txtConfirm;
    @BindView(R.id.txtcancel)
    TextView txtCancel;
    @BindView(R.id.img_track)
    ImageView imgTrack;
    @BindView(R.id.img_call)
    ImageView imgCall;

    @BindView(R.id.view_circle1)
    View viewCircle1;
    @BindView(R.id.view_circle2)
    View viewCircle2;
    @BindView(R.id.view_circle3)
    View viewCircle3;
    @BindView(R.id.view_circle4)
    View viewCircle4;



    int serWidth, serHeight, radius;
    int userWidth, userHeight; 
    int start = 0;
    RequestModel.ResponseBean mData;
    @Override
    protected int getContentView() {
        return R.layout.activity_schedule_detail;
    }

    @Override
    protected void onCreateStuff() {

        txtTitle.setText(R.string.scheduled_detail);
        setData();
    }

    private void setData() {
        txtServiceName.setText(mData.getCategory_name());
        txtServiceCharges.setText(mData.getRequest_price() + " " + mContext.getString(R.string.coins));
        txtServiceId.setText(mContext.getString(R.string.id_) + " " + mData.getId());
        txtUserName.setText(mData.getFullname());
        txtUserLocation.setText(mData.getAddress());
        txtNotes.setText(mData.getAdditional_notes());
        userRating.setRating(Float.parseFloat(mData.getAverage_rating()));

        txtRequestedTime.setText(Consts.getDateTime(mData.getCreated_at()));
        txtAcceptedTime.setText(Consts.getDateTime(mData.getAccepted_time()));
        txtEtaTime.setText(mData.getSchedule_time());
        if (!TextUtils.isEmpty(mData.getOntheway_time())){
            txtonTheWayTime.setText(Consts.getDateTime(mData.getOntheway_time()));
        }
        if (!TextUtils.isEmpty(mData.getCompleted_time())){
            txtConfirmTime.setText(Consts.getDateTime(mData.getCompleted_time()));
        }

        String status = mData.getRequest_status();
        if (status.equalsIgnoreCase(InterConst.ACCEPT_REQUEST)) {
            txtConfirm.setText(getString(R.string.on_the_way));
            start =25;
            setView(start);
        } else if (status.equalsIgnoreCase(InterConst.ON_THE_WAY)) {
            txtConfirm.setText(getString(R.string.confirm));
            imgTrack.setVisibility(View.VISIBLE);
            start =50;
            setView(start);
        } else if (status.equalsIgnoreCase(InterConst.CONFRIM)) {
            txtConfirm.setText(getString(R.string.complete));
            start =75;
            setView(start);
        }
        if (!TextUtils.isEmpty(mData.getCategory_pic())) {
            Picasso.get()
                    .load(mData.getCategory_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(serWidth, serHeight)
                    .into(imgService);
        }
        if (!TextUtils.isEmpty(mData.getProfile_pic())) {
            Picasso.get()
                    .load(mData.getProfile_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(userWidth, userHeight)
                    .into(imgUserImg);
        }
    }

    @Override
    protected void initUI() {

        mData = getIntent().getParcelableExtra(InterConst.EXTRA);

        serWidth = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        serHeight = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        userWidth = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        userHeight = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        radius = (int) mContext.getResources().getDimension(R.dimen._10sdp);
    }

    @Override
    protected void initListener() {
        txtConfirm.setOnClickListener(this);
        txtEtaTime.setOnClickListener(this);

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_confirm:
                String status = mData.getRequest_status();
                if (status.equalsIgnoreCase(InterConst.ACCEPT_REQUEST)) {
                    hitUpdateStatusApi( InterConst.ON_THE_WAY);
                } else if (status.equalsIgnoreCase(InterConst.ON_THE_WAY)) {
                    hitUpdateStatusApi(InterConst.CONFRIM);
                } else if (status.equalsIgnoreCase(InterConst.CONFRIM)) {
                    hitUpdateStatusApi(InterConst.COMPLETE);
                }
                break;
            case R.id.txt_eta_time:
                showCustomSnackBar(llAccepted,getString(R.string.arrival_time),getString(R.string.eta_time_mess));

                break;
        }
    }


    private void hitUpdateStatusApi( String status) {
        ApiInterface apiInterface = RetrofitClient.getInstance();
//    request_status:(1 for accept, -1 for decline, 2 for on the way, 3 for confirm)
        showProgress();
        Call<RequestModel> call = apiInterface.update_request_status(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""), mData.getId(),mData.getRequest_price(), "", status);
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                hideProgress();
                if (response.body().getResponse() != null && response.body().getCode() == InterConst.SUCCESS_RESULT) {

                    mData = response.body().getResponse().get(0);
                     setData();
//                    mAdapter = new NewRequestAdapter(mContext, click, mList);
//                    rvPast.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                t.printStackTrace();
                hideProgress();
            }
        });
    }
    @Override
    public void onBackPressed() {

        setResult(RESULT_OK,new Intent());
        finish();
        overridePendingTransition(R.anim.slide_right, R.anim.slide_out_right);
    }

    @OnClick(R.id.ic_back)
    void back() {
        onBackPressed();
    }
    private void setView( int start) {
        switch (start) {
            case 0:
                showPending();
                break;
            case 25:
                showAccepted();
                break;
            case 50:
                showOnWay();
                break;
            case 75:
                showConfirm();
                break;
        }
    }
    private void showPending() {
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(R.dimen._3sdp, 0);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        vprogressbar.setLayoutParams(relativeLayout);
        llRequest.setVisibility(View.VISIBLE);
        llAccepted.setVisibility(View.GONE);
        llOnWay.setVisibility(View.GONE);
        llConfirmed.setVisibility(View.GONE);

        viewCircle1.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle2.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_oval));
        viewCircle3.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle4.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
    }
    private void showAccepted() {
        int w = (int) mContext.getResources().getDimension(R.dimen._3sdp);
        int h = (int) mContext.getResources().getDimension(R.dimen._51sdp);
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(w, h);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        vprogressbar.setLayoutParams(relativeLayout);
        llRequest.setVisibility(View.VISIBLE);
        llAccepted.setVisibility(View.VISIBLE);
        llOnWay.setVisibility(View.GONE);
        llConfirmed.setVisibility(View.GONE);

        viewCircle1.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle2.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_oval));
        viewCircle3.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle4.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
    }

    private void showOnWay() {
        int w = (int) mContext.getResources().getDimension(R.dimen._3sdp);
        int h = (int) mContext.getResources().getDimension(R.dimen._102sdp);
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(w, h);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        vprogressbar.setLayoutParams(relativeLayout);
        llRequest.setVisibility(View.VISIBLE);
        llAccepted.setVisibility(View.VISIBLE);
        llOnWay.setVisibility(View.VISIBLE);
        llConfirmed.setVisibility(View.GONE);

        viewCircle1.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle2.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle3.setBackground(ContextCompat.getDrawable(this,R.drawable.yellow_oval));
        viewCircle4.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
    }

    private void showConfirm() {
        int w = (int) mContext.getResources().getDimension(R.dimen._3sdp);
        int h = (int) mContext.getResources().getDimension(R.dimen._155sdp);
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(w, h);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        vprogressbar.setLayoutParams(relativeLayout);
        llRequest.setVisibility(View.VISIBLE);
        llAccepted.setVisibility(View.VISIBLE);
        llOnWay.setVisibility(View.VISIBLE);
        llConfirmed.setVisibility(View.VISIBLE);

        viewCircle1.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle2.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle3.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
        viewCircle4.setBackground(ContextCompat.getDrawable(this,R.drawable.circle_green));
    }
}
