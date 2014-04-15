package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by aitor on 27/03/14.
 */
public class Posicionamiento {

    /**
     * Coloca al equipo en posicion de saque elegido predeterminado
     * por el jugador en los menus de configuracion
     *
     * @param campo Zona de juego
     * @param equipo Conjunto de jugadores que seran situados en el campo
     * @param lado Mitad del campo donde seran situados los jugadores
     *
     */
    public static void generarSaqueCampo (Campo campo,Equipo equipo,Lado lado)
    {
         campo.borrarEquipo(equipo);
        Jugador jugador;
        int posicionX;
        int posicionY;

        for(int i =0 ;i<Equipo.JUGADORES_CAMPO;i++)
        {

            if(equipo.getAlineacion().size()>i){
                 jugador = equipo.getAlineacion().get(i).jugador;
                 posicionX = equipo.getAlineacion().get(i).posicionX;
                 posicionY = equipo.getAlineacion().get(i).posicionY;
                 if(lado == Lado.izquierda){
                        campo.añadirElemento(jugador,posicionX,posicionY);
                 }
                 else
                 {
                     posicionY =ConstantesJuego.NUMERO_CASILLAS_LARGO_TABLERO -posicionY -1;
                     posicionX = ConstantesJuego.NUMERO_CASILLAS_ANCHO_TABLERO -posicionX- 1;
                     campo.añadirElemento(jugador,posicionX,posicionY);
                 }

            }
        }

    }

    /**
     * Coloca al equipo en posicion de saque determinado por las reglas
     * de juego
     *
     * @param campo Zona de juego
     * @param equipo Conjunto de jugadores que seran situados en el campo
     * @param lado Mitad del campo donde seran situados los jugadores
     *
     */
     public static void generarSaquePredeterminado(Campo campo,Equipo equipo,Lado lado)
     {

     }

    /**
     * Coloca los dos equipos en posicion de mele en la posicion determinada
     * por el jugador
     *
     * @param campo Zona de juego
     * @param equipo Conjunto de jugadores que seran situados en el campo
     * @param lado Mitad del campo donde seran situados los jugadores
     * @param posX posicion del eje x central de la mele
     * @param posY posicion del eje y central de lam ele
     */
    public static void  generarMele(Campo campo,Equipo equipo,Equipo equipo2,Lado lado,int posX,int posY){}
}
