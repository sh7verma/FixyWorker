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
import com.app.fixy.models.GooglePlaceModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by app on 17-Nov-17.
 */

public class GooglePlaceAdapter extends RecyclerView.Adapter<GooglePlaceAdapter.MyHolder> {

    final int ADD_WORK = 1;
    final int ADD_HOME = 2;
    Context mContext;
    int mWidth;
    Db db;
    List<GooglePlaceModal.PredictionsBean> list=new ArrayList<>();
    AddressInterface anInterface;
    public GooglePlaceAdapter(Context mContext, List<GooglePlaceModal.PredictionsBean> list, int mWidth, Object addressInterface) {
        this.mContext = mContext;
        this.list = list;
        this.mWidth = mWidth;
        anInterface = (AddressInterface) addressInterface;
        db = new Db(mContext);
    }

    @Override
    public GooglePlaceAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.auto_complete_row,parent,false);

        return new GooglePlaceAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(GooglePlaceAdapter.MyHolder holder, final int position) {
        Log.d("Destination", "Adapter items "+list.get(position).getDescription());



        holder.name.setText(list.get(position).getStructured_formatting().getMain_text());
        holder.address.setText(list.get(position).getStructured_formatting().getSecondary_text());
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Destination.search.setText(list.get(position));
                anInterface.onClick(position, InterConst.GOOGLE.AUTOCOMPLETE);


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
