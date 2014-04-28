package com.rugbysurvive.partida;

import com.rugbysurvive.partida.gestores.Dibujable;

/**
 * Representacion de la pantilla que se mostrara detras de los objetos
 * Created by Victor on 17/04/14.
 */
public class PlantillaObjetos implements Dibujable {

    private int posX;
    private int posY;
    private String textura;


    public PlantillaObjetos(int posX, int posY, String textura) {
        this.posX = posX;
        this.posY = posY;
        this.textura = textura;
    }

    @Override
    public String getTextura() {
        return textura;
    }

    @Override
    public int getPosicionX() {
        return posX;
    }

    @Override
    public int getPosicionY() {
        return posY;
    }
}
