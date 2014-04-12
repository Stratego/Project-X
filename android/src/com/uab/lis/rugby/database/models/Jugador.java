package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbJugadores;

/**
 * Created by Manuel on 7/04/14.
 */
public class Jugador {
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

    public static Jugador newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbJugadores._ID);
        int colNombre = cursor.getColumnIndex(tbJugadores.COL_NOMBRE);

        Jugador jugador = new Jugador();
        jugador.setId(cursor.getInt(colId));
        jugador.setNombre(cursor.getString(colNombre));

        return jugador;
    }
    public static ContentValues generateValues(Jugador jugador){
        ContentValues values = new ContentValues();
        values.put(tbJugadores._ID, jugador.getId());
        values.put(tbJugadores.COL_NOMBRE,jugador.getNombre());
        return values;
    }
    @Override
    public String toString() {
        return "tbJugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}