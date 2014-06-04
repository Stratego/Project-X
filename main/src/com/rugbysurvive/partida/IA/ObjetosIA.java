package com.rugbysurvive.partida.IA;


import com.models.Powerup;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.ColocadorObjetosCampo;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.jugadores.Habilidades;

/**
 * Clase que indica las condiciones en las que un jugador controlado por la IA usara un objeto si es que posee alguno.
 * en caso de que sus estado en alguna habilidad sean bajos se usara un powerUP,
 * si tiene un jugador enemigo cerca usara un objeto de campo.
 * Created by Victor on 15/05/14.
 */
public class ObjetosIA {

    /**
     * constructor de la clase
     */
    public ObjetosIA(){}

    /**
     * Clase principal que indica las condiciones de uso de un objeto tanto si es powerUP o objeto de campo
     * @param jugador jugador que usara el objeto
     * @return indica si se ha usado un objeto o no.
     */
    public boolean usarObjeto(Jugador jugador){
        if (jugador.getPowerUP().size()>0){
            // recorremos los objetos
            for(ObjetoJugador objetos : jugador.getPowerUP()){

                if (objetos instanceof ColocadorObjetosCampo == false){

                    if (objetos instanceof PowerUP == true){

                        // si el jugador esta a un 1/3 de una habilidad y tiene un  objeto que lo potencia lo usa

                        PowerUP powerUP = (PowerUP)objetos;

                        if (powerUP.getHabilidad()== Habilidades.defensa && jugador.getDefensa()<=(jugador.MAX_DEFENSA/3)){
                            objetos.activar();
                            return true;
                        }

                        if (powerUP.getHabilidad()== Habilidades.fuerza && jugador.getFuerza()<=(jugador.MAX_FUERZA/3)){
                            objetos.activar();
                            return true;
                        }

                        if (powerUP.getHabilidad()== Habilidades.ataque && jugador.getAtaque()<=(jugador.MAX_ATAQUE/3)){
                            objetos.activar();
                            return true;
                        }

                        if (powerUP.getHabilidad()== Habilidades.habilidad && jugador.getHabilidad()<=(jugador.MAX_HABILIDAD/3)){
                            objetos.activar();
                            return true;
                        }

                        if (powerUP.getHabilidad()== Habilidades.resistencia && jugador.getResistencia()<=(jugador.MAX_RESISTENCIA/3)){
                            objetos.activar();
                            return true;
                        }

                        if (powerUP.getHabilidad()== Habilidades.vida && jugador.getVida()<=(jugador.vidaOriginal/3)){
                            objetos.activar();
                            return true;
                        }
                    }
                } else {

                    for (int i = 0; i<3; i++){

                        switch (jugador.getDireccion())
                        {
                            // si el jugador tiene a un enigo a tres 3 casillas o menos de la
                            // posicion de la que esta mirando usa un objeto de campo
                            case arriba:
                                if (controlPosicion(jugador.getPosicionX(),jugador.getPosicionY()+i)){
                                    if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY() + i,
                                            jugador.getPosicionX()).sinJugador()==false){
                                        if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()+i,
                                                jugador.getPosicionX()).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                                            objetos.activar();
                                          
                                        }
                                    }
                                }
                                break;
                            case abajo:
                                if (controlPosicion(jugador.getPosicionX(),jugador.getPosicionY()-i)){
                                    if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()-i,
                                            jugador.getPosicionX()).sinJugador()==false){
                                        if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()-i,
                                                jugador.getPosicionX()).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                                            objetos.activar();
                                        }
                                    }
                                }
                                break;
                            case izquierda:
                                if (controlPosicion(jugador.getPosicionX()-i,jugador.getPosicionY())){
                                    if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),
                                            jugador.getPosicionX() - i).sinJugador()==false){
                                        if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),
                                                jugador.getPosicionX()-i).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                                            objetos.activar();
                                        }
                                    }
                                }
                                break;
                            case derecha:
                                if (controlPosicion(jugador.getPosicionX()+i,jugador.getPosicionY())){
                                if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),
                                        jugador.getPosicionX() + i).sinJugador()==false){
                                    if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),
                                            jugador.getPosicionX()+i).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                                        objetos.activar();
                                    }
                                }
                                }
                                break;

                        }
                    }
                }
            }
        }
        return false;
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
