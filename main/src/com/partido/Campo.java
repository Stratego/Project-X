package com.partido;

import java.util.ArrayList;

/**
 * Created by Victor on 24/03/14.
 */
public class Campo implements Entrada {

    private float posX;
    private float posY;




    ArrayList <Casilla> casillas= new ArrayList <Casilla>();

    private Casilla casilla;

    public Campo() {
        dibujartablero();
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


    public void dibujartablero(){
        if (casillas.size()==0){
            casilla = new Casilla(0,0);
            casillas.add(casilla);
        }
    }

    @Override
    public void accionEntrada(Imput imput, float posX, float posY) {
        if (imput.equals(imput.click)){
            for (Casilla iterador : casillas){
                if (iterador.getPosX()==posX && iterador.getPosY()==posY){
                    iterador.accionEntrada(Imput.click);
                    iterador.esSeleccionado(posX, posY);
                }
            }
        }

    }

    @Override
    public void accionEntrada(Imput imput) {

    }

    @Override
    public void accionArrastre(float posX, float posY) {

    }

    @Override
    public boolean esSeleccionado(float posX, float posY) {
        return false;
    }
}
