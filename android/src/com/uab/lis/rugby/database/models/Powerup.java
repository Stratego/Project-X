package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbPowerups;

/**
 * Created by Manuel on 28/04/14.
 */
public class Powerup extends Objeto {

    private int habilidad;
    private int efecto;

    public int getHabilidad() {
        return habilidad;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }

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
    @Override
    public String toString() {
        return "tbObjeto{}";
    }
}