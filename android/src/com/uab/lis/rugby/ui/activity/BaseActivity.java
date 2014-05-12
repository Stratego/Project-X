package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.uab.lis.rugby.service.MusicService;

/**
 * Created by adria on 27/03/14.
 */
public class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        Intent intent = new Intent(MusicService.IntentFilterMusicStart);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
        Intent intent = new Intent(MusicService.IntentFilterMusicStop);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
