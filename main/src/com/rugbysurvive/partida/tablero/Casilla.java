package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.Dibujables.CasillaDibujable;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;


/**
 * Definicion de la casilla, elemento basico del que se compone el tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Casilla implements GestionEntrada {
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

    Dibujante dibujante;

    CasillaDibujable casillaDibujable;

    /**
     * Constructor de  casilla
     * @param posX posicion X del la casilla
     * @param posY posicion Y de la casilla
     */
    public Casilla (float posX, float posY,Dibujante dibujante) {
        this.posY = posY;
        this.posX = posX;
        this.dibujante = dibujante;
    }



    public void dibujarCasilla(){
        System.out.println("Casilla dibujada");
    }



    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    @Override
    public void accionEntrada(Entrada entrada) {
        if (entrada==Entrada.clic){
            //System.out.println("Casilla  clicada en x: " + posX + " y: " +posY);

            casillaDibujable = new CasillaDibujable(dibujante,(int)this.posX,(int)this.posY);
        }

        if (entrada==Entrada.arrastrar){
            System.out.println("Arrastre en casilla en x: " + posX + " y: " +posY);
        }

        if (entrada==Entrada.clicklargo){
            System.out.println("Longclick en casilla en x: " + posX + " y: " +posY);
        }
    }

    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX posicion x en el campo
     * @param posY posicion y en el campo
     */
    public boolean esSeleccionado(float posX, float posY) {
        if (posX >= this.posX && posX <= this.posX+64){
            if (posY >= this.posY && posY <= this.posY+64){
                System.out.println("Valores casilla x: " + this.posX + " y: " +this.posY);
                System.out.println("Casilla en la posicion x: " + posX + " y: " +posY +" selecionada");
                //casillaDibujable = new CasillaDibujable(dibujante,(int)posX,(int)posY);
                selecionado=true;
            }else {
                selecionado=false;
            }
        }

        return selecionado;


    }

    public void dibujar (){
        casillaDibujable = new CasillaDibujable(dibujante,(int)this.posX,(int)this.posY);
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
