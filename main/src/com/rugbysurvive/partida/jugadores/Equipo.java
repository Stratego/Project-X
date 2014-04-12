package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.Jugador.Jugador;


import java.util.ArrayList;


/**
 * Created by aitor on 25/03/14.
 */
public class Equipo {

    private ArrayList<Jugador> jugadores = new ArrayList <Jugador>();
    private ArrayList<PosicionInicial> alineacion;

    public  Equipo(){
        crearEquipo();
    }

    public void crearEquipo(){
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,10,10));
        jugadores.add(new Jugador(10,20,30));
        jugadores.add(new Jugador(20,30,40));
        jugadores.add(new Jugador(30,40,50));
        jugadores.add(new Jugador(40,50,60));
        jugadores.add(new Jugador(50,60,70));
        jugadores.add(new Jugador(60,70,80));
        jugadores.add(new Jugador(70,80,90));
        jugadores.add(new Jugador(80,90,100));

    }


    public ArrayList<Jugador> listaSuplentes (){
        ArrayList<Jugador> suplentes = new ArrayList <Jugador>();
        for (int i=7; i<jugadores.size(); i++){
            System.out.println(jugadores.get(i).getFuerza());
            suplentes.add(jugadores.get(i));

        }
        return suplentes;
    }
   /* public boolean desalojarCampo(Campo campo){}
    public boolean bloquear(){}
    public boolean desbloquear(){}*/
}
