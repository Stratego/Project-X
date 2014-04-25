package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.uab.lis.rugby.R;

/**
 * Created by Manuel on 22/04/2014.
 */
public class Tienda extends BaseActivity {

    private TextView tituloTienda;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        tituloTienda = (TextView)findViewById(R.string.activity_tienda_titulo);

        Button fichajes = (Button)findViewById(R.id.btnFichajes);
        Button objetos = (Button)findViewById(R.id.btnObjetos);
        Button otros = (Button)findViewById(R.id.btnOtros);

        fichajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tienda.this,TiendaFichajes.class));
            }
        });

        objetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tienda.this,TiendaObjetos.class));
            }
        });

        otros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tienda.this,TiendaOtros.class));
            }
        });
    }
}