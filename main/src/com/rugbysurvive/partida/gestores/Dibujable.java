package com.rugbysurvive.partida.gestores;

/**
 * Created by aitor on 25/03/14.
 * Intefaz destinada a todas las clases que necesiten dibujar una textura en
 * una posicion de la pantalla determinada. Para ello debera implementar todos
 * los metodos.
 */
public interface Dibujable {

    /**
     * Devuelve la ruta donde esta situada la textura
     * dentro de la carpeta Assets
     * @return string con la ruta de la textura
     */
    public String getTextura();

    /**
     * Devuelve la posicion en el eje X donde se dibuja
     * la textura , puede ser o una posicion absoluta o la posicion
     * dentro de de la casilla.
     * @return posicion de la textura en el eje x
     */
    public int getPosicionX();

    /**
     * Devuelve la posicion en el eje Y donde se dibuja
     * la textura , puede ser o una posicion absoluta o la posicion
     * dentro de de la casilla.
     * @return posicion de la textura en el eje y
     */
    public int getPosicionY();



}
