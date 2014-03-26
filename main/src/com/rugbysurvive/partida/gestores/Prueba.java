package com.rugbysurvive.partida.gestores;

/**
 * Created by aitor on 26/03/14.
 */
public class Prueba implements Dibujable{

    int posicionX;
    int posicionY;

    public Prueba(Dibujante dibujante,int posicionX,int posicionY){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        dibujante.añadirDibujable(this);
    }

    @Override
    public String getTextura() {
        return "jugador1.png";
    }

    @Override
    public int getPosicionX() {
        return this.posicionX;
    }

    @Override
    public int getPosicionY() {
        return this.posicionY;
    }
}
