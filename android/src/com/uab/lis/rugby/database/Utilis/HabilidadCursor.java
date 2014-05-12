package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Habilidad;
import com.uab.lis.rugby.database.contracts.tbHabilidades;

/**
 * Created by adria on 12/05/14.
 */
public class HabilidadCursor {
    public static Habilidad newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbHabilidades._ID);
        int colNombre = cursor.getColumnIndex(tbHabilidades.COL_NOMBRE);
        int colDescripcion = cursor.getColumnIndex(tbHabilidades.COL_DESCRIPCION);
        int colValor = cursor.getColumnIndex(tbHabilidades.COL_VALOR);

        Habilidad habilidad = new Habilidad();
        habilidad.setId(cursor.getInt(colId));
        habilidad.setNombre(cursor.getString(colNombre));
        habilidad.setDescripcion(cursor.getString(colDescripcion));
        habilidad.setValor(cursor.getInt(colValor));

        return habilidad;
    }
    public static ContentValues generateValues(Habilidad habilidad){
        ContentValues values = new ContentValues();
        values.put(tbHabilidades._ID, habilidad.getId());
        values.put(tbHabilidades.COL_NOMBRE, habilidad.getNombre());
        values.put(tbHabilidades.COL_DESCRIPCION, habilidad.getDescripcion());
        values.put(tbHabilidades.COL_VALOR, habilidad.getValor());
        return values;
    }
}
