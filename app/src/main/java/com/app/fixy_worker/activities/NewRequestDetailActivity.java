package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.RoundedTransformation;
import com.app.fixy_worker.dialogs.AcceptedTimeDialog;
import com.app.fixy_worker.dialogs.ListDialog;
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
    @BindView(R.id.ic_info_time)
    ImageView icInfoTime;
    @BindView(R.id.txt_decline)
    TextView txtDecline;
    @BindView(R.id.txt_accept)
    TextView txtAccept;
    RequestModel.ResponseBean mData;

    int serWidth, serHeight, radius;
    int userWidth, userHeight;

    private Handler timeHandler = new Handler();
    private Runnable timeRunnable;
    private long timeMili;

    @Override
    protected int getContentView() {
        return R.layout.activity_new_request_detail;
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
    protected void onCreateStuff() {
        txtServiceName.setText(mData.getCategory_name());
        txtServiceCharges.setText(mData.getRequest_price() + " " + mContext.getString(R.string.coins));
        txtServiceId.setText(mContext.getString(R.string.id_) + " " + mData.getId());
        txtUserName.setText(mData.getFullname());
        txtTime.setText(mData.getCreated_at());
        txtUserLocation.setText(mData.getAddress());
        txtOtp.setText(mContext.getString(R.string.otp) + " " + mData.getRequest_otp());
        txtDescription.setText(mData.getAdditional_notes());
        userRating.setRating(Float.parseFloat(mData.getAverage_rating()));
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
        setHandler();
    }

    @Override
    protected void initListener() {
        icInfoTime.setOnClickListener(this);
        txtAccept.setOnClickListener(this);
        txtDecline.setOnClickListener(this);
    }

    private void setHandler() {
        timeHandler.removeCallbacks(timeRunnable);
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                timeMili = Consts.differnceServerToCurrentTime(mData.getExpired_time());
                txtTimeCounter.setText(Consts.convertMilisecondtoTime(timeMili));

                timeHandler.postDelayed(timeRunnable, 1000);
            }
        };
        timeHandler.postDelayed(timeRunnable, 1000);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_info_time:
                showCustomSnackBar(llMain, getString(R.string.expired_time), getString(R.string.expired_time_mess));
                break;
            case R.id.txt_accept:
                AcceptedTimeDialog acceptedTimeDialog = new AcceptedTimeDialog(getContext(), R.style.DialogSlideAnim, new ListDialog.CallBack() {

                    @Override
                    public void click(String time) {
                        Log.e("time",""+time);
                        hitAcceptRequestApi(time,mData);
                    }
                },mData);

                acceptedTimeDialog.show();
                break;
            case R.id.txt_decline:
                hitDeclineRequestApi(mData);
                break;
        }
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


    public void hitAcceptRequestApi(String time, RequestModel.ResponseBean responseBean) {
        ApiInterface apiInterface = RetrofitClient.getInstance();
//    request_status:(1 for accept, -1 for decline, 2 for on the way, 3 for confirm)
        showProgress();
        Call<RequestModel> call = apiInterface.update_request_status(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""),responseBean.getId(),responseBean.getRequest_price(),time,InterConst.ACCEPT_REQUEST);
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                hideProgress();
                if (response.body().getResponse() != null && response.body().getCode() == InterConst.SUCCESS_RESULT){
                    setResult(RESULT_OK,new Intent());
                    finish();

                }
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                t.printStackTrace();
                hideProgress();
            }
        });
    }
    public void hitDeclineRequestApi(RequestModel.ResponseBean responseBean) {
        ApiInterface apiInterface = RetrofitClient.getInstance();
//    request_status:(1 for accept, -1 for decline, 2 for on the way, 3 for confirm)
        showProgress();
        Call<RequestModel> call = apiInterface.update_request_status(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""),responseBean.getId(),responseBean.getRequest_price(),"",InterConst.DECLINE_REQUEST);
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {
                hideProgress();
                if (response.body().getResponse() != null && response.body().getCode() == InterConst.SUCCESS_RESULT){

                    setResult(RESULT_OK,new Intent());
                    finish();
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
}
