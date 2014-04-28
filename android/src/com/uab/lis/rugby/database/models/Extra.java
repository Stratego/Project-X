package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbExtras;

/**
 * Created by Manuel on 28/04/14.
 */
public class Extra {
    private int id;
    private String nombre;
    private String descripcion;
    private int valor;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

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
    @Override
    public String toString() {
        return "tbExtras{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +
                ", valor='" + valor + '\'' +
                '}';
    }
}
