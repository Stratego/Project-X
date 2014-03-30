package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import com.uab.lis.rugby.R;

/**
 * Created by adria on 30/03/14.
 */
public class MenuPrincipal extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meny_principal);
        Button jugar = (Button)findViewById(R.id.btnJugar);
        Button tienda = (Button)findViewById(R.id.btnTienda);
        Button configurar = (Button)findViewById(R.id.btnConfigurar);
    }
}