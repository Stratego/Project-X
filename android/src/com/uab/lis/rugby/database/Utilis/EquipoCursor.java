package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.models.Equipo;
import com.models.Jugador;
import com.uab.lis.rugby.database.contracts.tbEquipos;

/**
 * Created by adria on 12/05/14.
 */
public class EquipoCursor {
    public static Equipo newInstance(Context context, Cursor cursor){
        int colId = cursor.getColumnIndex(tbEquipos._ID);
        int colNombre = cursor.getColumnIndex(tbEquipos.COL_NOMBRE);
        int colEscudo = cursor.getColumnIndex(tbEquipos.COL_ESCUDO);
        int colEquipacion = cursor.getColumnIndex(tbEquipos.COL_EQUIPACION);
        // FALTA COMPLETAR

        Uri uri = null;
        String where = "";
        Cursor jugadors = context.getContentResolver().query(uri,null,where,null,null);
        jugadors.moveToFirst();
        do{
            Jugador j = JugadorCursor.newInstance(jugadors);
        }while (jugadors.moveToNext());

        Equipo equipo = new Equipo();
        equipo.setId(cursor.getInt(colId));
        equipo.setNombre(cursor.getString(colNombre));
        equipo.setEscudo(cursor.getString(colEscudo));
        equipo.setEquipacion(cursor.getString(colEquipacion));
        // FALTA COMPLETAR

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
