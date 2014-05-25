package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Campo;
import org.omg.CORBA.COMM_FAILURE;

/**
 * Created by aitor on 10/04/14.
 * Objeto que se puede colocar en una casilla del campo y que reacciona ante un evento
 * sea alguna interaccion con otro objeto o por decision del jugador
 */
public abstract class ObjetoCampo implements Dibujable ,Proceso{

    protected int id;
    protected int posX;
    protected int posY;
    private String textura;
    private Jugador jugador;
    private boolean finalizarProceso;
    private boolean vistualizacion;



    public ObjetoCampo(String textura,Jugador jugador){

        this.textura=textura;
        this.jugador = jugador;
        this.finalizarProceso = false;
        this.vistualizacion = false;
    }


    public void colocar(int posicionX,int posicionY){
        this.posX = posicionX;
        this.posY = posicionY;
        this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.añadirElemento(this,this.posY,this.posX);
        ProcesosContinuos.añadirProceso(this);
        this.vistualizacion = true;
    }

    public void quitar(){
        GestorGrafico.generarDibujante().eliminarTextura(id);
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.eliminarElemento(this.posY,this.posX);
        this.finalizarProceso = true;
    }

    public abstract void efecto(Jugador jugador,boolean animacionParada);

    @Override
    public boolean procesar(){

       if(this.vistualizacion){
        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo1();


        if(equipo1.jugadorEnEquipo(jugador) && equipo1.bloqueado() && this.id != -1){
            GestorGrafico.generarDibujante().eliminarTextura(id);
            this.id = -1;
        }
        else if (equipo2.jugadorEnEquipo(jugador) && equipo2.bloqueado() && this.id != -1){
            GestorGrafico.generarDibujante().eliminarTextura(id);
            this.id = -1;

        }
        else if(equipo1.jugadorEnEquipo(jugador) && !equipo1.bloqueado() && this.id == -1){
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this,TipoDibujo.elementosJuego);
        }

        else if(equipo2.jugadorEnEquipo(jugador) && !equipo2.bloqueado() && this.id == -1){
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        }

       }

        return this.animacion();

    }

    protected abstract boolean animacion();


    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return this.posX;
    }

    @Override
    public int getPosicionY() {
        return this.posY;
    }
}
