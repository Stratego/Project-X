package com.uab.lis.rugby.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.libgdx.skeleton.SkeletonMain;
import com.models.Equipo;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.Utilis.EquipoCursor;


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

		initialize(new SkeletonMain(equipo1,equipo2,ia,this), cfg);
        
	}

    @Override
    public void finichMatch(int rEquipo1, int rEquipo2, Equipo equipo1, Equipo equipo2) {

    }
}
