package com.models;

import java.util.ArrayList;

/**
 * Created by adria on 11/04/14.
 */
public class Jugador {
    public enum Tipos{
        Capitan,Chutador,Normal
    }
    public enum Posicion{
        Delantero,MedioCampo,Defensa
    }
    private ArrayList<Habilidad> habilidades;
    private ArrayList<Objeto> objetos;
    private int dorsal;
    private String nombre;
    private String[] imagenes;//son 4 imagenes la ruta
    private  Tipos tipo;
    private Posicion posicion;
    private int numPosicion;
}
