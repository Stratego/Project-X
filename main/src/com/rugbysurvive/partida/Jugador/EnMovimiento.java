package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Jugador.extras.IndicadorMovimientos;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;


/**
 * Created by Victor on 27/03/14.
 */
public class EnMovimiento implements Estado {

    /*Lista de los movimientos que hara un jugador*/
    int movimientos[][];
    int posicionActual;
    Estado estadoAnterior;
    IndicadorMovimientos indicador;

    public Jugador jugador;

    /**
     * Constructor de estado EnMovimiento
     * @param nPosiciones
     * @param estadoAnterior
     */
    public EnMovimiento(int nPosiciones,Estado estadoAnterior)
    {
        /*De momento declaramos una matriz de 8*2, hasta que logremos obtener cuanto podra mover cada jugador*/

        this.movimientos = new int[nPosiciones][2];
        this.posicionActual = 0;
        this.estadoAnterior = estadoAnterior;
        GestorGrafico.getCamara().bloquear();

    }


    /**
     * Genera la acción
     * @param jugador
     * @return false
     */
    public boolean generarAccion(Jugador jugador) {
        Simulador simulador = Simulador.getInstance();

        simulador.añadirAccion(jugador.getAccion());

        return false;
    }

    /**
     * Genera la acción
     * @param jugador
     * @param posX
     * @param posY
     * @return false
     */
    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        Simulador simulador = Simulador.getInstance();

        simulador.añadirAccion(jugador.getAccion());

        return false;
    }

    /**
     * Genera la acción
     * @param jugador
     * @param posX
     * @param posY
     * @param entrada
     * @return Boolean acción generada
     */
    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {

        this.jugador = jugador;

        if(entrada == Entrada.arrastrar)
        {
            System.out.println("ENMOVIMIENTO:"+posX+"-"+posY);
            //System.out.println("MOVIMIENTOOOOOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            if(((jugador.getPosicionX() == posX) && (jugador.getPosicionY() == posY)) && (this.posicionActual == 0))
            {
                this.movimientos[this.posicionActual][0] = posX;
                this.movimientos[this.posicionActual][1] = posY;
                this.posicionActual += 1;
                System.out.println("--------------Me guardo el primer movimiento--------------");
            }
            else
            {
                if(this.posicionActual > 0)
                {
                    if((posY < 20 && posY >= 0) && (posX<30 && posX>=0))
                    {
                        int posXAnterior = this.movimientos[this.posicionActual-1][0];
                        int posYAnterior = this.movimientos[this.posicionActual-1][1];

                        if(((posXAnterior == posX-1 || posXAnterior == posX+1)  ||  (posYAnterior == posY-1 || posYAnterior == posY+1))
                                && (posY == posYAnterior || posX == posXAnterior))
                        {
                            this.movimientos[this.posicionActual][0] = posX;
                            this.movimientos[this.posicionActual][1] = posY;
                            System.out.println("--------------Me guardo el movimiento--------------" + this.posicionActual);
                            System.out.println("--------------Me guardo el movimiento-------------- :" + posX+";"+posY);
                            ComponentesJuego.getComponentes().getCampo().seleccionarCasilla(posX,posY);
                            this.posicionActual += 1;
                        }
                    }
                }


            }

        }
         System.out.println("POSICION ACTUAL:"+this.posicionActual);

        if(this.posicionActual == movimientos.length || this.jugadorFinalizaMovimiento(entrada,posX,posY))
        {

            for(int i=0; i<movimientos.length; i++)
            {
                ComponentesJuego.getComponentes().getCampo().desSeleccionarCasilla(movimientos[i][0],movimientos[i][1]);
            }


            jugador.setAccion(new Movimiento(jugador, movimientos));

            Simulador.getInstance().añadirAccion(jugador.getAccion());

            jugador.setBloqueado(true);
            jugador.setSeleccionado(false);
            this.indicador = new IndicadorMovimientos(jugador,this.movimientos,this.posicionActual);
            this.indicador.procesar();

            /*Le devolvemos su estado anterior*/
            this.jugador.setEstado(this.estadoAnterior);

            //System.out.println("ESTADOOOOOOO:"+this.jugador.getEstado());

            return true;
        }
        return false;
    }


    /**
     * Finalización de movimiento de un jugador
     * @param entrada
     * @param posicionX
     * @param posicionY
     * @return
     */
    private boolean jugadorFinalizaMovimiento(Entrada entrada ,int posicionX,int posicionY)
    {

        if(this.posicionActual >0)
        {
             if(this.movimientos[this.posicionActual-1][0] == posicionX
                    && this.movimientos[this.posicionActual-1][1] == posicionY
                    && entrada == Entrada.clicklargo)
             {
                 System.out.println("ok");
                 return true;
             }
        }
        return false;
    }

    /**
     * Elimina el recorrido hecho por un jugador
     */
    private void eliminarRecorrido()
    {
        for(int i=0; i<movimientos.length; i++)
        {
            ComponentesJuego.getComponentes().getCampo().desSeleccionarCasilla(movimientos[i][0],movimientos[i][1]);
        }
    }

    /**
     * Obtenemos un jugador
     * @return
     */
    @Override
    public Jugador getJugador() {
        return this.jugador;
    }

    @Override
    public boolean getSeleccionado() {
        return false;
    }

    @Override
    public void setSeleccionado(boolean seleccionado) {

    }

    @Override
    public boolean getBloqueado() {
        return false;
    }

    @Override
    public void setBloqueado(boolean bloqueado) {

    }


    @Override
    public boolean getPaseOChute() {
        return false;
    }

    @Override
    public void setPaseOChute(boolean paseOChute) {

    }

    /**
     * Obtenemos estado
     * @return Estado
     */
    @Override
    public Estado getEstado() {
        this.eliminarRecorrido();
        return this.estadoAnterior;
    }

    /**
     * Obtenemos estadoAnterior
     * @return Estado
     */
    @Override
    public Estado getEstadoAnterior() {
        this.eliminarRecorrido();
        return this.estadoAnterior;
    }


    @Override
    public void setJugador(Jugador jugador) {

    }
}
