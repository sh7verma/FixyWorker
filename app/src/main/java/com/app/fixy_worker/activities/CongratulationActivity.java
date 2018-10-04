package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterConst;

import butterknife.BindView;
import butterknife.OnClick;

public class CongratulationActivity extends BaseActivity {


    @BindView(R.id.txt_buy_more)
    TextView txtBuyMore;
    @BindView(R.id.txt_browse_service)
    TextView txtBrowseService;

    @Override
    protected int getContentView() {
        return R.layout.activity_congratulation;
    }

    @Override
    protected void onCreateStuff() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {
        txtBrowseService.setOnClickListener(this);
        txtBuyMore.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_browse_service:
                utils.setInt(InterConst.PASS_CONGRATULATION,1);
                browseService();
                break;
            case R.id.txt_buy_more:
                buyMore();
                break;
        }
    }

    private void buyMore() {

    }

    void browseService() {
        Intent intent = new Intent(mContext, LandingActivity.class);
        finish();
        startActivity(intent);
    }
}
