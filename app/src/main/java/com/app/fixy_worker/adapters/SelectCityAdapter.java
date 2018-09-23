package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.database.Db;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.CItyModel;
import com.app.fixy_worker.models.LocationModel;
import com.app.fixy_worker.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityAdapter.ViewHolder> {

    Context con;
    int mScreenWidth;
    int mScreenHeight;
    Utils utils;
    int showEditMode = 0;
    int rowPosition = -1;
    Db db;
    ArrayList<CItyModel.ResponseBean> cItyModels;
    InterfacesCall.IndexClick mClick;
    public SelectCityAdapter(Context con, ArrayList<CItyModel.ResponseBean> Model, InterfacesCall.IndexClick click) {
        this.con = con;
        cItyModels = Model;
        mClick = click;
    }

    @Override
    public SelectCityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_city_item, parent, false);
        return new SelectCityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SelectCityAdapter.ViewHolder holder, final int position) {

        holder.txtCityName.setText(cItyModels.get(position).getCity_name());

        if (rowPosition == position){
            holder.icCheck.setImageDrawable(ContextCompat.getDrawable(con,R.mipmap.ic_gender_s));
        }
        else {
            holder.icCheck.setImageDrawable(ContextCompat.getDrawable(con,R.mipmap.ic_gender_un));
        }
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.clickIndex(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cItyModels.size();
    }

    public void setCityId(int id){
        rowPosition = id;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_main)
        public LinearLayout llMain;
        @BindView(R.id.txt_city_name)
        public TextView txtCityName;
        @BindView(R.id.ic_check)
        public ImageView icCheck;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }


}
