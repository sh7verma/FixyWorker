package com.app.fixy_worker.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterfacesCall;

public class UpdateDialog extends BaseDialog {


    @Override
    public InterfacesCall.IndexClick getInterfaceInstance() {
        return this;
    }

    public UpdateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_list_dialog;
    }


    @Override
    public void clickIndex(int pos) {

    }
}
