package com.app.fixy_worker.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.utils.Connection_Detector;
import com.app.fixy_worker.utils.Utils;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog implements InterfacesCall.IndexClick{
    private int contentView;
    private Context context;
    private Typeface typefaceMedium,typefaceRegular,typefaceBold;
    private Utils utils;
    private int mWidth,mHeight;
    InterfacesCall.IndexClick indexClick;
    private InterfacesCall.IndexClick interfaceInstance;

    public abstract InterfacesCall.IndexClick getInterfaceInstance();


    public interface DialogClick{
        void click(int pos);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        setContentView(getContentView());
        context = getContext();
        indexClick = getInterfaceInstance();
        ButterKnife.bind(this);
        utils = new Utils(context);
        getDefaults();
        onCreateStuff();
        wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(wmlp);
    }

    public boolean connectedToInternet() {
        if ((new Connection_Detector(context)).isConnectingToInternet())
            return true;
        else
            return false;
    }

    protected abstract void onCreateStuff();

    public abstract int getContentView();

    protected void getDefaults() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        mWidth = displayMetrics.widthPixels;
        mHeight = displayMetrics.heightPixels;
        Log.e("Height = ", mHeight + " width " + mWidth);
        utils.setInt("width", mWidth);
        utils.setInt("height", mHeight);
        typefaceMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf");
        typefaceBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Bold.ttf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Regular.ttf");

    }
    Snackbar mSnackbar;

    protected void showInternetAlert(View view) {
        mSnackbar = Snackbar.make(view, "Internet connection not available!", Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }
}
