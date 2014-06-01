package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Casilla;
import com.rugbysurvive.partida.tablero.Lado;

/**Clase principal de la inteligencia artificial.
 * Comprobara todos los jugadores del equipo controlado por la IA
 * y a traves de las reglas establecidas realizara la accion que mas se adecuen a la situacion,
 * ya sea realiazar pases, chutes, usar objetos o moverse.
 * Created by Victor on 12/05/14.
 */
public class IA {

    /**
     * instanciacion de clase objetos ia
     * que nos permitira usar un power-up
     * o un objeto campo
     */
    private ObjetosIA usoObjetos = new ObjetosIA();

    /**
     * instanciacion de clase pasechute ia
     * que nos permitira realiar un chute o un pase
     */
    private PaseChuteIA paseChuteIA = new PaseChuteIA();

    /**
     * instanciacion de clase movimiento ia
     * que nos permitira hayar el camino
     */
    private MovimentoIA movimentoIA;

    /**
     * constructor de la clase que ademas ejecuta la inteligencia artificial
     */
    public IA(){
        ejecutar();
    }

    /**
     * Ejecuta la inteligencia artificial. comprueba todos los jugadores del equipo controlado por la IA
     * y compreueba que accion es idonea en la situacion en la que se encuentra actualmente y la realiza.
     * Puede realizar en este orden  usar objeto pase, chute, o moverse. solo ejecuta una de las opciones
     * por descarte
     */
    public void ejecutar(){

        Casilla casillaPelota = ComponentesJuego.getComponentes().getCampo().posicionPelota();
        int rangoInicialPelota =0;
        int rangoFinalPelota = 0;
        if (casillaPelota.getJugador()!= null && casillaPelota.getJugador().getMiEquipo()==
                ComponentesJuego.getComponentes().getEquipo2()){
            rangoInicialPelota= casillaPelota.getPosicionX()-5;
            rangoFinalPelota= casillaPelota.getPosicionX()+5;
        }

        for (Jugador jugador: ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){


            if (usoObjetos.usarObjeto(jugador)==false){
                System.out.println("no usa objeto");
                if (paseChuteIA.hacerPaseChute(jugador)==false){
                    System.out.println("no usa pase chute");
                    if (casillaPelota.getJugador()!= null && casillaPelota.getJugador().miEquipo==
                            ComponentesJuego.getComponentes().getEquipo2()){
                        if (jugador.getMiEquipo().getLado()== Lado.izquierda){

                            if (jugador.getPosicionX()> rangoInicialPelota && jugador.getPosicionX()<rangoFinalPelota){

                                int correccion = 1;
                                boolean colocado = false;
                                while (colocado==false){
                                    if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(), 28).getJugador()==null){
                                        movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                                ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(), 28));
                                        colocado = true;
                                    }else if (jugador.getPosicionY()>10){
                                        movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                                ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()-correccion, 28));
                                        colocado = true;
                                    }else if (jugador.getPosicionY()<10){
                                        movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                                ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()+correccion, 28));
                                        colocado = true;
                                    }
                                    if (correccion<9){
                                        correccion+=1;
                                    }else {
                                        correccion=1;
                                    }
                                }


                            }else{
                                System.out.println("jugador va hacia pelota");
                                movimentoIA= new MovimentoIA(jugador.getCasilla(), casillaPelota);
                            }
                        }else{


                            if (jugador.getPosicionX()> rangoInicialPelota && jugador.getPosicionX()<rangoFinalPelota){

                                int correccion = 1;
                                boolean colocado = false;
                                while (colocado==false){
                                if (ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(), 1).getJugador()==null){
                                        movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                        ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY(), 1));
                                        colocado = true;
                                }else if (jugador.getPosicionY()>10){
                                    movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                            ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()-correccion, 1));
                                        colocado = true;
                                }else if (jugador.getPosicionY()<10){
                                    movimentoIA= new MovimentoIA(jugador.getCasilla(),
                                            ComponentesJuego.getComponentes().getCampo().getCasilla(jugador.getPosicionY()+correccion, 1));
                                        colocado = true;
                                }
                                    if (correccion<9){
                                        correccion+=1;
                                    } else {
                                        correccion=1;
                                    }
                                }
                            }else{
                                System.out.println("jugador va hacia pelota");
                                movimentoIA= new MovimentoIA(jugador.getCasilla(), casillaPelota);
                            }

                        }

                    }else{
                        movimentoIA= new MovimentoIA(jugador.getCasilla(), casillaPelota);
                    }

                    Simulador simulador = Simulador.getInstance();

                    //movimentoIA.imprimirLista(movimentoIA.calcularCamino());
                    if (movimentoIA.arraymovimento(jugador)!=null){
                        simulador.añadirAccion(new Movimiento(jugador,movimentoIA.arraymovimento(jugador), null));
                    }
                }
            }
        }
    }
}

