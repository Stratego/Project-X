package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;
import java.util.ArrayList;



/**
 * Clase que contiene todos los jugadores de un equipo.
 * Los jugadores se divien en suplentes , en jugadores jugando y descartados
 * Los jugadores descartados no se pueden usar en ningun momento de la partida
 * una vez indicado.
 * Los jugadores suplentes solo pueden ser usados mediante cambio
 * Un jugador en juego podra ser gestionado en cualqueir momento.
 *
 *Gestiona todos los elementos comunes como el lado del campo en que juegan, el color
 * de la equipacion o la bandera identificativa
 *
 * Ofrece ademas un conjunto de elementos que facilin el trabajo
 * del conjunto de jugadores como bloqueo, desbloqueo o saber que jugador esta seleccionado.
 *
 * Tambien gestiona la alineacion por la cual seran colocados cada vez
 * que haya un punto o al inicio del partido.
 *
 * Created by aitor on 25/03/14.
 */
public  class Equipo {

    // Listas de jugadores
    private ArrayList<Jugador> jugadores ;
    private ArrayList<Jugador> descartados ;
    private ArrayList<PosicionInicial> alineacion;

    //Identificacion mediante estandarte de los jugadores
    private String logo;
    private String estandarte;

    // Caracteristicas extras del equipo
    private Color color;
    private Lado lado;

    // estados
    private boolean jugando; // indica si el equipo esta siendo usado
    private Jugador jugadorSelecionado;



    private static Equipo equipo;
    Campo campo = Campo.getInstanciaCampo();



    public  Equipo(){
        this.alineacion = new ArrayList<PosicionInicial>();
        this.jugadores =  new ArrayList<Jugador>();
        this.descartados = new ArrayList<Jugador>();
        this.jugando = false;
        equipo = this;
        this.color = Color.azul;

    }

    /**
     * Se obtiene la instancia de la clase del equio de forma estatica
     * @return instancia de la calse
     */
    public static Equipo getEquipo() {
        return equipo;
    }


    /**
     * Indica si dentro del equipo hay un jugador con pelota
     * @return devuelve cierto si hay un jugador con pelota ,
     *          falso en caso contrario
     */
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
     * Devuelve una lista con todos los jugadores suplentes del equipo
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
     * Devuelve una lista con todos los jugadores que estan en el campo
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


    /**
     * Indica si hay algun jugador seleccionado
     * @return devuelve cierto si hay algun jugador seleccionado
     */
    public boolean hayJugadorSelecionado(){
        boolean hayJugador = false;

        for (Jugador iter :jugadores){
            if (iter.getSeleccionado()==true) {
                jugadorSelecionado = iter;
                hayJugador=true;
                return  hayJugador;
            }
        }
        return  hayJugador;
    }

    /**
     *Indica si el jugador existe en el equipo indicado
     * @param jugador Elemento que se desea buscar
     * @return Si el jugador existe o no en el equipo
     */
    public boolean jugadorEnEquipo(Jugador jugador){
        if(this.jugadores.indexOf(jugador) !=  -1)
            return true;
        else return false;
    }


    /**
     *
     */
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

    /**
     * Cualquier jugador en estado seleccionado pasa a estado
     * deseleccionado.
     */
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

    /**
     * Cambio el estado del jugador que tenga la pelota
     * a sin pelota.
     */
    public void quitarPelota(){
        for (Jugador jugador: listaJugadoresCampo()){
            if (jugador.getEstado()instanceof ConPelota){
                jugador.setEstado(new SinPelota());
            }
        }
    }


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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEstandarte() {
        return estandarte;
    }

    public void setEstandarte(String estandarte) {
        String[] split = estandarte.split("/");
        this.estandarte = "banderas/cambioTurno/"+split[split.length-1];
        System.out.println("ESTANDARTE:"+this.estandarte);
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



}
