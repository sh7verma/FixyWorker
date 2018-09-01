package com.app.fixy_worker.activities;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.BookingAdapter;
import com.app.fixy_worker.adapters.SearchCategoryAdapter;
import com.app.fixy_worker.adapters.SearchServiceAdapter;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.interfaces.InterfacesCall;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchServiceActivity extends BaseActivity {


    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycleview)
    RecyclerView rvService;
    @BindView(R.id.recycleview_slot)
    RecyclerView rvServiceSlot;
    @BindView(R.id.ic_clear)
    ImageView icClear;
    @BindView(R.id.et_service)
    MaterialEditText etService;
    SearchServiceAdapter serviceAdapter;
    SearchCategoryAdapter categoryAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_search_service;
    }

    @Override
    protected void onCreateStuff() {

        rvService.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvServiceSlot.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvService.setNestedScrollingEnabled(false);
        rvServiceSlot.setNestedScrollingEnabled(false);

        serviceAdapter = new SearchServiceAdapter(mContext,mHeight,clickService);
        categoryAdapter = new SearchCategoryAdapter(mContext,mHeight,clickCategory);
        rvService.setAdapter(serviceAdapter);
        rvServiceSlot.setAdapter(categoryAdapter);
        rvServiceSlot.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initUI() {
        txtTitle.setText(R.string.looking_for);

    }

    @Override
    protected void initListener() {
    icClear.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ic_clear:
                etService.setText("");
                break;
        }
    }


    @Override
    public void onBackPressed() {

        finish();
        overridePendingTransition(R.anim.slide_right, R.anim.slide_out_right);
    }

    @OnClick(R.id.ic_back)
    void back() {
        onBackPressed();
    }

    InterfacesCall.IndexClick clickService = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {

        }
    };
    InterfacesCall.IndexClick clickCategory = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {

        }
    };
}
