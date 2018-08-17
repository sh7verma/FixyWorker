package com.app.fixy.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.app.fixy.R;


public class BookingAdapter  extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private final Context mContext;
    Bitmap bitmap =null;

    private Handler handler = new Handler();
    Runnable runnable ;
    private int progressStatus=0, count =0;

    public BookingAdapter(Context con) {
        mContext = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booked, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressStatus = progressStatus+30;
                count  = count+25;
                handler.postDelayed(runnable,10);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View viewCircle1,viewCircle2,viewCircle3,viewCircle4;
        Button btn;
        ProgressBar vprogressbar;
        public ViewHolder(View itemView) {
            super(itemView);
            viewCircle1 = itemView.findViewById(R.id.view_circle1);
            viewCircle2 = itemView.findViewById(R.id.view_circle2);
            viewCircle3 = itemView.findViewById(R.id.view_circle3);
            viewCircle4 = itemView.findViewById(R.id.view_circle4);
            btn = itemView.findViewById(R.id.btn);
            vprogressbar = itemView.findViewById(R.id.vprogressbar);

            runnable = new Runnable() {
                @Override
                public void run() {
                    progressStatus++;
                    if (progressStatus>0 && progressStatus<2){
                        viewCircle1.setBackground(mContext.getResources().getDrawable(R.drawable.circle_green));
                    }
                    else if (progressStatus>24 && progressStatus<26){

                        viewCircle2.setBackground(mContext.getResources().getDrawable(R.drawable.circle_green));
                    }
                    else if (progressStatus>49 && progressStatus<52){

                        viewCircle3.setBackground(mContext.getResources().getDrawable(R.drawable.circle_green));
                    }
                    else if (progressStatus>73 && progressStatus<76){

                        viewCircle4.setBackground(mContext.getResources().getDrawable(R.drawable.circle_green));
                    }
                    vprogressbar.setProgress(progressStatus);
                    if (progressStatus<count){

                        handler.postDelayed(runnable,10);
                    }
                    else {
                        handler.removeCallbacks(runnable);
                    }
                }
            };


        }
    }
}
