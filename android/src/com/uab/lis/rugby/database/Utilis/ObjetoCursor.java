package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Objeto;
import com.uab.lis.rugby.database.contracts.tbObjetos;

/**
 * Created by adria on 12/05/14.
 */
public class ObjetoCursor {
    public static Objeto newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbObjetos._ID);
        int colNombre = cursor.getColumnIndex(tbObjetos.COL_NOMBRE);
        int colDescripcion = cursor.getColumnIndex(tbObjetos.COL_DESCRIPCION);

        Objeto objeto = new Objeto();
        objeto.setId(cursor.getInt(colId));
        objeto.setNombre(cursor.getString(colNombre));
        objeto.setDescripcion(cursor.getString(colDescripcion));

        return objeto;
    }
    public static ContentValues generateValues(Objeto objeto){
        ContentValues values = new ContentValues();
        values.put(tbObjetos._ID, objeto.getId());
        values.put(tbObjetos.COL_NOMBRE,objeto.getNombre());
        values.put(tbObjetos.COL_DESCRIPCION,objeto.getDescripcion());
        return values;
    }
}
