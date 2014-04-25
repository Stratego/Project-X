package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.TextView;
import com.uab.lis.rugby.R;

/**
 * Created by Adria on 22/04/2014.
 */
public class Configuracion extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
    }
}