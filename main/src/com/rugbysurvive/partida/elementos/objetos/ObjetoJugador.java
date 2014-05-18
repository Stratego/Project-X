package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;

/**
 * Created by aitor on 12/04/14.
 *
 * Objeto que sera usado por el jugador . Se puede activar
 * y gestionar las texturas.
 */
public abstract class ObjetoJugador {
    protected String textura;
    protected int posicionX;
    protected int posicionY;
    protected int id;

    //private int identificador;

    /*public ObjetoJugador(String textura)
    {
        this.textura = textura;
        this.id = -1;
    }*/
    public abstract void activar();


    public ObjetoJugador(int id, String textura){
        this.id = id;
        this.textura= textura;
    }



    public String getTextura(){return this.textura;}


    public int getId(){return this.id;}

}
