package com.app.fixy_worker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.interfaces.InterfacesCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.ViewHolder> {

    private ArrayList<String> list;
    private final InterfacesCall.IndexClick click;
    Context mContext;
    int mHeight;

    public ListDialogAdapter(Context context, ArrayList<String> arrayList, InterfacesCall.IndexClick mcliIndexClick) {
        mContext = context;
        list = arrayList;
        click = mcliIndexClick;
    }

    @NonNull
    @Override
    public ListDialogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dialog_single, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListDialogAdapter.ViewHolder holder, final int position) {
        holder.txtOption.setText(list.get(position));
        holder.txtOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.clickIndex(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_option)
        TextView txtOption;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
