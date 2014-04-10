package com.rugbysurvive.partida.tablero;


import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
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

    private Jugador jugador ;
    private ObjetoCampo objeto;

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
        this.jugador = null;
        this.objeto = null;

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

    /**
     * Añade un jugador a la casilla en caso de que no exista
     * ningun objeto o jugador colocado anteriormente
     *
     * En caso que existe algun elemento devolvera false y el
     * jugador no sera colocado , si esta vacia se colocara
     * sin problemas
     *
     * @param jugador elemento que se situa en esta casilla
     * @return si se ha podido colocar el jugador
     */
    public boolean añadirElemento(Jugador jugador)
    {
        if(this.jugador == null && this.objeto == null)
        {
            this.jugador = jugador;
            return true;
        }
        return false;
    }

    /**
     * Añade un objeto a la casilla en caso de que no exista
     * ningun objeto o jugador colocado anteriormente
     *
     * En caso que existe algun elemento devolvera false y el
     * objeto no sera colocado , si esta vacia se colocara
     * sin problemas
     *
     * @param objeto elemento que se situa en esta casilla
     * @return si se ha podido colocar el objeto
     */
    public boolean añadirElemento(ObjetoCampo objeto)
    {
        if(this.jugador == null && this.objeto == null)
        {
            this.objeto = objeto;
            return true;
        }
        return false;
    }

    /**
     * Indica si no hay ningun elemento situado en esta casilla
     *
     * @return cierto si la casilla esta libre
     */
    public boolean casillaLibre()
    {
        if(this.jugador == null && this.objeto == null)
        {
            return true;
        }
        return false;
    }

    /**
     * Elimina el elemento situado en la casilla
     */
    public void eliminarElemento()
    {
        this.jugador = null;
        this.objeto = null;
    }

    /**
     * Indica si hay un objeto en la casilla
     * @return devuelve cierto en caso que no haya
     * falso en caso contrario
     */
    public boolean sinObjeto()
    {
        if(this.objeto == null){
            return true;
        }
       return false;
    }

    /**
     * Indica si hay un jugador en la casilla
     * @return devuelve cierto en caso que no haya ,
     * falso en caso contrario
     */
    public boolean sinJugador()
    {
        if(this.jugador == null){
            return true;
        }
        return false;
    }

    public ObjetoCampo getObjeto(){return this.objeto;}




    public void setJugador(Jugador jugador)
    {
        this.jugador = jugador;
    }

    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla [][] casillas) {

        if(this.jugador != null)
        {
            this.jugador.accionEntrada(entrada, posX, posY, casillas);
        }

    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    @Override
    public void accionEntrada(Entrada entrada) {


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