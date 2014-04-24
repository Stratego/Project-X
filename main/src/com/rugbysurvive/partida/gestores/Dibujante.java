package com.rugbysurvive.partida.gestores;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.gestores.Entrada.DibujableEscalado;

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
     * @param dibujable Clase que contiene todos los atributos necesarios
     *                  para dibujar la textura
     *
     * @param tipoDibujo Estilo de dibujo que se realizara.
     *
     * @return identificador que debera ser usado para borrar la referencia
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

    public void dibujarLinia(int posicionXInicial,int posicionYInicial,int posicionXFinal,int posicionYFinal);

    /**
     * Añade una clase de tipo dibujable escalado a la clase de tipo dibujante
     * La clase añadida si la textura existe y las posiciones  y el escalado han sido
     * definidas sera dibujada y actualizada cada cierto tiempo en la pantalla.
     *
     * @param dibujable Clase que contiene todos los atributos necesarios
     *                  para dibujar la textura
     * @param tipoDibujo Estilo de Estilo de dibujo que se realizara.
     * @return identificador que debera ser usado para borrar la referencia
     *         posteriormente
     */
    public int añadirDibujable(DibujableEscalado dibujable,TipoDibujo tipoDibujo);

}
