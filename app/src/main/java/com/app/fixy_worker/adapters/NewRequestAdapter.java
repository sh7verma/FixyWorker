package com.app.fixy_worker.adapters;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
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
import com.app.fixy_worker.utils.Consts;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewRequestAdapter extends RecyclerView.Adapter<NewRequestAdapter.ViewHolder> {

    private final Context mContext;

    InterfacesCall.NewRequest click;
    List<RequestModel.ResponseBean> mDataList;
    int serWidth, serHeight, radius;
    int userWidth, userHeight;
    CountDownTimer timer ;
    private Handler timeHandler = new Handler();
    private Runnable timeRunnable;

    public NewRequestAdapter(Context con, InterfacesCall.NewRequest click, List<RequestModel.ResponseBean> mList) {
        mContext = con;
        this.click = click;
        mDataList = mList;
        serWidth = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        serHeight = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        userWidth = (int) mContext.getResources().getDimension(R.dimen._35sdp);
        userHeight = (int) mContext.getResources().getDimension(R.dimen._40sdp);
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
        holder.txtServiceCharges.setText(mDataList.get(position).getRequest_price()+" "+mContext.getString(R.string.coins));
        holder.txtServiceId.setText(mContext.getString(R.string.id_)+" "+mDataList.get(position).getId());
        holder.txtUserName.setText(mDataList.get(position).getFullname());
        holder.txtTime.setText(Consts.getDateTime(mDataList.get(position).getCreated_at()));
        holder.txtUserLocation.setText(mDataList.get(position).getAddress());
        holder.userRating.setRating(Float.parseFloat(mDataList.get(position).getAverage_rating()));
        holder.txtTimeCounter.setText(mDataList.get(position).getRemainingTime());
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
        holder.txtAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.Accept(position);
            }
        });
        holder.txtDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.Decline(position);
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
        @BindView(R.id.txt_accept)
        TextView txtAccept;
        @BindView(R.id.txt_decline)
        TextView txtDecline;

        long timeMili = 0;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//
        }

    }


}
