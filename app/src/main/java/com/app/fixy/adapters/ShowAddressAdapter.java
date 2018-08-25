package com.app.fixy.adapters;

import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.fixy.R;
import com.app.fixy.database.Db;
import com.app.fixy.models.LocationModel;
import com.app.fixy.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by app on 21-Dec-17.
 */

public class ShowAddressAdapter extends RecyclerView.Adapter<ShowAddressAdapter.ViewHolder> {

    Context con;
    int mScreenWidth;
    int mScreenHeight;
    Utils utils;
    int showEditMode = 0;
    int rowPosition = -1;
    Db db;
    ArrayList<LocationModel> locationModel;

    public ShowAddressAdapter(Context con, int screenWidth, int screenHeight, int showEdit, ArrayList<LocationModel> Model) {
        this.con = con;
        locationModel = Model;
        utils = new Utils(con);
        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
        this.showEditMode = showEdit;
        db = new Db(con);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_address_single, parent, false);
        ViewHolder vhItem = new ViewHolder(v);
        return vhItem;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    holder.txtName.setText(locationModel.get(position).getLabelName());
    holder.txtAdd.setText(getFullAddress(position));
    }

    @Override
    public int getItemCount() {
        return locationModel.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_background)
        public RelativeLayout viewBackground;
        @BindView(R.id.view_foreground)
        public RelativeLayout viewForeground;
        @BindView(R.id.txt_name)
        public TextView txtName;
        @BindView(R.id.txt_add)
        public TextView txtAdd;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }
    public void removeItem(int position) {
        locationModel.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

//    public void restoreItem(Item item, int position) {
////        cartList.add(position, item);
//        // notify item added by position
//        notifyItemInserted(position);
//    }
    public String getFullAddress(int pos){
        List<String> names = new ArrayList<>();
        String fullAdd="";
        if (!TextUtils.isEmpty(locationModel.get(pos).getHouse_flat())){
            names.add(locationModel.get(pos).getHouse_flat());
        }
        if (!TextUtils.isEmpty(locationModel.get(pos).getLandmark())){
            names.add(locationModel.get(pos).getLandmark());
        }
        if (!TextUtils.isEmpty(locationModel.get(pos).getPlace())){
            names.add(locationModel.get(pos).getPlace());
        }
        if (names.size()>0){
              fullAdd = TextUtils.join(",",names);
        }

        return fullAdd;
    }

}