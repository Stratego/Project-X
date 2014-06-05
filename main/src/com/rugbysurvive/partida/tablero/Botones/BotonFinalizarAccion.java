package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;

/**
 * Created by aitor on 30/04/14.
 * Fuerza la finalizacion de una accion en el simulador
 * instantaneamente una vez seleccionado el boton.
 */
public class BotonFinalizarAccion extends Boton {


    private boolean objetoElegido;

    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura  textura que identifica al boton
     * @param posicion
     */
    public BotonFinalizarAccion(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada, textura, posicion,ConstantesJuego.ANCHO_BOTON,ConstantesJuego.ALTO_BOTON);
        this.objetoElegido = false;
        this.posY = ConstantesJuego.POSICION_BOTON_ESCONDIDO;
        this.setEscondido(true);
    }

    /**
     * Finaliza una accion del simulador automaticamente
     * @param entrada datos de entrada del usuario
     */
    @Override
    public void accionEntrada(Entrada entrada) {
        System.out.println("finalizando accion");
        Simulador.getInstance().finalizarAccion();
    }

}





