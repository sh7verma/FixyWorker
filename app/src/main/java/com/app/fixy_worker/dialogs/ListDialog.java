package com.app.fixy_worker.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.ListDialogAdapter;
import com.app.fixy_worker.interfaces.InterfacesCall;

import java.util.ArrayList;

import butterknife.BindView;

public class ListDialog extends BaseDialog {

    private final dialogClick clicks;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    ListDialogAdapter adapter;
    ArrayList<String> list = new ArrayList<>();

    public ListDialog(@NonNull Context context, int themeResId, ArrayList<String> mList,dialogClick click) {
        super(context, themeResId);
        list = mList;
        clicks = click;
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wmlp);
    }

    @Override
    public InterfacesCall.IndexClick getInterfaceInstance() {
        return this;
    }

    @Override
    protected void onCreateStuff() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ListDialogAdapter(getContext(), list, indexClick);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getContentView() {

        return R.layout.activity_list_dialog;
    }




    @Override
    public void clickIndex(int pos) {

        clicks.click(pos);
        onBackPressed();
    }

    @Override
    public void onClick(View view) {

    }

    public interface dialogClick{
        void click(int pos);
    }



}
