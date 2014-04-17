package com.rugbysurvive.partida.elementos.objetos.objetosCampo;

import com.rugbysurvive.partida.Jugador.Jugador;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;


/**
 * Created by Victor on 15/04/14.
 */
public class MinaCampo extends ObjetoCampo {

    int posX;
    int posY;


    public MinaCampo(int posX, int posY, String textura){
        super(posX,posY,textura);
        this.posX=posX;
        this.posY=posY;

    }
    @Override
    public void efecto(Jugador jugador) {
        jugador.setVida(jugador.getVida()-100);
        //System.out.println("vida jugador que ha pisado"+jugador.getVida());

        quitar();
    }

    public void quitar(){
        super.quitar();

    }
}
