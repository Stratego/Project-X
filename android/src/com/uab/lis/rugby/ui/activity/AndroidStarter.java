package com.uab.lis.rugby.ui.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.libgdx.skeleton.SkeletonMain;
import com.models.Equipo;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.Utilis.EquipoCursor;
import com.uab.lis.rugby.database.Utilis.HistorialPartidosCursor;
import com.uab.lis.rugby.database.models.HistorialPartido;


public class AndroidStarter extends AndroidApplication implements SkeletonMain.CollBack{
    public static final String IA = "ia";
    public static final String IDEQUIPO = "idequipo";
    public static final String IDRIBAL = "idribal";
    public static final String IDUSER = "iduser";


    @Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useAccelerometer = false;
		cfg.useCompass = false;
		cfg.useWakelock = true;
		cfg.useGL20 = true;

        Intent intent = getIntent();
        int idEquipo1 = intent.getIntExtra(IDEQUIPO,-1);
        int idEquipo2 = intent.getIntExtra(IDRIBAL,-1);
        int idUser = intent.getIntExtra(IDUSER, -1);
        boolean ia = intent.getBooleanExtra(IA,false);

        Uri uriEquipo1 = UrisGenerated.getUriEquipo(idUser,idEquipo1);
        Uri uriEquipo2 = UrisGenerated.getUriEquipo(0,idEquipo2);

        Cursor cursorEquipo1 = getContentResolver().query(uriEquipo1,null,null,null,null);
        Cursor cursorEquipo2 = getContentResolver().query(uriEquipo2,null,null,null,null);

        cursorEquipo1.moveToFirst();
        cursorEquipo2.moveToFirst();

        Equipo equipo1 = EquipoCursor.newInstance(this,cursorEquipo1,idUser);
        Equipo equipo2 = EquipoCursor.newInstance(this,cursorEquipo2,1);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musuc = pref.getBoolean("musicPower",true);
		initialize(new SkeletonMain(equipo1,equipo2,ia,musuc,this), cfg);

        finichMatch(50,20,equipo1,equipo2);
	}

    @Override
    public void finichMatch(int rEquipo1, int rEquipo2, Equipo equipo1, Equipo equipo2) {
        HistorialPartido historial = new HistorialPartido();
        historial.setIdEquipo1((int)equipo1.getId());
        historial.setIdEquipo2((int) equipo2.getId());
        historial.setPuntuacionEquipo1(rEquipo1);
        historial.setPuntuacionEquipo2(rEquipo2);
        ContentValues values = HistorialPartidosCursor.generateValues(historial);

        Uri uri = UrisGenerated.getUriHistorialPArtida();
        Uri uriItem = getContentResolver().insert(uri,values);

        Toast.makeText(this,uriItem.toString(),Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,MenuPrincipal.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
