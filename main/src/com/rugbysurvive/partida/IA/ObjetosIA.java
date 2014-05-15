package com.rugbysurvive.partida.IA;


import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.ColocadorObjetosCampo;

/**
 * Created by Victor on 15/05/14.
 */
public class ObjetosIA {


    public ObjetosIA(){}

    public boolean usarObjeto(Jugador jugador){
        if (jugador.getPowerUP().size()>0){
            for(ObjetoJugador objetos : jugador.getPowerUP()){
                if (objetos instanceof ColocadorObjetosCampo == false){
                    objetos.activar();
                    return true;
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
