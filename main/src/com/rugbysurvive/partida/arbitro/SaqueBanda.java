package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.jugadores.Equipo;

/**
 * Created by Victor on 30/04/14.
 * Regla que determina si el arbitro pita un saque de banda o no.
 */
public class SaqueBanda extends Regla{
    private int x =0;
    private int y =0;
    private Equipo equipo;

    public SaqueBanda(int y ,int x, Equipo equipo){
        this.x = x;
        this.y = y;
        this.equipo = equipo;
    }
    @Override
    public boolean arbitrar() {

        if(this.arbitro.esSucesoVisible(this.x,this.y)==true){
            if (this.x<=2){
                this.x=3;
            }else if (this.x>=27){
                this.x=26;
            }
            System.out.println("saque de banda "+ this.x+","+this.y);
            if (this.y>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                System.out.println("GENENRAOD SAQUE DE BANDA superior");
                this.posicionamiento.generarSaqueBanda(this.x,ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR,this.equipo);
            }else if(this.y<=ConstantesJuego.POSICION_SAQUE_BANDA_INFERIOR){
                System.out.println("GENENRAOD SAQUE DE BANDA inferiorr");
                this.posicionamiento.generarSaqueBanda(this.x,ConstantesJuego.POSICION_SAQUE_BANDA_INFERIOR,this.equipo);
            }

            return  true;
        }
        return false;
    }
}
