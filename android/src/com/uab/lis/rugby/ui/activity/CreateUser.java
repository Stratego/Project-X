package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.uab.lis.rugby.R;

/**
 * Created by adria on 30/03/14.
 */
public class CreateUser extends Activity {
    private EditText nameUser;
    private EditText nameEquipo;
    private ViewPager escudo;
    private ViewPager camiseta;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        nameUser = (EditText)findViewById(R.id.edtNombreJugador);
        nameEquipo = (EditText)findViewById(R.id.edtNombreEquipo);
        camiseta = (ViewPager)findViewById(R.id.vp_Ropa);
        escudo = (ViewPager)findViewById(R.id.vp_Escudos);
        Button aceptar = (Button)findViewById(R.id.btnAceptar);
        Button cancelar = (Button)findViewById(R.id.btnCancelar);
        aceptar.setOnClickListener(new OnClickButton());
        cancelar.setOnClickListener(new OnClickButton());


    }

    private class OnClickButton implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnAceptar:
                    startActivity(new Intent(CreateUser.this,MenuPrincipal.class));
                    break;
                case R.id.btnCancelar:
                    break;
            }
        }
    }
}