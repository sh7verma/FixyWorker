package com.app.fixy.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.app.fixy.R;

import butterknife.BindView;
import butterknife.OnClick;

public class OtpActivity extends BaseActivity {


    @BindView(R.id.ed_first)
    EditText edFirst;
    @BindView(R.id.ed_second)
    EditText edSecond;
    @BindView(R.id.ed_third)
    EditText edThird;
    @BindView(R.id.ed_fourth)
    EditText edFourth;

    @BindView(R.id.ll_next)
    LinearLayout llNext;

    @Override
    protected int getContentView() {
        return R.layout.activity_otp;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {
        edFirst.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
        edSecond.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
        edThird.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
        edFourth.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf"));
    }

    @Override
    protected void initListener() {
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
        edFourth.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (edFourth.getText().toString().length() == 1) {
                    if (edFirst.getText().toString().length() > 0 && edSecond.getText().toString().length() > 0
                            && edThird.getText().toString().length() > 0 && edFourth.getText().toString().length() > 0) {
                        checkOTP();
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

        edFirst.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                }
                return false;
            }
        });

        edSecond.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if (edSecond.getText().toString().length() == 0) {
                        edFirst.setSelection(edFirst.getText().toString().length());
                        edFirst.requestFocus();
                    }
                }
                return false;
            }
        });

        edThird.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if (edThird.getText().toString().length() == 0) {
                        edSecond.setSelection(edSecond.getText().toString().length());
                        edSecond.requestFocus();
                    }
                }
                return false;
            }
        });

        edFourth.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    if (edFourth.getText().toString().length() == 0) {
                        edThird.setSelection(edThird.getText().toString().length());
                        edThird.requestFocus();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected Context getContext() {
        return this;
    }

    private void checkOTP() {

    }

    @OnClick(R.id.ll_next)
    void next() {
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}
