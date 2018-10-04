package com.app.fixy_worker.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.SelectServiceModel;
import com.app.fixy_worker.utils.Animations;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int mHeight;
    InterfacesCall.ItemCategoryClick click;
    List<SelectServiceModel> list;
    int HEADER=2,ITEM=1;
    public SelectServiceAdapter(Context context, int height, InterfacesCall.ItemCategoryClick clickSer, List<SelectServiceModel> models) {
        mContext = context;
        mHeight = height;
        click = clickSer;
        list = models;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_category_single, parent, false);
            return new ViewHeaderHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_service_single, parent, false);
            return new ViewItemHolder(view);
        }
    }

    int mHeaderDisplay = 18;
    boolean mMarginsFixed = false;
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        if (holder.getItemViewType() == HEADER){
            ViewHeaderHolder headerHolder = (ViewHeaderHolder) holder;
            if (list.get(position).isSelected()){
                headerHolder.viewBooking.icCheck.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_filter_box_s));
            }
            else {
                headerHolder.viewBooking.icCheck.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_filter_box));

            }
            headerHolder.viewBooking.txtCatName.setText(list.get(position).getCat_name());
            headerHolder.viewBooking.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.clickCategory(position);
//                    Toast.makeText(mContext, list.get(position).getCat_name(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (holder.getItemViewType() == ITEM){

            final ViewItemHolder itemHolder = (ViewItemHolder) holder;
            if (list.get(position).isSelected()){
                itemHolder.viewBooking.icCheck.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_filter_box_s));
            }
            else {
                itemHolder.viewBooking.icCheck.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_filter_box));

            }
            itemHolder.viewBooking.txtSubCatName.setText(list.get(position).getName());
            itemHolder.viewBooking.llMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.clickItem(position);

                    Animations.AnimatedClick(mContext,itemHolder.viewBooking.icCheck);

//                    Toast.makeText(mContext, list.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isHeader()){
            return HEADER;
        }
        else {
            return ITEM;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHeader {
        @BindView(R.id.ll_main_header)
        LinearLayout llMain;
        @BindView(R.id.txt_cat_name)
        TextView txtCatName;
        @BindView(R.id.ic_check)
        ImageView icCheck;
    }
    class ViewHeaderHolder extends RecyclerView.ViewHolder {

        ViewHeader viewBooking = new ViewHeader();
        ViewHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(viewBooking, itemView);
        }

    }
    public class ViewItem {
        @BindView(R.id.ll_main)
        LinearLayout llMain;
        @BindView(R.id.txt_sub_cat_name)
        TextView txtSubCatName;
        @BindView(R.id.ic_check)
        ImageView icCheck;
    }
    class ViewItemHolder extends RecyclerView.ViewHolder {

        ViewItem viewBooking = new ViewItem();
        ViewItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(viewBooking, itemView);
        }

    }
}
