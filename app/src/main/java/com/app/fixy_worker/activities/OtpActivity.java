package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Dialogs;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends BaseActivity {

    public CountDownTimer mTimer;
    public long time;
    @BindView(R.id.ed_first)
    MaterialEditText edFirst;
    @BindView(R.id.ed_second)
    MaterialEditText edSecond;
    @BindView(R.id.ed_third)
    MaterialEditText edThird;
    @BindView(R.id.ed_fourth)
    MaterialEditText edFourth;
    @BindView(R.id.ll_next)
    LinearLayout llNext;

    @BindView(R.id.txt_timer)
    TextView txtTimer;
    @BindView(R.id.ll_call)
    LinearLayout llCall;


    @Override
    protected int getContentView() {
        return R.layout.activity_otp;
    }

    @Override
    protected void onCreateStuff() {
        llNext.setEnabled(false);
    }

    @Override
    protected void initUI() {
        edFirst.setTypeface(typefaceMedium);
        edSecond.setTypeface(typefaceMedium);
        edThird.setTypeface(typefaceMedium);
        edFourth.setTypeface(typefaceMedium);
    }

    @Override
    protected void initListener() {

//        edFirst.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//                if (edFirst.getText().toString().length() == 1) {
//                    edSecond.requestFocus();
//                    edSecond.setEnabled(true);
//                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
//                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
//                        checkOTP();
//                    }
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//        edSecond.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//                Log.d("1", "1");
//                if (edSecond.getText().toString().length() == 1) {
//                    edThird.requestFocus();
//                    edThird.setEnabled(true);
//                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
//                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
//                        checkOTP();
//                    }
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//                Log.d("2", "2");
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//                Log.d("3", "3");
//                if (edSecond.getText().toString().length() == 0) {
//                    edFirst.setSelection(edFirst.getText().toString().length());
//                    edFirst.requestFocus();
//                }
//
//            }
//        });
//        edThird.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//                if (edThird.getText().toString().length() == 1) {
//                    edFourth.requestFocus();
//                    edFourth.setEnabled(true);
//                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
//                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
//                        checkOTP();
//                    }
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//                if (edThird.getText().toString().length() == 0) {
//                    edSecond.setSelection(edSecond.getText().toString().length());
//                    edSecond.requestFocus();
//                }
//
//            }
//        });
//        edFourth.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // TODO Auto-generated method stub
//                if (edFourth.getText().toString().length() == 1) {
//                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
//                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
//                        checkOTP();
//                    }
//                }
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//
//                if (edFourth.getText().toString().length() == 0) {
//                    edThird.setSelection(edThird.getText().toString().length());
//                    edThird.requestFocus();
//                }
//
//            }
//        });

        edFirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edFirst.getText().toString().length() == 1) {
                    edSecond.requestFocus();
                    edSecond.setEnabled(true);
                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
                        checkOTP();
                    }
                } else {
                    if (edFirst.getText().toString().length() > 0) {
                        edFirst.setText(edFirst.getText().toString().substring(0, 1));
                        edSecond.requestFocus();
                        llNext.setEnabled(false);

                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        edSecond.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                Log.d("1", "1");
                if (edSecond.getText().toString().length() == 1) {
                    edThird.requestFocus();
                    edThird.setEnabled(true);
                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
                        checkOTP();
                    }
                } else if (edSecond.getText().toString().length() == 0) {
                    edFirst.setSelection(edFirst.getText().toString().length());
                    edFirst.requestFocus();
                    llNext.setEnabled(false);
                } else {
                    edSecond.setText(edSecond.getText().toString().substring(0, 1));
                    edThird.requestFocus();
                    llNext.setEnabled(false);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                Log.d("2", "2");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                Log.d("3", "3");
            }
        });
        edThird.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edThird.getText().toString().length() == 1) {
                    edFourth.requestFocus();
                    edFourth.setEnabled(true);
                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
                        checkOTP();
                    }
                } else if (edThird.getText().toString().length() == 0) {
                    edSecond.setSelection(edSecond.getText().toString().length());
                    edSecond.requestFocus();
                    llNext.setEnabled(false);

                } else {
                    edThird.setText(edThird.getText().toString().substring(0, 1));
                    edFourth.requestFocus();
                    llNext.setEnabled(false);

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (edThird.getText().toString().length() == 1) {
                    edFourth.requestFocus();
                }
            }
        });
        edFourth.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edFourth.getText().toString().length() == 1) {
                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
                        checkOTP();
                    }
                } else if (edFourth.getText().toString().length() == 0) {
                    edThird.setSelection(edThird.getText().toString().length());
                    edThird.requestFocus();
                    llNext.setEnabled(false);

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void checkOTP() {
        if (connectedToInternet()) {
            llNext.setEnabled(true);
            hitOTPapi();
        } else {
            Toast.makeText(mContext, R.string.internet, Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.ll_next)
    void next() {
        Consts.hideKeyboard(this);
//        if (edFirst.getText().toString().length() < 1) {
//            Validations.checkOTPValidation(this, edFirst);
//        } else if (edFirst.getText().toString().length() < 1) {
//            Validations.checkOTPValidation(this, edSecond);
//        } else if (edThird.getText().toString().length() < 1) {
//            Validations.checkOTPValidation(this, edThird);
//        } else if (edFourth.getText().toString().length() < 1) {
//            Validations.checkOTPValidation(this, edFourth);
//        } else {
        hitOTPapi();
//        }

    }

    private String makeOTP() {
        StringBuilder builder = new StringBuilder();
        builder.append(edFirst.getText().toString());
        builder.append(edSecond.getText().toString());
        builder.append(edThird.getText().toString());
        builder.append(edFourth.getText().toString());
        return builder.toString();
    }

    public void hitOTPapi() {

        if (connectedToInternet()) {
            showProgress();
            Call<LoginModel> call = RetrofitClient.getInstance().confirm_otp(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    makeOTP(),
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    hideProgress();
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {

                        utils.setString(InterConst.ACCESS_TOKEN, response.body().getResponse().getAccess_token());
                        utils.setString(InterConst.USER_ID, response.body().getResponse().getId());
                        utils.setString(InterConst.USER_NAME, response.body().getResponse().getFullname());
                        utils.setString(InterConst.PROFILE_STATUS, response.body().getResponse().getProfile_status());
                        utils.setString(InterConst.GENDER, response.body().getResponse().getGender());
                        utils.setString(InterConst.PROFILE_IMAGE, response.body().getResponse().getProfile_pic());
                        utils.setString(InterConst.EMAIL, response.body().getResponse().getEmail());
                        utils.setString(InterConst.COUNTRY_CODE, response.body().getResponse().getCountry_code());
                        utils.setString(InterConst.PHONE_NUMBER, response.body().getResponse().getPhone_number());
                        utils.setString(InterConst.NUMBER_VERIFY, response.body().getResponse().getNumber_verify());
                        utils.setString(InterConst.REFFERAL_CODE, response.body().getResponse().getRefferal_code());
                        utils.setString(InterConst.NEW_USER_COIN, response.body().getResponse().getCoins());
                        utils.setString(InterConst.REFFERAL_COIN, response.body().getResponse().getRefferal_coins());
                        utils.setString(InterConst.CITY_NAME, response.body().getResponse().getCity());
                        utils.setString(InterConst.CITY_ID, response.body().getResponse().getCity_id());
                        Intent intent;
                        if (Integer.parseInt(utils.getString(InterConst.PROFILE_STATUS, "0")) == InterConst.PROFILE_VERIFY) {

                            intent = new Intent(OtpActivity.this, LandingActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            intent = new Intent(OtpActivity.this, CreateProfileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }
                        overridePendingTransition(R.anim.slide_left, R.anim.slide_left_out);
                    } else if (response.body().getError().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext, llNext, response.body().getError().getMessage());
                    } else if (response.body().getError().getCode() == InterConst.INVALID_ACCESS_TOKEN) {
                        moveToSplash();
                    }
                    else {
                        showSnackBar(txtTimer,response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    hideProgress();
                    t.printStackTrace();
                }
            });
        } else {
            showInternetAlert(llCall);
        }

    }

    @Override
    public void onClick(View view) {

    }
}
