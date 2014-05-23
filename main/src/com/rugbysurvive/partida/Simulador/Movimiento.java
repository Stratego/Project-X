package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
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
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Victor on 27/03/14.
 */
public class Movimiento extends Accion implements Proceso {
    private static final int POSICION_INICIAL_IZQUIERDA = ConstantesJuego.getHeight()/2 - 2*ConstantesJuego.TAMAÑO_PUÑO;
    private static final int POSICION_INICIAL_DERECHA =ConstantesJuego.getHeight()/2 + ConstantesJuego.TAMAÑO_PUÑO;
    private static final int POSICION_FINAL_IZQUIERDA = ConstantesJuego.getHeight()/2 -1 -ConstantesJuego.TAMAÑO_PUÑO;
    private static final int POSICION_FINAL_DERECHA = ConstantesJuego.getHeight()/2 + 1;
    private static final int VELOCIDAD = (int)(10 * ConstantesJuego.constanteRescalado);

    private int posicionPuñoIzquierda;
    private int posicionPuñoDerecha;
    private int posicionPuñoY;


    private ElementoDibujable puñoIzquierda;
    private ElementoDibujable puñoDerecha;

    private int camino[][];
    private Jugador jugador;
    private int contador  = 0;//test
    private IndicadorMovimientos indicadorMovimientos;

    private boolean animacionInicializada;
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
        this.animacionInicializada = false;
        this.puñoIzquierda = new ElementoDibujable(TipoDibujo.interficieUsuario,"simulacion/izquierda.png");
        this.puñoDerecha = new ElementoDibujable(TipoDibujo.interficieUsuario,"simulacion/derecha.png");
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
       if (contador<=camino.length){
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
            choque.arbitrar();
            return true;
        }

