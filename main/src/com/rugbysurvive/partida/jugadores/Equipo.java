package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Casilla;


import java.util.ArrayList;



/**
 * Clase que contiene todos los jugadores de un equipo, tambien jestiona el tema de suplentes
 * Created by aitor on 25/03/14.
 */
public  class Equipo {

    private ArrayList<Jugador> jugadores = new ArrayList <Jugador>();
    private ArrayList<PosicionInicial> alineacion;

    private ArrayList<ObjetoJugador> powerup = new ArrayList<ObjetoJugador>();
    private ArrayList<ObjetoJugador> powerup2 = new ArrayList<ObjetoJugador>();

    private static Equipo equipo;

    private Jugador jugadorSelecionado;

    Campo campo = Campo.getInstanciaCampo();

    //objetos de prueba
    ObjetoJugador objeto1 = new ObjetoJugador(1,"casellalila.png") {
        @Override
        public void activar() {

        }
    };

    //objetos de prueba
    ObjetoJugador objeto2 = new ObjetoJugador(2,"casilla.png") {
        @Override
        public void activar() {

        }
    };

    //objetos de prueba
    ObjetoJugador objeto3 = new ObjetoJugador(3,"casilla.png") {
        @Override
        public void activar() {

        }
    };

    //objetos de prueba
    ObjetoJugador objeto4 = new ObjetoJugador(4,"casellalila.png") {
        @Override
        public void activar() {

        }
    };

    /**
     * contructor de la clase
     */
    public  Equipo(){
        crearEquipo();
        equipo = this;
    }

    /**
     * crea el equipo
     */
    public void crearEquipo(){



        // equipo por defecto , implementar funcion una vez se haya unido con la otra parte
        powerup.add(objeto1);
        jugadores.add(new Jugador(new Casilla(0,0),10,10,10,powerup));


        powerup.add(objeto2);
        jugadores.add(new Jugador(new Casilla(0,1),10,10,10,powerup));

        powerup.add(objeto3);
        jugadores.add(new Jugador(new Casilla(2,0),10,10,10,powerup));

        powerup.add(objeto4);
        jugadores.add(new Jugador(new Casilla(2,1),10,10,10,powerup));

        powerup2.add(objeto2);
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
    public ArrayList<ObjetoJugador> objetosJugador(){

        ArrayList<ObjetoJugador> powerupJugador = new ArrayList<ObjetoJugador>();
        if (hayJugadorSelecionado()==true){
            return jugadorSelecionado.getPowerUP();
        }
        /*
        for (Jugador iter :jugadores){
            if (iter.getSeleccionado()==true){
                System.out.println("jugador selecionado");
                jugadorSelecionado = iter;
                return iter.getPowerUP();
            }
        }
        */
        return powerupJugador;
    }

    public static Equipo getInstanciaEquipo(){return equipo;}


    public boolean hayJugadorSelecionado(){
        boolean hayJugador = false;
        for (Jugador iter :jugadores){
            if (iter.getSeleccionado()==true){
                jugadorSelecionado = iter;
                hayJugador=true;
                return  hayJugador;
            }
        }

        return  hayJugador;
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

    public void dibujarEquipo(){
        for (Jugador iter :this.getJugadores()){
            campo.añadirElemento(iter, iter.getPosicionX(), iter.getPosicionY());
        }
    }

    public void intercambioJugadores(int posicionSuplente){
        int posicion;
        if (hayJugadorSelecionado()==true){
            posicion=jugadores.indexOf(jugadorSelecionado);
            jugadores.add(posicion,jugadores.get(posicionSuplente));
            jugadores.remove(posicion+1);
            jugadores.remove(posicionSuplente);
            jugadores.add(jugadorSelecionado);
        }
    }
    /* public boolean desalojarCampo(Campo campo){}
    public boolean bloquear(){}
    public boolean desbloquear(){}*/
}
