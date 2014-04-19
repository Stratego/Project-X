package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 27/03/14.
 */
public class Movimiento extends Accion {
    private int camino[][];
    private Jugador jugador;
    private int contador  = 0;//test

    public Movimiento(Jugador jugador,int camino[][])
    {
        this.camino = camino;
        this.jugador = jugador;
        this.contador = 0;
    }

    @Override
    public boolean simular() {
        System.out.println("mover");

        boolean incrementa = true;


       /* if(jugador.getEstado().getEstadoAnterior() instanceof ConPelota)
        {*/

            /*Comprobaciones si esta con pelota el jugador*/


            if(contador <= this.camino.length)
            {
                /*Comprobamos si en la siguiente casilla hay un jugador ya que si no no se podra llamar a la funcion getMiEquipo*/
                if(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1], this.camino[contador][0]).getJugador() != null)
                {
                    /*Comprobamos si pertenecen al mismo equipo*/
                    if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getMiEquipo())
                    {
                        /*Si uno de los dos tiene la pelota, se pelearan por ella*/
                        if((jugador.getEstado() instanceof ConPelota) || (Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getEstado() instanceof ConPelota))
                        {
                            /*------------------>Aqui se debera añadir la pelea por la pelota<----------------*/
                            System.out.println("Nos damos de ostias por la pelota");
                        }

                        incrementa = false;
                    }
                    /*Si son del mismo equipo, comprobamos que al pasarle por encima, que no se quedara superpuesto, es decir, que seguira avanzando*/
                    else
                    {
                        incrementa = false;
                        /*Verificamos que es posible que el jugador pueda seguir avanzando sin problemas*/
                        for(int i=contador; i<this.camino.length; i++)
                        {
                            if(Campo.getInstanciaCampo().getCasilla(this.camino[i][1],this.camino[i][0]).getJugador() == null)
                            {
                                incrementa = true;
                                i = this.camino.length-1;
                            }
                            else
                            {
                                if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(this.camino[i][1],this.camino[i][0]).getJugador().getMiEquipo())
                                {
                                    i = this.camino.length-1;
                                }
                            }
                        }
                    }
                }
                else
                {
                    if(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1], this.camino[contador][0]).getObjeto() != null)
                    {
                        Campo.getInstanciaCampo().getCasilla(this.camino[contador][1], this.camino[contador][0]).getObjeto().efecto(this.jugador);
                    }
                }

            }
       /* }
        else
        {
            if(jugador.getEstado().getEstadoAnterior() instanceof SinPelota)
            {*/
                /*Comprobaciones si esta sin pelota el jugador*/
           // }
       // }


        if(incrementa == true)
        {
            /*Referenciamos jugador y casillas en ambos sentidos*/
            this.jugador.colocar(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]));
            Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).setJugador(this.jugador);

            /*Quitamos la referencia de la posicion anterior del jugador en la casilla*/
            if(contador > 0)
            {
                Campo.getInstanciaCampo().getCasilla(this.camino[contador-1][1],this.camino[contador-1][0]).setJugador(null);
            }

            contador = contador + 1;
        }
        else
        {
            contador = this.camino.length;
        }

        //System.out.println(this.camino[contador][1]+"-"+this.camino[contador][0]);

        if(contador == this.camino.length)return true;//test
        return false;
    }


    @Override
    public void simularAnimacion() {

    }
}
