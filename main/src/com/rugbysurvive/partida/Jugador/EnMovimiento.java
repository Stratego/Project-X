package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 */
public class EnMovimiento implements Estado {

    /*Lista de los movimientos que hara un jugador*/
    int movimientos[][];
    int posicionActual;


    public EnMovimiento(int nPosiciones)
    {
        /*De momento declaramos una matriz de 8*2, hasta que logremos obtener cuanto podra mover cada jugador*/
        this.movimientos = new int[nPosiciones][2];
        this.posicionActual = 0;
    }

    public boolean generarAccion(Jugador jugador) {
        Simulador simulador = Simulador.getInstance();

        simulador.addAccionesSimulador(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {
        Simulador simulador = Simulador.getInstance();

        simulador.addAccionesSimulador(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {


        System.out.println("MOVIMIENTOOOOOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
                if(posY <= 16 && posY >= 0 && posX<=28 && posX>=0)
                {
                    int posXAnterior = this.movimientos[this.posicionActual-1][0];
                    int posYAnterior = this.movimientos[this.posicionActual-1][1];
                    if(((posXAnterior == posX-1 || posXAnterior == posX+1) && (posYAnterior == posY)) || ((posYAnterior == posY-1 || posYAnterior == posY+1) && (posXAnterior == posX)))
                    {
                        this.movimientos[this.posicionActual][0] = posX;
                        this.movimientos[this.posicionActual][1] = posY;
                        System.out.println("--------------Me guardo el movimiento--------------" + this.posicionActual);
                        this.posicionActual += 1;
                    }
                }
            }
        }

        System.out.println("POSICION ACTUAL:"+this.posicionActual);

        if(this.posicionActual == movimientos.length)
        {
            System.out.println("--------------INICIO DEL MOVIMIENTO--------------");
            for(int i=0; i<movimientos.length; i++)
            {
                System.out.println("Me muevo a "+movimientos[i][0]+"-"+movimientos[i][1]);
            }
            System.out.println("--------------FIN DEL MOVIMIENTO--------------");
            Simulador.getInstance().addAccionesSimulador(jugador.getAccion());

            jugador.getEstado().setBloqueado(true);

            return true;
        }
        return false;
    }

    @Override
    public Jugador getJugador() {
        return null;
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
    public void setJugador(Jugador jugador) {

    }
}
