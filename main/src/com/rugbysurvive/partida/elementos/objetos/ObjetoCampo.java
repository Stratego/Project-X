package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by aitor on 10/04/14.
 * Objeto que se puede colocar en una casilla del campo y que reacciona ante un evento
 * sea alguna interaccion con otro objeto o por decision del jugador
 */
public abstract class ObjetoCampo implements Dibujable {

    private int id;
    private int posX;
    private int posY;
    private String textura;



    public ObjetoCampo(String textura){

        this.textura=textura;

    }


    public void colocar(int posicionX,int posicionY){
        this.posX = posicionX;
        this.posY = posicionY;
        this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.añadirElemento(this,this.posY,this.posX);
    }

    public void quitar(){
        GestorGrafico.generarDibujante().eliminarTextura(id);
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        campo.eliminarElemento(this.posX,this.posY);

    }

    public abstract void efecto(Jugador jugador);






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
