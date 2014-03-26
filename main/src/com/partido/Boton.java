package com.partido;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Boton implements Entrada{

    private float posX;
    private float posY;
    private String nombre;

    private boolean selecionado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boton(float posX, float posY, String nombre) {
        this.posY = posY;
        this.posX = posX;
        this.nombre = nombre;
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
            System.out.println("Boton "+nombre +" en la posicion x: " + posX + " y: " +posY +" clicado");
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
        if (this.posX == posX && this.posY == posY){
            selecionado=true;
        }else {
            selecionado=false;
        }
        return selecionado;
    }
}
