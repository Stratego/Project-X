package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.*;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.Utilis.JugadorCursor;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.models.Jugador;
import com.uab.lis.rugby.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adria on 08/05/2014.
 */
public class SelectPositionPlayers extends Activity {
    public static final String ID_EQUIP_RIBAL = "idequiporival";
    public static final String IA = "ia";
    private List<Jugador> jugadores;
    private List<Drawable> equipaciones;
    private Adapter adapter;
    private int IDequipo = -1;
    private int IDuser = -1;
    private int ribal = -1;
    private boolean ia = false;
    public static final String ID_EQUIP = "idequip";
    public static final String ID_USER = "iduser";

    private HashMap<Integer,Jugador> listaFinal = new HashMap<Integer,Jugador>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_position_players);

        Intent intent = getIntent();
        IDequipo = intent.getIntExtra(ID_EQUIP,-1);
        IDuser = intent.getIntExtra(ID_USER,-1);
        ia = intent.getBooleanExtra(IA, false);
        ribal = intent.getIntExtra(ID_EQUIP_RIBAL,-1);
        Log.e("datos","equipo:"+IDequipo + " user:"+IDuser + " ia:" + ia + " id_equipo_ribal:"+ribal);


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
                view.setTag(new Integer[]{f,c});
                view.setBackgroundColor(getResources().getColor(color));
                view.setOnDragListener(new MyDragListener());
            }
        }
        //todo fer la query perque vingin tots de la base de dades 

        ListView lista = (ListView) findViewById(R.id.lista);

        jugadores = new ArrayList<Jugador>();
        equipaciones = new ArrayList<Drawable>();
        initList(IDequipo);
        initList(ribal);
        adapter = new Adapter(this,jugadores,equipaciones);
        lista.setAdapter(adapter);
    }

    private void initList(int idequipo){
        Uri urijugadores = UrisGenerated.getUriJugadoresEquipo(IDuser,idequipo);
        Cursor cursor = getContentResolver().query(urijugadores,null,null,null,null);
        cursor.moveToFirst();
        Drawable equipacion = null;
        try {
            equipacion = Utils.getDrawableFromAssets(this, cursor.getString(cursor.getColumnIndex(tbEquipos.COL_EQUIPACION)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        do{
            Jugador jugador = JugadorCursor.newInstance(cursor);
            equipaciones.add(equipacion);
            jugadores.add(jugador);

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
                    if(view.getParent() instanceof ListView){
                        ListView owner = (ListView) view.getParent();
                        ArrayAdapter adapter = (ArrayAdapter) owner.getAdapter();

                        LinearLayout container = (LinearLayout) v;
                        Object[] xy = (Object[]) container.getTag();
                        Jugador jugador = (Jugador)(((Object[])view.getTag())[0]);
                        Drawable equipacion = (Drawable)(((Object[])view.getTag())[1]);
                        jugador.setPosX(Integer.parseInt(xy[0].toString()));
                        jugador.setPosY(Integer.parseInt(xy[1].toString()));

                        listaFinal.put(jugador.getId(),jugador);

                        View item = getLayoutInflater().inflate(R.layout.row_select_player,null);
                        ImageView image = (ImageView) item.findViewById(android.R.id.icon);
                        TextView text = (TextView) item.findViewById(android.R.id.text1);
                        item.setOnTouchListener(new MyTouchListener());
                        if(equipacion != null) {
                            image.setImageDrawable(equipacion);
                        }else{
                            image.setImageResource(R.drawable.icon);
                        }
                        text.setText(jugador.getNombre());
                        item.setTag(jugador);
                        container.addView(item);
                        item.setVisibility(View.VISIBLE);

                        adapter.remove((Jugador)(((Object[])view.getTag())[0]));
                        adapter.notifyDataSetChanged();
                    }else{
                        ViewGroup owner = (ViewGroup) view.getParent();
                        Jugador jugador = (Jugador)view.getTag();
                        LinearLayout container = (LinearLayout) v;
                        Object[] xy = (Object[]) container.getTag();
                        jugador.setPosX(Integer.parseInt(xy[0].toString()));
                        jugador.setPosY(Integer.parseInt(xy[1].toString()));
                        owner.removeView(view);

                        listaFinal.put(jugador.getId(),jugador);

                        Drawable equipacion = ((ImageView)(view.findViewById(android.R.id.icon))).getDrawable();

                        View item = getLayoutInflater().inflate(R.layout.row_select_player,null);
                        ImageView image = (ImageView) item.findViewById(android.R.id.icon);
                        TextView text = (TextView) item.findViewById(android.R.id.text1);
                        item.setOnTouchListener(new MyTouchListener());
                        if(equipacion != null) {
                            image.setImageDrawable(equipacion);
                        }else{
                            image.setImageResource(R.drawable.icon);
                        }
                        text.setText(jugador.getNombre());
                        item.setTag(jugador);

                        container.addView(item);

                    }
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
            for(Jugador jugador : listaFinal.values()){
                Log.e("jugadores",jugador.toString());
                ContentValues values = JugadorCursor.generateValues(jugador);
                Uri uri = UrisGenerated.getUriJugadoresEquipoItem(IDuser,IDequipo,jugador.getId());
                int id = cr.update(uri,values,null,null);
            }
            Intent intent = new Intent(SelectPositionPlayers.this,AndroidStarter.class);
            intent.putExtra(AndroidStarter.IA,ia);
            intent.putExtra(AndroidStarter.IDEQUIPO,IDequipo);
            intent.putExtra(AndroidStarter.IDRIBAL,ribal);
            intent.putExtra(AndroidStarter.IDUSER,IDuser);
            startActivity(intent);
        }
    }

    private class CancelarListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            SelectPositionPlayers.this.finish();
        }
    }
    private class Adapter extends ArrayAdapter{
        private List<Jugador> jugadores;
        private List<Drawable> equipaciones;
        public Adapter(Context context, List<Jugador> jugadores, List<Drawable> equipaciones) {
            super(context, 0);
            this.jugadores = jugadores;
            this.equipaciones = equipaciones;
        }

        @Override
        public int getCount() {
            return this.jugadores.size();
        }

        @Override
        public Jugador getItem(int position) {
            return jugadores.get(position);
        }

        @Override
        public void remove(Object object) {
            int position = jugadores.indexOf(object);
            jugadores.remove(position);
            equipaciones.remove(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = getLayoutInflater().inflate(R.layout.row_select_player,null);
            ImageView image = (ImageView) item.findViewById(android.R.id.icon);
            TextView text = (TextView) item.findViewById(android.R.id.text1);
            item.setOnTouchListener(new MyTouchListener());

            Jugador jugador = getItem(position);
            Drawable equipacion = equipaciones.get(position);
            if(equipacion != null) {
                image.setImageDrawable(equipacion);
            }else{
                image.setImageResource(R.drawable.icon);
            }
            text.setText(jugador.getNombre());
            item.setTag(new Object[]{jugador,equipacion});
            return item;
        }
    }
}