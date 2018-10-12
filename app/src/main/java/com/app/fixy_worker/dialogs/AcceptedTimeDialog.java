package com.app.fixy_worker.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fixy_worker.R;
import com.app.fixy_worker.customviews.FlowLayout;
import com.app.fixy_worker.interfaces.InterfacesCall;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;

public class AcceptedTimeDialog extends BaseDialog {


    @BindView(R.id.flow_layout)
    FlowLayout flowlayout;
    @BindView(R.id.ic_close)
    ImageView icClose;
    @BindView(R.id.txt_done)
    TextView txtDone;

    HashMap<Integer, String> selectedList = new HashMap<>();
    List<String> timeSlots = new ArrayList<>();
    private final ListDialog.CallBack clicks;

    public AcceptedTimeDialog(@NonNull Context context, int themeResId, ListDialog.CallBack click) {
        super(context, themeResId);
        clicks = click;
        WindowManager.LayoutParams wmlp = this.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wmlp);
    }

    protected AcceptedTimeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        clicks = null;
    }

    @Override
    public InterfacesCall.IndexClick getInterfaceInstance() {
        return this;
    }

    @Override
    protected void onCreateStuff() {
        initListener();
        timeSlots = displayTimeSlots();
        createChips();
    }

    private void initListener() {
        icClose.setOnClickListener(this);
        txtDone.setOnClickListener(this);
    }

    @Override
    public int getContentView() {
        return R.layout.accepted_time_dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_close:
                dismiss();
                break;
            case R.id.txt_done:
                String time = TextUtils.join("",selectedList.values());
                clicks.click(time);
                dismiss();
                break;
        }
    }

    @Override
    public void clickIndex(int pos) {

    }

    void createChips() {
        flowlayout.removeAllViews();
        for (int i = 0; i < timeSlots.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.accept_time_item, null, false);

            final TextView txtView = view.findViewById(R.id.txt_name);
            txtView.setText(timeSlots.get(i));
            txtView.setTag(i);
            if (selectedList.containsKey(i)) {
                txtView.setBackgroundResource(R.drawable.chip_background_fill);
                txtView.setTextColor(getContext().getResources().getColor(R.color.white));
            } else {
                txtView.setBackgroundResource(R.drawable.chips_background);
                txtView.setTextColor(getContext().getResources().getColor(R.color.black));
            }

            flowlayout.addView(view);
            txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedList = new HashMap<>();
                    txtView.setBackgroundResource(R.drawable.chip_background_fill);
                    txtView.setTextColor(getContext().getResources().getColor(R.color.white));
                    selectedList.put((Integer) txtView.getTag(), txtView.getText().toString());
                    createChips();

                }
            });
        }
    }

    private int getHoursValue(int hours) {
        return hours - 12;
    }


    private List<String> displayTimeSlots() {
//        String timeValue = "2015-10-28T18:37:04.899+05:30";
        List<String> timeSlots = new ArrayList<>();
        String timeValue = "2018-10-12 07:10:59";
        StringTokenizer stringTokenizer = new StringTokenizer(timeValue, " ");
        String dateValue = stringTokenizer.nextElement().toString();
        String restString = stringTokenizer.nextElement().toString();
        StringTokenizer secondTokeniser = new StringTokenizer(restString, ":");
        String hours = secondTokeniser.nextElement().toString();
        String minutes = secondTokeniser.nextElement().toString();
        hours = String.valueOf(Integer.parseInt(hours));
        if (Integer.parseInt(minutes) > 30) {
            minutes = "00";
        } else {
            minutes = "30";
        }

        String amOrPm;
        if (Integer.parseInt(hours) < 12) {
            amOrPm = "AM";
        } else {
            amOrPm = "PM";
            hours = String.valueOf(getHoursValue(Integer.parseInt(hours)));
        }
        String time1 = hours + ":" + minutes + " " + amOrPm;
        String time2 = "12" + ":" + "00" + " AM ";
        String format = "yyyy-MM-dd hh:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            Date dateObj1 = sdf.parse(dateValue + " " + time1);
            // next day day from created date
            Date dateObj2 = sdf.parse(dateValue + " " + time1);
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj2);
            c.add(Calendar.HOUR, 10);
            dateObj2 = c.getTime();
            //--------------------
            Log.d("TAG", "Date Start: " + dateObj1);
            Log.d("TAG", "Date End: " + dateObj2);
            long dif = dateObj1.getTime();
            String range;
            while (dif < dateObj2.getTime()) {
                Date slot1 = new Date(dif);
                dif += 60 * 60000;
                Date slot2 = new Date(dif);
//                dif += 60 * 60000;
                SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
//                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a, dd/MM/yy");
                range = sdf1.format(slot1) + " - " + sdf1.format(slot2);
                timeSlots.add(range);
                Log.d("TAG", "Hour slot = " + sdf1.format(slot1) + " - " + sdf1.format(slot2));
            }
            return timeSlots;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return timeSlots;
    }
}
