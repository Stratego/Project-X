package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbRoles;

/**
 * Created by Manuel on 18/04/2014.
 */
public class Rol {
    private int id;
    private String nombre;
    private String descripcion;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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
    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +'\'' +
                '}';
    }
}