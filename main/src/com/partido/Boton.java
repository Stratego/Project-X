package com.partido;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Boton implements Entrada{

    private float posX;
    private float posY;

    public Boton(float posX, float posY) {
        this.posY = posY;
        this.posX = posX;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    @Override
    /**
     * Obtenemos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     * posX eje x donde se ha realizado la acciion /entrada
     * posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionEntrada(Imput imput, float posX, float posY) {
        if (imput==Imput.click){
            System.out.println("Boton en la posicion x: " + posX + " y: " +posY +" clicado");
        }
    }

    @Override
    /**
     * Obtenemos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     */
    public void accionEntrada(Imput imput) {

    }

    @Override
    /**
     * Obtenemos  la posicion dentro del tablero donde se esta realizando el arrastre
     * posX eje x donde se ha realizado la acciion /entrada
     * posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionArrastre(float posX, float posY) {

    }

    @Override
    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     */
    public boolean esSeleccionado(float posX, float posY) {
        return false;
    }
}
