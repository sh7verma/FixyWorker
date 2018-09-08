package com.app.fixy_worker.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.app.fixy_worker.R;
import com.app.fixy_worker.activities.CreateProfileActivity;
import com.app.fixy_worker.activities.ViewImageActivity;
import com.app.fixy_worker.adapters.CreateProfilePagerAdapter;
import com.app.fixy_worker.adapters.MyPagerAdapter;
import com.app.fixy_worker.customviews.CircleTransform;
import com.app.fixy_worker.customviews.FloatingEditText;
import com.app.fixy_worker.customviews.MaterialEditText;
import com.app.fixy_worker.dialogs.PhotoSelectionDialog;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.utils.Connection_Detector;
import com.app.fixy_worker.utils.Validations;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class CreateProfileFragment extends BaseFragment {


    private static final int PIC = 12;
    private static final int RC_SIGN_IN = 1;
    @BindView(R.id.rv_main)
    RelativeLayout rvMain;

    @BindView(R.id.ll_no_photo)
    LinearLayout llNoPhoto;
    @BindView(R.id.ed_referral_code)
    EditText edReferralCode;
    @BindView(R.id.img_profile)
    ImageView imgProfile;
    @BindView(R.id.rd_group)
    RadioGroup rdGroup;
    @BindView(R.id.ed_name)
    FloatingEditText edName;
    @BindView(R.id.ed_email)
    FloatingEditText edEmail;

    public  static CreateProfileFragment fragment;

    public static Context mContext;
    private File pathImageFile = null;
    private String imagePath;
    private String gender;

    public CreateProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CreateProfileFragment newInstance(Context context) {
        if (fragment == null) {
            fragment = new CreateProfileFragment();
        }
        mContext = context;
        return fragment;
    }
    View view;

    @Override
    protected int getContentView() {
          view = LayoutInflater.from(mContext).inflate(R.layout.fragment_create_profile,null,false);
        return R.layout.fragment_create_profile;
    }

    @Override
    protected void onCreateStuff() {

        edName.setTypeface(typefaceMedium);
        edEmail.setTypeface(typefaceMedium);
        edReferralCode.setTypeface(typefaceMedium);

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rd_male:
                        gender = InterConst.MALE;
                        break;
                    case R.id.rd_female:

                        gender = InterConst.FEMALE;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.img_profile)
    void pickImageProfile() {

        Intent inProfileno = new Intent(mContext, PhotoSelectionDialog.class);
        inProfileno.putExtra(InterConst.TYPE, "2");// for add photo
        startActivityForResult(inProfileno, PIC);
    }

    @OnClick(R.id.ll_no_photo)
    void pickImage() {

        Intent inProfileno = new Intent(mContext, PhotoSelectionDialog.class);
        inProfileno.putExtra(InterConst.TYPE, "1");// for add photo
        startActivityForResult(inProfileno, PIC);

    }
    @OnClick(R.id.img_referral)
    void imgReferral() {
         showCustomSnackBar(rvMain, getString(R.string.referral_code), getString(R.string.enter_referral_code_detail));
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PIC:
                    if ((new Connection_Detector(mContext)).isConnectingToInternet()) {
                        if (data.getStringExtra(InterConst.RESULT_DATA_KEY).equalsIgnoreCase(InterConst.NULL)) {
                            hideProfilePic();//remove pic
                            pathImageFile = null;
                            utils.setString(InterConst.PROFILE_IMAGE,"");
                        } else if (data.getStringExtra(InterConst.RESULT_DATA_KEY).equalsIgnoreCase(InterConst.SHOW_PIC)) {
                            String picValue = "";
                            if (pathImageFile != null) {
                                picValue = imagePath;
                            } else {
                                picValue = utils.getString(InterConst.PROFILE_IMAGE, "");
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ActivityOptionsCompat option = ActivityOptionsCompat
                                        .makeSceneTransitionAnimation(getActivity(), imgProfile, "full_imageview");
                                Intent in = new Intent(mContext, ViewImageActivity.class);
                                in.putExtra("display", "" + picValue);
                                startActivity(in, option.toBundle());
                            } else {
                                Intent in = new Intent(mContext, ViewImageActivity.class);
                                in.putExtra("display", "" + picValue);
                                startActivity(in);
                            }
                        } else {
                            imagePath = data.getStringExtra(InterConst.RESULT_DATA_KEY);
                            pathImageFile = new File(imagePath);
                            Log.e("IMage Path = ", data.getStringExtra(InterConst.RESULT_DATA_KEY));
                            showImage(pathImageFile);
                            showProfilePic();// on result
                        }

                    } else
                        showInternetAlert(llNoPhoto);
                    break;
                case RC_SIGN_IN:

                    // The Task returned from this call is always completed, no need to attach
                    // a listener.
//                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                    handleSignInResult(task);
                    break;
                case RESULT_CANCELED:

                    break;

            }
        }

    }

    private void hideProfilePic() {
        llNoPhoto.setVisibility(View.VISIBLE);
        imgProfile.setVisibility(View.GONE);
    }

    private void showProfilePic() {
        llNoPhoto.setVisibility(View.GONE);
        imgProfile.setVisibility(View.VISIBLE);
    }

    void showImage(File file) {
        if (file != null) {
            Picasso.get()
                    .load(file)
                    .transform(new CircleTransform())
                    .resize((int) (mHeight * 0.13), (int) (mHeight * 0.13))
                    .placeholder(R.mipmap.ic_profile)
                    .centerCrop(Gravity.TOP)
                    .error(R.mipmap.ic_profile).into(imgProfile, new Callback() {
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
                    .transform(new CircleTransform())
                    .resize((int) (mHeight * 0.13), (int) (mHeight * 0.13))
                    .into(imgProfile);
        }

    }

    private MultipartBody.Part prepareFilePart(File mFile) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), mFile);
        return MultipartBody.Part.createFormData("profile_image", mFile.getName(), requestFile);


    }

    public boolean checkValidation(){
        if (!TextUtils.isEmpty(Validations.checkNameValidation(fragment.getContext(),edName))){
            showValidationSnackBar(rvMain,Validations.checkNameValidation(fragment.getContext(),edName));
            return false;
        }
        else  if (!TextUtils.isEmpty(Validations.checkChangeEmailValidation(fragment.getContext(),edEmail))){
            showValidationSnackBar(rvMain,Validations.checkChangeEmailValidation(fragment.getContext(),edEmail));
            return false;
        }
        else  if (TextUtils.isEmpty(gender)){
            showValidationSnackBar(rvMain,mContext.getResources().getString(R.string.gender_validation));
            return false;
        }
        else  if (TextUtils.isEmpty(imagePath)){
            showValidationSnackBar(rvMain,mContext.getResources().getString(R.string.select_photo_validation));
            return false;
        }

        else {
            return true;
        }
    }


    public RequestBody createPartFromString(String data) {
        return RequestBody.create(MediaType.parse("text/plain"), data);
    }

    private MultipartBody.Part prepareStringPart() {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), "");
        return MultipartBody.Part.createFormData("profile_image", "", requestFile);
    }
}
