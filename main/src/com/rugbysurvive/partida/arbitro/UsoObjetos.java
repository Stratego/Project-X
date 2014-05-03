package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.Jugador.Jugador;

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
        if(this.arbitro.esSucesoVisible(this.jugador.getPosicionX(),this.jugador.getPosicionY())==true){
            System.out.println("ha ocurrido un choque");
            if (this.jugador.getPosicionX()>=7 && this.jugador.getPosicionX()<=22 ){
                this.posicionamiento.generarMele(this.jugador.getPosicionX(),this.jugador.getPosicionY());
            }else{
                //GENERAR CHUTE
                System.out.println("generando chute");
            }

            return true;
        }
        return false;
    }
}
