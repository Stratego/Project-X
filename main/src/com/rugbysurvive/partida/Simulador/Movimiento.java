package com.rugbysurvive.partida.Simulador;

import com.badlogic.gdx.Gdx;
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
        this.indicadorMovimientos = indicadorMovimientos;
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

            ProcesosContinuos.añadirProceso(this);

            if(choque.arbitrar()) {
               Simulador.getInstance().eliminarAccionsSimulador();

            }

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

    @Override
    public boolean procesar() {
        if(!this.animacionInicializada) {
            this.posicionPuñoDerecha = POSICION_INICIAL_DERECHA;
            this.posicionPuñoIzquierda = POSICION_INICIAL_IZQUIERDA;
            this.posicionPuñoY = (ConstantesJuego.getWidth()-ConstantesJuego.TAMAÑO_PUÑO)/2;
            this.puñoDerecha.dibujar(this.posicionPuñoDerecha,this.posicionPuñoY);
            this.puñoDerecha.dibujar(this.posicionPuñoIzquierda,this.posicionPuñoY);
            this.animacionInicializada = true;
        }

        else{

            this.puñoDerecha.borrar();
            this.puñoIzquierda.borrar();
            this.puñoDerecha.dibujar(this.posicionPuñoDerecha,this.posicionPuñoY);
            this.puñoIzquierda.dibujar(this.posicionPuñoIzquierda,this.posicionPuñoY);

            this.posicionPuñoIzquierda = this.posicionPuñoIzquierda + VELOCIDAD;
            this.posicionPuñoDerecha= this.posicionPuñoDerecha-VELOCIDAD;

            if(this.posicionPuñoDerecha <= POSICION_FINAL_DERECHA && this.posicionPuñoIzquierda >= POSICION_FINAL_IZQUIERDA){

                this.puñoDerecha.borrar();
                this.puñoIzquierda.borrar();
                return true;
            }

        }


        return false;
    }
}
