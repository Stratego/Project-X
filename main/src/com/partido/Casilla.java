package com.partido;

/**
 * Definicion de la casilla, elemento basico del que se compone el tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Casilla implements Entrada {
    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;


    /**
     * indicara si el elemento esta seleccionado
     */
    private boolean selecionado;


    /**
     * Constructor de  casilla
     * @param posX posicion X del la casilla
     * @param posY posicion Y de la casilla
     */
    public Casilla (float posX, float posY) {
        this.posY = posY;
        this.posX = posX;
    }



    public void dibujarCasilla(){
        System.out.println("Casilla dibujada");
    }

    @Override
    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     * @param posX eje x donde se ha realizado la acciion /entrada
     * @param posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionEntrada(Imput imput, float posX, float posY) {
        if (imput==Imput.click){
            System.out.println("Casilla en la posicion x: " + posX + " y: " +posY +" clicada");
        }

        if (imput==Imput.arrastre){
            System.out.println("Arrastrandose por la casilla en posicion x: " + posX + " y: " +posY );

        }

        if (imput==Imput.longclick){
            System.out.println("Longclik en casilla en la posicion x: " + posX + " y: " +posY +" clicada");
        }
    }

    @Override
    /**
     * Indicamos el imput que se ha realizado
     * @param imput tipo de entrada
     */
    public void accionEntrada(Imput imput) {

        if (imput==Imput.click){
            System.out.println("Casilla  clicada en x: " + posX + " y: " +posY);
        }

        if (imput==Imput.arrastre){
            System.out.println("Arrastre en casilla en x: " + posX + " y: " +posY);
        }

        if (imput==Imput.longclick){
            System.out.println("Longclick en casilla en x: " + posX + " y: " +posY);
        }

        if(imput==Imput.boton1){

            System.out.println("Ejecucion de boton1 en casilla x: " + posX + " y: " +posY);

        }
        if(imput==Imput.boton2){

            System.out.println("Ejecucion de boton2 en campo en x: " + posX + " y: " +posY);

        }

        if(imput==Imput.boton3){

            System.out.println("Ejecucion de boton3 en campo en x: " + posX + " y: " +posY);

        }

        if(imput==Imput.boton4){

            System.out.println("Ejecucion de boton4 en campoen x: " + posX + " y: " +posY);

        }

    }



    @Override
    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX posicion x en el campo
     * @param posY posicion y en el campo
     */
    public boolean esSeleccionado(float posX, float posY) {
        if (this.posX >posX && this.posX<64){
            if (this.posX >posY && this.posY<64){
                selecionado=true;
            }else {
                selecionado=false;
            }
        }
        System.out.println("Casilla en la posicion x: " + posX + " y: " +posY +" selecionada");
        return selecionado;


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
