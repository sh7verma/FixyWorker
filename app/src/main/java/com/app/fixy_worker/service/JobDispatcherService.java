package com.app.fixy_worker.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.app.fixy_worker.helper.ServiceJob;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobDispatcherService extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e("service call","hit again");
        ServiceJob.scheduleJob(getApplicationContext());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e("service call","hit STOP");
        return false;
    }



}