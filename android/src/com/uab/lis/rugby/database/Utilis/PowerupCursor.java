package com.uab.lis.rugby.database.Utilis;

import android.content.ContentValues;
import android.database.Cursor;
import com.models.Objeto;
import com.models.Powerup;
import com.uab.lis.rugby.database.contracts.tbPowerups;

/**
 * Created by adria on 12/05/14.
 */
public class PowerupCursor {
    public static Powerup newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbPowerups._ID);
        int colNombre = cursor.getColumnIndex(tbPowerups.COL_NOMBRE);
        int colDescripcion = cursor.getColumnIndex(tbPowerups.COL_DESCRIPCION);
        int colHabilidad = cursor.getColumnIndex(tbPowerups.COL_HABILIDAD);
        int colEfecto = cursor.getColumnIndex(tbPowerups.COL_EFECTO);

        Powerup powerup = new Powerup();
        powerup.setId(cursor.getInt(colId));
        powerup.setNombre(cursor.getString(colNombre));
        powerup.setDescripcion(cursor.getString(colDescripcion));
        powerup.setHabilidad(cursor.getInt(colHabilidad));
        powerup.setEfecto(cursor.getInt(colEfecto));

        return powerup;
    }
    public static ContentValues generateValues(Objeto objeto){
        ContentValues values = new ContentValues();
        values.put(tbPowerups._ID, objeto.getId());
        values.put(tbPowerups.COL_NOMBRE,objeto.getNombre());
        values.put(tbPowerups.COL_DESCRIPCION,objeto.getDescripcion());
        return values;
    }
}
