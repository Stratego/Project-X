package com.rugbysurvive.partida.gestores;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;

/**
 * Clase que dibujara un texto en nuestro juego
 * Created by Victor on 11/04/14.
 */
public class Texto implements Dibujable{
    int ID;
    int posicionX;
    int posicionY;
    String textura;

    public  Texto(int posicionX, int posicionY, String textura){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.ID = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.texto);
        this.textura = textura;
    }


    public int getID(){
        return ID;
    }

    @Override
    public String getTextura() {
        return this.textura;
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
