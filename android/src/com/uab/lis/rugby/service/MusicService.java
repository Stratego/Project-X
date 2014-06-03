package com.uab.lis.rugby.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import com.uab.lis.rugby.R;

/**
 * Servicio que gestiona la reproducción de la música en el juego.
 */
public class MusicService extends Service {
    private static final String TAG = "MusicService";
    public static final String IntentFilterMusicStart = "com.uab.lis.rugby.service.MusicService:music:start";
    public static final String IntentFilterMusicStop = "com.uab.lis.rugby.service.MusicService:music:stop";

    private MediaPlayer mediaPlayer;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        /*MusicBrodcastReceiver brodcast = new MusicBrodcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(brodcast, new IntentFilter(IntentFilterMusicStart));
        LocalBroadcastManager.getInstance(this).registerReceiver(brodcast, new IntentFilter(IntentFilterMusicStop));*/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
       /* Intent intent2 = new Intent(MusicService.IntentFilterMusicStart);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);*/
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        /*Log.e(TAG,"onDestroy");
        mediaPlayer.stop();
        super.onDestroy();*/
    }





    public final class MusicBrodcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            if(pref.getBoolean("musicPower",true)){

                String action = intent.getAction();
                if(action.equals(MusicService.IntentFilterMusicStart)){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music_bird);
                    mediaPlayer.setVolume(5,100);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    Log.e(TAG,"Play");
                }else if(action.equals(MusicService.IntentFilterMusicStop)){
                    if(mediaPlayer != null){
                        mediaPlayer.stop();
                    }
                    Log.e(TAG,"Stop");
                }
            }
        }
    }

}
