package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Estado;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Casilla;
import com.rugbysurvive.partida.tablero.Lado;


import java.util.ArrayList;



/**
 * Clase que contiene todos los jugadores de un equipo, tambien jestiona el tema de suplentes
 * Created by aitor on 25/03/14.
 */
public  class Equipo {


    private ArrayList<Jugador> jugadores = new ArrayList <Jugador>();
    private ArrayList<Jugador> descartados = new ArrayList<Jugador>();


    private Color color;

    private Lado lado;
    private ArrayList<PosicionInicial> alineacion;
    private boolean jugando; // indica si el equipo esta siendo usado

    private static Equipo equipo;

    private Jugador jugadorSelecionado;

    Campo campo = Campo.getInstanciaCampo();

    /**
     * contructor de la clase
     */
    public  Equipo(){
        this.alineacion = new ArrayList<PosicionInicial>();
        this.jugadores =  new ArrayList<Jugador>();
        this.descartados = new ArrayList<Jugador>();
        this.jugando = false;
        equipo = this;
        this.color = Color.azul;

    }

    public static Equipo getEquipo() {
        return equipo;
    }

    public static void setEquipo(Equipo equipo) {
        Equipo.equipo = equipo;
    }

    /**
     * crea el equipo
     */
    public void crearEquipo(){

    }

    public boolean jugadorConPelota()
    {
        for(Jugador jugador :this.jugadores){
            if(jugador.getEstado() instanceof  ConPelota) {
                return true;
            }
        }
        return false;
    }



    /**
     * devuelve una lista con todos los jugadores suplentes del equipo
     * @return lista de jugadores suplentes
     */
    public ArrayList<Jugador> listaSuplentes (){
        ArrayList<Jugador> suplentes = new ArrayList <Jugador>();
        for (int i= ConstantesJuego.JUGADORES_CAMPO; i<jugadores.size(); i++){
            System.out.println(jugadores.get(i).getFuerza());
            suplentes.add(jugadores.get(i));

        }
        return suplentes;
    }

    /**
     * devuelve una lista con todos los jugadores que estan en el campo
     * @return lista de jugadores en campo
     */
    public ArrayList<Jugador> listaJugadoresCampo() {
        ArrayList<Jugador> JugadoresCampo = new ArrayList <Jugador>();
        for (int i=0; i<ConstantesJuego.JUGADORES_CAMPO; i++){
            //System.out.println(jugadores.get(i).getFuerza());
           if(!jugadores.get(i).isExpulsado()) {
                JugadoresCampo.add(jugadores.get(i));
           }
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
    public boolean jugadorEnEquipo(Jugador jugador){
        if(this.jugadores.indexOf(jugador) !=  -1)
            return true;
        else return false;
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
            if(!iter.isExpulsado()) {
                campo.añadirElemento(iter, iter.getPosicionX(), iter.getPosicionY());
            }
        }
    }

    /**
     * Desbloquea todos los jugadores del equipo
     */
    public void desbloquear()
    {
        for(Jugador jugador : this.jugadores){
            if(!jugador.isExpulsado()) {
                jugador.setBloqueado(false);
            }

        }
    }
    /**
     * Bloquea todos los jugadores del equipo
     */
    public void bloquear()
    {
        for(Jugador jugador : this.jugadores){
            if(!jugador.isExpulsado()) {
                jugador.setBloqueado(true);
            }
        }
    }

    /**
     * Indica si el equipo entero esta bloqueado
     * A la que haya un jugador desbloqueado el
     * equipo entero se considera desbloqueado
     * @return equipo bloqueado o no
     */
    public boolean bloqueado(){

        for(Jugador jugador : this.jugadores){
            if(jugador.getBloqueado() == false && !jugador.isExpulsado()) {
                return false;
             }
        }
        return true;
    }

    public void deseleccionar(){
        for(Jugador jugador : this.jugadores) {
            jugador.setSeleccionado(false);
        }
    }

    /**
     * Intercambia un jugador de la lista de suplentes por un jugador que este
     * en la partida .
     * Automaticamente detecta si es el equipo corecto o no
     * El jugador colocado aparece en estado bloqueado.
     *
     */
    public void intercambioJugadores(Jugador jugador){


        if (hayJugadorSelecionado()){

            Campo campo = ComponentesJuego.getComponentes().getCampo();
            int posY = jugadorSelecionado.getPosicionY();
            int posX= jugadorSelecionado.getPosicionX();


            campo.eliminarElemento(posY, posX);
            GestorGrafico.getCamara().desbloquear();

            jugadores.remove(jugador);
            this.jugadores.add(0,jugador);
            this.jugadores.remove(jugadorSelecionado);
            this.descartados.add(jugadorSelecionado);
            campo.añadirElemento(jugador,posY,posX);
            jugador.setBloqueado(true);


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
        jugador.setColor(this.color);
        this.alineacion.add(new PosicionInicial(jugador,posicionX,posicionY));
        this.jugadores.add(jugador);

    }

    public void quitarPelota(){
        for (Jugador jugador: listaJugadoresCampo()){
            if (jugador.getEstado()instanceof ConPelota){
                jugador.setEstado(new SinPelota());
            }
        }
    }
    /* public boolean desalojarCampo(Campo campo){}
    public boolean bloquear(){}
    public boolean desbloquear(){}*/

    public Jugador getJugadorActivo (){
        return  this.jugadorSelecionado;
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public Lado getLado() {
        return lado;
    }

    public void setLado(Lado lado) {
        this.lado = lado;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
