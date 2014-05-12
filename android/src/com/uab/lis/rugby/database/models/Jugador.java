package com.uab.lis.rugby.database.models;

import android.content.ContentValues;
import android.database.Cursor;
import com.uab.lis.rugby.database.contracts.tbJugadores;
import java.util.List;

/**
 * Created by Manuel on 7/04/14.
 */
public class Jugador {
    private int id;
    private String nombre;
    private Rol rol;
    private List<Habilidad> habilidades;
    private int posX;
    private int posY;
    private String extrasVisuales;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getExtrasVisuales() {
        return extrasVisuales;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setExtrasVisuales(String extrasVisuales) {
        this.extrasVisuales = extrasVisuales;
    }

    public static Jugador newInstance(Cursor cursor){
        int colId = cursor.getColumnIndex(tbJugadores._ID);
        int colNombre = cursor.getColumnIndex(tbJugadores.COL_NOMBRE);
        // FALTA COMPLETAR

        Jugador jugador = new Jugador();
        jugador.setId(cursor.getInt(colId));
        jugador.setNombre(cursor.getString(colNombre));
        // FALTA COMPLETAR

        return jugador;
    }
    public static ContentValues generateValues(Jugador jugador){
        ContentValues values = new ContentValues();
        values.put(tbJugadores._ID, jugador.getId());
        values.put(tbJugadores.COL_NOMBRE,jugador.getNombre());
        // FALTA COMPLETAR
        return values;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}