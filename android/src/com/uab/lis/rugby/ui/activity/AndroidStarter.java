package com.uab.lis.rugby.ui.activity;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.libgdx.skeleton.SkeletonMain;
import com.models.Equipo;


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


        Equipo equipo1 = null;
        Equipo equipo2 = null;
		initialize(new SkeletonMain(equipo1,equipo2,true,this), cfg);
        
	}

    @Override
    public void finichMatch(int rEquipo1, int rEquipo2, Equipo equipo1, Equipo equipo2) {

    }
}
