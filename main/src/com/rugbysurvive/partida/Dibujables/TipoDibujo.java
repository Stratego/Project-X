package com.rugbysurvive.partida.Dibujables;

/**
 * Created by aitor on 1/04/14.
 * Caracteristica basica de la textura dentro del juego
 *
 * InterficieUsuario : La textura se mantendra fija y sera dibujada segun la ç
 *                     resolucion de la pantalla, tampoco le afecta el zoom
 *
 * Fondo :  Primeras texturas en ser dibujadas , le afectan todos los procesos
 *          de la camara
 *
 * ElementosJuego : Se dibujan despues del fondo  , le afecta todos los procesos
 *                  de la camara
 *
 * Texto :  Dibuja texto en lugar de texturas , se dibuja al mismo nivel que una interficie de
 *          usuario por lo que implicitamente forma parte de el.
 *
 */
public enum TipoDibujo {
    interficieUsuario,fondo,elementosJuego,texto
}
