package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.tablero.Casilla;


import java.util.ArrayList;



/**
 * Clase que contiene todos los jugadores de un equipo, tambien jestiona el tema de suplentes
 * Created by aitor on 25/03/14.
 */
public class Equipo {

    private ArrayList<Jugador> jugadores = new ArrayList <Jugador>();
    private ArrayList<PosicionInicial> alineacion;

    private ArrayList<PowerUP> powerup = new ArrayList<PowerUP>();
    private ArrayList<PowerUP> powerup2 = new ArrayList<PowerUP>();

    /**
     * contructor de la clase
     */
    public  Equipo(){
        crearEquipo();
    }

    /**
     * crea un equipo por defecto
     */
    public void crearEquipo(){


        powerup.add(new PowerUP(7));
        jugadores.add(new Jugador(new Casilla(0,0),10,10,10,powerup));


        powerup.add(new PowerUP(8));
        jugadores.add(new Jugador(new Casilla(0,1),10,10,10,powerup));

        powerup.add(new PowerUP(9));
        jugadores.add(new Jugador(new Casilla(2,0),10,10,10,powerup));

        powerup.add(new PowerUP(10));
        jugadores.add(new Jugador(new Casilla(2,1),10,10,10,powerup));

        powerup2.add(new PowerUP(14));
        jugadores.add(new Jugador(new Casilla(4,0),10,10,10,powerup2));
        jugadores.add(new Jugador(new Casilla(4,1),10,10,10,powerup2));
        jugadores.add(new Jugador(new Casilla(6,0),10,10,10,powerup2));
        jugadores.add(new Jugador(new Casilla(6,1),10,20,30,powerup2));
        jugadores.add(new Jugador(new Casilla(7,0),20,30,40,powerup2));
        jugadores.add(new Jugador(new Casilla(7,1),30,40,50,powerup2));
        jugadores.add(new Jugador(new Casilla(9,0),40,50,60,powerup2));
        jugadores.add(new Jugador(new Casilla(9,1),50,60,70,powerup2));
        jugadores.add(new Jugador(new Casilla(11,0),60,70,80,powerup2));
        jugadores.add(new Jugador(new Casilla(11,1),70,80,90,powerup2));
        jugadores.add(new Jugador(new Casilla(13,0),80,90,100,powerup2));

    }

    /**
     * devuelve una lista con todos los jugadores suplentes del equipo
     * @return lista de jugadores suplentes
     */
    public ArrayList<Jugador> listaSuplentes (){
        ArrayList<Jugador> suplentes = new ArrayList <Jugador>();
        for (int i=7; i<jugadores.size(); i++){
            System.out.println(jugadores.get(i).getFuerza());
            suplentes.add(jugadores.get(i));

        }
        return suplentes;
    }

    /**
     * devuelve una lista con todos los jugadores que estan en el campo
     * @return lista de jugadores en campo
     */
    public ArrayList<Jugador> listaJugadoresCampo (){
        ArrayList<Jugador> JugadoresCampo = new ArrayList <Jugador>();
        for (int i=0; i<7; i++){
            System.out.println(jugadores.get(i).getFuerza());
            JugadoresCampo.add(jugadores.get(i));

        }
        return JugadoresCampo;
    }

    /**
     * devuelve los objetos del jugador actualmente selecionado del equipo
     * @return objetos del jugador actualmente selecionado
     */
    public ArrayList<PowerUP> objetosJugador(){

        ArrayList<PowerUP> powerupJugador = new ArrayList<PowerUP>();

        for (Jugador iter :jugadores){
            if (iter.getSeleccionado()==true){
                System.out.println("jugador selecionado");
                return iter.getPowerUP();
            }
        }

        return powerupJugador;
    }



    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<PosicionInicial> getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(ArrayList<PosicionInicial> alineacion) {
        this.alineacion = alineacion;
    }

    /* public boolean desalojarCampo(Campo campo){}
    public boolean bloquear(){}
    public boolean desbloquear(){}*/
}
