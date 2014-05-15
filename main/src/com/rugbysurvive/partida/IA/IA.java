package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Pase;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.arbitro.UsoObjetos;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Victor on 12/05/14.
 */
public class IA {

    private ObjetosIA usoObjetos = new ObjetosIA();

    private PaseChuteIA paseChuteIA = new PaseChuteIA();

    private MovimentoIA movimentoIA;

    public IA(){
        ejecutar();
    }


    public void ejecutar(){

        for (Jugador jugador: ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

            if (usoObjetos.usarObjeto(jugador)==false){
                if (paseChuteIA.hacerPaseChute(jugador)==false){

                    if (ComponentesJuego.getComponentes().getCampo().posicionPelota()==null){
                        if (jugador.getMiEquipo().getLado()== Lado.izquierda){
                            movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                    ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),28));
                        }else{
                            movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                    ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(),1));
                        }

                    }else{
                        movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                ComponentesJuego.getComponentes().getCampo().posicionPelota());
                    }

                    Simulador simulador = Simulador.getInstance();

                    movimentoIA.imprimirLista(movimentoIA.calcularCamino());

                    simulador.añadirAccion(new Movimiento(jugador,movimentoIA.arraymovimento(), null));
                }
            }
        }
    }
}