        return false;
    }

    /**
     * Sumulación de la acción movimiento por parte de un jugador
     * @return Boolean Condición que indica si la simulación ha llegado o no al final
     * */
     @Override
    public boolean simular() {

        boolean haceEfectoObjeto = false;
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

                    /*Si en esta función hay un choque entre jugadores y lo ve el arbitro, entonces devolvera true*/
                    if(encuentroEntreJugadores() == true)
                    {
                        return true;
                    }

                    incrementa = false;
                }
                /*Si son del mismo equipo, comprobamos que al pasarle por encima, que no se quedara superpuesto, es decir, que seguira avanzando*/
                else
                {
                    incrementa = false;
                    if(puedeAvanzar() == true)
                    {
                        incrementa = true;
                    }
                }
            }
            else
            {
                /*Verifica si hay un objeto y se lo aplica al jugador*/
                if(efectoObjeto()){
                    haceEfectoObjeto = true;
                }
            }

        }

        if(incrementa == true)
        {
            /*Giramos la textura del jugador segun convenga*/
             posicionarTexturaJugador();

            /*Referenciamos jugador y casillas en ambos sentidos*/
            this.jugador.colocar(Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]));



            /*Si pasa por encima de una casilla con pelota, entonces la recoge*/
            recogerPelota();


            Campo.getInstanciaCampo().getCasilla(this.camino[contador][1],this.camino[contador][0]).setJugador(this.jugador);
            GestorGrafico.getCamara().variarPosicion(this.camino[contador][0]*64,this.camino[contador][1]*64);



            /*Quitamos la referencia de la posicion anterior del jugador en la casilla*/
            if(contador > 0)
            {
                Campo.getInstanciaCampo().getCasilla(this.camino[contador-1][1],this.camino[contador-1][0]).setJugador(null);

                if(haceEfectoObjeto)
                {
                    this.jugador.setBloqueado(true);
                    return true;
                }
                
                if(marcarPunto()== true)
                {
                    Campo.getInstanciaCampo().recolocarJugadoresDespuesDelPunto(this.jugador);

                    if(this.indicadorMovimientos != null)
                    {
                        this.indicadorMovimientos.borrar();
                    }

                    return true;
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
     * Recoge la pelota del campo
     */
    public void recogerPelota()
    {
        if(this.jugador.getEstado() instanceof SinPelota)
        {


            if(Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).hayPelota())
            {
                this.jugador.setEstado(new ConPelota(this.jugador));
                Campo.getInstanciaCampo().quitarPelota(this.camino[this.contador][1], this.camino[this.contador][0]);
            }
        }
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

    /**
     * Verifica si se le debe aplicar un efecto de un objeto a un jugador
     */
    public boolean efectoObjeto()
    {
        if(Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1], this.camino[this.contador][0]).getObjeto() != null)
        {
            Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1], this.camino[this.contador][0]).getObjeto().efecto(this.jugador);
            return true;
        }
        return false;
    }

    /**
     * Verifica si un jugador marca un punto
     * @return Boolean Ha marcado un punto
     */
    public boolean marcarPunto()
    {
        if(this.contador < this.camino.length)
        {
                    /*Solo un jugador con pelota puede marcar*/
            if(this.jugador.getEstado() instanceof ConPelota)
            {
                if(this.jugador.getMiEquipo().getLado() == Lado.izquierda)
                {
                    if(this.camino[this.contador][0] >= 28)
                    {
                        Marcador.getInstanceMarcador().sumarPuntuacion(5, this.jugador);
                        return true;
                    }
                }
                else
                {
                    if(this.camino[this.contador][0] <= 1)
                    {
                        Marcador.getInstanceMarcador().sumarPuntuacion(5, this.jugador);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Posiciona las texturas acorde al movimiento que realiza el jugador
     */
    public void posicionarTexturaJugador()
    {
        if((this.camino[this.contador][0] > this.camino[this.contador-1][0]) && (this.camino[this.contador][1] == this.camino[this.contador-1][1]))
        {
            this.jugador.setDireccion(DireccionJugador.derecha);
        }
        else
        {
            if((this.camino[this.contador][0] < this.camino[this.contador-1][0]) && (this.camino[this.contador][1] == this.camino[this.contador-1][1]))
            {
                this.jugador.setDireccion(DireccionJugador.izquierda);
            }
            else
            {
                if((this.camino[this.contador][0] == this.camino[this.contador-1][0]) && (this.camino[this.contador][1] > this.camino[this.contador-1][1]))
                {
                    this.jugador.setDireccion(DireccionJugador.arriba);
                }
                else
                {
                    if((this.camino[this.contador][0] == this.camino[this.contador-1][0]) && (this.camino[this.contador][1] < this.camino[this.contador-1][1]))
                    {
                        this.jugador.setDireccion(DireccionJugador.abajo);
                    }
                }
            }
        }
    }

    /**
     * Verifica si dos jugadores se encuentran, y aplica las acciones pertinentes segun en el estado que se encuentren los jugadores
     * @return
     */
    public boolean encuentroEntreJugadores()
    {
        /*AQUI SE HACE OTRO CHOQUE*/
        if(this.jugador.getEstado() instanceof ConPelota)
        {
            int Fuerza = this.jugador.getFuerza();
            int Defensa = Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).getJugador().getDefensa();

            if(luchaPelota(Fuerza, Defensa))
            {
                this.jugador.setEstado(new SinPelota());
                this.jugador.lesionar(10);
                Jugador jugador = Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).getJugador();
                jugador.setEstado(new ConPelota(jugador));
                System.out.println("Me quitan la pelota-----------------<");
            }
        }
        else
        {
            if(Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).getJugador().getEstado() instanceof ConPelota)
            {
                int Fuerza = Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).getJugador().getFuerza();
                int Defensa = this.jugador.getDefensa();

                if(luchaPelota(Fuerza, Defensa))
                {
                    this.jugador.setEstado(new ConPelota(this.jugador));
                    Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).getJugador().setEstado(new SinPelota());
                    Campo.getInstanciaCampo().getCasilla(this.camino[this.contador][1],this.camino[this.contador][0]).getJugador().lesionar(10);
                    System.out.println("Quito la pelota------------->");
                }
            }
            else
            {
                            /*Llamamos a la función choque de jugadores, para ver si hay dos jugadores que colisionan entre ellos*/
                if(this.ChoqueJugadores() == true)
                {
                    ProcesosContinuos.añadirProceso(this);
                    Simulador.getInstance().setParado(true);
                    //AQUI SE HACE UN CHOQUE
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Comprueba si un jugador puede ir realizando el movimiento de forma correcta
     * @return Boolean Puede mover
     */
    public boolean puedeAvanzar()
    {
        /*Verificamos que es posible que el jugador pueda seguir avanzando sin problemas*/
        for(int i=contador; i<this.camino.length; i++)
        {
            if(Campo.getInstanciaCampo().getCasilla(this.camino[i][1],this.camino[i][0]).getJugador() == null)
            {
                return true;
            }
            else
            {
                if(jugador.getMiEquipo() != Campo.getInstanciaCampo().getCasilla(this.camino[i][1],this.camino[i][0]).getJugador().getMiEquipo())
                {
                    return false;
                }
            }
        }
        return false;
    }
    @Override
    public void simularAnimacion() {

    }

    @Override
    public boolean procesar() {
        if(!animacionParada && !this.animacionInicializada) {
                this.posicionPuñoDerecha = POSICION_INICIAL_DERECHA;
                this.posicionPuñoIzquierda = POSICION_INICIAL_IZQUIERDA;
                this.posicionPuñoY = (ConstantesJuego.getWidth()-ConstantesJuego.TAMAÑO_PUÑO)/2;
                this.puñoDerecha.dibujar(this.posicionPuñoDerecha,this.posicionPuñoY);
                this.puñoDerecha.dibujar(this.posicionPuñoIzquierda,this.posicionPuñoY);
                this.animacionInicializada = true;
            }

       else if(this.animacionInicializada){

                this.puñoDerecha.borrar();
                this.puñoIzquierda.borrar();
                this.puñoDerecha.dibujar(this.posicionPuñoDerecha,this.posicionPuñoY);
                this.puñoIzquierda.dibujar(this.posicionPuñoIzquierda,this.posicionPuñoY);

                this.posicionPuñoIzquierda = this.posicionPuñoIzquierda + VELOCIDAD;
                this.posicionPuñoDerecha= this.posicionPuñoDerecha-VELOCIDAD;

                if((this.posicionPuñoDerecha <= POSICION_FINAL_DERECHA && this.posicionPuñoIzquierda >= POSICION_FINAL_IZQUIERDA)
                        || this.animacionParada) {

                    this.puñoDerecha.borrar();
                    this.puñoIzquierda.borrar();
                    Simulador.getInstance().setParado(false);
                    return true;
                }

        }
        else{return true;}
        return false;
    }
}
