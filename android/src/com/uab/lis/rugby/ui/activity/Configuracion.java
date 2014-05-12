package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.TextView;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.SQLiteHelper;

/**
 * Created by Adria on 22/04/2014.
 */
public class Configuracion extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
        Preference preference = findPreference("export");
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SQLiteHelper.backupDatabase();
                return true;
            }
        });
    }
}