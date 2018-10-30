package com.app.fixy_worker.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.RoundedTransformation;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.utils.Consts;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScheduledAdapter extends RecyclerView.Adapter<ScheduledAdapter.ViewHolder> {

    private final Context mContext;
    Bitmap bitmap =null;
    InterfacesCall.ScheduleRequest mClick;

    private Handler handler = new Handler();
    Runnable runnable;
    private int progressStatus = 0, count = 25;

    int serWidth, serHeight, radius;
    int userWidth, userHeight;
    int start = 0,end = 75,next=0;
    List<RequestModel.ResponseBean> mData;
    public ScheduledAdapter(Context con, InterfacesCall.ScheduleRequest click, List<RequestModel.ResponseBean> mList) {
        mContext = con;
        mClick = click;
        mData = mList;

        serWidth = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        serHeight = (int) mContext.getResources().getDimension(R.dimen._40sdp);
        userWidth = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        userHeight = (int) mContext.getResources().getDimension(R.dimen._30sdp);
        radius = (int) mContext.getResources().getDimension(R.dimen._10sdp);
    }

    @NonNull
    @Override
    public ScheduledAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scheduled, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        if (mData.get(position).getRequest_status().equalsIgnoreCase(InterConst.ACCEPT_REQUEST)){
            start = 25;
            setView(holder,start);
            holder.viewBooking.txtRequestedTime.setText(Consts.getDateTime(mData.get(position).getCreated_at()));
            holder.viewBooking.txtAcceptedTime.setText(Consts.getDateTime(mData.get(position).getAccepted_time()));
            holder.viewBooking.txtConfirm.setText(mContext.getString(R.string.on_the_way));
        }
       else if (mData.get(position).getRequest_status().equalsIgnoreCase(InterConst.ON_THE_WAY)){
            start = 50;

            holder.viewBooking.imgTrack.setVisibility(View.VISIBLE);
            setView(holder,start);
            holder.viewBooking.txtRequestedTime.setText(Consts.getDateTime(mData.get(position).getCreated_at()));
            holder.viewBooking.txtAcceptedTime.setText(Consts.getDateTime(mData.get(position).getAccepted_time()));
//            holder.viewBooking.txtConfirmTime.setText(Consts.getDateTime(mData.get(position).geto()));
            holder.viewBooking.txtConfirm.setText(mContext.getString(R.string.confirm));
        }
       else if (mData.get(position).getRequest_status().equalsIgnoreCase(InterConst.CONFRIM)){
            start = 75;
            setView(holder,start);
            holder.viewBooking.txtRequestedTime.setText(Consts.getDateTime(mData.get(position).getCreated_at()));
            holder.viewBooking.txtAcceptedTime.setText(Consts.getDateTime(mData.get(position).getAccepted_time()));
//            holder.viewBooking.txtConfirmTime.setText(Consts.getDateTime(mData.get(position).geto()));
            holder.viewBooking.txtConfirm.setText(mContext.getString(R.string.complete));
        }
        holder.viewBooking.txtServiceName.setText(mData.get(position).getCategory_name());
        holder.viewBooking.txtServiceCharges.setText(mData.get(position).getRequest_price()+" "+mContext.getString(R.string.coins));
        holder.viewBooking.txtServiceId.setText(mContext.getString(R.string.id_)+" "+mData.get(position).getId());
        holder.viewBooking.txtUserName.setText(mData.get(position).getFullname());
        holder.viewBooking.txtEtaTime.setText(mContext.getString(R.string.eta)+": "+mData.get(position).getSchedule_time());
        holder.viewBooking.userRating.setRating(Float.parseFloat(mData.get(position).getAverage_rating()));
        if (!TextUtils.isEmpty(mData.get(position).getCategory_pic())) {
            Picasso.get()
                    .load(mData.get(position).getCategory_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(serWidth, serHeight)
                    .into(holder.viewBooking.imgService);
        }
        if (!TextUtils.isEmpty(mData.get(position).getProfile_pic())) {
            Picasso.get()
                    .load(mData.get(position).getProfile_pic())
                    .transform(new RoundedTransformation(radius, 0))
                    .resize(userWidth, userHeight)
                    .into(holder.viewBooking.imgUserImg);
        }
        holder.viewBooking.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.clickIndex(position);
            }
        });
        holder.viewBooking.txtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.Confirm(position);
            }
        });
        holder.viewBooking.txtEtaTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.clickIndex(InterConst.SHOW_ADAPTER_SNACKBAR);
            }
        });

