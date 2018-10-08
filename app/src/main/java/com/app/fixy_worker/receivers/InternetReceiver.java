package com.app.fixy_worker.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.fixy_worker.R;
import com.app.fixy_worker.dialogs.InternetDialog;
import com.app.fixy_worker.helper.ServiceJob;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Utils;


public class InternetReceiver extends BroadcastReceiver {
    Utils utils;
    @Override
    public void onReceive(Context context, Intent intent) {
        utils = new Utils(context);
//        InternetDialog dialog = new InternetDialog(context,false, R.style.pullBottomfromTop);
        if (!isOnline(context) && utils.getBoolean(Consts.FOURGROUND,false)){
            ServiceJob.stopJob();
//            dialog.show();
            Intent intent1 = new Intent(context,InternetDialog.class);
            intent1.putExtra(InterConst.EXTRA,InterConst.DISCONNECT);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent1);
        }
        else if(utils.getBoolean(Consts.FOURGROUND,false)){
            ServiceJob.scheduleJob(context);
            Intent intent1 = new Intent(context,InternetDialog.class);
            intent1.putExtra(InterConst.EXTRA,InterConst.CONNECT);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent1);
//            dialog.dismiss();
        }
    }
    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }
}
