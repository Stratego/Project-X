package com.rugbysurvive.partida.elementos.objetos.objetosCampo;

import com.badlogic.gdx.Gdx;
import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Campo;
import org.omg.CORBA.COMM_FAILURE;

/**
 * Objeto colocable en el campo que retirara a un jugador
 * enemigo que lo pise de la partida durante unos turnos
 * Se ejecutara una animacion de caida
 * Created by aitor on 20/05/14.
 */
public class Agujero extends ObjetoCampo {

    /**
     * tiempo de vida del agujero en el campo
     */
    private static final int TIEMPO_VIDA_AGUJERO_POST_CAIDA = 500;

    /**
     * numero de turnos que el juguador enemigo no esta disponible
     */
    private static final int  NUMERO_TURNOS_CASTIGADO = 2;

    /**
     * tiempo que se ejecutara la animacion
     */
    private int tiempo;

    /**
     * dibujo de la animacion en el campo
     */
    private ElementoDibujable animacion;


    private int contador;

    /**
     * indica si se esta ejecutando la animacion
     */
    public boolean animando;

    /**
     * turno del jugador
     */
    public int turno;

    /**
     * jugador que cae en el agujero
     */
    public Jugador jugador;

    /**
     * posicion X donde se colocara el agujero
     */
    private int posicionX;

    /**
     * posicion Y donde se colocara el agujero
     */
    private int posicionY;

    /**
     * constructor del agujero
     * @param textura textura que representara graficamente el agujero en el campo
     * @param jugador jugador que ha colocado el agujero
     */
    public Agujero(String textura,Jugador jugador){
        super(textura,jugador);
        this.tiempo = 0;
        this.animando = false;
        this.contador = 1;


    }
    @Override
    public void efecto(Jugador jugador,boolean animacionParada) {

        this.turno = GestorTurnos.getTurno();
        this.jugador = jugador;
        this.posicionX = this.jugador.getPosicionX();
        this.posicionY = this.jugador.getPosicionY();


            this.animando = true;

            ProcesosContinuos.añadirProceso(this);

        if(!animacionParada){
            Gdx.audio.newMusic(Gdx.files.internal("sonido/acciones/caida.wav")).play();
        }


    }


    @Override
    protected boolean animacion() {
        if(this.animando) {
            if(tiempo == TIEMPO_VIDA_AGUJERO_POST_CAIDA) {
                this.quitar();
                return true;
            }

            else if(this.tiempo==4){
                int posicionX = this.jugador.getPosicionX();
                int posicionY = this.jugador.getPosicionY();

                this.jugador.quitar();
                ComponentesJuego.getComponentes().getCampo().getCasilla(posicionY,posicionX).setJugador(null);

                if(jugador.getEstado() instanceof ConPelota){
                    dejarPelotaSuelo(jugador,jugador.getPosicionX(),jugador.getPosicionY());
                }
                 jugador.setExpulsado(true);

            }

         /* if(GestorTurnos.getTurno()== this.turno + NUMERO_TURNOS_CASTIGADO) {
                this.añadirBorde();
                return true;
       }*/
            this.tiempo++;
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
        Equipo equipo = ComponentesJuego.getComponentes().getEquipo1();

        if(!equipo.jugadorEnEquipo(this.jugador)){
            equipo = ComponentesJuego.getComponentes().getEquipo2();
        }

        while(!colocado){
            if(!campo.añadirElemento(this.jugador,posicionInicialY,posicionInicialX)){
                posicionInicialX++;
            }
            else{
                colocado = true;
                this.jugador.setExpulsado(false);
            }

            }
        }


    /**
     * Si el jugador tiene la pelota el arbitro lo dejara en el juego
     * y el partido seguira
     */
    private void dejarPelotaSuelo(Jugador jugador,int posicionX,int posicionY){
        jugador.setEstado(new SinPelota());
        Campo campo =  ComponentesJuego.getComponentes().getCampo();
        campo.colocarPelota(posicionY,posicionX);
    }


}
