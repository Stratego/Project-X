package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Campo;
import org.omg.CORBA.COMM_FAILURE;

/**
 * Created by aitor on 10/04/14.
 * Objeto que se puede colocar en una casilla del campo y que reacciona ante un evento
 * sea alguna interaccion con otro objeto o por decision del jugador
 */
public abstract class ObjetoCampo implements Dibujable ,Proceso{

    /**
     * identificador de la textura
     */
    protected int id;

    /**
     * posicion X donde se colocara el objeto
     */
    protected int posX;

    /**
     * posicion Y donde se colocara el objeto
     */
    protected int posY;

    /**
     * textura que representara al objeto
     */
    private String textura;

    /**
     * jugador que coloca el objeto
     */
    private Jugador jugador;
    private boolean finalizarProceso;
    private boolean vistualizacion;

    /**
     * constructor de objetos de campo
     * @param textura textura que representara graficamente el objeto de campo
     * @param jugador jugador que ha colocado el objeto de campo
     */
    public ObjetoCampo(String textura,Jugador jugador){

        this.textura=textura;
        this.jugador = jugador;
        this.finalizarProceso = false;
        this.vistualizacion = false;
    }

    /**
     * coloca el objeto campo de un jugador en el campo
     * @param posicionX posicion X donde se colocara el objeto
     * @param posicionY posicion Y donde se colocara el objeto
     */
    public void colocar(int posicionX,int posicionY){
        this.posX = posicionX;
        this.posY = posicionY;
        this.id = GestorGrafico.generarDibujante().añadirDibujable(this,
                TipoDibujo.elementosJuego);
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.añadirElemento(this,this.posY,this.posX);
        ProcesosContinuos.añadirProceso(this);
        this.vistualizacion = true;
    }

    /**
     * elimina el objeto del campo y de la simulacion
     */
    public void quitar(){
        GestorGrafico.generarDibujante().eliminarTextura(id);
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.eliminarElemento(this.posY,this.posX);
        this.vistualizacion = false;
        this.finalizarProceso = true;
    }

    /**
     * define el comportamiento de un objeto colocado en campo asi como
     * las consequencias que tendra el jugador que ha pisado el objeto
     * @param jugador jugador enemigo que pisa el objeto en el campo
     * @param animacionParada indica el estado de la ejecucion de la animacion
     */
    public abstract void efecto(Jugador jugador,boolean animacionParada);

    @Override
    public boolean procesar(){

       if(this.vistualizacion){
        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo1();


        if(equipo1.jugadorEnEquipo(jugador) && equipo1.bloqueado() && this.id != -1){
            GestorGrafico.generarDibujante().eliminarTextura(id);
            this.id = -1;
        }
        else if (equipo2.jugadorEnEquipo(jugador) && equipo2.bloqueado() && this.id != -1){
            GestorGrafico.generarDibujante().eliminarTextura(id);
            this.id = -1;

        }
        else if(equipo1.jugadorEnEquipo(jugador) && !equipo1.bloqueado() && this.id == -1){
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this,TipoDibujo.elementosJuego);
        }

        else if(equipo2.jugadorEnEquipo(jugador) && !equipo2.bloqueado() && this.id == -1){
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        }

       }

        return this.animacion();

    }

    /**
     * Define la ejecucion de un proceso de animacion de texturas,
     * desde el tiempo hasta las texturas que se usaran
     * @return indica si la animacion se esta ejecutando
     */
    protected abstract boolean animacion();


    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return this.posX;
    }

    @Override
    public int getPosicionY() {
        return this.posY;
    }
}
