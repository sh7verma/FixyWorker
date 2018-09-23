package com.app.fixy_worker.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.SelectServiceActivity;
import com.app.fixy_worker.activities.ViewImageActivity;
import com.app.fixy_worker.adapters.SelectServiceAdapter;
import com.app.fixy_worker.customviews.CircleTransform;
import com.app.fixy_worker.customviews.FloatingEditText;
import com.app.fixy_worker.customviews.HeaderItemDecoration;
import com.app.fixy_worker.customviews.RoundedTransformation;
import com.app.fixy_worker.dialogs.PhotoSelectionDialog;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.AllServiceModel;
import com.app.fixy_worker.models.CreateActivityModel;
import com.app.fixy_worker.models.LoginModel;
import com.app.fixy_worker.models.SelectServiceModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Connection_Detector;
import com.app.fixy_worker.utils.Validations;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class VerificationFragment  extends BaseFragment {

    private static final int PIC = 12;
    private static final int PIC_BACK = 13;
    @BindView(R.id.rd_group)
    RadioGroup rdGroup;
    @BindView(R.id.et_name)
    FloatingEditText etName;
    @BindView(R.id.et_number)
    FloatingEditText etNumber;
    @BindView(R.id.img_front)
    ImageView imgFront;
    @BindView(R.id.img_back)
    ImageView imgBack;

    public  static VerificationFragment fragment;

    public static Context mContext;
    private InterConst.Docs documentType;
    private File pathImageFileFront =null;
    private String imagePathFront ="";
    private File pathImageFileBack =null;
    private String imagePathBack ="";
    CreateActivityModel model;

    public VerificationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VerificationFragment newInstance(Context context) {
        if (fragment == null) {
            fragment = new VerificationFragment();
        }
        mContext = context;
        return fragment;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_verification;
    }

    @Override
    protected void onCreateStuff() {
        model = CreateActivityModel.getInstance();
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId){
                    case R.id.rd_aadhar:
                        etName.setHint(getString(R.string.name_ason_aadhar));
                        etNumber.setHint(getString(R.string.aadhar_number));
                        documentType = InterConst.Docs.AADHAR;
                        break;
                    case R.id.rd_licence:
                        etName.setHint(getString(R.string.name_ason_licence));
                        etNumber.setHint(getString(R.string.licence_number));
                        documentType = InterConst.Docs.LICENCE;
                        break;
                }
            }
        });
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    public  boolean checkValidation(){
        if (imgFront.getVisibility() == View.GONE){
            showValidationSnackBar(imgBack,getString(R.string.add_front_image));

        }
        else if (imgBack.getVisibility() == View.GONE){
            showValidationSnackBar(imgBack,getString(R.string.add_back_image));

        }
        else if (documentType == null){
            showValidationSnackBar(imgBack,getString(R.string.document_type_validation));
        }
        else if (!TextUtils.isEmpty(Validations.checkDocNameValidation(fragment.getContext(),etName))){
            showValidationSnackBar(imgBack,Validations.checkDocNameValidation(fragment.getContext(),etName));
        }
        else if (TextUtils.isEmpty(etName.getText().toString())){
            showValidationSnackBar(imgBack,getString(R.string.document_number_validation));
        }
        else {
            model.setFront_image(imagePathFront);
            model.setBack_image(imagePathBack);
            model.setDocument_number(etNumber.getText().toString().trim());
            model.setDocumented_name(etName.getText().toString().trim());
            model.setBack_image(imagePathBack);
            CreateActivityModel.setInstance(model);
            return true;
        }
        return false;
    }

    @OnClick(R.id.img_front)
    void imgFrontImage() {

        Intent inProfileno = new Intent(mContext, PhotoSelectionDialog.class);
        inProfileno.putExtra(InterConst.TYPE, "2");// for add photo
        startActivityForResult(inProfileno, PIC);
    }
    @OnClick(R.id.img_back)
    void imgBackImage() {

        Intent inProfileno = new Intent(mContext, PhotoSelectionDialog.class);
        inProfileno.putExtra(InterConst.TYPE, "2");// for add photo
        startActivityForResult(inProfileno, PIC_BACK);
    }

    @OnClick(R.id.txt_no_front)
    void noFrontImage() {

        Intent inProfileno = new Intent(mContext, PhotoSelectionDialog.class);
        inProfileno.putExtra(InterConst.TYPE, "1");// for add photo
        startActivityForResult(inProfileno, PIC);

    }

    @OnClick(R.id.txt_no_back)
    void noBackImage() {

        Intent inProfileno = new Intent(mContext, PhotoSelectionDialog.class);
        inProfileno.putExtra(InterConst.TYPE, "1");// for add photo
        startActivityForResult(inProfileno, PIC_BACK);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PIC:
                    if ((new Connection_Detector(mContext)).isConnectingToInternet()) {
                        if (data.getStringExtra(InterConst.RESULT_DATA_KEY).equalsIgnoreCase(InterConst.NULL)) {
                            hideProfilePic(imgFront);//remove pic
                            pathImageFileFront = null;
                            utils.setString(InterConst.PROFILE_IMAGE,"");
                        } else if (data.getStringExtra(InterConst.RESULT_DATA_KEY).equalsIgnoreCase(InterConst.SHOW_PIC)) {
                            String picValue = "";
                            if (pathImageFileFront != null) {
                                picValue = imagePathFront;
                            } else {
                                picValue = utils.getString(InterConst.PROFILE_IMAGE, "");
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ActivityOptionsCompat option = ActivityOptionsCompat
                                        .makeSceneTransitionAnimation(getActivity(), imgFront, "full_imageview");
                                Intent in = new Intent(mContext, ViewImageActivity.class);
                                in.putExtra("display", "" + picValue);
                                startActivity(in, option.toBundle());
                            } else {
                                Intent in = new Intent(mContext, ViewImageActivity.class);
                                in.putExtra("display", "" + picValue);
                                startActivity(in);
                            }
                        } else {
                            imagePathFront = data.getStringExtra(InterConst.RESULT_DATA_KEY);
                            pathImageFileFront = new File(imagePathFront);
                            Log.e("IMage Path = ", data.getStringExtra(InterConst.RESULT_DATA_KEY));
                            showImage(pathImageFileFront,imgFront);
                            showProfilePic(imgFront);// on result
                        }

                    } else {
                        showInternetAlert(imgFront);
                    }
                    break;
                case PIC_BACK:
                    if ((new Connection_Detector(mContext)).isConnectingToInternet()) {
                        if (data.getStringExtra(InterConst.RESULT_DATA_KEY).equalsIgnoreCase(InterConst.NULL)) {
                            hideProfilePic(imgBack);//remove pic
                            pathImageFileBack = null;
                            utils.setString(InterConst.PROFILE_IMAGE,"");
                        } else if (data.getStringExtra(InterConst.RESULT_DATA_KEY).equalsIgnoreCase(InterConst.SHOW_PIC)) {
                            String picValue = "";
                            if (pathImageFileBack != null) {
                                picValue = imagePathBack;
                            } else {
                                picValue = utils.getString(InterConst.PROFILE_IMAGE, "");
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ActivityOptionsCompat option = ActivityOptionsCompat
                                        .makeSceneTransitionAnimation(getActivity(), imgBack, "full_imageview");
                                Intent in = new Intent(mContext, ViewImageActivity.class);
                                in.putExtra("display", "" + picValue);
                                startActivity(in, option.toBundle());
                            } else {
                                Intent in = new Intent(mContext, ViewImageActivity.class);
                                in.putExtra("display", "" + picValue);
                                startActivity(in);
                            }
                        } else {
                            imagePathBack = data.getStringExtra(InterConst.RESULT_DATA_KEY);
                            pathImageFileBack = new File(imagePathBack);
                            Log.e("IMage Path = ", data.getStringExtra(InterConst.RESULT_DATA_KEY));
                            showImage(pathImageFileBack,imgBack);
                            showProfilePic(imgBack);// on result
                        }

                    } else {
                        showInternetAlert(imgBack);
                    }
                    break;
                case RESULT_CANCELED:

                    break;

            }
        }

    }

    private void hideProfilePic(ImageView imageView) {
        imageView.setVisibility(View.GONE);
    }

    private void showProfilePic(ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
    }

    void showImage(File file,ImageView imageView) {
        int wid = (int) mContext.getResources().getDimension(R.dimen._60sdp);
        int hei = (int) mContext.getResources().getDimension(R.dimen._70sdp);
        int radius = (int) mContext.getResources().getDimension(R.dimen._6sdp);
        if (file != null) {
            Picasso.get()
                    .load(file)
                    .transform(new RoundedTransformation(radius,1))
                    .resize(wid, hei)
                    .placeholder(R.mipmap.ic_profile)
                    .centerCrop(Gravity.TOP)
                    .error(R.mipmap.ic_profile).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                    Log.w("", "onError:Sucess code=");
                }

                @Override
                public void onError(Exception e) {
                    Log.w("", "onError:failed code=" + e.getMessage());

                }
            });
        } else {
            Picasso.get()
                    .load(R.mipmap.ic_profile)
                    .transform(new RoundedTransformation(radius,1))
                    .resize(wid, hei)
                    .into(imageView);
        }

    }
}


