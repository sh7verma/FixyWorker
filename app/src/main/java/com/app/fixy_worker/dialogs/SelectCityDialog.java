package com.app.fixy_worker.dialogs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.CreateProfileActivity;
import com.app.fixy_worker.activities.OtpActivity;
import com.app.fixy_worker.adapters.SelectCityAdapter;
import com.app.fixy_worker.interfaces.AddressInterface;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.CItyModel;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Dialogs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCityDialog extends BaseDialog {

    private final InterfacesCall.Callback callback;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_mess)
    TextView txtMess;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    ArrayList<CItyModel.ResponseBean> list;
    SelectCityAdapter mAdapter;



    @Override
    public InterfacesCall.IndexClick getInterfaceInstance() {
        return this;
    }

    public SelectCityDialog(@NonNull Context context, boolean cancelOnTouch,int themeResId, ArrayList<CItyModel.ResponseBean> cityList,InterfacesCall.Callback call) {
        super(context, themeResId);
        list = cityList;
        callback = call;

        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wmlp);
        setCanceledOnTouchOutside(cancelOnTouch);
    }

    @Override
    protected void onCreateStuff() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SelectCityAdapter(getContext(),list,indexClick);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public int getContentView() {
        return R.layout.dialog_select_city;
    }



    @Override
    public void clickIndex(int pos) {
        dismiss();
        mAdapter.setCityId(pos);
        callback.selected(pos);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {

    }
}
