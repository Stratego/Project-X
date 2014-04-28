package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.ContentProviders.MyAppContentProvider;
import com.uab.lis.rugby.database.contracts.tbJugadores;
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

        Class classe;
        if(first){
            classe = CreateUser.class;
        }else{
            classe = MenuPrincipal.class;
        }

        Intent intent = new Intent(this,classe);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        Uri uri = Uri.withAppendedPath(MyAppContentProvider.URI_BASE, "jugadores");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.close();
    }
}