package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

                DialogInterface.OnClickListener positive = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final CharSequence[] items = {
                                "Equipo 1","Equipo 2","Equipo 3","Equipo 4"
                        };


                        AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
                        builder.setTitle("Selecciona el ribal");
                        //builder.setMessage("Selecciona el quipo ribal que deseas ganar");
                        builder.setItems(items,new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(MenuPrincipal.this, AndroidStarter.class));
                                    }
                                 }
                        );
                        builder.create().show();
                    }
                };
                DialogInterface.OnClickListener negative = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MenuPrincipal.this, AndroidStarter.class));
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
                builder.setTitle("Selecciona el ribal");
                builder.setMessage("Selecciona el tipo de ribal contra el que deseas jugar");
                builder.setPositiveButton("P vs P", positive);
                builder.setNegativeButton("P vs IA",negative);
                builder.create().show();

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