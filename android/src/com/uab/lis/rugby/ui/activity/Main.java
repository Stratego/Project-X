package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences preferencias = getSharedPreferences("firstEje", Context.MODE_PRIVATE);

        boolean first = preferencias.getBoolean("first",true);

        if(first){
            startActivity(new Intent(this,CreateUser.class));
        }else{
            startActivity(new Intent(this,MenuPrincipal.class));
        }
    }
}