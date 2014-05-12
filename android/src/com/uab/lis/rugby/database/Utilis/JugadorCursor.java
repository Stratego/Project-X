package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Jugador;
import com.uab.lis.rugby.database.contracts.tbJugadores;

/**
 * Created by adria on 12/05/14.
 */
public class JugadorCursor {
    public static Jugador newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbJugadores._ID);
        int colNombre = cursor.getColumnIndex(tbJugadores.COL_NOMBRE);
        // FALTA COMPLETAR

        Jugador jugador = new Jugador();
        jugador.setId(cursor.getInt(colId));
        jugador.setNombre(cursor.getString(colNombre));
        // FALTA COMPLETAR

        return jugador;
    }
    public static ContentValues generateValues(Jugador jugador){
        ContentValues values = new ContentValues();
        values.put(tbJugadores._ID, jugador.getId());
        values.put(tbJugadores.COL_NOMBRE,jugador.getNombre());
        // FALTA COMPLETAR
        return values;
    }
}
