package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterfacesCall;
import com.app.fixy_worker.models.SelectServiceModel;
import com.app.fixy_worker.utils.Animations;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.ViewHolder> {

    private Context mContext;
    private int mHeight;
    InterfacesCall.IndexClick mcClick;
    int temp=-1;
    ArrayList<SelectServiceModel> models = new ArrayList<>();

    public SearchCategoryAdapter(Context context,int height, InterfacesCall.IndexClick click) {
        mContext = context;
        mHeight = height;
        mcClick = click;
    }

    @NonNull
    @Override
    public SearchCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_single, parent, false);
        return new SearchCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchCategoryAdapter.ViewHolder holder, final int position) {

        if (temp == position){
            holder.viewBooking.llMain.setBackground(mContext.getResources().getDrawable(R.drawable.black_oval));
            Animations.AnimatedClick(mContext,holder.viewBooking.llMain);
        }
        else {
            holder.viewBooking.llMain.setBackground(mContext.getResources().getDrawable(R.drawable.white_oval));

        }
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

//        GradientDrawable bgShape = (GradientDrawable) holder.llBackground.getBackground();
//        bgShape.setColor(Color.BLACK);

//        Picasso.get()
//                .load(R.mipmap.ic_beauty)
//                .transform(new CircleTransform())
//                .resize((int) (mHeight * 0.08), (int) (mHeight * 0.08))
//                .into(holder.imgService);
//            }

        holder.viewBooking.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = position;
                notifyDataSetChanged();
                mcClick.clickIndex(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewBooking {
        @BindView(R.id.ll_main)
        LinearLayout llMain;
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ViewBooking viewBooking = new ViewBooking();
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(viewBooking, itemView);
        }

    }
}


