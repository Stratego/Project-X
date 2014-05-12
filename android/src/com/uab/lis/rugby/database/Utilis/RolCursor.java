package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Rol;
import com.uab.lis.rugby.database.contracts.tbRoles;

/**
 * Created by adria on 12/05/14.
 */
public class RolCursor {
    public static Rol newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbRoles._ID);
        int colNombre = cursor.getColumnIndex(tbRoles.COL_NOMBRE);
        int colDescripcion = cursor.getColumnIndex(tbRoles.COL_DESCRIPCION);

        Rol rol = new Rol();
        rol.setId(cursor.getInt(colId));
        rol.setNombre(cursor.getString(colNombre));
        rol.setDescripcion(cursor.getString(colDescripcion));

        return rol;
    }
    public static ContentValues generateValues(Rol rol){
        ContentValues values = new ContentValues();
        values.put(tbRoles._ID, rol.getId());
        values.put(tbRoles.COL_NOMBRE, rol.getNombre());
        values.put(tbRoles.COL_DESCRIPCION, rol.getDescripcion());
        return values;
    }
}
