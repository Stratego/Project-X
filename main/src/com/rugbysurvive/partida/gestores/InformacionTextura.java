package com.rugbysurvive.partida.gestores;

/**
 * Created by aitor on 26/03/14.
 */
public class InformacionTextura
{

    private  int posicionY;
    private int posicionX;
    private  String nombretextura;

    public InformacionTextura(String nombretextura ,int posicionX,int posicionY){
        this.nombretextura = nombretextura;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }


    public String getNombretextura() {
        return nombretextura;
    }

    public void setNombretextura(String nombretextura) {
        this.nombretextura = nombretextura;
    }



    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }


    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }


}