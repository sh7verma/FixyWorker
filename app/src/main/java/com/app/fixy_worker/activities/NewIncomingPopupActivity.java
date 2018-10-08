package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterConst;

import butterknife.BindView;

public class NewIncomingPopupActivity extends BaseActivity {

    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @Override
    protected int getContentView() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSPARENT);

        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        return R.layout.new_incoming_popup;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra(InterConst.EXTRA,-1) == InterConst.DISMISS){
            finish();
        }
    }

    @Override
    protected void initUI() {

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_enlarge);
        llMain.startAnimation(anim);

        if (getIntent().getIntExtra(InterConst.EXTRA,-1) == InterConst.DISMISS){
            finish();
        }
    }

    @Override
    protected void initListener() {
        llMain.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_main:
                Intent intent1 = new Intent(this, LandingActivity.class);
                intent1.putExtra(InterConst.EXTRA, InterConst.INCOMING_BROADCAST);
                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                finish();
                break;
        }

    }
}
