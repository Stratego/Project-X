package com.partido;

import java.util.ArrayList;

/**
 * Created by Victor on 24/03/14.
 */
public class Campo implements Entrada {

    private float posX;
    private float posY;

    private boolean selecionado;




    ArrayList <Casilla> casillas= new ArrayList <Casilla>();

    private Casilla casilla;

    public Campo() {
        dibujarTablero();
    }
/*
    public Campo(float posX, float posY) {
        this.posY = posY;
        this.posX = posX;
    }
*/
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

    /**
     * Dibujamos el tablero de juego
     */
    public void dibujarTablero(){
        if (casillas.size()==0){
            casilla = new Casilla(0,0);
            casillas.add(casilla);
        }
    }

    @Override
    /**
     * Indicamos el imput que se ha realizado y su posicion dentro del tablero
     * Imput tipo de entrada
     * posX eje x donde se ha realizado la acciion /entrada
     * posY eje y donde se ha realizado la acciion /entrada
     */
    public void accionEntrada(Imput imput, float posX, float posY) {

        if (imput==Imput.click){
            for (Casilla iterador : casillas){
                if (iterador.getPosX()==posX && iterador.getPosY()==posY){
                    if(iterador.esSeleccionado(posX, posY)){
                        iterador.accionEntrada(Imput.click);
                    }
                }
            }
        }

        if (imput==Imput.arrastre){
            System.out.println("Arrastrandose por la posicion x: " + posX + " y: " +posY +" del campo");
        }

        if(imput==Imput.boton1){

            System.out.println("Ejecucion de boton1 en campo");

        }
        if(imput==Imput.boton2){

            System.out.println("Ejecucion de boton2 en campo");

        }

        if(imput==Imput.boton3){

            System.out.println("Ejecucion de boton3 en campo");

        }

        if(imput==Imput.boton4){

            System.out.println("Ejecucion de boton4 en campo");

        }

    }

    @Override
    /**
     * Indicamos el imput que se ha realizado
     * Imput tipo de entrada
     */
    public void accionEntrada(Imput imput) {

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
