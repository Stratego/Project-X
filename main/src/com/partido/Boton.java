package com.partido;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Boton implements Entrada{

    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;

    /**
     * nombre del boton que nos servira para identificarlo
     */
    private String nombre;


    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;


    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param nombre nombre del boton
     */
    public Boton(float posX, float posY, String nombre) {
        this.posY = posY;
        this.posX = posX;
        this.nombre = nombre;
    }



    @Override
    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * @param Imput tipo de entrada
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public void accionEntrada(Imput imput, float posX, float posY) {
        if (imput==Imput.click){
            System.out.println("Boton "+nombre +" en la posicion x: " + posX + " y: " +posY +" clicado");
        }else if(imput==Imput.longclick){
            System.out.println("Boton "+nombre +" en la posicion x: " + posX + " y: " +posY +" longpress");
        }
    }

    @Override
    /**
     * Indicamos el imput que se ha realizado
     * @param Imput tipo de entrada
     */
    public void accionEntrada(Imput imput) {
        if (imput==Imput.click){
            System.out.println("Boton "+nombre+" clicado");
        }else if(imput==Imput.longclick){
            System.out.println("Boton "+nombre+" longpress");
        }
    }



    @Override
    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {
        if (this.posX == posX && this.posY == posY){
            selecionado=true;
        }else {
            selecionado=false;
        }
        return selecionado;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
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
}
