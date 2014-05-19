package com.rugbysurvive.partida.arbitro;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by victor on 26/04/14.
 *
 * Personaje encargado de visualizar las jugadas.
 * Se mueve y gira durante el campo con una linia visual
 * concreta. Fuera de esta linia visual las reglas no se evaluan
 *
 */
public class Arbitro implements Dibujable{


    public static Arbitro arbitro = null;

    private DireccionJugador direccion = DireccionJugador.izquierda;

    private int posX =5;

    private int posY = 0;

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

            int rangoAleatorio = new Random().nextInt(movimiento);
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

            if (controlPosicion(rangoX,rangoY,true)==true){
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
            }else{

                arbitro.mover();

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

    /**
     * genera el campo de vision en que las faltas van a ser pitadas.
     */
    public void generarCampoVision(){
        int ancho =2;
        int posXAux=0;

        int posYAux=0;

        System.out.println("angulo vision");

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
                if (controlPosicion(posXAux,posYAux,false)==true){
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

        // CODIGO PRUEBA
        this.casilla.clear();
        for(int i=0;i<20;i++) {
            for(int j=0;j<30;j++) {
                this.casilla.add(new Casilla(j,i));

            }
        }
    }


    /**
     * Controla que la posicision en la que se va colocar el arbitro sea posible
     * @param x posible cordenada x
     * @param y posible cordenada y
     * @param control indica si la casilla es para el arbritro o la zona de vision
     * @return
     */
    public boolean controlPosicion(int x, int y, boolean control){


        boolean colocable = false;


                if (x>=0 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){
                    if (control==true){
                        if(x>=2 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO-2 && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO-2 && y>=2){
                            if (ComponentesJuego.getComponentes().getCampo().getCasilla(y,x).sinJugador()==true){
                                colocable=true;
                            }
                        }

                    }else{
                        colocable=true;
                    }
                }

        return colocable;

    }

    /**
     * quita el arbitro del campo y lo borra graficamente
     */
    private void quitar() {

        GestorGrafico.generarDibujante().eliminarTextura(id);
        ComponentesJuego.getComponentes().getCampo().eliminarElemento(posY,posX);

        this.casilla.clear();
    }



    @Override
    public String getTextura() {
        String textura=  "arbitro/arbitre4.png";;
        switch (this.direccion)
        {
            case arriba:
                textura = "arbitro/arbitre2.png";
                break;
            case abajo:
                textura = "arbitro/arbitre4.png";
                break;
            case izquierda:
                textura = "arbitro/arbitre1.png";
                break;
            case derecha:
                textura = "arbitro/arbitre3.png";
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