/*
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressStatus = progressStatus+30;
                start+=25;
                progressStatus=start;
//                handler.postDelayed(runnable, 10);
                setView(holder, start);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateAdapter(List<RequestModel.ResponseBean> mList) {
        mData = mList;
        notifyDataSetChanged();
    }

    public class ViewBooking {
        @BindView(R.id.ll_main)
        LinearLayout llMain;
        @BindView(R.id.img_service)
        ImageView imgService;
        @BindView(R.id.img_user_img)
        ImageView imgUserImg;
        @BindView(R.id.img_track)
        ImageView imgTrack;
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

        @BindView(R.id.ll_request)
        LinearLayout llRequest;
        @BindView(R.id.txt_requested_time)
        TextView txtRequestedTime;
        @BindView(R.id.ll_accepted)
        LinearLayout llAccepted;
        @BindView(R.id.txt_accepted_time)
        TextView txtAcceptedTime;
        @BindView(R.id.txt_eta_time)
        TextView txtEtaTime;
        @BindView(R.id.ll_on_way)
        LinearLayout llOnWay;
        @BindView(R.id.txt_confirm_time)
        TextView txtConfirmTime;
        @BindView(R.id.ll_confirmed)
        LinearLayout llConfirmed;
        @BindView(R.id.txt_confirm)
        TextView txtConfirm;
    } 
    private void setView(@NonNull ScheduledAdapter.ViewHolder holder, int start) {
        switch (start) {
            case 0:
                showPending(holder);
                break;
            case 25:
                showAccepted(holder);
                break;
            case 50:
                showOnWay(holder);
                break;
            case 75:
                showConfirm(holder);
                break;
        }
    }
    private void showPending(ScheduledAdapter.ViewHolder holder) {
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(R.dimen._3sdp, 0);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        holder.vprogressbar.setLayoutParams(relativeLayout);
        holder.viewBooking.llRequest.setVisibility(View.VISIBLE);
        holder.viewBooking.llAccepted.setVisibility(View.GONE);
        holder.viewBooking.llOnWay.setVisibility(View.GONE);
        holder.viewBooking.llConfirmed.setVisibility(View.GONE);


        holder.viewCircle1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle2.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
    }
    private void showAccepted(ScheduledAdapter.ViewHolder holder) {
        int w = (int) mContext.getResources().getDimension(R.dimen._3sdp);
        int h = (int) mContext.getResources().getDimension(R.dimen._51sdp);
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(w, h);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        holder.vprogressbar.setLayoutParams(relativeLayout);
        holder.viewBooking.llRequest.setVisibility(View.VISIBLE);
        holder.viewBooking.llAccepted.setVisibility(View.VISIBLE);
        holder.viewBooking.llOnWay.setVisibility(View.GONE);
        holder.viewBooking.llConfirmed.setVisibility(View.GONE);


        holder.viewCircle1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle2.setBackground(ContextCompat.getDrawable(mContext,R.drawable.blue_oval));
        holder.viewCircle3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
    }

    private void showOnWay(ScheduledAdapter.ViewHolder holder) {
        int w = (int) mContext.getResources().getDimension(R.dimen._3sdp);
        int h = (int) mContext.getResources().getDimension(R.dimen._102sdp);
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(w, h);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        holder.vprogressbar.setLayoutParams(relativeLayout);
        holder.viewBooking.llRequest.setVisibility(View.VISIBLE);
        holder.viewBooking.llAccepted.setVisibility(View.VISIBLE);
        holder.viewBooking.llOnWay.setVisibility(View.VISIBLE);
        holder.viewBooking.llConfirmed.setVisibility(View.GONE);

        holder.viewCircle1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle2.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.yellow_oval));
        holder.viewCircle4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
    }

    private void showConfirm(ScheduledAdapter.ViewHolder holder) {
        int w = (int) mContext.getResources().getDimension(R.dimen._3sdp);
        int h = (int) mContext.getResources().getDimension(R.dimen._155sdp);
        RelativeLayout.LayoutParams relativeLayout = new RelativeLayout.LayoutParams(w, h);
        int l = (int) mContext.getResources().getDimension(R.dimen._10sdp);
        int t = (int) mContext.getResources().getDimension(R.dimen._5sdp);
        relativeLayout.setMargins(l, t, 0, 0);
        holder.vprogressbar.setLayoutParams(relativeLayout);
        holder.viewBooking.llRequest.setVisibility(View.VISIBLE);
        holder.viewBooking.llAccepted.setVisibility(View.VISIBLE);
        holder.viewBooking.llOnWay.setVisibility(View.VISIBLE);
        holder.viewBooking.llConfirmed.setVisibility(View.VISIBLE);

        holder.viewCircle1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle2.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle3.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
        holder.viewCircle4.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circle_green));
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        View viewCircle1, viewCircle2, viewCircle3, viewCircle4;
        ProgressBar vprogressbar;
        ScheduledAdapter.ViewBooking viewBooking = new ScheduledAdapter.ViewBooking();

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(viewBooking, itemView);
            viewCircle1 = itemView.findViewById(R.id.view_circle1);
            viewCircle2 = itemView.findViewById(R.id.view_circle2);
            viewCircle3 = itemView.findViewById(R.id.view_circle3);
            viewCircle4 = itemView.findViewById(R.id.view_circle4);
            vprogressbar = itemView.findViewById(R.id.vprogressbar);

            viewBooking.llRequest.setVisibility(View.VISIBLE);
            viewBooking.llAccepted.setVisibility(View.GONE);
            viewBooking.llOnWay.setVisibility(View.GONE);
            viewBooking.llConfirmed.setVisibility(View.GONE);
//            runProgressbar();


        }
    }
}