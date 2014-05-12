package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Extra;
import com.uab.lis.rugby.database.contracts.tbExtras;

/**
 * Created by adria on 12/05/14.
 */
public class ExtraCursor {
    public static Extra newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbExtras._ID);
        int colNombre = cursor.getColumnIndex(tbExtras.COL_NOMBRE);
        int colDescripcion = cursor.getColumnIndex(tbExtras.COL_DESCRIPCION);
        int colValor = cursor.getColumnIndex(tbExtras.COL_VALOR);

        Extra extra = new Extra();
        extra.setId(cursor.getInt(colId));
        extra.setNombre(cursor.getString(colNombre));
        extra.setDescripcion(cursor.getString(colDescripcion));
        extra.setValor(cursor.getInt(colValor));

        return extra;
    }
    public static ContentValues generateValues(Extra extra){
        ContentValues values = new ContentValues();
        values.put(tbExtras._ID, extra.getId());
        values.put(tbExtras.COL_NOMBRE, extra.getNombre());
        values.put(tbExtras.COL_DESCRIPCION, extra.getDescripcion());
        values.put(tbExtras.COL_VALOR, extra.getValor());
        return values;
    }
}
