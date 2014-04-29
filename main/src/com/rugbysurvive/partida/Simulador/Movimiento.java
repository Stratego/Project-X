package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
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
        this.contador = 1;
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
                        /*Si uno de los dos tiene la pelota, se pelearan por ella
                        * En el primera caso, el propietaro de la pelota es el que esta haciendo la acción de movimiento
                        * En el segundo caso, el propietario de la pelota es el jugador que esta en la casilla por la que el jugador de la accion movimiento va a pasar
                        */
                        if(jugador.getEstado() instanceof ConPelota)
                        {
                            int Fuerza = jugador.getFuerza();
                            int Defensa = Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getDefensa();

                            if(luchaPelota(Fuerza, Defensa))
                            {
                                jugador.setEstado(new SinPelota());
                                Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().setEstado(new ConPelota());
                                System.out.println("Me quitan la pelota-----------------<");
                            }
                        }
                        else
                        {
                            if(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getEstado() instanceof ConPelota)
                            {
                                int Fuerza = Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getFuerza();
                                int Defensa = jugador.getDefensa();

                                if(luchaPelota(Fuerza, Defensa))
                                {
                                    jugador.setEstado(new ConPelota());
                                    Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().setEstado(new SinPelota());
                                    System.out.println("Quito la pelota------------->");
                                }
                            }
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

    /*Siempre consideramos como atacado aquel que tiene la pelota*/
    public boolean luchaPelota(int fuerzaAtacante, int defensaAtacado)
    {
        int ganaAtacante =0;
        int ganaDefensor =0;

        for(int i=0; i<3; i++)
        {
            int probAdquirir = new Double(Math.random() * (fuerzaAtacante + defensaAtacado)).intValue();
            System.out.println("Numero aleatorio generado: "+probAdquirir);

            /*Si la variable probAdquirir es menor que la fuerza de un jugador, el atacante se queda la pelota, en caso contrario gana el defensor*/
            if(probAdquirir < fuerzaAtacante)
            {
                ganaAtacante += 1;
            }
            else
            {
                ganaDefensor += 1;
            }
        }


        if(ganaAtacante >= ganaDefensor)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Override
    public void simularAnimacion() {

    }
}
