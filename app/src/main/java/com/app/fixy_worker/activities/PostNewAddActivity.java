package com.app.fixy_worker.activities;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.adapters.PostAdAdapter;
import com.app.fixy_worker.customviews.CirclePageIndicator;
import com.app.fixy_worker.customviews.CustomViewPager;
import com.app.fixy_worker.fragments.AdsDetailFormFragment;
import com.app.fixy_worker.fragments.PostServiceFragment;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.AdsModel;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Dialogs;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostNewAddActivity extends BaseActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.ic_back)
    ImageView icBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_done)
    Button txtDone;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    private PostAdAdapter adapter;
    ArrayList<LoginModel.ResponseBean.SelectedServicesBean> serviceList;

    @Override
    protected int getContentView() {
        return R.layout.activity_post_new_add;
    }

    @Override
    protected void onCreateStuff() {
        AdsModel.setInstance(null);
        serviceList = getIntent().getParcelableArrayListExtra(InterConst.EXTRA);
        txtTitle.setText(getString(R.string.post_new_add));
        adapter = new PostAdAdapter(getSupportFragmentManager(),mContext,serviceList);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        viewPager.disableScroll(true);
        indicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {
        txtDone.setOnClickListener(this);
        icBack.setOnClickListener(this);
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_done:
                if (viewPager.getCurrentItem() == 0 && ((PostServiceFragment) adapter.getItem(0)).validate()){
                    viewPager.setCurrentItem(1,true);
                }
                else if(viewPager.getCurrentItem() == 1 && ((AdsDetailFormFragment) adapter.getItem(1)).validate()){
                    hitAddPostApi();
                }

                break;
            case R.id.ic_back:
                if (viewPager.getCurrentItem() == 1){
                    viewPager.setCurrentItem(0,true);
                }
                else if(viewPager.getCurrentItem() == 0){
                    onBackPressed();
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1){
            viewPager.setCurrentItem(0,true);
        }
        else if(viewPager.getCurrentItem() == 0){
            finish();
            overridePendingTransition(R.anim.stay,R.anim.trans_exit);
        }
    }

    private void hitAddPostApi() {
        AdsModel model = AdsModel.getInstance();
        if (connectedToInternet()) {
            showProgress();
            Call<AdsModel> call = RetrofitClient.getInstance().add_new_offer(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""),model.getSubcategoriesBean().getId(),model.getOriginal_price(),
                    model.getPercentage(),model.getPeriod(),model.getDescription());
            call.enqueue(new Callback<AdsModel>() {
                @Override
                public void onResponse(Call<AdsModel> call, Response<AdsModel> response) {
                    hideProgress();
                    if (response.body().getCode() == InterConst.SUCCESS_RESULT) {
                        showToast(getString(R.string.your_post_is_uploaded));
                        finish();
                        overridePendingTransition(R.anim.stay,R.anim.trans_exit);
                    } else if (response.body().getError().getCode() == InterConst.ERROR_RESULT) {
                        Dialogs.showValidationSnackBar(mContext, txtDone, response.body().getError().getMessage());
                    } else if (response.body().getError().getCode() == InterConst.INVALID_ACCESS_TOKEN) {
                        moveToSplash();
                    } else {
                        showSnackBar(txtDone, response.body().getError().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<AdsModel> call, Throwable t) {
                    hideProgress();
                    t.printStackTrace();
                    showSnackBar(txtDone, t.getMessage());
                }
            });
        } else {
            showInternetAlert(txtDone);
        }
    }

}
