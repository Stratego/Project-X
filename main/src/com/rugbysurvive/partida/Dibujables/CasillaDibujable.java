package com.rugbysurvive.partida.Dibujables;

import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Dibujante;

/**
 * Created by Victor on 28/03/14.
 */
public class CasillaDibujable implements Dibujable {

    int posicionX;
    int posicionY;
    int ID;
    Dibujante dibujante;

    public CasillaDibujable(Dibujante dibujante,int posicionX,int posicionY){

        this.posicionX = posicionX;
        this.posicionY = posicionY;
        ID =  dibujante.añadirDibujable(this);
        this.dibujante = dibujante;
    }


    @Override
    public String getTextura() {
        return "casellalila.png";
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
