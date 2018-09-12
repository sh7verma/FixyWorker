package com.app.fixy_worker.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.utils.Connection_Detector;

import butterknife.ButterKnife;

public abstract class SimpleFragment extends Fragment  {
    private Snackbar mSnackbar;

    abstract void initUI();
    abstract void onCreateStuff();
    abstract void initListener();
    Context mContext = getContext();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        initUI();
        initListener();
        onCreateStuff();
    }


    protected void showCustomSnackBar(View containerLayout, String header, String message) {
        LayoutInflater mInflater = LayoutInflater.from(containerLayout.getContext());

        // Create the Snackbar
        mSnackbar = Snackbar.make(containerLayout, message, Snackbar.LENGTH_LONG);
        mSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        mSnackbar.getView().setBackground(mContext.getResources().getDrawable(R.drawable.primary_top_round));

        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) mSnackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = mInflater.inflate(R.layout.my_snackbar, null);
        // Configure the view
        TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
        textViewTop.setText(message);
        textViewTop.setTextColor(Color.WHITE);

        TextView txtHeader = (TextView) snackView.findViewById(R.id.txt_header);
        txtHeader.setText(header);
        txtHeader.setTextColor(Color.WHITE);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
        // Show the Snackbar
        mSnackbar.show();
    }

    public boolean connectedToInternet() {
        if ((new Connection_Detector(mContext)).isConnectingToInternet())
            return true;
        else
            return false;
    }

    protected void showInternetAlert(View view) {
        mSnackbar = Snackbar.make(view, "Internet connection not available!", Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }


}
