package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbUsuarios;

import java.util.List;

/**
 * Created by Manuel on 19/04/2014.
 */
public class Usuario {
    private int id;
    private String nombre;
    private List<Equipo> equipos;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

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
    @Override
    public String toString() {
        return "tbObjeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
