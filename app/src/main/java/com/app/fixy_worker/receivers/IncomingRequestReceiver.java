package com.app.fixy_worker.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.fixy_worker.activities.NewIncomingPopupActivity;
import com.app.fixy_worker.interfaces.InterConst;

public class IncomingRequestReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getIntExtra(InterConst.EXTRA,-1) == InterConst.SHOW) {
            Intent intent1 = new Intent(context, NewIncomingPopupActivity.class);
            intent1.putExtra(InterConst.EXTRA, InterConst.SHOW);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent1);
        }
        else if (intent.getIntExtra(InterConst.EXTRA,-1) == InterConst.DISMISS){

            Intent intent1 = new Intent(context, NewIncomingPopupActivity.class);
            intent1.putExtra(InterConst.EXTRA, InterConst.DISMISS);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent1);
        }

//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(2000);
    }
}
