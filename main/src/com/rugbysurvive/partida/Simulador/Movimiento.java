package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.Jugador.extras.IndicadorMovimientos;
import com.rugbysurvive.partida.arbitro.Choque;
import com.rugbysurvive.partida.arbitro.SaqueBanda;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.Marcador;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Victor on 27/03/14.
 */
public class Movimiento extends Accion {
    private int camino[][];
    private Jugador jugador;
    private int contador  = 0;//test
    private IndicadorMovimientos indicadorMovimientos;

    /**
     * Constructor de la acción movimiento
     * @param jugador
     * @param camino
     */
    public Movimiento(Jugador jugador,int camino[][], IndicadorMovimientos indicadorMovimientos)
    {
        this.camino = camino;
        this.jugador = jugador;
        this.contador = 1;
        this.indicadorMovimientos = indicadorMovimientos;
    }


    /**
     * Verifica si un jugador con pelota sale fuera del campo, y si es asi llama a la función arbitrar de SaqueBanda
     * @param contador posición actual del vector
     * @param camino Multidimensional Array del camino a seguir por parte del jugador
     * @return Boolean Respuesta del arbitro
     */
    private Boolean SaleDeBanda(int contador, int camino[][])
    {

            /*Si un jugador se sale del campo se llama a la funcion arbitrar de saquebanda*/
       if((this.camino[contador][1] > 18 || this.camino[contador][1] < 1) && (this.jugador.getEstado() instanceof ConPelota))
        {
            Equipo equipoRival = ComponentesJuego.getComponentes().getEquipo1();
            if(this.jugador.getMiEquipo() == ComponentesJuego.getComponentes().getEquipo1())
            {
                equipoRival = ComponentesJuego.getComponentes().getEquipo2();
            }

            SaqueBanda saquebanda = new SaqueBanda(this.camino[contador][1],this.camino[contador][0],equipoRival);
            return saquebanda.arbitrar();
        }

        return false;
    }

    /**
     * Verifica si dos jugadores colisionan, y si es así, se llama a la función arbitrar de la clase choque
     * @return Booelan Respuesta del arbitro
     */
    private Boolean ChoqueJugadores()
    {
        /*Verificamos que los dos jugadores no tienen la pelota, y si es así, se arbitra*/
        if((this.jugador.getEstado() instanceof SinPelota) && (Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getEstado() instanceof SinPelota))
        {
            Choque choque = new Choque(this.jugador, Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador());
            return choque.arbitrar();
        }

        return false;
    }

    /**
     * Sumulación de la acción movimiento por parte de un jugador
     * @return Boolean Condición que indica si la simulación ha llegado o no al final
     * */
     @Override
    public boolean simular() {
        System.out.println("mover");

        boolean incrementa = true;

        if(contador <= this.camino.length)
        {
            /*Verificamos si sale de banda*/
            if(this.SaleDeBanda(contador,this.camino) == true)
            {
                return true;
            }

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

                    /*AQUI SE HACE OTRO CHOQUE*/
                    if(jugador.getEstado() instanceof ConPelota)
                    {
                        int Fuerza = jugador.getFuerza();
                        int Defensa = Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().getDefensa();

                        if(luchaPelota(Fuerza, Defensa))
                        {
                            jugador.setEstado(new SinPelota());
                            jugador.lesionar();
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
                                Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).getJugador().lesionar();
                                System.out.println("Quito la pelota------------->");
                            }
                        }
                        else
                        {
                            /*Llamamos a la función choque de jugadores, para ver si hay dos jugadores que colisionan entre ellos*/
                            if(this.ChoqueJugadores() == true)
                            {
                                //AQUI SE HACE UN CHOQUE
                                return true;
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


        if(incrementa == true)
        {


        /*Giramos la textura del jugador segun convenga*/
            if((this.camino[contador][0] > this.camino[contador-1][0]) && (this.camino[contador][1] == this.camino[contador-1][1]))
            {
                jugador.setDireccion(DireccionJugador.derecha);
            }
            else
            {
                if((this.camino[contador][0] < this.camino[contador-1][0]) && (this.camino[contador][1] == this.camino[contador-1][1]))
                {
                    jugador.setDireccion(DireccionJugador.izquierda);
                }
                else
                {
                    if((this.camino[contador][0] == this.camino[contador-1][0]) && (this.camino[contador][1] > this.camino[contador-1][1]))
                    {
                        jugador.setDireccion(DireccionJugador.arriba);
                    }
                    else
                    {
                        if((this.camino[contador][0] == this.camino[contador-1][0]) && (this.camino[contador][1] < this.camino[contador-1][1]))
                        {
                            jugador.setDireccion(DireccionJugador.abajo);
                        }
                    }
                }
            }

            /*Referenciamos jugador y casillas en ambos sentidos*/
            this.jugador.colocar(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]));
            //eliminar de alguna forma la textura de la casilla anterior


            Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).setJugador(this.jugador);
            GestorGrafico.getCamara().variarPosicion(this.camino[contador][0]*64,this.camino[contador][1]*64);


            /*Quitamos la referencia de la posicion anterior del jugador en la casilla*/
            if(contador > 0)
            {
                Campo.getInstanciaCampo().getCasilla(this.camino[contador-1][1],this.camino[contador-1][0]).setJugador(null);
                if(contador < this.camino.length)
                {
                    /*Solo un jugador con pelota puede marcar*/
                    if(jugador.getEstado() instanceof ConPelota)
                    {
                        if(this.jugador.getMiEquipo().getLado() == Lado.izquierda)
                        {
                            if(this.camino[contador][0] >= 28)
                            {
                                Marcador.getInstanceMarcador().sumarPuntuacion(7, jugador);
                                return true;
                            }
                        }
                        else
                        {
                            if(this.camino[contador][0] <= 1)
                            {
                                Marcador.getInstanceMarcador().sumarPuntuacion(7, jugador);
                                return true;
                            }
                        }
                    }
                }
            }

            contador = contador + 1;
        }
        else
        {
            contador = this.camino.length;
        }

        //System.out.println(this.camino[contador][1]+"-"+this.camino[contador][0]);

        if(contador == this.camino.length)
        {
            if(this.indicadorMovimientos != null)
            {
                this.indicadorMovimientos.borrar();
            }

            return true;//test
        }
        return false;
    }

    /**
     * Permite decidir que jugador ganara o mantendrá la posesión del valón
     * @param fuerzaAtacante Jugador que ataca
     * @param defensaAtacado Jugador que defiende
     * @return Boolean Condición del jugador que poseerá la pelota
     * */
    public boolean luchaPelota(int fuerzaAtacante, int defensaAtacado)
    {
        /*Siempre consideramos como atacado aquel que tiene la pelota*/
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
