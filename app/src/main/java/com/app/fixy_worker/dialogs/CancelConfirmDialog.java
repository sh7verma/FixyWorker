package com.app.fixy_worker.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;

import butterknife.BindView;

public class CancelConfirmDialog extends BaseDialog {

    @BindView(R.id.txt_confirm)
    TextView txtConfirm;
    @BindView(R.id.txt_cancel)
    TextView txtCancel;
    Context mContext;
    InterfacesCall.Callback call;

    public CancelConfirmDialog(@NonNull Context context, int themeResId, InterfacesCall.Callback callback) {
        super(context, themeResId);
        mContext = context;
        call = callback;
    }

    @Override
    public InterfacesCall.IndexClick getInterfaceInstance() {
        return this;
    }

    @Override
    protected void onCreateStuff() {
        txtCancel.setOnClickListener(this);
    }

    @Override
    public int getContentView() {
        return R.layout.dialog_cancel_confirm;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_cancel:
                dismiss();
                call.selected(InterConst.CANCEL);
                break;
            case R.id.txt_confirm:
                dismiss();
                call.selected(InterConst.CONFIRM);
                break;
        }

    }

    @Override
    public void clickIndex(int pos) {

    }
}
