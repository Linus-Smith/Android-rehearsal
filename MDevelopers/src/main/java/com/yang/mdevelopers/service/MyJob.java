package com.yang.mdevelopers.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

/**
 * Created by Linus on 2017/3/9.
 */

public class MyJob extends JobService{
    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "我执行了哈哈 start job id: "+ params.getJobId(), Toast.LENGTH_SHORT).show();
        jobFinished(params, false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
