package com.app.fixy_worker.dialogs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.BaseActivity;
import com.app.fixy_worker.activities.DialogBaseActivity;
import com.app.fixy_worker.adapters.ListDialogAdapter;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;

import java.util.ArrayList;

import butterknife.BindView;

public class ListDialog extends DialogBaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    ListDialogAdapter adapter;
    ArrayList<String> list = new ArrayList<>();
/*

    @Override
    protected int getContentView() {
        return R.layout.activity_list_dialog;
    }
*/
    @Override
    public void onCreateStuff() {

//        getWindow().getAttributes().width = mWidth;
        list = getIntent().getStringArrayListExtra(InterConst.EXTRA);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ListDialogAdapter(this, list, mcliIndexClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getContentView() {

        return R.layout.activity_list_dialog;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    InterfacesCall.IndexClick mcliIndexClick = new InterfacesCall.IndexClick() {
        @Override
        public void clickIndex(int pos) {
            Intent intent = new Intent();
            intent.putExtra(InterConst.EXTRA,pos);
            setResult(RESULT_OK,intent);
            onBackPressed();
        }
    };


    @Override
    public void onClick(View view) {

    }


}
