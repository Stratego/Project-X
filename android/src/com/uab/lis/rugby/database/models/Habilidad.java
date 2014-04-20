package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbHabilidades;

/**
 * Created by Manuel on 19/04/2014.
 */
public class Habilidad {
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
    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +
                ", valor='" + valor + '\'' +
                '}';
    }
}
