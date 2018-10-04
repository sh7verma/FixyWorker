package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.CircleTransform;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.LoginModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shubham verma on 17-08-2018.
 */

public class MyServicesAdapter extends RecyclerView.Adapter<MyServicesAdapter.ViewHolder> {
    private Context mContext;
    private int mHeight;
    int w,h,margin;
    List<LoginModel.ResponseBean.SelectedServicesBean> serList;
    InterfacesCall.ServiceOffer clicks;
    int pos;

    public MyServicesAdapter(Context context, List<LoginModel.ResponseBean.SelectedServicesBean> servicesList,
                             int height, InterfacesCall.ServiceOffer interfaces) {
        mContext = context;
        mHeight = height;
        w= (int) mContext.getResources().getDimension(R.dimen._50sdp);
        h= (int) mContext.getResources().getDimension(R.dimen._50sdp);
        serList = servicesList;
        clicks = interfaces;
        margin= (int) mContext.getResources().getDimension(R.dimen._1sdp);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_services, parent, false);
        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        pos = position-1;
        if (holder.getAdapterPosition() == 0) {

//            GradientDrawable bgShape = (GradientDrawable) holder.rlBackground.getBackground();
//            bgShape.setColor(mContext.getResources().getColor(R.color.app_color));

            holder.imgService.setImageDrawable(ContextCompat.getDrawable(mContext,R.mipmap.ic_add_ser_2));
//            Picasso.get()
//                    .load(R.mipmap.ic_add_ser_2)
//                    .transform(new CircleTransform())
//                    .resize((int) (mHeight * 0.05), (int) (mHeight * 0.05))
//                    .into(holder.imgService);
            holder.txtName.setText(mContext.getString(R.string.add_new));

        } else {

//            if (!TextUtils.isEmpty(mData.getProfilePicURL().getOriginal())) {
//                Picasso.with(mContext)
//                        .load(mData.getProfilePicURL().getOriginal())
//                        .transform(new RoundCorners())
//                        .resize((int) (mHeight * 0.13), (int) (mHeight * 0.13))
//                        .placeholder(R.mipmap.ic_img_ph)
//                        .error(R.mipmap.ic_img_ph)
//                        .centerCrop()
//                        .into(imgProfile);
//            } else {
//            GradientDrawable bgShape = (GradientDrawable) holder.rlBackground.getBackground();
//            bgShape.setColor(mContext.getResources().getColor(R.color.cleaner));

            holder.rlBackground.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_background));

            holder.txtName.setText(serList.get(pos).getCategory_name());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w,h);
            params.setMargins(0,margin,0,0);
            holder.rlBackground.setLayoutParams(params);
            Picasso.get()
                    .load(R.mipmap.ic_beauty_w)
                    .transform(new CircleTransform())
                    .into(holder.imgService);
            holder.rlBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clicks.serviceClick(position);
                }
            });
//            }
        }
    }

    @Override
    public int getItemCount() {
        if (serList.size()>8){
            return 9;
        }
        return serList.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_background)
        RelativeLayout rlBackground;

        @BindView(R.id.img_service)
        ImageView imgService;
        @BindView(R.id.txt_name)
        TextView txtName;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
