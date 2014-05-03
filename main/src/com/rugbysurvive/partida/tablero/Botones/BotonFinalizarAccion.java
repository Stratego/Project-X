package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;

/**
 * Created by aitor on 30/04/14.
 */
public class BotonFinalizarAccion extends Boton {


    private boolean objetoElegido;

    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     * @param posicion
     */
    public BotonFinalizarAccion(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada, textura, posicion,ConstantesJuego.ANCHO_BOTON,ConstantesJuego.ALTO_BOTON);
        this.objetoElegido = false;
        this.posY = -140;
        this.setEscondido(true);
    }

    @Override
    public void accionEntrada(Entrada entrada) {
        System.out.println("finalizando accion");
        Simulador.getInstance().finalizarAccion();
    }

}





