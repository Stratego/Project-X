package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Pase;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Victor on 15/05/14.
 */
public class PaseChuteIA {

    public static ArrayList<Jugador> jugadaequipo1 = new ArrayList<Jugador>();

    public static ArrayList<Jugador> jugadaequipo2 = new ArrayList<Jugador>();

    public PaseChuteIA(){}

    public boolean hacerPaseChute(Jugador jugador){

        if (jugador.getEstado() instanceof ConPelota){

            if (jugador.getPosicionX()>=7 && jugador.getPosicionX()<= ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO-7){
               return hacerPase(jugador);
            } else {
                return  hacerChute(jugador);
            }

        }
        return false;
    }


    public boolean hacerChute(Jugador jugador){
        Chute chute;
        if (jugador.getMiEquipo().getLado()== Lado.derecha && jugador.getPosicionX()<7 && jugador.getPosicionY()<=7
                &&jugador.getPosicionY()>=12){
            chute = new Chute(jugador,new Random().nextInt()%2,(int)(Math.random()*(11-8+1)+8));
            Simulador.getInstance().añadirAccion(chute);
            System.out.println("chute izquierda IA");
            return true;
        }else if(jugador.getMiEquipo().getLado()== Lado.izquierda && jugador.getPosicionX()<ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO-7 &&
                jugador.getPosicionY()<=7 &&jugador.getPosicionY()>=12){
            chute = new Chute(jugador,(int)(Math.random()*(29-28+1)+28),(int)(Math.random()*(11-8+1)+8));
            Simulador.getInstance().añadirAccion(chute);
            System.out.println("chute derecha IA");
            return true;

        }
        return false;
    }

    public boolean hacerPase(Jugador jugador){


        jugadoresCercanos(jugador.getPosicionX(),jugador.getPosicionY());

        if (jugadaequipo1.size()>0 && jugadaequipo2.size()>0){
            Pase pase = new Pase(jugador,jugadaequipo2.get(jugadaequipo2.size()-1).getPosicionX(),jugadaequipo2.get(jugadaequipo2.size()-1).getPosicionY());
            Simulador.getInstance().añadirAccion(pase);
            System.out.println("pase IA");
            return true;
        }

        return false;
    }

    public static  void jugadoresCercanos (int posX,int posY){

        int posXAux=posX-3;
        int posYAux =posY+3;
        int rango = 6;
        boolean salida=false;

        //while (salida==false){

            for (int x=0; x<=rango; x++){
                for (int y=0; y<=rango; y++){

                    if (controlPosicion(posXAux+x,posYAux-y)==true){
                        //casillaVision = new ElementoDibujable(TipoDibujo.elementosJuego,"casilla.png");
                        //casillaVision.dibujar(posXAux+x,posYAux-y);
                        if (ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador()!=null){
                            //System.out.println("entra selecion jugador");
                            if (ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                                //if (ComponentesJuego.getComponentes().getEquipo1().jugadorEnEquipo(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==true){

                                if (jugadaequipo1.contains(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==false && jugadaequipo1.size()<4){
                                    jugadaequipo1.add(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador());
                                    System.out.println("añadido jugador equipo1: " + jugadaequipo1.size());
                                }
                            }else{
                                if (jugadaequipo2.contains(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==false  && jugadaequipo2.size()<4){
                                    jugadaequipo2.add(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador());
                                    System.out.println("añadido jugador equipo2 :" + jugadaequipo2.size());
                                }
                            }
                        }

                    }

                }
            }
            /*if ( jugadaequipo1.size()>3 && jugadaequipo2.size()>3){
                salida=true;
                break;
            }
            posYAux+=1;
            posXAux-=2;
            rango +=3;
        }*/
    }



    public static   boolean controlPosicion(int x, int y){

        boolean colocable = false;

        if (x>=0 && x<= ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){

            colocable=true;
        }

        return colocable;

    }
}
