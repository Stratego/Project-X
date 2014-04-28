package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Created by Victor on 24/04/14.
 */
public class BotonSuplente extends Boton {

    private Jugador jugador;
    ArrayList<ElementoDibujable> habilidades;


    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     * @param posicion
     */
    public BotonSuplente(float posX, float posY, Entrada entrada, String textura, int posicion,Jugador jugador) {
        super(posX, posY, entrada, textura, posicion);
        this.jugador = jugador;
    }

    @Override
    public void accionEntrada(Entrada entrada) {
        //obteniendo la instansacion de equipo y realizar cambio en la lista de jugadores
        ComponentesJuego.getComponentes().getEquipo1().intercambioJugadores(posicion);

    }

    private void dibujarHabilidades(){


    }
}
