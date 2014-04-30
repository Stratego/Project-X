package com.rugbysurvive.partida.tablero.Botones;

import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;

/**
 * Created by aitor on 29/04/14.
 */
public class BotonCambioTurno extends Boton {


    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     * @param posicion
     */
    public BotonCambioTurno(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada, textura, posicion);
        this.ancho = ConstantesJuego.variables().getAnchoBoton();
        this.alto =ConstantesJuego.variables().getAnchoBoton();

    }

    @Override
    public void accionEntrada(Entrada entrada) {
        GestorTurnos.cambiarTurno();
        System.out.println("CAMBIO TURNO");
    }
}
