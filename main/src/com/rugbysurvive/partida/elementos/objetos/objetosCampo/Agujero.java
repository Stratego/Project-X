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
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by aitor on 20/05/14.
 */
public class Agujero extends ObjetoCampo {

    private static final int TIEMPO_VIDA_AGUJERO_POST_CAIDA = 500;
    private static final int  NUMERO_TURNOS_CASTIGADO = 2;
    private int tiempo;
    private ElementoDibujable animacion;
    private int contador;
    public boolean animando;
    public int turno;
    public Jugador jugador;

    public Agujero(String textura,Jugador jugador){
        super(textura,jugador);
        this.tiempo = 0;
        this.animando = false;
        this.contador = 1;

    }
    @Override
    public void efecto(Jugador jugador) {

        Gdx.audio.newMusic(Gdx.files.internal("sonido/acciones/explosion.mp3")).play();
         turno = GestorTurnos.getTurno();
        int posicionX = jugador.getPosicionX();
        int posicionY = jugador.getPosicionY();

        ComponentesJuego.getComponentes().getCampo().eliminarElemento(posicionY,posicionX);
        if(jugador.getEstado() instanceof ConPelota){
            dejarPelotaSuelo(jugador,posicionX,posicionY);
        }
        this.jugador = jugador;
         jugador.setExpulsado(true);
        ProcesosContinuos.añadirProceso(this);
        this.animando = true;

        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.eliminarElemento(this.posY,this.posX);


    }


    @Override
    protected boolean animacion() {
        if(this.animando){
            if(tiempo == TIEMPO_VIDA_AGUJERO_POST_CAIDA) {
                GestorGrafico.generarDibujante().eliminarTextura(id);
            return false;
        }
       if(GestorTurnos.getTurno()== this.turno + NUMERO_TURNOS_CASTIGADO) {
                this.añadirBorde();
                this.jugador.setExpulsado(false);
                return true;
            }
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
    private void dejarPelotaSuelo(Jugador jugador,int posicionX,int posicionY){
        jugador.setEstado(new SinPelota());
        Campo campo =  ComponentesJuego.getComponentes().getCampo();
        campo.colocarPelota(posicionY,posicionX);
    }


}
