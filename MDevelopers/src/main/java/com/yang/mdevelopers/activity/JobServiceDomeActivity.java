package com.yang.mdevelopers.activity;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yang.mdevelopers.R;
import com.yang.mdevelopers.service.MyJob;

public class JobServiceDomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_service_dome);

        onBtnClick();
    }



  public  class JobSchedulerService extends JobService {


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


    public void onBtnClick() {
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(this, MyJob.class);
        JobInfo.Builder builder = new JobInfo.Builder(1, componentName);
        builder.setMinimumLatency(5000);// 设置任务运行最少延迟时间
         builder.setOverrideDeadline(60000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresDeviceIdle(false);//是否要求设备为idle状态
        builder.setRequiresCharging(true);//是否要设备为充电状态
       System.out.println("注册完毕");
        scheduler.schedule(builder.build());
    //    Log.i(TAG, "schedule job:" + mJobId);
    }
}
