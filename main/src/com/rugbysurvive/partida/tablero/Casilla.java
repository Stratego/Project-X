package com.rugbysurvive.partida.tablero;



import com.rugbysurvive.partida.Jugador.Jugador;
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

    private Jugador jugador = null;

    /**
     * indicara si el elemento esta seleccionado
     */
    private boolean selecionado;

    Dibujante dibujante;

    /**
     * Constructor de  casilla
     * @param posX posicion X del la casilla
     * @param posY posicion Y de la casilla
     */
    public Casilla (float posX, float posY) {
        this.posY = posY;
        this.posX = posX;

        this.dibujante = dibujante;


        /*Este objeto solo se usara para hacer pruebas*/
        //if((posY == 1 || posY == 9 || posY==17) && ((2+posX)%4 == 0))
        if(posY == 10 && posX == 4)
        {
            this.jugador = new Jugador(this);
        }
        else
        {
            this.jugador = null;
        }


    }

    public void setJugador(Jugador jugador)
    {
        this.jugador = jugador;
    }


    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla [][] casillas) {
        //System.out.println(entrada + " x: "+ posX + "y: " + posY);
        if(this.jugador != null)
        {
            //System.out.println("jugador "+entrada + "x: "+ posX + "y: " + posY);
            this.jugador.accionEntrada(entrada, posX, posY, casillas);
        }

    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    @Override
    public void accionEntrada(Entrada entrada) {


    }

    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {

        if (posX == this.posX){
            if (posY == this.posY){
                selecionado=true;
            }
        }else {

            selecionado=false;
        }
        return selecionado;
    }

    public Jugador getJugador()
    {
        return this.jugador;
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