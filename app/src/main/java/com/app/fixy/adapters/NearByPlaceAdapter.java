package com.app.fixy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.app.fixy.R;
import com.app.fixy.database.Db;
import com.app.fixy.interfaces.AddressInterface;
import com.app.fixy.interfaces.InterConst;
import com.app.fixy.models.NearbyPlaceModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by app on 06-Feb-18.
 */

public class NearByPlaceAdapter extends RecyclerView.Adapter<NearByPlaceAdapter.MyHolder> {

    final int ADD_WORK = 1;
    final int ADD_HOME = 2;
    Context mContext;
    int mWidth;
    Db db;
    List<NearbyPlaceModel.ResultsBean> list=new ArrayList<>();
    AddressInterface anInterface;
    public NearByPlaceAdapter(Context mContext, List<NearbyPlaceModel.ResultsBean> list, int mWidth, Object addressInterface) {
        this.mContext = mContext;
        this.list = list;
        this.mWidth = mWidth;
        db = new Db(mContext);
        anInterface = (AddressInterface) addressInterface;
    }

    @Override
    public NearByPlaceAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.auto_complete_row,parent,false);

        return new NearByPlaceAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(NearByPlaceAdapter.MyHolder holder, final int position) {
        Log.d("Destination", "Adapter items "+list.get(position).getName());


        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getVicinity());
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Destination.search.setText(list.get(position));
                anInterface.onClick(position, InterConst.GOOGLE.NEARBY);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView name,address;
        LinearLayout llMain;
        View view;
        public MyHolder(View itemView) {
            super(itemView);

            name= (TextView) itemView.findViewById(R.id.place_name);
            address= (TextView) itemView.findViewById(R.id.place_name_address);
            llMain = (LinearLayout) itemView.findViewById(R.id.ll_main);
            view =   itemView.findViewById(R.id.view);

        }
    }
}
