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

    public static final int JUGADORES_CAMPO = 7;
    private ArrayList<Jugador> jugadores = new ArrayList <Jugador>();
    private ArrayList<PosicionInicial> alineacion;

    private static Equipo equipo;

    private Jugador jugadorSelecionado;

    Campo campo = Campo.getInstanciaCampo();

    /**
     * contructor de la clase
     */
    public  Equipo(){
        this.alineacion = new ArrayList<PosicionInicial>();
        this.jugadores =  new ArrayList<Jugador>();
        equipo = this;

    }

    /**
     * crea el equipo
     */
    public void crearEquipo(){




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
            /*jugadorSelecionado.añadirObjeto(new ObjetoJugador(1,"casellalila.png") {
                @Override
                public void activar() {

                }
            });*/
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

    /**
     * Añade un jugador al equipo , los primeros jugadores pertenecen
     * a la lista de jugadores que saldran al campo , el resto quedaran
     * como reserva.
     * La posicionx y la posiciony son la posicion de la alineacion.
     *
     * La alineacion siempre se tiene en cuenta el lado izquierda del campo.
     * La propia clase se encarga de convertirla al lado contrario
     *
     * @param jugador Jugador que sera añadido al equipo
     * @param posicionX posicion incial del eje x de la alineacion
     * @param posicionY posicion inicial en el eje y de la alineacion
     */
    public void añadirJugador(Jugador jugador ,int posicionX,int posicionY)
    {
        this.alineacion.add(new PosicionInicial(jugador,posicionX,posicionY));
        this.jugadores.add(jugador);
    }
    /* public boolean desalojarCampo(Campo campo){}
    public boolean bloquear(){}
    public boolean desbloquear(){}*/

    public Jugador getJugadorActivo (){
        return  this.jugadorSelecionado;
    }
}
