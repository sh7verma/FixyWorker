package com.app.fixy_worker.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fixy_worker.R;
import com.app.fixy_worker.helper.MarshMallowPermission;
import com.app.fixy_worker.utils.Connection_Detector;
import com.app.fixy_worker.utils.Encode;
import com.app.fixy_worker.utils.LoadingDialog;
import com.app.fixy_worker.utils.Utils;
import com.google.gson.Gson;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    public MarshMallowPermission mPermission;
    protected int mWidth, mHeight;
    protected Context mContext;
    protected String errorInternet;
    protected String platformStatus = "2";
    protected String errorAPI;
    protected String errorAccessToken;
    protected String terminateAccount;
    //    protected Db db;
    Utils utils;
    Gson mGson = new Gson();
    Encode encode;
    public Typeface typefaceRegular, typefaceBold, typefaceMedium;
//    RoomDb mRoomDb;

    private Snackbar mSnackbar;

    public static void hideKeyboard(Activity mContext) {
        // Check if no view has focus:
        View view = mContext.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        utils = new Utils(getActivity());
//        db = new Db(getActivity());
        encode = new Encode();
        getDefaults();
        mPermission = new MarshMallowPermission(getActivity());
        errorInternet = getResources().getString(R.string.internet);
        errorAPI = getResources().getString(R.string.error);
        errorAccessToken = getResources().getString(R.string.invalid_access_token);
    }

    public int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / getResources().getDimension(R.dimen._60sdp));
        return noOfColumns;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(BaseFragment.this, view);
        mContext = getContext();
//        mRoomDb = Room.databaseBuilder(mContext.getApplicationContext(),
//                RoomDb.class, "nass-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        initListeners();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreateStuff();
    }

    protected abstract int getContentView();

    //onCreate
    protected abstract void onCreateStuff();

    protected abstract void initListeners();

    protected void getDefaults() {
        DisplayMetrics display = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(display);
        mWidth = display.widthPixels;
        mHeight = display.heightPixels;
        Log.e("Height = ", mHeight + " width " + mWidth);
        utils.setInt("width", mWidth);
        utils.setInt("height", mHeight);

        typefaceMedium = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Medium.ttf");
        typefaceBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Bold.ttf");
        typefaceRegular = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Regular.ttf");
    }

    protected void showProgress() {
        LoadingDialog.getLoader().showLoader(getActivity());
    }

    protected void hideProgress() {
        LoadingDialog.getLoader().dismissLoader();
    }

    protected void showSnakbarfragment(View view, String message) {
        mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        mSnackbar.getView().setBackgroundColor(Color.RED);
        mSnackbar.show();
    }

    protected void showSnackBar(View view, String message) {
        mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        mSnackbar.show();
    }

    public boolean connectedToInternet() {
        if ((new Connection_Detector(mContext)).isConnectingToInternet()) {
//            Consts.NO_INTERNET = false;
            return true;
        } else {
            return false;
        }
    }

    public void moveToSplash() {

    }

    protected void showInternetAlert(View view) {
        mSnackbar = Snackbar.make(view, "Internet connection not available!", Snackbar.LENGTH_SHORT);
        mSnackbar.show();
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

    protected void showValidationSnackBar(View containerLayout, String message) {
        LayoutInflater mInflater = LayoutInflater.from(containerLayout.getContext());

        // Create the Snackbar
        mSnackbar = Snackbar.make(containerLayout, message, Snackbar.LENGTH_LONG);
        mSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        mSnackbar.getView().setBackground(mContext.getResources().getDrawable(R.drawable.red_top_round));

        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) mSnackbar.getView();
        // Hide the text
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = mInflater.inflate(R.layout.validation_layout, null);
        // Configure the view
        TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
        textViewTop.setText(message);
        textViewTop.setTextColor(Color.WHITE);
        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
        // Show the Snackbar
        mSnackbar.show();
    }


    public void toast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }


}
