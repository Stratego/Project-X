package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Jugador.extras.IndicadorMovimientos;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;


/**
 * Created by Victor on 27/03/14.
 * Gestiona el estado EnMovimiento de un jugador,
 * el cual va rellenando una lista de posiciones
 * para posteriormente crear la accion Movimiento
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
     * @param jugador Jugador que posee el estado
     * @param posX Posición X del jugador
     * @param posY Posición Y del jugador
     * @return false
     */
    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        Simulador simulador = Simulador.getInstance();

        simulador.añadirAccion(jugador.getAccion());

        return false;
    }

    /**
     * Genera la acción de movimiento.
     * Una accion de movimiento transcurre a lo largo del tiempo.
     * Debe recibir un conjunto de acciones de entrada de arrastre a lo largo
     * de diferenets casillas finalizado con un longclic en la ultima casilla
     * Una vez finalizado el jugador pasara a estado bloqueado
     *
     * @param jugador Jugador que posee el estado
     * @param posX Posición X de la siguiente casilla a mover
     * @param posY Posición y de la siguiente casilla a mover
     * @param entrada Tipo de evento que le llega a un jugador
     * @return Boolean acción generada
     */
    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {

        this.jugador = jugador;

        //Se permiten solo acciones de arrastre
        if(entrada == Entrada.arrastrar)
        {
            System.out.println("ENMOVIMIENTO:"+posX+"-"+posY);
            //System.out.println("MOVIMIENTOOOOOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            // se van guardando los movimientos consecutivamente mientras esten dentro del tablero
            // y no se hayan hecho mas movimientos
            if(((jugador.getPosicionX() == posX) && (jugador.getPosicionY() == posY)) && (this.posicionActual == 0))  {
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

        if(this.posicionActual == movimientos.length || this.jugadorFinalizaMovimiento(entrada,posX,posY))  {

            int movimientosAux[][] = new int[this.posicionActual][2];

            for(int i=0; i<this.posicionActual; i++)
            {
                ComponentesJuego.getComponentes().getCampo().desSeleccionarCasilla(movimientos[i][0],movimientos[i][1]);
                movimientosAux[i][0] = movimientos[i][0];
                movimientosAux[i][1] = movimientos[i][1];
            }


            this.indicador = new IndicadorMovimientos(jugador,movimientosAux,this.posicionActual);

            jugador.setAccion(new Movimiento(jugador, movimientosAux, this.indicador));

            Simulador.getInstance().añadirAccion(jugador.getAccion());

            /*Este metodo ira haciendo que el jugador se vaya cansando*/
            this.jugador.cansancio();

            jugador.setBloqueado(true);
            jugador.setSeleccionado(false);


            this.indicador.procesar();

            /*Le devolvemos su estado anterior*/
            this.jugador.setEstado(this.estadoAnterior);

            //System.out.println("ESTADOOOOOOO:"+this.jugador.getEstado());

            return true;
        }
        return false;
    }


    /**
     * Condicion de finalización de movimiento de un jugador
     * @param entrada Tipo de evento que le llega a un jugador
     * @param posicionX Posición X de la casilla a mover
     * @param posicionY Posición Y de la casilla a mover
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
    private void eliminarRecorrido() {
        for(int i=0; i<movimientos.length; i++) {
            ComponentesJuego.getComponentes().getCampo().desSeleccionarCasilla(movimientos[i][0],movimientos[i][1]);
        }
    }


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


    @Override
    public Estado getEstado() {
        this.eliminarRecorrido();
        return this.estadoAnterior;
    }


    @Override
    public Estado getEstadoAnterior() {
        this.eliminarRecorrido();
        return this.estadoAnterior;
    }


    @Override
    public void setJugador(Jugador jugador) {

    }
}
