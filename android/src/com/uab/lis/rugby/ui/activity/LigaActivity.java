package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.models.Equipo;
import com.uab.lis.rugby.R;
import com.uab.lis.rugby.database.ContentProviders.MyAppContentProvider;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.Utilis.EquipoCursor;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import com.uab.lis.rugby.database.contracts.tbLiga;
import com.uab.lis.rugby.utils.Utils;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Adria on 20/05/2014.
 */
public class LigaActivity extends ListActivity {
    public static final String IDUSER = "iduser";
    private int idUser;
    private Cursor cursor;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idUser = getIntent().getIntExtra(IDUSER,-1);

        String where = tbLiga.COL_ID_LIGA + " = " + 0;
        cursor = getContentResolver().query(Uri.withAppendedPath(MyAppContentProvider.URI_BASE,tbLiga.TABLE),null,where,
                null,null);
        LigaAdaper adaper = new LigaAdaper(this,cursor);
        setListAdapter(adaper);
        ListView list = getListView();
        list.setDividerHeight(10);
        list.setPadding(10,10,10,10);
        list.setBackgroundResource(R.drawable.herba);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        cursor.moveToPosition(position);
        int idEquipo1 = cursor.getInt(cursor.getColumnIndex(tbLiga.COL_ID_EQUIPO_1));
        int idEquipo2 = cursor.getInt(cursor.getColumnIndex(tbLiga.COL_ID_EQUIPO_2));
        int puntuacio1 = cursor.getInt(cursor.getColumnIndex(tbLiga.COL_PUNTUACION_EQUIPO_1));
        int puntuacio2 = cursor.getInt(cursor.getColumnIndex(tbLiga.COL_PUNTUACION_EQUIPO_2));

        if(puntuacio1 != -1 && puntuacio2 != -1){
            Toast.makeText(this,"Partido ya jugado",Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(LigaActivity.this, AndroidStarter.class);
            intent.putExtra(AndroidStarter.IA, true);
            intent.putExtra(AndroidStarter.IDEQUIPO, idEquipo1);
            intent.putExtra(AndroidStarter.IDRIBAL, idEquipo2);
            intent.putExtra(AndroidStarter.LIGA, true);
            intent.putExtra(AndroidStarter.LIGAID, id);
            intent.putExtra(AndroidStarter.IDUSER, idUser);

            startActivity(intent);
        }
    }

    private class LigaAdaper extends CursorAdapter{
        private LayoutInflater inflater;
        private HashMap<String,Drawable> escudos;
        public LigaAdaper(Context context, Cursor c) {
            super(context, c, 0);
            inflater = LayoutInflater.from(context);
            escudos = new HashMap<String, Drawable>();

            try {
                escudos.put("logo1.png",Utils.getDrawableFromAssets(context, "logo1.png"));
                escudos.put("Logo2.png",Utils.getDrawableFromAssets(context, "Logo2.png"));
                escudos.put("logo3.png",Utils.getDrawableFromAssets(context, "logo3.png"));
                escudos.put("logo4.png",Utils.getDrawableFromAssets(context, "logo4.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return inflater.inflate(R.layout.row_liga,null);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            String[] columnas = new String[]{
                    "e."+ tbEquipos.COL_ESCUDO,
                    "e2."+tbEquipos.COL_ESCUDO,
                    "l."+tbLiga.COL_PUNTUACION_EQUIPO_1,
                    "l."+tbLiga.COL_PUNTUACION_EQUIPO_2,
                    "l."+tbLiga.COL_FECHA,
                    "l."+tbLiga.COL_ID_LIGA
            };

            String escudo1 = cursor.getString(cursor.getColumnIndex("e." + tbEquipos.COL_ESCUDO));
            String escudo2 = cursor.getString(cursor.getColumnIndex("e2."+tbEquipos.COL_ESCUDO + "2"));
            int puntuacio1 = cursor.getInt(cursor.getColumnIndex(tbLiga.COL_PUNTUACION_EQUIPO_1));
            int puntuacio2 = cursor.getInt(cursor.getColumnIndex(tbLiga.COL_PUNTUACION_EQUIPO_2));
            String fecha = cursor.getString(cursor.getColumnIndex(tbLiga.COL_FECHA));

            TextView txtPuntuacio1 = (TextView) view.findViewById(R.id.txtPuntosEquipo1);
            TextView txtPuntuacio2 = (TextView) view.findViewById(R.id.txtPuntuacionEquipo2);
            TextView txtFecha = (TextView) view.findViewById(R.id.txtFecha);
            ImageView imgEquipo1 = (ImageView) view.findViewById(R.id.imgEquipo1);
            ImageView imgEquipo2 = (ImageView) view.findViewById(R.id.imgEquipo2);

            txtPuntuacio1.setText((puntuacio1 == -1 ? 0 : puntuacio1) + "");
            txtPuntuacio2.setText((puntuacio2 == -1 ? 0 : puntuacio2) + "");
            txtFecha.setText(fecha);

            imgEquipo1.setImageDrawable(escudos.get(escudo1));
            imgEquipo2.setImageDrawable(escudos.get(escudo2));

            Log.e("liga escudos", escudo1 + " - " + escudo2);
        }
    }
}