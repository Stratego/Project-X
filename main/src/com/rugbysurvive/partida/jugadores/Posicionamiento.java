package com.rugbysurvive.partida.jugadores;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Casilla;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aitor on 27/03/14.
 */
public class Posicionamiento {

    public static ArrayList<Jugador> jugadaequipo1 = new ArrayList<Jugador>();
    public static ArrayList<Jugador> jugadaequipo2 = new ArrayList<Jugador>();
    public static ElementoDibujable casillaVision;

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
     * @param posX posicion del eje x central de la mele
     * @param posY posicion del eje y central de lam ele
     */
    public static void  generarMele(int posX,int posY){


        Campo campo = ComponentesJuego.getComponentes().getCampo();
        jugadoresCercanos(posX,posY);
        int x = posX;
        int y = posY +1;
        for (Jugador jugador1:jugadaequipo1){
            campo.eliminarElemento(jugador1.getPosicionY(),jugador1.getPosicionX());
            jugador1.colocar(new Casilla((float)x,(float)y));

            y -=1;
            if (y==(posY-2)){
                x-=1;
                y=posY;
            }
        }

        x = posX+1;
        y = posY +1;
        for (Jugador jugador2:jugadaequipo2){
            campo.eliminarElemento(jugador2.getPosicionY(),jugador2.getPosicionX());
            jugador2.colocar(new Casilla((float) x, (float) y));

            y -=1;
            if (y==(posY-2)){
                x+=1;
                y=posY;
            }
        }

        jugadaequipo1.clear();
        jugadaequipo2.clear();
    }


    /**
     * Coloca los dos equipos en posicion de saque de banda en la posicion determinada
     * por el jugador
     *
     * @param posX posicion del eje x central del saque
     * @param posY posicion del eje y central del saque
     * @param equipo equipo que saca la pelota
     */
    public static void  generarSaqueBanda(int posX,int posY,Equipo equipo){


        Campo campo = ComponentesJuego.getComponentes().getCampo();
        jugadoresCercanos(posX,posY);
        int x = posX-1;
        int y = posY +3;
        if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
            y = posY -3;
        }
        for (Jugador jugador1:jugadaequipo1){
                campo.eliminarElemento(jugador1.getPosicionY(),jugador1.getPosicionX());
                jugador1.colocar(new Casilla((float)x,(float)y));
            if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                y +=1;
            }else{
                y -=1;
            }
            if (y==(posY)){
                x=posX;
                y=posY;
                if (equipo!=jugador1.getMiEquipo()){
                    break;
                }
            }
        }

        x = posX+1;

        if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
            y = posY -3;
        }else{
            y = posY +3;
        }

        for (Jugador jugador2:jugadaequipo2){

            campo.eliminarElemento(jugador2.getPosicionY(),jugador2.getPosicionX());
            jugador2.colocar(new Casilla((float) x, (float) y));

            if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                y +=1;
            }else{
                y -=1;
            }

            if (y==(posY)){
                x+=1;
                y=posY;
                if (equipo!=jugador2.getMiEquipo()){
                    break;
                }
            }

        }

        jugadaequipo1.clear();
        jugadaequipo2.clear();
    }





    public static  void jugadoresCercanos (int posX,int posY){

        int posXAux=posX-1;
        int posYAux =posY+1;

        int rango = 2;
        boolean salida=false;

        while (salida==false){




            for (int x=0; x<=rango; x++){
                for (int y=0; y<=rango; y++){

                    if (controlPosicion(posXAux+x,posYAux-y)==true){

                        //casillaVision = new ElementoDibujable(TipoDibujo.elementosJuego,"casilla.png");
                        //casillaVision.dibujar(posXAux+x,posYAux-y);

                        if (ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador()!=null){
                            //System.out.println("entra selecion jugador");
                            if (ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                                if (jugadaequipo1.contains(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==false && jugadaequipo1.size()<4){
                                    jugadaequipo1.add(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador());
                                    System.out.println("añadido jugador equipo1" + jugadaequipo1.size());
                                }

                            }else{

                                if (jugadaequipo2.contains(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==false  && jugadaequipo2.size()<4){
                                    jugadaequipo2.add(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador());
                                    System.out.println("añadido jugador equipo2" + jugadaequipo2.size());
                                }


                            }


                    }

                }

            }



        }
            if ( jugadaequipo1.size()>3 && jugadaequipo2.size()>3){
                salida=true;
                break;
            }
            posYAux+=1;
            posXAux-=2;
            rango +=3;
        }
    }


    public static   boolean controlPosicion(int x, int y){


        boolean colocable = false;


        if (x>=0 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){

            colocable=true;
        }



        return colocable;

    }
}
