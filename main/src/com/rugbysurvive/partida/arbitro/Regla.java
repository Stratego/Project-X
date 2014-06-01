package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.jugadores.Posicionamiento;

/**
 * Created by aitor on 26/04/14.
 * Esqueleto del conjunto de reglas que deben ser arbitradas.
 * En el constructor se obtiene el conjunto de elementos necesarios
 * para poder realizar el arbitraje.
 *
 */
public abstract class Regla {

    /**
     * instancia de la clase arbitro
     */
    protected Arbitro arbitro;

    /**
     * clase posicionamiento que nos permitara realizar meeles, penaltys y saques de banda
     */
    protected Posicionamiento posicionamiento = new Posicionamiento();

    /**
     * constructor que crea la regla obteniendo la instacia del arbitro
     */
    public Regla() {
       this.arbitro = Arbitro.getInstancia();
    }


    /**
     * Realiza el proceso de arbitre de la jugada.
     * Si el arbitro la ha visto y la jugada es arbitrable
     * bloquea la simulacion , coloca todos los elementos en las
     * posiciones necesarias y realiza todos los castigos necesarios.
     * Despues del proceso de arbitraje debe seguir el siguiente turno del
     * jugador .
     *
     * @return cierto si el arbitro determina que se ha infrigido una norma
     *         falso en caso contrario
     */
    public abstract boolean arbitrar();
}
