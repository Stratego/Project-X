package com.uab.lis.rugby.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by adria on 25/03/14.
 */
public class SystemBrodcastReceiver extends BroadcastReceiver {
    private static final String TAG = "SystemBrodcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_SCREEN_OFF)){
            Log.e(TAG, "Backgrounf");
        }else if(action.equals(Intent.ACTION_SCREEN_ON)){
            Log.e(TAG,"Foreground");
        }
    }
}
