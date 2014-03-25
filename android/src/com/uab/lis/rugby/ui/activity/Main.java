package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.service.MusicService;

/**
 * Created by adria on 13/03/14.
 */
public class Main extends Activity {
    private static final String TAG ="Main";
    private boolean state = true;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(state == true ? MusicService.IntentFilterMusicStop : MusicService.IntentFilterMusicStart);
                LocalBroadcastManager.getInstance(Main.this).sendBroadcast(intent);
                state = !state;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        Intent intent = new Intent(MusicService.IntentFilterMusicStart);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        Intent intent = new Intent(MusicService.IntentFilterMusicStop);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}