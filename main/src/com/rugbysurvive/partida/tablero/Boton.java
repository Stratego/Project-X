package com.rugbysurvive.partida.tablero;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Lista;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.gestores.Entrada.*;
import com.rugbysurvive.partida.gestores.GestorGrafico;

import java.util.ArrayList;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Boton implements GestionEntrada,Dibujable{

    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;

    private Entrada entrada;
    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;

    /**
     * identificador del boton para el gestor grafico
     */
    int ID;

    String textura;

    int posicion;

    //Lista lista = new Lista();
    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param entrada tipo de boton que sera
     */
    public Boton(float posX, float posY, Entrada entrada,String textura, int posicion) {
        this.posY = posY;
        this.posX = posX;
        this.entrada = entrada;
        this.textura = textura;
        this.posicion = posicion;
        ID=GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
    }



    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {
        //System.out.println("X: " + this.posX + " Y: " + this.posY);
        //System.out.println(Gdx.graphics.getHeight());
        int anchoBoton=0;
        int altoBoton=0;
        if (this.posicion == 20){
            anchoBoton=ConstantesJuego.variables().getAnchoBoton();
            altoBoton=ConstantesJuego.variables().getAnchoBoton();

            /*
            if (posX >= this.posX && posX <= this.posX+anchoBoton){
                if (posY >= Gdx.graphics.getHeight()-altoBoton){
                    accionEntrada(this.entrada);

                    selecionado=true;


                }
            }else {
                //System.out.println("Boton no selecionado");
                selecionado=false;
            }*/

        }else if(this.obtenerEntrada()==Entrada.listasuplente){
            anchoBoton=768;
            altoBoton=64;


        }else {
            anchoBoton=64;
            altoBoton=64;
        }

        if (posX >= this.posX && posX <= this.posX+anchoBoton){
            if (posY >= Gdx.graphics.getHeight() - this.posY -altoBoton && posY <= Gdx.graphics.getHeight()  -this.posY){
                accionEntrada(this.entrada);

                selecionado=true;


            }
        }else {
            //System.out.println("Boton no selecionado");
            selecionado=false;
        }
        /*if (posX >= this.posX && posX <= this.posX+anchoBoton){
            if (posY >= Gdx.graphics.getHeight()-altoBoton){
                accionEntrada(this.entrada);

                selecionado=true;


            }
        }else {
            //System.out.println("Boton no selecionado");
            selecionado=false;
        }*/
        return selecionado;
    }

    public Entrada obtenerEntrada()
    {
        return this.entrada;
    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {



    }

    @Override
    public void accionEntrada(Entrada entrada) {
        //lista.crearLista(entrada);

        System.out.println("Entrada: " + entrada);
        System.out.println("posicion: " + posicion);

        if (entrada ==Entrada.pase){
            this.entrada = Entrada.chute;
        } else if (entrada ==Entrada.chute){
            this.entrada = Entrada.pase;
        }

        if (entrada == Entrada.listaobjetos){
            Jugador jugador = ComponentesJuego.getComponentes().getEquipo1().getJugadorActivo();
            ArrayList<ObjetoJugador> objetos = jugador.getPowerUP();
            System.out.println("vida jugador antes objeto "+jugador.getVida());
            for (ObjetoJugador iter: objetos){
                if (iter.getId()==this.posicion){
                    iter.activar();
                    System.out.println("vida jugador despues objeto "+ jugador.getVida());
                    jugador.getPowerUP().remove(iter);
                    break;
                }
            }
            //obteniendo la instansacion de equipo obtener la de objetos de jugador activo y activar objeto

        }

        if (entrada == Entrada.listasuplente){
            //obteniendo la instansacion de equipo y realizar cambio en la lista de jugadores
            //equipo.intercambioJugadores(12);
        }

        if (entrada==Entrada.finalizar){

        }

    }

    public int getID(){
        return ID;
    }

    public int getPosicion(){
        return this.posicion;
    }

    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return (int)this.posX;
    }

    @Override
    public int getPosicionY() {
        return (int)this.posY;
    }
}