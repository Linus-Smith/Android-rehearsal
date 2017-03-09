package com.yang.mdevelopers.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Linus on 2017/3/9.
 */

public class BatteryBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("广播来了哈哈");
        Toast.makeText(context,"充电了哈哈", Toast.LENGTH_SHORT).show();
    }
}
