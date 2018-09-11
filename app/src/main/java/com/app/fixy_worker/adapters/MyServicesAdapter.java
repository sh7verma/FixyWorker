package com.app.fixy_worker.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shubham verma on 17-08-2018.
 */

public class MyServicesAdapter extends RecyclerView.Adapter<MyServicesAdapter.ViewHolder> {
    private Context mContext;
    private int mHeight;


    public MyServicesAdapter(Context context, int height) {
        mContext = context;
        mHeight = height;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_services, parent, false);
        return new ViewHolder(v, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (holder.getAdapterPosition() == 0) {

            GradientDrawable bgShape = (GradientDrawable) holder.llBackground.getBackground();
            bgShape.setColor(mContext.getResources().getColor(R.color.app_color));

            Picasso.get()
                    .load(R.mipmap.ic_plus_advr)
                    .transform(new CircleTransform())
                    .resize((int) (mHeight * 0.05), (int) (mHeight * 0.05))
                    .into(holder.imgService);
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
            GradientDrawable bgShape = (GradientDrawable) holder.llBackground.getBackground();
            bgShape.setColor(mContext.getResources().getColor(R.color.cleaner));

            Picasso.get()
                    .load(R.mipmap.ic_beauty_w)
                    .transform(new CircleTransform())
                    .resize((int) (mHeight * 0.05), (int) (mHeight * 0.05))
                    .into(holder.imgService);
//            }
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_background)
        LinearLayout llBackground;

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
