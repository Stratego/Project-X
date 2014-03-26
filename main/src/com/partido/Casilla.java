package com.partido;

/**
 * Created by Victor on 24/03/14.
 */
public class Casilla implements Entrada {

    private float posX;
    private float posY;

    private boolean selecionado;



    public Casilla (float posX, float posY) {
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

    public void dibujarCasilla(){
        System.out.println("Casilla dibujada");
    }

    @Override
    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     * posX eje x donde se ha realizado la acciion /entrada
     * posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionEntrada(Imput imput, float posX, float posY) {

    }

    @Override
    /**
     * Indicamos el imput que se ha realizado
     * Imput tipo de entrada
     */
    public void accionEntrada(Imput imput) {

        if (imput==Imput.click){
            System.out.println("Casilla en la posicion x: " + posX + " y: " +posY +" clicada");
        }

    }



    @Override
    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     */
    public boolean esSeleccionado(float posX, float posY) {
        if (this.posX == posX && this.posY == posY){
            selecionado=true;
        }else {
            selecionado=false;
        }
        System.out.println("Casilla en la posicion x: " + posX + " y: " +posY +" selecionada");
        return selecionado;


    }
}
