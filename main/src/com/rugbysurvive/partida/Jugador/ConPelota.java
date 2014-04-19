package com.rugbysurvive.partida.Jugador;


import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Pase;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;

/**
 * Created by Victor on 27/03/14.
 */
public class ConPelota implements Estado {
    public boolean seleccionado = false;
    public boolean bloqueado = false;
    ElementoDibujable indicadorPelota;



    public boolean generarAccion(Jugador jugador) {

        Simulador.getInstance().añadirAccion(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {


        if(entrada == Entrada.arrastrar)
        {
            jugador.setEstado(new EnMovimiento(8,this));
            System.out.println("<ME PONGO EN MOVIMIENTO>");
            return false;
        }
        else
        {
            if(jugador.getPaseOChute() == Entrada.pase)
            {
                //Comprovem que fem el pase en una posció en horitzontal o per davant del jugador
                if (posX <= jugador.getPosicionX()){
                    jugador.setAccion(new Pase(jugador, posX, posY));
                    System.out.println("La PASOOOOO!!!");
                }
            }
            else
            {
                //Comprovem que fem el xut en una posció en horitzontal o per davant del jugador


                if(posX >= jugador.getPosicionX()){

                    jugador.setAccion(new Chute(jugador, posX, posY));
                    System.out.println("La CHUTOOO!!!");

                }

            }
        }


        if(jugador.getAccion() != null)
        {
            Simulador.getInstance().añadirAccion(jugador.getAccion());
            this.indicadorPelota = new ElementoDibujable(TipoDibujo.fondo,"indicadoresMovimiento/pilotaPosessio.png");
            this.indicadorPelota.dibujar(posX,posY);
            jugador.setBloqueado(true);
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
        return this.seleccionado;
    }

    @Override
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public boolean getBloqueado() {
        return this.bloqueado;
    }

    @Override
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    @Override
    public boolean getPaseOChute() {
        return true;
    }

    @Override
    public void setPaseOChute(boolean paseOChute) {

    }

    @Override
    public Estado getEstado() {
        return this;
    }

    @Override
    public Estado getEstadoAnterior() {
        return null;
    }


    @Override
    public void setJugador(Jugador jugador) {

    }


}
