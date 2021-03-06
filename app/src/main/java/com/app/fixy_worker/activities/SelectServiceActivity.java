package com.app.fixy_worker.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.SelectServiceAdapter;
import com.app.fixy_worker.customviews.HeaderItemDecoration;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.AllServiceModel;
import com.app.fixy_worker.models.SelectServiceModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectServiceActivity extends BaseActivity {

    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.search_recycleview)
    RecyclerView searchRecycleView;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.ic_cancel)
    ImageView icCancel;
    @BindView(R.id.txt_done)
    TextView txtDone;
    @BindView(R.id.et_search)
    MaterialEditText etSearch;

    List<SelectServiceModel> models = new ArrayList<>();
    private LinearLayoutManager mLayoutManager,mLyoutManagerSearch;
    SelectServiceAdapter adapter;
    private boolean isChange;
    LinkedHashMap<Integer,String> selectedList = new LinkedHashMap<>();
    HashMap<Integer,String> alreadySelected = new HashMap<>();
    private List<AllServiceModel.ResponseBean.CategoriesBean> responseList;
    private List<SelectServiceModel> allList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_select_service;
    }

    @Override
    protected void onCreateStuff() {
        alreadySelected = (HashMap<Integer, String>) getIntent().getSerializableExtra(InterConst.EXTRA);
        selectedList.putAll(alreadySelected);
        hitAllService();

        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mLyoutManagerSearch = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        searchRecycleView.setLayoutManager(mLyoutManagerSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0){
                    searchFilter(charSequence);
                    icCancel.setVisibility(View.VISIBLE);
                }
                else {
                    models = allList;
                    icCancel.setVisibility(View.GONE);
                    setMainAdapter();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void setMainAdapter() {
        recyclerView.setVisibility(View.VISIBLE);
        searchRecycleView.setVisibility(View.GONE);
        adapter = new SelectServiceAdapter(SelectServiceActivity.this, mHeight, clickInterface, models);
        recyclerView.addItemDecoration(new HeaderItemDecoration(recyclerView, stickyHeaderInterface));
        recyclerView.setHasFixedSize(true);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
    }

    private void searchFilter(CharSequence text) {
        models = new ArrayList<>();
        for (int j=0 ; j < allList.size(); j++){
            if ((!allList.get(j).isHeader()) && (allList.get(j).getName() != null)
                    && (allList.get(j).getName().toLowerCase().contains(text.toString().toLowerCase()))){
                models.add(allList.get(j));
            }
        }
        if (models.size()>0){
            recyclerView.setVisibility(View.GONE);
            searchRecycleView.setVisibility(View.VISIBLE);
            adapter = new SelectServiceAdapter(SelectServiceActivity.this, mHeight, clickInterface, models);
            searchRecycleView.setHasFixedSize(true);
            adapter.setHasStableIds(true);
            searchRecycleView.setAdapter(adapter);
        }
        else {
            setMainAdapter();
        }
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
    icCancel.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_cancel:
                etSearch.setText("");
                break;
        }
    }

    InterfacesCall.ItemCategoryClick clickInterface = new InterfacesCall.ItemCategoryClick() {
        @Override
        public void clickItem(int pos) {//ITEM
            if (models.get(pos).isSelected()){
                models.get(pos).setSelected(false);//item uncheck
                models.get(models.get(pos).getPosition()).setSelected(false);//header uncheck
                selectedList.remove(pos);
                adapter.notifyItemChanged(models.get(pos).getPosition());
            }//
            else {
                models.get(pos).setSelected(true);
                selectedList.put(models.get(pos).getSub_id(),models.get(pos).getName());
                boolean flag = false;
                for (int i = models.get(pos).getPosition()+1; i<models.size();i++){
                    if (!models.get(i).isHeader()){
                        if (models.get(i).isSelected()){
                            flag = true;
                        }
                        else {
                            flag = false;
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
                if (flag){
                    models.get(models.get(pos).getPosition()).setSelected(true);//header checked if all sub items checked
                    adapter.notifyItemChanged(models.get(pos).getPosition());
                }
            }
            adapter.notifyItemChanged(pos);

        }

        @Override
        public void clickCategory(int pos) {

            headerClickItem(pos);
        }

    };

    //HEADER
    private void headerClickItem(int pos) {

        Log.d("click","item no "+pos);
        //SELECTED
        if (models.get(pos).isHeader() && !models.get(pos).isSelected()){
            models.get(pos).setSelected(true);
            for (int i = pos+1; i<models.size();i++){
                if (!models.get(i).isHeader()){
                    models.get(i).setSelected(true);
                    selectedList.put(models.get(i).getSub_id(),models.get(i).getName());
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
                    selectedList.remove(models.get(i).getSub_id());
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


    int position=-1;
    public void hitAllService() {
        if (connectedToInternet()) {
            showProgress();
            ApiInterface apiInterface = RetrofitClient.getInstance();

            Call<AllServiceModel> call = apiInterface.services(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""),
                    utils.getString(InterConst.CITY_ID, ""));
            call.enqueue(new Callback<AllServiceModel>() {
                @Override
                public void onResponse(Call<AllServiceModel> call, Response<AllServiceModel> response) {

                    hideProgress();
                    int pos;
                    responseList = response.body().getResponse().getCategories();
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {
                        for (int i = 0; i < responseList.size(); i++) {
                             position+=1;
                            pos = position;
                            SelectServiceModel serviceModel = new SelectServiceModel();
                            serviceModel.setCat_id(responseList.get(i).getId() + "");
                            serviceModel.setPosition(position);
                            serviceModel.setCat_name(responseList.get(i).getCategory_name());
                            serviceModel.setSelected(false);
                            serviceModel.setHeader(true);
                            models.add(serviceModel);
                            for (int j = 0; j < responseList.get(i).getSubcategories().size(); j++) {
                                position+=1;
                                SelectServiceModel subModel = new SelectServiceModel();
                                subModel.setPosition(pos);
                                subModel.setHeader(false);
                                subModel.setHeaderPos(i);
                                subModel.setSub_id(Integer.parseInt(responseList.get(i).getSubcategories().get(j).getId()));
                                // this is used when already selected item would be checked in the list
                                if (alreadySelected.size() > 0) {
                                    if (alreadySelected.containsKey(Integer.parseInt(responseList.get(i).getSubcategories().get(j).getId()))) {
                                        subModel.setSelected(true);
                                    } else {
                                        subModel.setSelected(false);
                                    }
                                }
                                subModel.setName(responseList.get(i).getSubcategories().get(j).getCategory_name());
                                models.add(subModel);

                            }

                        }
                        allList.addAll(models);

                        setMainAdapter();

                    } else if (response.body().getCode() == InterConst.ERROR_RESULT) {
                        showAlert(txtDone, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<AllServiceModel> call, Throwable t) {
                    t.printStackTrace();
                    hideProgress();
                }
            });
        }
        else {
            showInternetAlert(txtDone);
        }

    }

}
