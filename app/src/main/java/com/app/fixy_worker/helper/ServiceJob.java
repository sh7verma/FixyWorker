package com.app.fixy_worker.helper;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.app.fixy_worker.service.JobDispatcherService;
import com.app.fixy_worker.service.JobServiceSchedule;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class ServiceJob {

    public static String JOB_NAME = "job_incoming";
    public static JobScheduler jobScheduler = null;
    public static FirebaseJobDispatcher dispatcher= null;
    public static void stopJob(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler.cancelAll();
        }
        else {
            dispatcher.cancel(JOB_NAME);
        }
    }
    public static void scheduleJob(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ComponentName serviceComponent = new ComponentName(context, JobDispatcherService.class);
            JobInfo.Builder builder = null;
            builder = new JobInfo.Builder(10, serviceComponent);
            builder.setMinimumLatency(3 * 1000); // wait at least
            builder.setOverrideDeadline(3 * 1000); // maximum delay
//            builder.setPeriodic(3000);
            builder.setBackoffCriteria(6000, JobInfo.BACKOFF_POLICY_LINEAR);
//            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // require unmetered network
            //builder.setRequiresDeviceIdle(true); // device should be idle
            //builder.setRequiresCharging(false); // we don't care if the device is charging or not
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                jobScheduler = context.getSystemService(JobScheduler.class);
            }
            else {
                jobScheduler = (JobScheduler)context.getSystemService(JOB_SCHEDULER_SERVICE);
            }
             jobScheduler.schedule(builder.build());

        }
        else {
             dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
            Job myJob = dispatcher.newJobBuilder()
                    .setService(JobServiceSchedule.class) // the JobServiceSchedule that will be called
                    .setTag(JOB_NAME)        // uniquely identifies the job
                    .build();

            dispatcher.mustSchedule(myJob);

        }
    }
}
