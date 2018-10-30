package com.app.fixy_worker.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.SettingsActivity;
import com.app.fixy_worker.activities.ShowAddressActivity;
import com.app.fixy_worker.customviews.CircleTransform;
import com.app.fixy_worker.interfaces.InterConst;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by Shubham verma on 16-08-2018.
 */

public class ProfileFragment extends BaseFragment {

    @SuppressLint("StaticFieldLeak")
    static ProfileFragment fragment;

    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_settings)
    LinearLayout llSettings;
    @BindView(R.id.txt_email)
    TextView txtEmail;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.txt_gender)
    TextView txtGender;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.txt_referral_code)
    TextView txtReferralCcode;
    @BindView(R.id.img_user_img)
    ImageView imgUserpic;

    public static ProfileFragment newInstance(Context mContext) {
        fragment = new ProfileFragment();
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onCreateStuff() {
        setData();
    }

    private void setData() {
        txtEmail.setText(utils.getString(InterConst.EMAIL,""));
        txtPhone.setText(utils.getString(InterConst.PHONE_NUMBER,""));
        txtUserName.setText(utils.getString(InterConst.USER_NAME,""));
        txtGender.setText(utils.getString(InterConst.GENDER,""));
        txtReferralCcode.setText(utils.getString(InterConst.REFFERAL_CODE,""));
        Picasso.get()
                .load(utils.getString(InterConst.PROFILE_IMAGE,""))
                .transform(new CircleTransform())
                .resize((int) (mHeight * 0.13), (int) (mHeight * 0.13))
                .placeholder(R.mipmap.ic_profile)
                .centerCrop(Gravity.TOP)
                .error(R.mipmap.ic_profile).into(imgUserpic, new Callback() {
            @Override
            public void onSuccess() {

                Log.w("", "onError:Sucess code=");
            }

            @Override
            public void onError(Exception e) {
                Log.w("", "onError:failed code=" + e.getMessage());

            }
        });
    }

    @Override
    protected void initListeners() {
        llAddress.setOnClickListener(this);
        llSettings.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_address:
                intent = new Intent(getActivity(), ShowAddressActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
                break;
            case R.id.ll_settings:
                intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
                break;
        }

    }
}
