package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.models.Equipo;
import com.models.Jugador;
import com.uab.lis.rugby.database.UrisGenerated;
import com.uab.lis.rugby.database.contracts.tbEquipos;

import java.util.ArrayList;

/**
 * Created by adria on 12/05/14.
 */
public class EquipoCursor {
    public static Equipo newInstance(Context context, Cursor cursor,int iduser){
        int colId = cursor.getColumnIndex(tbEquipos._ID);
        int colNombre = cursor.getColumnIndex(tbEquipos.COL_NOMBRE);
        int colEscudo = cursor.getColumnIndex(tbEquipos.COL_ESCUDO);
        int colEquipacion = cursor.getColumnIndex(tbEquipos.COL_EQUIPACION);

        Equipo equipo = new Equipo();
        equipo.setId(cursor.getInt(colId));
        equipo.setNombre(cursor.getString(colNombre));
        equipo.setEscudo(cursor.getString(colEscudo));
        equipo.setEquipacion(cursor.getString(colEquipacion));

        Uri uri = UrisGenerated.getUriJugadoresEquipo(iduser,(int)equipo.getId());
        Cursor jugadors = context.getContentResolver().query(uri,null,null,null,null);
        jugadors.moveToFirst();
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        do{
            Jugador j = JugadorCursor.newInstance(context, jugadors);
            jugadores.add(j);
        }while (jugadors.moveToNext());

        equipo.setJugadores(jugadores);

        return equipo;
    }
    public static ContentValues generateValues(Equipo equipo){
        ContentValues values = new ContentValues();
        if(equipo.getId() != -1){
            values.put(tbEquipos._ID, equipo.getId());
        }
        values.put(tbEquipos.COL_NOMBRE,equipo.getNombre());
        values.put(tbEquipos.COL_ESCUDO,equipo.getEscudo());
        values.put(tbEquipos.COL_EQUIPACION,equipo.getEquipacion());
        return values;
    }
}
