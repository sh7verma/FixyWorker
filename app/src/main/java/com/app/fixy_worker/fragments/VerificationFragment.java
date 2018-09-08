package com.app.fixy_worker.fragments;

import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.FloatingEditText;

import butterknife.BindView;

public class VerificationFragment  extends BaseFragment {

    @BindView(R.id.rd_group)
    RadioGroup rdGroup;
    @BindView(R.id.et_name)
    FloatingEditText etName;
    @BindView(R.id.et_number)
    FloatingEditText etNumber;

    public  static VerificationFragment fragment;

    public static Context mContext;
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
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId){
                    case R.id.rd_aadhar:
                        etName.setHint("Your Name as on AADHAAR CARD");
                        etNumber.setHint("AADHAAR NUMBER");
                        break;
                    case R.id.rd_licence:
                        etName.setHint("Your Name as on DRIVING LICENCE");
                        etNumber.setHint("LICENCE NUMBER");
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

}


