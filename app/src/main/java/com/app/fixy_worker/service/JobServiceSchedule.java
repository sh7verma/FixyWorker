package com.app.fixy_worker.service;

import android.content.Intent;
import android.util.Log;

import com.app.fixy_worker.helper.ServiceJob;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Utils;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobServiceSchedule extends JobService {

    Utils utils;
    @Override
    public boolean onStartJob(JobParameters job) {

        Log.e("  JobServiceSchedule","hit again");
        if (utils == null){
            utils = new Utils(getApplicationContext());
        }
        ServiceJob.scheduleJob(getApplicationContext());
        hitIncomingRequest();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.e("  JobServiceSchedule","hit STOP");
        return false;
    }

    public void hitIncomingRequest() {
        ApiInterface apiInterface = RetrofitClient.getInstance();

        Call<RequestModel> call = apiInterface.incomming_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""));
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {

                if(utils.getBoolean(Consts.FOURGROUND,false) &&
                        utils.getInt(InterConst.ON_BOOKING,InterConst.ZERO) == InterConst.ZERO){

                    Intent intent  = new Intent(InterConst.INCOMING_BROADCAST);
                    intent.putExtra(InterConst.EXTRA,InterConst.SHOW);
                    // call broadcast of NewIncomingPopoupActivity
                    if (response.body().getResponse().size()>0){
                        sendBroadcast(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
