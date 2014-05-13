package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;

/**
 * Created by Victor on 12/05/14.
 */
public class IA {

    public IA(){
        ejecutar();
    }


    public void ejecutar(){

        for (Jugador jugador: ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){
            System.out.println("Y: "+jugador.getPosicionY()+" X:" + jugador.getPosicionX());
            MovimentoIA movimentoIA= new MovimentoIA(ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),jugador.getPosicionX()),
                    ComponentesJuego.getComponentes().getCampo().getCasilla(5,0));
            Simulador simulador = Simulador.getInstance();
            simulador.añadirAccion(new Movimiento(jugador,movimentoIA.arraymovimento(), null));
        }
    }
}
