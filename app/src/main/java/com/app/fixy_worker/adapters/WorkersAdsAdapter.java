package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterfacesCall;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shubham verma on 18-08-2018.
 */

public class WorkersAdsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int mHeight;
    int ADD_POST = 0;
    int POSTS = 1;
    InterfacesCall.ServiceOffer clicks;

    public WorkersAdsAdapter(Context context, int height, InterfacesCall.ServiceOffer interfaces) {
        mContext = context;
        mHeight = height;
        clicks = interfaces;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 ){
            return ADD_POST;
        }
        else {
            return POSTS;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_new_post, parent, false);
            return new NewPostViewHolder(v);
        }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workers_ads, parent, false);
            return new AdsViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewPostViewHolder){
            ((NewPostViewHolder) holder).rlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 clicks.adsClick(holder.getAdapterPosition());
                }
            });

        }
//        if (!TextUtils.isEmpty(mData.getProfilePicURL().getOriginal())) {
//                Picasso.with(mContext)
//                        .load(mData.getProfilePicURL().getOriginal())
//                        .transform(new RoundCorners())
//                        .resize((int) (mHeight * 0.13), (int) (mHeight * 0.13))
//                        .placeholder(R.mipmap.ic_img_ph)
//                        .error(R.mipmap.ic_img_ph)
//                        .centerCrop()
//                        .into(imgProfile);
//            } else {

//        GradientDrawable bgShape = (GradientDrawable) holder.rlBackground.getBackground();
//        bgShape.setColor(Color.BLACK);

//        Picasso.get()
//                .load(R.mipmap.ic_beauty)
//                .transform(new CircleTransform())
//                .resize((int) (mHeight * 0.08), (int) (mHeight * 0.08))
//                .into(holder.imgService);
//            }

        }


    @Override
    public int getItemCount() {
        return 10;
    }

    class NewPostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_main)
        RelativeLayout rlMain;

        NewPostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    class AdsViewHolder extends RecyclerView.ViewHolder {

        AdsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
