package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Lado;

/**Clase principal de la inteligencia artificial.
 * Comprobara todos los jugadores del equipo controlado por la IA
 * y a traves de las reglas establecidas realizara la accion que mas se adeque a la situacion,
 * yasea realiazar pases, chutes, usar objetos o moverse.
 * Created by Victor on 12/05/14.
 */
public class IA {

    private ObjetosIA usoObjetos = new ObjetosIA();

    private PaseChuteIA paseChuteIA = new PaseChuteIA();

    private MovimentoIA movimentoIA;

    /**
     * constructor de la clase
     */
    public IA(){
        ejecutar();
    }

    /**
     * Ejecuta la inteligencia artificial. comprueba todos los jugadores del equipo controlado por la IA
     * y compreueba que accion es idonea en la situacion en la que se encuentra actualmente y la realiza.
     * Puede realizar pase, chute, usar objeto o moverse.
     */
    public void ejecutar(){

        for (Jugador jugador: ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

            if (usoObjetos.usarObjeto(jugador)==false){
                System.out.println("no usa objeto");
                if (paseChuteIA.hacerPaseChute(jugador)==false){
                    System.out.println("no usa pase chute");
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

                    simulador.añadirAccion(new Movimiento(jugador,movimentoIA.arraymovimento(jugador), null));
                }
            }
        }
    }
}

