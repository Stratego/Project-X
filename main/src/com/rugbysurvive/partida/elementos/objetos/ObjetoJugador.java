package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;

/**
 * Created by aitor on 12/04/14.
 *
 * Objeto que sera usado por el jugador sobre si mismo.
 * afectara una habilidad durante un tiempo limitado
 * Se puede activar y gestionar las texturas.
 */
public abstract class ObjetoJugador {

    /**
     * textura que representa el objeto graficamente
     */
    protected String textura;

    /**
     * posicion x en la que se usara el objeto
     */
    protected int posicionX;

    /**
     * posicion y en la que se usara el objeto
     */
    protected int posicionY;

    /**
     * identificador del objeto
     */
    protected int id;

    /**
     * activa el uso de un objeto sobre el jugador,
     * define su comportamiento asi como la introduccion
     * en el gestor de procesos
     */
    public abstract void activar();

    /**
     * constructor de la clase objeto
     * @param id identificador del objeto
     * @param textura textura que representa el objeto graficamente
     */
    public ObjetoJugador(int id, String textura){
        this.id = id;
        this.textura= textura;
    }



    public String getTextura(){return this.textura;}


    public int getId(){return this.id;}

}
