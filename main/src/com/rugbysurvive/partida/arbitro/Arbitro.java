package com.rugbysurvive.partida.arbitro;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by victor on 26/04/14.
 *
 * Personaje encargado de hacer cumplir la reglas del juego.
 * Sin enbargo solo hara cumplir la reglas cuando se infrinjan dentro de su rango de vision.
 * En caso contrario los jugadores pueden hacer lo que quieran.
 * el arbitro se mueve aleatoriamente por el campo, y en cada MOVIMIENTO mira a una direccion diferente.
 */
public class Arbitro implements Dibujable,Proceso{

    private static final int TIEMPO_PITADA = 50;
    private int tiempo;

    /**
     * instancia de arbitro que se envia a otras clases
     */
    private static Arbitro arbitro = null;

    /**
     * direccion hacia la que mira el arbitro
     */
    private DireccionJugador direccion = DireccionJugador.abajo;

    /**
     * posicion X donde se movera el arbitro
     */
    private int posicionX =15;

    /**
     * posicion Y donde se movera el arbitro
     */
    private int posicionY = 13;

    /**
     * largo y ancho maximo del cono de vision del arbitro
     */
    private int RANGO_VISION = 5;

    /**
     * limite de movimento del arbitro
     */
    private int MOVIMIENTO = 8;

    /**
     * identificador textura del arbitro
     */
    private int id = 0;

    /**
     * coleccion de casillas que estan bajo la vision del arbitro
     */
    private ArrayList<Casilla> casilla = new ArrayList<Casilla>();

    //ElementoDibujable casillaVision;

    /**
     * Constructor de la clase que dibjuara el arbitro y
     * lo colocara en su posicion inicial.
     */
    public Arbitro() {
        this.id = GestorGrafico.generarDibujante().añadirDibujable(this,
                TipoDibujo.elementosJuego);
        arbitro=this;
        ComponentesJuego.getComponentes().getCampo().getCasilla(this.posicionY,
                this.posicionX).añadirElemento(arbitro);
        generarCampoVision();
        this.tiempo = 0;
    }

    /**
     * devuelve la instancia de la calse para que pueda ser usada en otras clases.
     * @return instancia de la clase arbitro.
     */
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

            int rangoAleatorio = new Random().nextInt(MOVIMIENTO);
            if (new Random().nextInt()%2 != 0){
                rangoAleatorio*=-1;
            }

            int rangoX = posicionX + rangoAleatorio;
            int rangoY;
            if (new Random().nextInt()%2 != 0){
                rangoY = posicionY -(MOVIMIENTO -rangoAleatorio);
            }else{
                rangoY = posicionY +(MOVIMIENTO -rangoAleatorio);
            }

            if (controlPosicion(rangoX,rangoY,true)==true){
                ComponentesJuego.getComponentes().getCampo().getCasilla(this.posicionY,this.posicionX).eliminarElemento();
                this.quitar();
                System.out.println("moviendo arbitro");
                this.posicionX = rangoX;
                this.posicionY = rangoY;
                this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
                this.direccion = DireccionJugador.getRandom();
                generarCampoVision();
                ComponentesJuego.getComponentes().getCampo().getCasilla(this.posicionY,this.posicionX).añadirElemento(this);
            }else{
                arbitro.mover();
            }
        }
    }

    /**
     * Indica se una jugada ha sido visualizada o no por el arbitro
     * @param posicionX Posicion x donde se ha efectuado una jugada.
     * @param posicionY Posicion y donde se ha efectuado una jugada.
     * @return indica si la posicion esta en el rango de vision del arbitro.
     */
    public boolean esSucesoVisible(int posicionX,int posicionY){

        for (Casilla iteracion: casilla){
            if (posicionX==iteracion.getPosicionX() && posicionY==iteracion.getPosicionY() ){
                 Gdx.audio.newMusic(Gdx.files.internal("sonido/acciones/pito.mp3")).play();
                ProcesosContinuos.añadirProceso(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Genera el campo de vision del arbitro que es donde las faltas seran pitadas.
     * el campo se representa como un conjunto conico de casillas que surge de la
     * direccion desde donde esta mirando el arbitro.
     */
    private void generarCampoVision(){
        int ancho =2;
        int posicionXAux=0;

        int posicionYAux=0;

        System.out.println("angulo vision");

        switch (this.direccion)
        {
            case derecha:
                posicionXAux = posicionX +1;
                posicionYAux = posicionY -1;
                break;

            case izquierda:
                posicionXAux = posicionX -1;
                posicionYAux = posicionY -1;
                break;

            case arriba:
                posicionXAux = posicionX -1;
                posicionYAux = posicionY +1;
                break;

            case abajo:
                posicionXAux = posicionX -1;
                posicionYAux = posicionY -1;
                break;
        }

        for (int i=0; i< RANGO_VISION; i++){

            for (int j=0; j<=ancho; j++){
                if (controlPosicion(posicionXAux,posicionYAux,false)==true){
                    //this.casillaVision = new ElementoDibujable(TipoDibujo.elementosJuego,"casilla.png");
                    //this.casillaVision.dibujar(posXAux,posYAux);
                    this.casilla.add(new Casilla((float)posicionXAux,(float)posicionYAux));
                }

                if (this.direccion == DireccionJugador.abajo ||this.direccion == DireccionJugador.arriba){
                    posicionXAux+=1;
                }else {
                    posicionYAux+=1;
                }

            }
            ancho+=2;
            switch (this.direccion)
            {
                case derecha:
                    posicionXAux += 1;
                    posicionYAux-=ancho;
                    break;

                case izquierda:
                        posicionXAux -= 1;
                        posicionYAux-=ancho;
                    break;

                case arriba:
                    posicionYAux+=1;
                    posicionXAux-=ancho;
                    break;

                case abajo:
                    posicionYAux-=1;
                    posicionXAux-=ancho;
                    break;
            }
        }

        // CODIGO PRUEBA
        /*this.casilla.clear();
        for(int i=0;i<20;i++) {
            for(int j=0;j<30;j++) {
                this.casilla.add(new Casilla(j,i));

            }
        }*/
    }


    /**
     * Controla que la posicision en la que se va colocar el arbitro sea posible
     * @param x posible cordenada x
     * @param y posible cordenada y
     * @param control indica si la casilla es para el arbritro o la zona de vision
     * @return la casilla es apta para colocar el arbitro o no
     */
    private boolean controlPosicion(int x, int y, boolean control){

        boolean colocable = false;

                if (x>=0 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO &&
                        y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){
                    if (control==true){
                        if(x>=2 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO-2 &&
                                y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO-2 && y>=2){
                            if (ComponentesJuego.getComponentes().getCampo().getCasilla(y,x).sinJugador()==true
                                    && ComponentesJuego.getComponentes().getCampo().getCasilla(y,x).hayPelota()==false){
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
        ComponentesJuego.getComponentes().getCampo().eliminarElemento(posicionY, posicionX);

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
        return posicionX;
    }

    @Override
    public int getPosicionY() {
        return posicionY;
    }

    @Override
    public boolean procesar() {
        if(tiempo == TIEMPO_PITADA){
            Gdx.audio.newMusic(Gdx.files.internal("sonido/acciones/quejas.mp3")).play();
            this.tiempo =0;
            return true;
        }
        tiempo++;
        return false;
    }
}
