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

        //for (Jugador jugador: ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){
        Jugador jugador=  ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo().get(4);
            System.out.println(jugador.getColor());
            System.out.println("Y: "+jugador.getCasilla().getPosicionY()+" X:" + jugador.getCasilla().getPosicionX());
            MovimentoIA movimentoIA= new MovimentoIA(jugador.getCasilla(),
                    ComponentesJuego.getComponentes().getCampo().getCasilla(5,0));
            Simulador simulador = Simulador.getInstance();
            movimentoIA.imprimirLista(movimentoIA.calcularCamino());
            simulador.añadirAccion(new Movimiento(jugador,movimentoIA.arraymovimento()));
       // }
    }
}
