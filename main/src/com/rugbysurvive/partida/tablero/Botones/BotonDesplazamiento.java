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
     * @param textura
     */
    public BotonDesplazamiento(float posX, float posY, Entrada entrada, String textura, int direccion,Lista lista) {
        super(posX, posY, entrada, textura, 0,ConstantesJuego.ANCHO_BOTON,ConstantesJuego.ALTO_BOTON);
        this.direccion = direccion;
        this.lista = lista;

    }

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
