package com.app.fixy_worker.dialogs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.BaseActivity;
import com.app.fixy_worker.interfaces.InterConst;

import butterknife.BindView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class InternetDialog extends BaseActivity {


    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_mess)
    TextView txtMessage;
    @BindView(R.id.ll_main)
    LinearLayout llMain;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra(InterConst.EXTRA, 0) == InterConst.DISCONNECT) {
            txtTitle.setText(getString(R.string.oopss));
            txtMessage.setText(getString(R.string.internet));
            llMain.setBackground(ContextCompat.getDrawable(this, R.drawable.yellow_bottom_round_corner));

        } else {//connected
            txtTitle.setText(getString(R.string.hureyy));
            txtMessage.setText(getString(R.string.got_internet));
            llMain.setBackground(ContextCompat.getDrawable(this, R.drawable.green_bottom_round_corner));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);

        }
    }

    @Override
    protected int getContentView() {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP;
        wmlp.windowAnimations = R.style.dilog_slide_top_to_down;
        return R.layout.activity_internet_dialog;
    }

    @Override
    protected void initUI() {
        getWindow().setLayout(mWidth, (int) mHeight);
//        getWindow().setLayout(MATCH_PARENT, MATCH_PARENT);
    }

    @Override
    protected void onCreateStuff() {
        if (getIntent().getIntExtra(InterConst.EXTRA, 0) == InterConst.DISCONNECT) {
            txtTitle.setText(getString(R.string.oopss));
            txtMessage.setText(getString(R.string.internet));
            llMain.setBackground(ContextCompat.getDrawable(this, R.drawable.yellow_bottom_round_corner));

        } else {
            txtTitle.setText(getString(R.string.hureyy));
            txtMessage.setText(getString(R.string.got_internet));
            llMain.setBackground(ContextCompat.getDrawable(this, R.drawable.green_bottom_round_corner));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);

        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
    }
}