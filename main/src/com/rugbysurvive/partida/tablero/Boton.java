package com.rugbysurvive.partida.tablero;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Boton implements GestionEntrada,Dibujable {

    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;

    private Entrada entrada;
    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;



    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param entrada tipo de boton que sera
     */
    public Boton(float posX, float posY, Entrada entrada) {
        this.posY = posY;
        this.posX = posX;
        this.entrada = entrada;
        GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
    }



    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {
        //System.out.println("X: " + this.posX + " Y: " + this.posY);
        //System.out.println(Gdx.graphics.getHeight());
        if (posX >= this.posX && posX <= this.posX+ ConstantesJuego.variables().getAnchoBoton()){
            if (posY >= Gdx.graphics.getHeight()- ConstantesJuego.variables().getAnchoBoton()){
                accionEntrada(this.entrada);

                selecionado=true;
            }
        }else {
            //System.out.println("Boton no selecionado");
            selecionado=false;
        }
        return selecionado;
    }

    public Entrada obtenerEntrada()
    {
        return entrada;
    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {



    }

    @Override
    public void accionEntrada(Entrada entrada) {

        System.out.println("Entrada: " + entrada);
        if (entrada == Entrada.pase){
            this.entrada = Entrada.chute;
        } else if (entrada == Entrada.chute){
            this.entrada = Entrada.pase;
        }
    }


    @Override
    public String getTextura() {
        return "boto.png";
    }

    @Override
    public int getPosicionX() {
        return (int)this.posX;
    }

    @Override
    public int getPosicionY() {
        return (int)this.posY;
    }
}