package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Created by Victor on 24/04/14.
 */
public class BotonObjeto extends Boton {
    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     * @param posicion
     */
    public BotonObjeto(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada, textura, posicion);
    }

    @Override
    public void accionEntrada(Entrada entrada) {
        //obtenemos el elemento de la lista mediante la posicion le dimos al crear el boton

            Jugador jugador = ComponentesJuego.getComponentes().getEquipo1().getJugadorActivo();
            ArrayList<ObjetoJugador> objetos = jugador.getPowerUP();
            System.out.println("vida jugador antes objeto "+jugador.getVida());
            //activamos y eliminamos el objeto de la lista
            for (ObjetoJugador iter: objetos){
                if (iter.getId()==this.posicion){
                    iter.activar();
                    System.out.println("vida jugador despues objeto "+ jugador.getVida());
                    jugador.getPowerUP().remove(iter);
                    break;
                }
            }



    }
}
