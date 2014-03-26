package com.uab.lis.rugby.app;

import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.uab.lis.rugby.service.MusicService;

/**
 * Created by adria on 25/03/14.
 */
public class RugbyApplication extends Application {
    private static final String TAG = "application";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"start");
        startService(new Intent(this, MusicService.class));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.e(TAG,"stop");
    }



}
