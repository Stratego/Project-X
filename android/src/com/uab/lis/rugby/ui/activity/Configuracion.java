package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.uab.lis.rugby.R;

/**
 * Created by Manuel on 22/04/2014.
 */
public class Configuracion extends Activity {

    private TextView tituloConfiguracion;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        tituloConfiguracion = (TextView)findViewById(R.string.activity_configuracion_titulo);
    }
}