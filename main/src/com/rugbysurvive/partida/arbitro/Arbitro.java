package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aitor on 26/04/14.
 *
 * Personaje encargado de visualizar las jugadas.
 * Se mueve y gira durante el campo con una linia visual
 * concreta. Fuera de esta linia visual las reglas no se evaluan
 *
 */
public class Arbitro implements Dibujable{


    public static Arbitro arbitro = null;

    private DireccionJugador direccion = DireccionJugador.izquierda;

    private int posX =25;

    private int posY = 10;

    private int rangoVision = 5;
    private int movimiento = 8;

    private int id = 0;

    private ArrayList<Casilla> casilla = new ArrayList<Casilla>();

    ElementoDibujable casillaVision;

    public Arbitro() {
        this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        arbitro=this;
        ComponentesJuego.getComponentes().getCampo().getCasilla(this.posY,this.posX).añadirElemento(arbitro);
        generarCampoVision();
    }
    public static Arbitro getInstancia() {

        if(arbitro == null) {
            arbitro = new Arbitro();
            return arbitro;
        }
        return arbitro;
    }


    /**
     * Mueve el arbitro de posicion y lo hace girar sobre si mismo
     * Solo puede moverse a casillas donde no hay ningun objeto o
     * jugador y se puede girar como quiera.
     */
    public void mover(){
        if(arbitro == null){
            arbitro = new Arbitro();

            System.out.println("Creacion en mover");

        }
        else {

            //ComponentesJuego.getComponentes().getCampo().getCasilla(this.posY,this.posX).eliminarElemento();
            //this.quitar();
            //int rangoAleatorio = (int)Math.random()*(movimiento-(movimiento*=-1))+(movimiento*=-1);
            int rangoAleatorio = new Random().nextInt(movimiento);
            //int positivoNegativo= new Random().nextInt(1);
            if (new Random().nextInt()%2 != 0){
                rangoAleatorio*=-1;
            }

            int rangoX = posX + rangoAleatorio;
            int rangoY;
            if (new Random().nextInt()%2 != 0){
                rangoY = posY-(movimiento-rangoAleatorio);
            }else{
                rangoY = posY+(movimiento-rangoAleatorio);
            }
            //int rangoY = posY+(movimiento-rangoAleatorio);
            //System.out.println(rangoX);
            //System.out.println(rangoY);
            if (controlPosicion(rangoX,rangoY)==true){
                ComponentesJuego.getComponentes().getCampo().getCasilla(this.posY,this.posX).eliminarElemento();
                this.quitar();
                System.out.println("moviendo arbitro");
                this.posX= rangoX;
                this.posY = rangoY;
                this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
                this.direccion = DireccionJugador.getRandom();
                System.out.println(rangoX);
                System.out.println(rangoY);
                System.out.println(direccion);
                generarCampoVision();
                ComponentesJuego.getComponentes().getCampo().getCasilla(this.posY,this.posX).añadirElemento(this);
            }


        }
    }

    /**
     * Indica se una jugada ha sido visualizada o no por el arbitro
     * @param posicionX Posicion
     * @param posicionY
     * @return
     */
    public boolean esSucesoVisible(int posicionX,int posicionY){

        for (Casilla iter: casilla){
            if (posicionX==iter.getPosicionX() && posicionY==iter.getPosicionY() ){
                return true;
            }
        }
        return false;
    }

    public void generarCampoVision(){
        int ancho =2;
        int posXAux=0;

        int posYAux=0;


        switch (this.direccion)
        {
            case derecha:
                posXAux = posX +1;
                posYAux = posY -1;
                break;

            case izquierda:
                posXAux = posX -1;
                posYAux = posY -1;
                break;

            case arriba:
                posXAux = posX -1;
                posYAux = posY +1;
                break;

            case abajo:
                posXAux = posX -1;
                posYAux = posY -1;
                break;
        }



        for (int i=0; i<rangoVision; i++){

            for (int j=0; j<=ancho; j++){
                if (controlPosicion(posXAux,posYAux)==true){
                    //this.casillaVision = new ElementoDibujable(TipoDibujo.elementosJuego,"casilla.png");
                    //this.casillaVision.dibujar(posXAux,posYAux);
                    this.casilla.add(new Casilla((float)posXAux,(float)posYAux));
                }

                if (this.direccion == DireccionJugador.abajo ||this.direccion == DireccionJugador.arriba){
                    posXAux+=1;
                }else {
                    posYAux+=1;
                }

            }
            ancho+=2;
            switch (this.direccion)
            {
                case derecha:
                    posXAux += 1;
                    posYAux-=ancho;
                    break;

                case izquierda:
                        posXAux -= 1;
                        posYAux-=ancho;
                    break;

                case arriba:
                    posYAux+=1;
                    posXAux-=ancho;
                    break;

                case abajo:
                    posYAux-=1;
                    posXAux-=ancho;
                    break;
            }

        }
    }


    /**
     * Controla que la posicision en la que se va colocar el arbitro sea posible
     * @param x posible cordenada x
     * @param y posible cordenada y
     * @return
     */
    public boolean controlPosicion(int x, int y){


        boolean colocable = false;


                if (x>=0 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){
                    colocable=true;
                }



        return colocable;

    }

    private void quitar(){
        System.out.println("quitar");
        GestorGrafico.generarDibujante().eliminarTextura(id);
        this.casilla.clear();
    }



    @Override
    public String getTextura() {
        String textura="jugador1.png" ;
        switch (this.direccion)
        {
            case arriba:
                textura = "jugador/jugador4.png";
                break;
            case abajo:
                textura = "jugador/jugador2.png";
                break;
            case izquierda:
                textura = "jugador/jugador5.png";
                break;
            case derecha:
                textura = "jugador/jugador1.png";
                break;
        }
        return textura;

    }

    @Override
    public int getPosicionX() {
        return posX;
    }

    @Override
    public int getPosicionY() {
        return posY;
    }
}
