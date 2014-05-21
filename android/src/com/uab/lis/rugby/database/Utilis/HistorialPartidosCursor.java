package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Habilidad;
import com.uab.lis.rugby.database.contracts.tbHabilidades;
import com.uab.lis.rugby.database.contracts.tbHistorialPartido;
import com.uab.lis.rugby.database.contracts.tbJugadorHabilidad;
import com.uab.lis.rugby.database.models.HistorialPartido;

/**
 * Created by Adria on 12/05/2014.
 */
public class HistorialPartidosCursor {
    public static HistorialPartido newInstance(Cursor cursor){
        return null;
    }
    public static ContentValues generateValues(HistorialPartido historial){
        ContentValues values = new ContentValues();
        values.put(tbHistorialPartido.COL_ID_EQUIPO_1, historial.getIdEquipo1());
        values.put(tbHistorialPartido.COL_ID_EQUIPO_2, historial.getIdEquipo2());
        values.put(tbHistorialPartido.COL_PUNTUACION_EQUIPO_1, historial.getPuntuacionEquipo1());
        values.put(tbHistorialPartido.COL_PUNTUACION_EQUIPO_2, historial.getPuntuacionEquipo2());
        return values;
    }
}
