package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Usuario;
import com.uab.lis.rugby.database.contracts.tbUsuarios;

/**
 * Created by adria on 12/05/14.
 */
public class UsuarioCursor {
    public static Usuario newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbUsuarios._ID);
        int colNombre = cursor.getColumnIndex(tbUsuarios.COL_NOMBRE);

        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(colId));
        usuario.setNombre(cursor.getString(colNombre));
        // FALTA COMPLETAR

        return usuario;
    }
    public static ContentValues generateValues(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put(tbUsuarios.COL_NOMBRE, usuario.getNombre());
        // FALTA COMPLETAR
        return values;
    }
}
