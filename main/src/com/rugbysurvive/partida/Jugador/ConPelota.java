package com.rugbysurvive.partida.Jugador;


import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Pase;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Victor on 27/03/14.
 */
public class ConPelota implements Estado, Proceso {
    public boolean seleccionado = false;
    public boolean bloqueado = false;
    ElementoDibujable indicadorPelota;
    ElementoDibujable atencionJugador;
    ElementoDibujable indicarJugadorConPelota;
    int count;
    public Jugador jugador;

    /**
     * Constructor Estado ConPelota
     * @param jugador Jugador al que se le asigna el estado
     */
    public ConPelota(Jugador jugador){
        this.jugador = jugador;
        this.indicarJugadorConPelota = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/estado/conPelota.png");
        this.indicarJugadorConPelota.dibujar(this.jugador.getPosicionX(),this.jugador.getPosicionY());
    }

    public boolean generarAccion(Jugador jugador) {

        Simulador.getInstance().añadirAccion(jugador.getAccion());

        return false;
    }

    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY) {

        return false;
    }

    /**
     *
     * @param jugador Jugador que genera la Accion
     * @param posX Posición X del jugador
     * @param posY Posicion Y del jugador
     * @param entrada Tipo de evento que recibe el jugador
     * @return Boolean Accion Generada
     */
    @Override
    public boolean generarAccion(Jugador jugador, int posX, int posY, Entrada entrada) {

        this.jugador = jugador;

        if(entrada == Entrada.arrastrar)
        {
            int distancia = jugador.getResistencia()/10;

            if(distancia <=2) {
                distancia = 3;
            }

            jugador.setEstado(new EnMovimiento(distancia,this));
            return false;
        }
        else
        {
            if(jugador.getPaseOChute() == Entrada.pase)
            {
                //Comprovem que fem el pase en una posció en horitzontal o per davant del jugador
                if (jugador.getMiEquipo().getLado()== Lado.izquierda){

                    if(posX <= jugador.getPosicionX()){

                        jugador.setAccion(new Pase(jugador, posX, posY));
                        jugador.setSeleccionado(false);
                        System.out.println("La PASOOOOO!!!");
                    }

                    else{

                        this.atencionJugador = new ElementoDibujable(TipoDibujo.elementosJuego, "jugador/extras/caution.png" );
                        this.atencionJugador.dibujar(jugador.getPosicionX(), jugador.getPosicionY());
                        ProcesosContinuos.añadirProceso(this);
                        count = 0;

                    }
                }
                if(jugador.getMiEquipo().getLado()== Lado.derecha){

                        if (posX >= jugador.getPosicionX()){
                            jugador.setAccion(new Pase(jugador, posX, posY));
                            jugador.setSeleccionado(false);
                            System.out.println("La PASOOOOO!!!");
                        }

                    else {

                            this.atencionJugador = new ElementoDibujable(TipoDibujo.elementosJuego, "jugador/extras/caution.png" );
                            this.atencionJugador.dibujar(jugador.getPosicionX(), jugador.getPosicionY());
                            ProcesosContinuos.añadirProceso(this);
                            count = 0;

                        }
                }
            }

            else
            {
                //Comprovem que fem el xut en una posció en horitzontal o per davant del jugador

                if (jugador.getMiEquipo().getLado()== Lado.izquierda){

                    if(posX >= jugador.getPosicionX()){

                        jugador.setAccion(new Chute(jugador, posX, posY));
                        jugador.setSeleccionado(false);
                        System.out.println("La CHUTOOO!!!");
                    }
                    else{

                        this.atencionJugador = new ElementoDibujable(TipoDibujo.elementosJuego, "jugador/extras/caution.png" );
                        this.atencionJugador.dibujar(jugador.getPosicionX(), jugador.getPosicionY());
                        ProcesosContinuos.añadirProceso(this);
                        count = 0;
                    }
                }
                if (jugador.getMiEquipo().getLado() == Lado.derecha){
                        if(posX <= jugador.getPosicionX()){

                            jugador.setAccion(new Chute(jugador, posX, posY));
                            jugador.setSeleccionado(false);
                            System.out.println("La CHUTOOO!!!");
                        }
                    else{
                            this.atencionJugador = new ElementoDibujable(TipoDibujo.elementosJuego, "jugador/extras/caution.png" );
                            this.atencionJugador.dibujar(jugador.getPosicionX(), jugador.getPosicionY());
                            ProcesosContinuos.añadirProceso(this);
                            count = 0;

                        }
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


    @Override
    public boolean procesar() {
        count++;
        if(count == 100){
            this.atencionJugador.borrar();
            return true;
        }
        return false;
    }

    /**
     * El estado con pelota contiene un conjunto de texturas
     * que deben ser eliminadas antes de realizar cualquier dibujado
     */
    public void borrarTexturas() {
        if(this.indicarJugadorConPelota!= null){
            this.indicarJugadorConPelota.borrar();
        }
        if(this.indicadorPelota!= null){
            this.indicadorPelota.borrar();
        }
    }

    /**
     * Si hay una pelota dibujandose es necesario
     * que vaya actualizandose con la posicion del jugador
     */
    public void actualizarTexturas(){
        this.indicarJugadorConPelota.borrar();
        this.indicarJugadorConPelota.dibujar(this.jugador.getPosicionX(),this.jugador.getPosicionY());
    }
}
