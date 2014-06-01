package com.rugbysurvive.partida.arbitro;

import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 30/04/14.
 * Regla que determina si el uso del objetos es falta o no en funcion
 * de la posicion y si esta a la vista del arbitro.
 * En caso afirmativo se expulsa al jugador durante unos turnos.
 */
public class UsoObjetos extends Regla implements Proceso{

    /**
     * numero de turnos en los que el jugador estara castigado
     */
    private static final int NUMERO_TURNOS_CASTIGADO = 2;

    /**
     * jugador que usa un objeto
     */
    private Jugador jugador;


    private int turno;

    /**
     * Constructor de la regla uso de objetos
     * @param jugador jugador que usa un objeto
     */
    public UsoObjetos (Jugador jugador){
        super();
        this.jugador = jugador;
    }

    @Override
    public boolean arbitrar() {
        System.out.println("arbitrando uso de objetos");
        boolean zonapenalty1 = false;
        boolean zonapenalty2 = false;


        if(this.arbitro.esSucesoVisible(this.jugador.getPosicionX(),this.jugador.getPosicionY())){
            this.turno = GestorTurnos.getTurno();
            int posicionX = this.jugador.getPosicionX();
            int posicionY = this.jugador.getPosicionY();

            ComponentesJuego.getComponentes().getCampo().eliminarElemento(posicionY,posicionX);
            if(this.jugador.getEstado() instanceof ConPelota){
                this.dejarPelotaSuelo(posicionX,posicionY);
            }
            this.jugador.setExpulsado(true);
            ProcesosContinuos.añadirProceso(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean procesar() {
        if(GestorTurnos.getTurno()== this.turno + NUMERO_TURNOS_CASTIGADO) {
            this.añadirBorde();
            this.jugador.setExpulsado(false);
            return true;
        }
        return false;
    }

    /**
     * Una vez el jugador deja de estar expulsado
     * saldra por el borde inferior del tablero.
     * La funcion busca que no haya ningun jugador ocupando
     * esa posicion.
     */
    private void añadirBorde(){
        boolean colocado = false;
        int posicionInicialX = ConstantesJuego.NUMERO_CASILLAS_LARGO_TABLERO/2;
        int posicionInicialY = 0;
        Campo campo =  ComponentesJuego.getComponentes().getCampo();

        while(!colocado){
            if(!campo.añadirElemento(this.jugador,posicionInicialY,posicionInicialX)){
                posicionInicialX++;
            }
            else{
               colocado = true;
               this.jugador.setExpulsado(false);
                this.jugador.setBloqueado(true);
            }
       }
    }

    /**
     * Si el jugador tiene la pelota el arbitro lo dejara en el juego
     * y el partido seguira
     */
    private void dejarPelotaSuelo(int posicionX,int posicionY){
        this.jugador.setEstado(new SinPelota());
        Campo campo =  ComponentesJuego.getComponentes().getCampo();
        campo.colocarPelota(posicionY,posicionX);
    }
}
