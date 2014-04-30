package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.ArrayList;
import java.util.Random;

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

        for(int i =0 ;i<ConstantesJuego.JUGADORES_CAMPO;i++)
        {

            if(equipo.getAlineacion().size()>i){
                 jugador = equipo.getAlineacion().get(i).jugador;
                 posicionX = equipo.getAlineacion().get(i).posicionX;
                 posicionY = equipo.getAlineacion().get(i).posicionY;
                 if(lado == Lado.izquierda){
                        campo.añadirElemento(jugador,posicionX,posicionY);
                        equipo.setLado(lado);
                 }
                 else
                 {
                     posicionY =ConstantesJuego.NUMERO_CASILLAS_LARGO_TABLERO - posicionY -1;
                     posicionX = ConstantesJuego.NUMERO_CASILLAS_ANCHO_TABLERO  -posicionX- 1;
                     campo.añadirElemento(jugador,posicionX,posicionY);
                     equipo.setLado(lado);
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
     * //@param campo Zona de juego
     * //@param equipo Conjunto de jugadores que seran situados en el campo
     * @param lado Mitad del campo donde seran situados los jugadores
     * @param posX posicion del eje x central de la mele
     * @param posY posicion del eje y central de lam ele
     */
    public static void  generarMele(Lado lado,int posX,int posY){
    //public static void  generarMele(Campo campo,Equipo equipo,Equipo equipo2,Lado lado,int posX,int posY){

        Campo campo = ComponentesJuego.getComponentes().getCampo();
        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo1();
        ArrayList<Jugador> meeleequipo1 = new ArrayList<Jugador>();
        ArrayList<Jugador> meeleequipo2 = new ArrayList<Jugador>();
        //if (posY>ConstantesJuego.FUERA_CAMPO_ABAJO && posY<ConstantesJuego.FUERA_CAMPO_ARRIBA ){
        //}


        //while (meeleequipo1.size()<4 && meeleequipo2.size()<4 ){


        //}
        while (meeleequipo1.size()>4){

                Jugador jugadorMelee=equipo1.listaJugadoresCampo().get(new Random().nextInt(equipo1.listaJugadoresCampo().size()));
                if (meeleequipo1.contains(jugadorMelee)==false){
                    meeleequipo1.add(jugadorMelee);
                }

         }
        while (meeleequipo2.size()>4){

            Jugador jugadorMelee=equipo2.listaJugadoresCampo().get(new Random().nextInt(equipo2.listaJugadoresCampo().size()));
            if (meeleequipo2.contains(jugadorMelee)==false){
                meeleequipo2.add(jugadorMelee);
            }

        }

    }

    public void jugadoresCercanos (int posX,int posY){
        for (int x= posX; x<ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO;x++ ){
            for (int y= posY; y<ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO;y++ ){
                if (ComponentesJuego.getComponentes().getCampo().getCasilla(x,y).getJugador()!=null){
                    if (ComponentesJuego.getComponentes().getCampo().getCasilla(x,y).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                        
                    }
                }
            }
        }
    }
}
