package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.SelectServiceAdapter;
import com.app.fixy_worker.customviews.HeaderItemDecoration;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.SelectServiceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectServiceActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.txt_done)
    TextView txtDone;

    List<SelectServiceModel> models = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    SelectServiceAdapter adapter;
    private boolean isChange;
    LinkedHashMap<Integer,String> selectedList = new LinkedHashMap<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_select_service;
    }

    @Override
    protected void onCreateStuff() {

        for (int i = 0; i<50;i++){
            SelectServiceModel serviceModel = new SelectServiceModel();
            serviceModel.setId(i+"");
            int po = 0;
            if (i%5==0){

                  po = i%5;
                serviceModel.setHeader(true);
                if (isChange){
                    isChange=!isChange;
                    serviceModel.setCat_name("Beauty");
                }
                else {
                    serviceModel.setCat_name("PLumber");
                    isChange=!isChange;

                }
            }
            else {
                serviceModel.setHeader(false);
            }
            serviceModel.setHeaderPos(po);
            serviceModel.setSelected(false);
            serviceModel.setName(""+i+" item");
            models.add(serviceModel);
        }

        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new SelectServiceAdapter(this,mHeight, clickInterface,models);
        recyclerView.addItemDecoration(new HeaderItemDecoration(recyclerView,stickyHeaderInterface));
        recyclerView.setHasFixedSize(true);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

    }
    HeaderItemDecoration.StickyHeaderInterface stickyHeaderInterface = new HeaderItemDecoration.StickyHeaderInterface() {
        @Override
        public int getHeaderPositionForItem(int itemPosition) {
            int headerPosition = 0;
            do {
                if (this.isHeader(itemPosition)) {
                    headerPosition = itemPosition;
                    break;
                }
                itemPosition -= 1;
            } while (itemPosition >= 0);
            return headerPosition;
        }

        @Override
        public int getHeaderLayout(int headerPosition) {
//            View view = LayoutInflater.from(SelectServiceActivity.this).inflate(R.layout.select_category_single, null, false);
//            TextView textView =  view.findViewById(R.id.txt_cat_name);
//            textView.setText(models.get(headerPosition).getCat_name());
            return R.layout.select_category_single;
        }

        @Override
        public void bindHeaderData(View header, final int headerPosition) {
            if (models.get(headerPosition).isHeader()){
                Log.d("click","bindHeaderData no "+headerPosition);
                TextView tv = header.findViewById(R.id.txt_cat_name);
                ImageView check = header.findViewById(R.id.ic_check);
                tv.setText(models.get(headerPosition).getCat_name());
                if (models.get(headerPosition).isSelected()){
                    check.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_filter_box_s));
                }
                else {
                    check.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_filter_box));
                }

            }
        }

        @Override
        public boolean isHeader(int itemPosition) {
            if (models.get(itemPosition).isHeader()){
                return true;
            }
            return false;
        }

        @Override
        public void headerClick(int headerPosition) {
            headerClickItem(headerPosition);
        }

    };
    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    InterfacesCall.ItemCategoryClick clickInterface = new InterfacesCall.ItemCategoryClick() {
        @Override
        public void clickItem(int pos) {
            if (models.get(pos).isSelected()){
                models.get(pos).setSelected(false);
                selectedList.remove(pos);
            }
            else {
                models.get(pos).setSelected(true);
                selectedList.put(models.get(pos).getHeaderPos(),models.get(pos).getName());

            }
            adapter.notifyItemChanged(pos);

        }

        @Override
        public void clickCategory(int pos) {

            headerClickItem(pos);
        }

    };

    private void headerClickItem(int pos) {

        Log.d("click","item no "+pos);
        //SELECTED
        if (models.get(pos).isHeader() && !models.get(pos).isSelected()){
            models.get(pos).setSelected(true);
            for (int i = pos+1; i<models.size();i++){
                if (!models.get(i).isHeader()){
                    models.get(i).setSelected(true);
                    selectedList.put(Integer.valueOf(models.get(i).getId()),models.get(i).getName());
                }
                else {
                    break;
                }
            }
            adapter.notifyDataSetChanged();
        }
        else {//UNSELECTED
            models.get(pos).setSelected(false);
            for (int i = pos+1; i<models.size();i++){
                if (!models.get(i).isHeader()){
                    models.get(i).setSelected(false);
                    selectedList.remove(models.get(i).getHeaderPos());
                }
                else {
                    break;
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {

        finish();
        overridePendingTransition(R.anim.stay,R.anim.slide_down_out);
    }

    @OnClick(R.id.ic_back)
    public void back(){
        onBackPressed();
    }

    @OnClick(R.id.txt_done)
    public void done(){
        Intent intent = new Intent();
        intent.putExtra(InterConst.RESULT_DATA_KEY,selectedList);
        setResult(RESULT_OK,intent);
        onBackPressed();
    }
}
