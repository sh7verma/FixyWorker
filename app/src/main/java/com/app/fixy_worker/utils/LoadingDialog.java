package com.app.fixy_worker.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.ChromeFloatingProgressDialog;


public class LoadingDialog {

    static LoadingDialog mInstance;
    Dialog mProgress;

    public static void initLoader() {
        if (mInstance == null)
            mInstance = new LoadingDialog();
    }

    public static LoadingDialog getLoader() {
        if (mInstance != null)
            return mInstance;
        else {
            mInstance = new LoadingDialog();
            return mInstance;
        }
    }
    public void dismissLoader() {
        try {
            if (mProgress != null) {
                mProgress.cancel();
                mProgress = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    Drawable progressDrawable = null;
    private int[] getProgressDrawableColors(Context context) {
        int[] colors = new int[4];
        colors[0] = context.getResources().getColor(R.color.colorPrimary);
        colors[1] = context.getResources().getColor(R.color.green_light);
        colors[2] = context.getResources().getColor(R.color.yellow);
        colors[3] = context.getResources().getColor(R.color.purple);
        return colors;
    }
    public void showLoader(Context con) {
        progressDrawable = new ChromeFloatingProgressDialog.Builder(con)
                .colors(getProgressDrawableColors(con))
                .build();
        if (mProgress != null) {
            mProgress.cancel();
        }
        mProgress = new Dialog(con);
        mProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wmlp = mProgress.getWindow().getAttributes();

        wmlp.gravity = Gravity.CENTER ;
        mProgress.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mProgress.setCancelable(false);
        View view = LayoutInflater.from(con).inflate(R.layout.activity_loading_dialog,null);
        mProgress.setContentView(view);
        ProgressBar progressBar = view.findViewById(R.id.google_progress);

        Rect bounds = progressBar.getIndeterminateDrawable().getBounds();
        progressBar.setIndeterminateDrawable(progressDrawable);
        progressBar.getIndeterminateDrawable().setBounds(bounds);
        mProgress.show();
    }
}
