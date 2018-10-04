package com.app.fixy_worker.service;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class JobServiceSchedule extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
