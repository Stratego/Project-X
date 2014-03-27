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
public class Main extends BaseActivity {
    private boolean state = true;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /** Intent intent = new Intent(state == true ? MusicService.IntentFilterMusicStop : MusicService.IntentFilterMusicStart);
                LocalBroadcastManager.getInstance(Main.this).sendBroadcast(intent);
                state = !state;**/
                startActivity(new Intent(Main.this,AndroidStarter.class));
            }
        });
    }
}