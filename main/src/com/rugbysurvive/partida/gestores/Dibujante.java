package com.rugbysurvive.partida.gestores;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;

/**
 * Created by aitor on 26/03/14.
 * La clase que contenga este interfaz debera gestionar todas las clases
 * de de tipo dibujable que reciba o deban ser eliminadas.
 */
public interface Dibujante {

    /**
     * Añade una clase de tipo dibujable a la clase de tipo dibujante
     * La clase añadida si la textura existe y las posiciones han sido
     * definidas sera dibujada y actualizada cada cierto tiempo en la pantalla
     * de juego
     *
     * @param dibujable clase que contiene todos los atributos necesarios
     *                  para dibujar la textura
     * @return identificar que debera ser usado para borrar la referencia
     *         posteriormente
     */
    public int añadirDibujable(Dibujable dibujable,TipoDibujo tipoDibujo);

    /**
     * Elimina la referencia de la clase que envia la identificacion
     *  Eso quiere decir que dejara de dibujarse inmediatamente.
     * @param ID identificador de la clase , generado
     *           al añadir la clase anteriormente
     */
    public void eliminarTextura(int ID);
}
