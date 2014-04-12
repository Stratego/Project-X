package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import java.util.Set;

/**
 * Created by Manuel on 7/04/14.
 */
public class Equipo {
    private int id;
    private String nombre;
    private String escudo;
    private String equipacion;
    private Set<Jugador> alineacion;
    private Set<Jugador> jugadores;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEscudo() {
        return escudo;
    }

    public String getEquipacion() {
        return equipacion;
    }

    public Set<Jugador> getAlineacion() {
        return alineacion;
    }

    public Set<Jugador> getJugadoes() {
        return jugadores;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public void setEquipacion(String equipacion) {
        this.equipacion = equipacion;
    }

    public void setAlineacion(Set<Jugador> alineacion) {
        this.alineacion = alineacion;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public static Equipo newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbEquipos._ID);
        int colNombre = cursor.getColumnIndex(tbEquipos.COL_NOMBRE);
        int colEscudo = cursor.getColumnIndex(tbEquipos.COL_ESCUDO);
        int colEquipacion = cursor.getColumnIndex(tbEquipos.COL_EQUIPACION);
        int colAlineacion = cursor.getColumnIndex(tbEquipos.COL_ALINEACION);
        int colJugadores = cursor.getColumnIndex(tbEquipos.COL_JUGADORES);

        Equipo equipo = new Equipo();
        equipo.setId(cursor.getInt(colId));
        equipo.setNombre(cursor.getString(colNombre));
        equipo.setEscudo(cursor.getString(colEscudo));
        equipo.setEquipacion(cursor.getString(colEquipacion));
        //equipo.setAlineacion(cursor.getString(colAlineacion));
        //equipo.setJugadores(cursor.getString(colJugadores));

        return equipo;
    }
    public static ContentValues generateValues(Equipo equipo){
        ContentValues values = new ContentValues();
        values.put(tbEquipos._ID, equipo.getId());
        values.put(tbEquipos.COL_NOMBRE,equipo.getNombre());
        return values;
    }
    @Override
    public String toString() {
        return "tbEquipos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}