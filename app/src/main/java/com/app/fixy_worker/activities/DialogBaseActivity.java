package com.app.fixy_worker.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

public abstract  class DialogBaseActivity extends Activity implements View.OnClickListener {
    private int contentView;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        setContentView(getContentView());
        mContext = getContext();
        ButterKnife.bind(this);
        onCreateStuff();

    }

    public abstract void onCreateStuff();
    public abstract int getContentView();
    public abstract Context getContext();
}
