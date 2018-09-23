package com.app.fixy_worker.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Dialogs;
import com.app.fixy_worker.utils.Validations;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterNumberActivity extends BaseActivity {

    private static final int PERMISSIONS_CODE = 1;
    @BindView(R.id.ll_next)
    LinearLayout llNext;

    @BindView(R.id.ed_number)
    MaterialEditText edNumber;
    @BindView(R.id.txt_country_code)
    TextView txtCountryCode; 
    String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE};

    @Override
    protected int getContentView() {
        return R.layout.activity_enter_number;
    }

    @Override
    protected void onCreateStuff() {
        if (mPermission.checkPermissionsList(permissions)){
            getImeiNumber();
        }
        else {
            mPermission.openPermissions(permissions,InterConst.PHONE_STATE,PERMISSIONS_CODE,R.string.read_phone_state);
        }
    }

    @Override
    protected void initUI() {
        edNumber.setTypeface(typefaceMedium);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_CODE:
                boolean flag = false;
                for (int i=0 ; i<grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        flag = true;
                    }
                    else {
                        flag = false;
                    }
                    if (flag){
                        getImeiNumber();
                    }
                    else {
                        mPermission.openPermissions(permissions,InterConst.PHONE_STATE,PERMISSIONS_CODE,R.string.read_phone_state);

                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void hitUserSignup() {
        if (connectedToInternet()) {

            ApiInterface apiInterface = RetrofitClient.getInstance();

            Call<LoginModel> call = apiInterface.create_user(txtCountryCode.getText().toString(),
                    edNumber.getText().toString().trim(), InterConst.USER_TYPE, InterConst.APPLICATION_MODE, InterConst.PLATFORM_TYPE,
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {

                        utils.setString(InterConst.ACCESS_TOKEN, response.body().getResponse().getAccess_token());
                        utils.setString(InterConst.USER_ID, response.body().getResponse().getId());
                        utils.setString(InterConst.USER_NAME, response.body().getResponse().getFullname());
                        utils.setString(InterConst.PROFILE_STATUS, response.body().getResponse().getProfile_status());
                        utils.setString(InterConst.GENDER, response.body().getResponse().getGender());
                        utils.setString(InterConst.PROFILE_IMAGE, response.body().getResponse().getProfile_pic());
                        utils.setString(InterConst.EMAIL, response.body().getResponse().getEmail());
//                    utils.setString(InterConst.COUNTRY_CODE,response.body().getResponse().ge());
//                    utils.setString(InterConst.PHONE_NUMBER,response.body().getResponse().getp());
                        Intent intent = new Intent(EnterNumberActivity.this, OtpActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    } else if (response.body().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext,llNext, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                t.printStackTrace();
                }
            });
        }
        else {
            showInternetAlert(llNext);
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @OnClick(R.id.ll_next)
    void next() {
        Consts.hideKeyboard(this);
        if (mPermission.checkPermissionsList(permissions)){
            if (Validations.checkPhoneValidation(this,edNumber) && mPermission.checkPermissionsList(permissions)) {
                hitUserSignup();
            }
        }
        else {
            mPermission.openPermissions(permissions,InterConst.PHONE_STATE,PERMISSIONS_CODE,R.string.read_phone_state);
        }

    }


    @Override
    public void onClick(View view) {
    }

    @SuppressLint("MissingPermission")
    void getImeiNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        utils.setString(InterConst.DEVICE_ID,telephonyManager.getDeviceId() );
        /*countryCode = telephonyManager.getNetworkCountryIso().toUpperCase();
        String[] rl = this.getResources().getStringArray(R.array.CountryCodes);

        for (int i = 0; i < rl.length; i++) {
            String[] g = rl[i].split(",");
            if (g[1].trim().equals(countryCode.trim())) {
                countryCode = "+" + g[0];
                if (!TextUtils.isEmpty(countryCode)) {

                    etCode.setText("" + countryCode);
                }
                break;
            }

        }*/

    }

    private void getFirebaseToken() {
        /*FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Firebase Token", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        utils.setString(Consts.REFRESH_TOKEN,token);
                    }
                });*/
    }
}
