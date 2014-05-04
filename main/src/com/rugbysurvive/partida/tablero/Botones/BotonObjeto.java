package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Created by Victor on 24/04/14.
 */
public class BotonObjeto extends Boton {


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
    public BotonObjeto(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada,textura,posicion,ConstantesJuego.getAnchoBotonObjetos(),ConstantesJuego.getAltoBotonObjetos());
        this.objetoElegido = false;

    }

    @Override
    public void accionEntrada(Entrada entrada) {
        //obtenemos el elemento de la lista mediante la posicion le dimos al crear el boton
            if(!objetoElegido) {
                 Jugador jugador = ComponentesJuego.getComponentes().getEquipo1().getJugadorActivo();
                 ArrayList<ObjetoJugador> objetos = jugador.getPowerUP();
                 //activamos y eliminamos el objeto de la lista
                 for (ObjetoJugador iter: objetos){
                     if (iter.getId()==this.posicion){
                             iter.activar();
                             jugador.getPowerUP().remove(iter);
                             this.objetoElegido = true;
                             GestorGrafico.generarDibujante().eliminarTextura(this.ID);
                             break;
                 }
            }


    }





    }
}
