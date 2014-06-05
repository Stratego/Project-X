package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Lista;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Created by aitor on 2/05/14.
 * Permite el desplazamiento dentro de la lista de jugadores
 * de la lista de suplentes.
 * Puede desplazarse hacia arriba de la lista o hacia abajo.
 *
 */
public class BotonDesplazamiento extends Boton {

    private Lista lista;
    private int direccion;
    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura  textura que identifica al boton
     * @param direccion indica la direccion de desplazamiento puediendo ser hacia
     *                  arriba o hacia abajo
     * @param lista elementos de la lista de suplentes por los que se ha de desplazar
     */

    public BotonDesplazamiento(float posX, float posY, Entrada entrada, String textura, int direccion,Lista lista) {
        super(posX, posY, entrada, textura, 0,ConstantesJuego.ANCHO_BOTON,ConstantesJuego.ALTO_BOTON);
        this.direccion = direccion;
        this.lista = lista;

    }

    /**
     * Deplaza la lista hacia arriba o hacia abajo
     * segun el boton que se haya creado.
     * @param entrada entrada de datos de usuario.
     */
    @Override
    public void accionEntrada(Entrada entrada) {

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        Equipo equipoSeleccionado = equipo2;

        if(equipo1.hayJugadorSelecionado()){
            equipoSeleccionado  = equipo1;
        }

        ArrayList<Jugador> suplentes= equipoSeleccionado.listaSuplentes();

        int posInicial = this.lista.getPosicionListaSuplentesInicial();


        if(direccion == 0) {

           if(posInicial+2 < suplentes.size()-1){
                this.lista.setPosicionListaSuplentesInicial(posInicial+1);
                this.lista.eliminarListaSuplentes();
                this.lista.crearLista(Entrada.listasuplente);
            }
        }

        else{
           if(posInicial>0){
               this.lista.setPosicionListaSuplentesInicial(posInicial-1);
               this.lista.eliminarListaSuplentes();
               this.lista.crearLista(Entrada.listasuplente);
           }

        }



    }
}
