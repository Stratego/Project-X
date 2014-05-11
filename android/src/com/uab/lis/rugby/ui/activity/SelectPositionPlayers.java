package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.models.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adria on 08/05/2014.
 */
public class SelectPositionPlayers extends Activity {
    private List<Jugador> jugadores;
    private int IDequipo = -1;
    private int IDuser = -1;
    public static final String ID_EQUIP = "idequip";
    public static final String ID_USER = "iduser";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_position_players);

        Intent intent = getIntent();
        IDequipo = intent.getIntExtra(ID_EQUIP,-1);
        IDuser = intent.getIntExtra(ID_USER,-1);

        Log.e("datos","equipo:"+IDequipo + " user:"+IDuser);


        View ButtonAceptar = findViewById(R.id.Aceptar);
        View ButtonCancelar = findViewById(R.id.Cancelar);
        ButtonAceptar.setOnClickListener(new AceptarListener());
        ButtonCancelar.setOnClickListener(new CancelarListener());

        int[] fil = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int[] col = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int color = 0;
        for(int f : fil){
            color = color == android.R.color.holo_green_light ? android.R.color.holo_green_dark : android.R.color.holo_green_light;
            for(int c : col){
                color = color == android.R.color.holo_green_light ? android.R.color.holo_green_dark : android.R.color.holo_green_light;
                Log.e("error","A"+f+"A"+c);
                int id = getResources().getIdentifier("A"+f+"A"+c,"id","com.uab.lis.rugby");
                View view = findViewById(id);
                view.setTag(new int[]{f,c});
                view.setBackgroundColor(getResources().getColor(color));
                view.setOnDragListener(new MyDragListener());
            }
        }
        //todo fer la query perque vingin tots de la base de dades
        LinearLayout lista = (LinearLayout) findViewById(R.id.lista);
        jugadores = new ArrayList<Jugador>();
        Uri urijugadores = UrisGenerated.getUriJugadoresEquipo(IDuser,IDequipo);
        Cursor cursor = getContentResolver().query(urijugadores,null,null,null,null);
        cursor.moveToFirst();
        do{
            Jugador jugador = Jugador.newInstance(cursor);
            ImageView image = new ImageView(this);
            image.setOnTouchListener(new MyTouchListener());
            image.setImageResource(R.drawable.icon);

            jugadores.add(jugador);
            image.setTag(jugador);
            lista.addView(image);

        }while (cursor.moveToNext());

    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    //try {
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        int[] xy = (int[]) container.getTag();
                        Jugador jugador = (Jugador)view.getTag();
                        jugador.setPosX(xy[0]);
                        jugador.setPosY(xy[1]);
                        view.setTag(jugador);

                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                    //}catch(Exception ex){

                    //}
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }

    private class AceptarListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ContentResolver cr = SelectPositionPlayers.this.getContentResolver();
            for(Jugador jugador : jugadores){
                ContentValues values = Jugador.generateValues(jugador);
                Uri uri = UrisGenerated.getUriJugadoresEquipoItem(IDuser,IDequipo,jugador.getId());
                int id = cr.update(uri,values,null,null);
            }
        }
    }

    private class CancelarListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            SelectPositionPlayers.this.finish();
        }
    }
}