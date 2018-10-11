package com.app.fixy_worker.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.app.fixy_worker.interfaces.InterConst;
import com.app.fixy_worker.models.RequestModel;
import com.app.fixy_worker.network.ApiInterface;
import com.app.fixy_worker.network.RetrofitClient;
import com.app.fixy_worker.utils.Consts;
import com.app.fixy_worker.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobDispatcherService extends JobService {

    Utils utils;


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e("  JobDispatcherService", "hit again");
        if (utils == null) {
            utils = new Utils(getApplicationContext());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            scheduleRefresh();
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            startService(new Intent(getApplicationContext(), NewRequestService.class));
        } else {
            startForegroundService(new Intent(getApplicationContext(), NewRequestService.class));
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e("  JobDispatcherService", "hit STOP");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            stopService(new Intent(getApplicationContext(), NewRequestService.class));
        } else {
            stopForeground(Service.STOP_FOREGROUND_REMOVE);
        }
        return false;
    }

    public void hitIncomingRequest() {
        ApiInterface apiInterface = RetrofitClient.getInstance();

        Call<RequestModel> call = apiInterface.incomming_request(utils.getString(InterConst.ACCESS_TOKEN, ""),
                utils.getString(InterConst.DEVICE_ID, ""));
        call.enqueue(new Callback<RequestModel>() {
            @Override
            public void onResponse(Call<RequestModel> call, Response<RequestModel> response) {

                if (utils.getBoolean(Consts.FOURGROUND, false) &&
                        utils.getInt(InterConst.ON_BOOKING, InterConst.ZERO) == InterConst.ZERO) {

                    Intent intent = new Intent(InterConst.INCOMING_BROADCAST);
                    intent.putExtra(InterConst.EXTRA, InterConst.SHOW);
                    // call broadcast of NewIncomingPopoupActivity
                    if (response.body().getResponse().size() > 0) {
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

    int RECALL =15 * 1000;
    private void scheduleRefresh() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName jobService = new ComponentName(getPackageName(), JobDispatcherService.class.getName());
        JobInfo jobInfo;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(1, jobService).setMinimumLatency(RECALL).build();
        } else {
            jobInfo = new JobInfo.Builder(1, jobService).setPeriodic(RECALL).build();
        }
        jobScheduler.schedule(jobInfo);
    }


}