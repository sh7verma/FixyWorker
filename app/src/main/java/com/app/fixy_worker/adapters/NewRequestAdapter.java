package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.RoundedTransformation;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.RequestModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewRequestAdapter extends RecyclerView.Adapter<NewRequestAdapter.ViewHolder> {

    private final Context mContext;

    InterfacesCall.IndexClick click;
    List<RequestModel.ResponseBean> mDataList;
    int serWidth, serHeight, radius;
    int userWidth, userHeight;

    public NewRequestAdapter(Context con, InterfacesCall.IndexClick click, List<RequestModel.ResponseBean> mList) {
        mContext = con;
        this.click = click;
        mDataList = mList;
        serWidth = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        serHeight = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        userWidth = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        userHeight = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        radius = (int) mContext.getResources().getDimension(R.dimen._10sdp);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_request, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.txtServiceName.setText(mDataList.get(position).getCategory_name());
        holder.txtServiceCharges.setText(mDataList.get(position).getRequest_price());
        holder.txtServiceId.setText(mDataList.get(position).getId());
        holder.txtUserName.setText(mDataList.get(position).getFullname());
        holder.txtTime.setText(mDataList.get(position).getCreated_at());
        holder.txtUserLocation.setText(mDataList.get(position).getAddress());
//        holder.userRating.setProgress(Integer.parseInt(mDataList.get(position).getAverage_rating()));
        if (!TextUtils.isEmpty(mDataList.get(position).getCategory_pic())) {
            Picasso.get()
                    .load(mDataList.get(position).getCategory_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(serWidth, serHeight)
                    .into(holder.imgService);
        }
        if (!TextUtils.isEmpty(mDataList.get(position).getProfile_pic())) {
            Picasso.get()
                    .load(mDataList.get(position).getCategory_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(userWidth, userHeight)
                    .into(holder.imgUserImg);
        }
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.clickIndex(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_main)
        LinearLayout llMain;
        @BindView(R.id.img_service)
        ImageView imgService;
        @BindView(R.id.img_user_img)
        ImageView imgUserImg;
        @BindView(R.id.txt_service_name)
        TextView txtServiceName;
        @BindView(R.id.txt_service_charges)
        TextView txtServiceCharges;
        @BindView(R.id.txt_service_id)
        TextView txtServiceId;
        @BindView(R.id.txt_user_name)
        TextView txtUserName;
        @BindView(R.id.txt_user_rating)
        RatingBar userRating;
        @BindView(R.id.txt_user_location)
        TextView txtUserLocation;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_time_counter)
        TextView txtTimeCounter;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            runProgressbar();


        }

    }
}
