package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Victor on 30/04/14.
 * Regla que determina si el arbitro sanciona el uso del objetos.
 */
public class UsoObjetos extends Regla {
    private Jugador jugador;

    public UsoObjetos (Jugador jugador){
        super();
        this.jugador = jugador;
    }
    @Override
    public boolean arbitrar() {
        System.out.println("arbitrando uso de objetos");
        if(this.arbitro.esSucesoVisible(this.jugador.getPosicionX(),this.jugador.getPosicionY())==true){
            System.out.println("uso de objetos");

            if ((jugador.getMiEquipo().getLado()== Lado.izquierda && this.jugador.getPosicionX()<=7)||(jugador.getMiEquipo().getLado()== Lado.derecha && this.jugador.getPosicionX()>=23)){
                this.posicionamiento.generarPenalty(this.jugador.getMiEquipo(), jugador.getPosicionX(), jugador.getPosicionY());
                System.out.println("chute");
            }else{
                this.posicionamiento.generarMele(this.jugador.getPosicionX(), this.jugador.getPosicionY());
                System.out.println("meele");
            }

            return true;
        }
        return false;
    }
}
