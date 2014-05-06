package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.uab.lis.rugby.database.contracts.tbEquipos;
import java.util.List;

/**
 * Created by Manuel on 7/04/14.
 */
public class Equipo {
    private long id = -1;
    private String nombre;
    private List<Jugador> jugadores;
    private String escudo;
    private String equipacion;
    private List<Jugador> alineacion;

    public long getId() {
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

    public List<Jugador> getAlineacion() {
        return alineacion;
    }

    public List<Jugador> getJugadoes() {
        return jugadores;
    }

    public void setId(long id) {
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

    public void setAlineacion(List<Jugador> alineacion) {
        this.alineacion = alineacion;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public static Equipo newInstance(Context context, Cursor cursor){
        int colId = cursor.getColumnIndex(tbEquipos._ID);
        int colNombre = cursor.getColumnIndex(tbEquipos.COL_NOMBRE);
        int colEscudo = cursor.getColumnIndex(tbEquipos.COL_ESCUDO);
        int colEquipacion = cursor.getColumnIndex(tbEquipos.COL_EQUIPACION);
        // FALTA COMPLETAR

        Uri uri = null;
        String where = "";
        Cursor jugadors = context.getContentResolver().query(uri,null,where,null,null);
        jugadors.moveToFirst();
        do{
            Jugador j = Jugador.newInstance(jugadors);
        }while (jugadors.moveToNext());

        Equipo equipo = new Equipo();
        equipo.setId(cursor.getInt(colId));
        equipo.setNombre(cursor.getString(colNombre));
        equipo.setEscudo(cursor.getString(colEscudo));
        equipo.setEquipacion(cursor.getString(colEquipacion));
        // FALTA COMPLETAR

        return equipo;
    }
    public static ContentValues generateValues(Equipo equipo){
        ContentValues values = new ContentValues();
        if(equipo.getId() != -1){
            values.put(tbEquipos._ID, equipo.getId());
        }
        values.put(tbEquipos.COL_NOMBRE,equipo.getNombre());
        values.put(tbEquipos.COL_ESCUDO,equipo.getEscudo());
        values.put(tbEquipos.COL_EQUIPACION,equipo.getEquipacion());
        return values;
    }
    @Override
    public String toString() {
        return "tbEquipos{" +
                "id=" + id +
                "nombre=" + nombre +
                "escudo=" + escudo +
                ", equipacion='" + equipacion + '\'' +
                '}';
    }
}