package com.app.fixy_worker.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.app.fixy_worker.helper.ServiceJob;
import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobDispatcherService extends JobService {

    Utils utils;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e("  JobDispatcherService","hit again");
        if (utils == null){
            utils = new Utils(getApplicationContext());
        }
        hitIncomingRequest();

        ServiceJob.scheduleJob(getApplicationContext());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e("  JobDispatcherService","hit STOP");
        return false;
    }

    public void hitIncomingRequest() {
            ApiInterface apiInterface = RetrofitClient.getInstance();

            Call<RequestModel> call = apiInterface.incomming_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                    utils.getString(InterConst.DEVICE_ID, ""));
            call.enqueue(new Callback<RequestModel>() {
                @Override
                public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {

                    Intent intent  = new Intent(InterConst.INCOMING_BROADCAST);
                    intent.putExtra(InterConst.EXTRA,InterConst.SHOW);
                    sendBroadcast(intent);
                }

                @Override
                public void onFailure(Call<RequestModel> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }



}