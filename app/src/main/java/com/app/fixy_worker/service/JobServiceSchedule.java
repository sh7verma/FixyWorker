package com.app.fixy_worker.service;

import android.util.Log;

import com.app.fixy_worker.helper.ServiceJob;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class JobServiceSchedule extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {

        Log.e("  JobServiceSchedule","hit again");

        ServiceJob.scheduleJob(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.e("  JobServiceSchedule","hit STOP");
        return false;
    }
}
