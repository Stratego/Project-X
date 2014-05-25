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
 * Clase que estipula cuando realiza un pase o un chute un jugador controlado por la IA
 * Created by Victor on 15/05/14.
 */
public class PaseChuteIA {

    public static ArrayList<Jugador> jugadaequipo1 = new ArrayList<Jugador>();

    public static ArrayList<Jugador> jugadaequipo2 = new ArrayList<Jugador>();

    /**
     * constructor de la clase
     */
    public PaseChuteIA(){}

    /**
     * Comprueba que se pueda realizar un chute o un pase y si es asi lo realiza
     * @param jugador jugador que realizara el pase o chute
     * @return indica si se ha realizado alguna accion sea pase o chute
     */
    public boolean hacerPaseChute(Jugador jugador){

        if (jugador.getEstado() instanceof ConPelota){
            System.out.println(jugador.getPosicionX());
            System.out.println(jugador.getPosicionY());
            if (jugador.getPosicionX()>=7 && jugador.getPosicionX()<= ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO-7){
                System.out.println("zona de pase");
               return hacerPase(jugador);

            } else {
                System.out.println("zona de chute");
                return  hacerChute(jugador);
            }

        }
        return false;
    }

    /**
     * Si el jugador esta cerca de los palos de su zona de marque realizara un chute
     * @param jugador jugador que hara el chute
     * @return indica si se a realizado un chuete
     */
    public boolean hacerChute(Jugador jugador){
        Chute chute;
        if (jugador.getMiEquipo().getLado()== Lado.derecha && jugador.getPosicionX()<7 && jugador.getPosicionY()>=7
                && jugador.getPosicionY()<=12){
            chute = new Chute(jugador,0,(int)(Math.random()*(11-8+1)+8));
            Simulador.getInstance().añadirAccion(chute);
            System.out.println("chute izquierda IA");
            return true;
        }else if(jugador.getMiEquipo().getLado()== Lado.izquierda && jugador.getPosicionX()>ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO-7 &&
                jugador.getPosicionY()>=7 && jugador.getPosicionY()<=12){
            chute = new Chute(jugador,(int)(Math.random()*(29-28+1)+28),(int)(Math.random()*(11-8+1)+8));
            Simulador.getInstance().añadirAccion(chute);
            System.out.println("chute derecha IA");
            return true;

        }
        return false;
    }

    /**
     * Si el jugador tiene muchos jugadores rivales a su alrederor realizara un pase al mas lejano dentro de su rango de pase
     * @param jugador jugador que hara el pase
     * @return indica si se ha realizado el pase
     */
    public boolean hacerPase(Jugador jugador){


        jugadoresCercanos(jugador.getPosicionX(),jugador.getPosicionY(),jugador);

        if (jugadaequipo1.size()>0 && jugadaequipo2.size()>0 && jugadaequipo1.size()>jugadaequipo2.size()){
            Pase pase = new Pase(jugador,jugadaequipo2.get(jugadaequipo2.size()-1).getPosicionX(),jugadaequipo2.get(jugadaequipo2.size()-1).getPosicionY());
            Simulador.getInstance().añadirAccion(pase);
            System.out.println("pase IA");
            return true;
        }

        return false;
    }

    /**
     * busca a los jugadores cercanos tanto de nuestro equipo como del rival en un rango cercano a la posicion dada
     * @param posX pos x de la casilla que central del rango.
     * @param posY pos y de la casilla que central del rango.
     */
    public static  void jugadoresCercanos (int posX,int posY,Jugador jugador){

        int posXAux=posX-3;
        int posYAux =posY+3;
        int rango = (int)(jugador.getFuerza()/10);
        if (rango ==0){
            rango =1;
        }
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


    /**
     * Controla que laposicion que queremos cojer este dentro del tablero de juego
     * @param x posicion x que queremos comprobar
     * @param y posicion y que queremos comprobar
     * @return indica si la posicion es correcta o no
     */
    public static   boolean controlPosicion(int x, int y){

        boolean colocable = false;

        if (x>=0 && x<= ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){

            colocable=true;
        }

        return colocable;

    }
}
