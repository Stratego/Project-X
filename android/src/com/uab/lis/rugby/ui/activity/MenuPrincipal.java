package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.uab.lis.rugby.R;

/**
 * Created by adria on 30/03/14.
 */
public class MenuPrincipal extends BaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meny_principal);
        Button jugar = (Button)findViewById(R.id.btnJugar);
        Button tienda = (Button)findViewById(R.id.btnTienda);
        Button configuracion = (Button)findViewById(R.id.btnConfigurar);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this,AndroidStarter.class));
            }
        });

        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this,Configuracion.class));
            }
        });

        tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrincipal.this,Tienda.class));
            }
        });
    }
}