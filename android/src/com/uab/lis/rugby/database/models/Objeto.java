package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbObjetos;

/**
 * Created by Manuel on 7/04/14.
 */
public class Objeto {
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static Objeto newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbObjetos._ID);
        int colNombre = cursor.getColumnIndex(tbObjetos.COL_NOMBRE);

        Objeto objeto = new Objeto();
        objeto.setId(cursor.getInt(colId));
        objeto.setNombre(cursor.getString(colNombre));

        return objeto;
    }
    public static ContentValues generateValues(Objeto objeto){
        ContentValues values = new ContentValues();
        values.put(tbObjetos._ID, objeto.getId());
        values.put(tbObjetos.COL_NOMBRE,objeto.getNombre());
        return values;
    }
    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}