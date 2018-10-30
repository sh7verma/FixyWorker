package com.app.fixy_worker.dialogs;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Validations;

import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmOtpDialog extends BaseDialog {


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
    private  ListDialog.CallBack clicks;
    Context mContext;

    public ConfirmOtpDialog(@NonNull Context context, int themeResId, ListDialog.CallBack click) {
        super(context, themeResId);
        clicks = click;
        mContext = context;
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wmlp);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


    @Override
    public InterfacesCall.IndexClick getInterfaceInstance() {
        return this;
    }


    @Override
    protected void onCreateStuff() {


        edFirst.setTypeface(typefaceMedium);
        edSecond.setTypeface(typefaceMedium);
        edThird.setTypeface(typefaceMedium);
        edFourth.setTypeface(typefaceMedium);

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
    private void checkOTP() {
        if (connectedToInternet()) {
            llNext.setEnabled(true);
//            hitOTPapi();
        } else {
            Toast.makeText(getContext(), R.string.internet, Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.ll_next)
    void next() {
        Consts.hideKeyboard((Activity) mContext);
        if (edFirst.getText().toString().length() < 1) {
            Validations.checkOTPValidation(mContext, edFirst);
        } else if (edFirst.getText().toString().length() < 1) {
            Validations.checkOTPValidation(mContext, edSecond);
        } else if (edThird.getText().toString().length() < 1) {
            Validations.checkOTPValidation(mContext, edThird);
        } else if (edFourth.getText().toString().length() < 1) {
            Validations.checkOTPValidation(mContext, edFourth);
        } else {
//        hitOTPapi();
        }

    }

    @Override
    public int getContentView() {
        return R.layout.activity_confirm_otp_dialog;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void clickIndex(int pos) {

    }
}
