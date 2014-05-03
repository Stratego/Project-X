package com.rugbysurvive.partida.elementos.objetos.objetosCampo;

import com.rugbysurvive.partida.Jugador.Jugador;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;


/**
 * Created by Victor on 15/04/14.
 */
public class MinaCampo extends ObjetoCampo {




    public MinaCampo(String textura){
        super(textura);
    }
    @Override
    public void efecto(Jugador jugador) {
        jugador.setVida(jugador.getVida()-100);
        //System.out.println("vida jugador que ha pisado"+jugador.getVida());
        quitar();
    }


}
