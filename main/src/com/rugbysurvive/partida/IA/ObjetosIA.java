package com.rugbysurvive.partida.IA;


import com.models.Powerup;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.ColocadorObjetosCampo;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.jugadores.Habilidades;

/**
 * Created by Victor on 15/05/14.
 */
public class ObjetosIA {


    public ObjetosIA(){}

    public boolean usarObjeto(Jugador jugador){
        if (jugador.getPowerUP().size()>0){
            for(ObjetoJugador objetos : jugador.getPowerUP()){
                if (objetos instanceof ColocadorObjetosCampo == false){
                    if (objetos instanceof PowerUP == true){
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
                            case arriba:
                                if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()+i,
                                        jugador.getPosicionX()).sinJugador()==false){
                                    objetos.activar();
                                }
                                break;
                            case abajo:
                                if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()-i,
                                        jugador.getPosicionX()).sinJugador()==false){
                                    objetos.activar();
                                }
                                break;
                            case izquierda:
                                if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),
                                        jugador.getPosicionX()-i).sinJugador()==false){
                                    objetos.activar();
                                }
                                break;
                            case derecha:
                                if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),
                                        jugador.getPosicionX()+i).sinJugador()==false){
                                    objetos.activar();
                                }
                                break;

                        }
                    }
                }
            }
        }
        return false;
    }
}
