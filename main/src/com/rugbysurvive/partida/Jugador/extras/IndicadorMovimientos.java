package com.rugbysurvive.partida.Jugador.extras;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;

import java.util.ArrayList;

/**
 * Created by aitor on 16/04/14.
 *
 * Gestiona el proceso de dibujado del camino que debe realizar el jugador.
 * Esta clase no altera ningun elemento del juego unicamente es un apoyo visual.
 * El camino va desde la posicion inicial del jugador hasta la posicion final
 * siguiendo casilla por casilla el camino que ara el jugador
 *
 */
public class IndicadorMovimientos {

    private static final String CRUZ = "indicadoresMovimiento/cruzDiagonal.png";
    private static final String ABAJO_DERECHA ="indicadoresMovimiento/curvaAbajoDerecha.png";
    private static final String ARRIBA_DERECHA ="indicadoresMovimiento/curvaArribaDerecha.png";
    private static final String IZQUIERDA_ABAJO ="indicadoresMovimiento/curvaIzquierdaAbajo.png";
    private static final String IZQUIERDA_ARRIBA ="indicadoresMovimiento/curvaIzquierdaArriba.png";
    private static final String HORIZONTAL ="indicadoresMovimiento/rectaHorizontal.png";
    private static final String VERTICAL ="indicadoresMovimiento/rectaVertical.png";

    private ArrayList<ElementoDibujable> recorridoGrafico;
    private ArrayList<PosicionTrozoCamino> direccionCamino;
    private int camino[][];
    private Jugador jugador;
    private DireccionJugador direccion;
    private int longitud;

    /**
     * Genera todos los elementos necesarios para dibujar el camino
     * @param jugador Jugador que realiza el movimiento
     * @param camino Recorrido que realiza el jugador
     * @param longitud Longitud del camino
     */
    public IndicadorMovimientos(Jugador jugador,int camino[][],int longitud)
    {
        this.direccionCamino = new ArrayList<PosicionTrozoCamino>();
        this.jugador = jugador;
        this.camino = camino;
        this.direccion = this.jugador.getDireccion();
        this.longitud = longitud;
        this.recorridoGrafico = new ArrayList<ElementoDibujable>();
        GestorIndicadorMovimientos.getInstancia().añadirIndicadorMovimientos(this);
    }

    /**
     * Elimina el dibujo realizado por el camino y reinicia
     * el camino recorrido
     */
    public void borrar(){
        for(ElementoDibujable dibujable : this.recorridoGrafico){
            dibujable.borrar();
        }
        this.recorridoGrafico = new ArrayList<ElementoDibujable>();
        this.direccionCamino = new ArrayList<PosicionTrozoCamino>();
    }

    /**
     * Genera el proceso de dibujado del camino.
     * Dibuja dentro del recorrido que realizara el jugador
     * una ruta desde la posicion inicial a la posicion final .
     * El dibujo se mantiene hasta que se realice el borrado
     *
     */
    public void procesar(){

       this.generarPrimerElemeno();


       for(int i=2; i<camino.length; i++) {
           if(longitud > i) {
               if(longitud -1 > i) {
                    DireccionJugador entrada = this.generarEntrada();
                    DireccionJugador salida = this.generarSalida(i);
                    this.direccionCamino.add(new PosicionTrozoCamino(entrada,salida));
               }
               else {
                  this.direccionCamino.add(new PosicionTrozoCamino(DireccionJugador.derecha,DireccionJugador.derecha));
               }

           }

       }

        this.generarCamino();
    }

    /**
     * Una vez se ha analizado el camino que se debe dibujar
     * eso es definir su estructura la funcion realiza el
     * proceso de dibujado
     */
    private void generarCamino() {
        ElementoDibujable elementoDibujable;

        for(int i=0; i<this.direccionCamino.size(); i++) {

            if(this.direccionCamino.size()-1 > i) {

                if(this.esHorizontal(i)) {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,HORIZONTAL);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }

                else if(this.esVertical(i)) {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,VERTICAL);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }

                else if(this.esDesvioIzquierdaAbajo(i)) {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,IZQUIERDA_ABAJO);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }

                else if(this.esDesvioIzquierdaArriba(i)) {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,IZQUIERDA_ARRIBA);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }

                else if(this.esDesvioDerechaAbajo(i)) {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,ABAJO_DERECHA);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }

                else if(this.esDesvioDerechaArriba(i)) {
                     elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,ARRIBA_DERECHA);
                     elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                     this.recorridoGrafico.add(elementoDibujable);
                }
            }

            else if(this.direccionCamino.size()-1 == i) {
                elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,CRUZ);
                elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                this.recorridoGrafico.add(elementoDibujable);
            }
        }


    }

    /**
     * Indica si la pieza a dibujar es horizontal
     * @param i Posicion del camino que se debe analizar
     * @return cierto si es horizontal falso en caso contrario
     */
    private boolean esHorizontal(int i) {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.izquierda
                && this.direccionCamino.get(i).salida == DireccionJugador.derecha)
                || (this.direccionCamino.get(i).salida == DireccionJugador.izquierda
                && this.direccionCamino.get(i).entrada== DireccionJugador.derecha)) {
            return true;
        }
        return false;
    }

    /**
     * Indica si la pieza a dibujar es vertical
     * @param i Posicion del camino que se debe analizar
     * @return cierto si es vertical falso en caso contrario
     */
    private boolean esVertical(int i) {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.abajo
                && this.direccionCamino.get(i).salida== DireccionJugador.arriba)
                || (this.direccionCamino.get(i).salida == DireccionJugador.abajo
                && this.direccionCamino.get(i).entrada== DireccionJugador.arriba))
        {
            return true;
        }
        return false;
    }

    /**
     * Indica si es una bifurcacion llegando desde la izquierda y hacia abajo
     * Tambien puede ser el recorrido inverso , llegando desde abajo y girando
     * a la izquierda
     * @param i Posicion del camino que se debe analizar
     * @return cierto si es es la bifurcacion indicada falso en caso contrario
     */
    private boolean esDesvioIzquierdaAbajo(int i) {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.izquierda
                && this.direccionCamino.get(i).salida == DireccionJugador.abajo)
            ||(this.direccionCamino.get(i).entrada== DireccionJugador.abajo
                && this.direccionCamino.get(i).salida == DireccionJugador.izquierda))

        {
            return true;
        }
        return false;
    }


    /**
     * Indica si es una bifurcacion llegando desde la izquierda y hacia arriba
     * Tambien puede ser el recorrido inverso , llegando desde arriba y girando
     * a la izquierda
     * @param i Posicion del camino que se debe analizar
     * @return cierto si es es la bifurcacion indicada falso en caso contrario
     */
    private boolean esDesvioIzquierdaArriba(int i) {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.izquierda
                && this.direccionCamino.get(i).salida == DireccionJugador.arriba)
        ||(this.direccionCamino.get(i).entrada== DireccionJugador.arriba
            && this.direccionCamino.get(i).salida == DireccionJugador.izquierda))

        {
            return true;
        }
        return false;
    }

    /**
     * Indica si es una bifurcacion llegando desde la derecha y hacia arriba
     * Tambien puede ser el recorrido inverso , llegando desde la derecha y girando
     * hacia arriba
     * @param i Posicion del camino que se debe analizar
     * @return cierto si es es la bifurcacion indicada falso en caso contrario
     */
    private boolean esDesvioDerechaArriba(int i) {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.derecha
                && this.direccionCamino.get(i).salida == DireccionJugador.arriba)
        ||(this.direccionCamino.get(i).entrada== DireccionJugador.arriba
            && this.direccionCamino.get(i).salida == DireccionJugador.derecha))

        {
            return true;
        }
        return false;
    }


    /**
     * Indica si es una bifurcacion llegando desde la derecha y hacia abajo
     * Tambien puede ser el recorrido inverso , llegando desde abajo y girando
     * a la derecha
     * @param i Posicion del camino que se debe analizar
     * @return cierto si es es la bifurcacion indicada falso en caso contrario
     */
    private boolean esDesvioDerechaAbajo(int i) {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.derecha
                && this.direccionCamino.get(i).salida == DireccionJugador.abajo)
        ||(this.direccionCamino.get(i).entrada== DireccionJugador.abajo
            && this.direccionCamino.get(i).salida == DireccionJugador.derecha))

        {
            return true;
        }

        return false;
    }


    /**
     * Genera la primera posicion de la casilla generandose
     * de forma diferente al resto de casillas ya que no
     * tiene ninguna casilla anterior para generar la entrada
     */
    private void generarPrimerElemeno() {

       DireccionJugador salida = null;
       DireccionJugador entrada = null;


       // Generacion del valor de entrada
       if(camino[0][0] == camino[1][0]-1){
           entrada = DireccionJugador.izquierda;
       }

       else if(camino[0][0] == camino[1][0]+1) {
            entrada = DireccionJugador.derecha;
       }

       else if(camino[0][1] == camino[1][1]-1) {
            entrada = DireccionJugador.abajo;
       }

       else if(camino[0][1] == camino[1][1]+1) {
            entrada = DireccionJugador.arriba;
       }

       // Generacion del valor de salida
       if(camino[1][0] == camino[2][0]-1) {
            salida = DireccionJugador.derecha;
       }

       else if(camino[1][0] == camino[2][0]+1) {
            salida = DireccionJugador.izquierda;
       }

       if(camino[1][1] == camino[2][1]-1) {
            salida = DireccionJugador.arriba;
       }

       if(camino[1][1] == camino[2][1]+1) {
            salida = DireccionJugador.abajo;
       }

        System.out.println(entrada+"'"+salida);
        this.direccionCamino.add(new PosicionTrozoCamino(entrada,salida));

    }

    /**
     * Calcula el valor de entrada a partir de la ultima parte
     * del recorrido generada hasta el momento
     * El valor es la dirreccion a la que apuntara
     * la entrada de la seccion del movimiento
     *
     * @return Direccion a la que apunta la entrada de laseccion
     * del movimiento
     */
    private DireccionJugador generarEntrada() {

        DireccionJugador entrada = null;

        switch (this.direccionCamino.get(this.direccionCamino.size()-1).salida) {

            case derecha:
                entrada = DireccionJugador.izquierda;
                break;
            case izquierda:
                entrada = DireccionJugador.derecha;
                break;
            case arriba:
                entrada = DireccionJugador.abajo;
                break;
            case abajo:
                entrada = DireccionJugador.arriba;
                break;
        }
        return entrada;
    }


    /**
     * Calcula el valor de salida a partir de la ultima parte
     * del recorrido generada hasta el momento
     * El valor es la direccion a la que apuntara la salida
     * de la seccion del movimiento
     *
     * @return Direccion a la que apunta la salida de laseccion
     * del movimiento
     */
    private DireccionJugador generarSalida(int i) {

        DireccionJugador salida = null;

        if(camino[i][0] == camino[i+1][0] - 1) {
            salida = DireccionJugador.derecha;
        }
        else if(camino[i][0] == camino[i+1][0] + 1) {
            salida = DireccionJugador.izquierda;
        }

        else if(camino[i][1] == camino[i+1][1] - 1) {
            salida = DireccionJugador.arriba;
        }
        else if(camino[i][1] == camino[i+1][1] + 1) {
            salida = DireccionJugador.abajo;
        }

        return salida;
    }

    /**
     * Clase que gestiona una porcion del movimiento
     */
    private class PosicionTrozoCamino
    {

        public DireccionJugador entrada;
        public DireccionJugador salida;
        /**
         * Genera una porcion del camino
         * @param entrada direccion a la que apunta la entrada
         * @param salida direccion a la que apunta la salida
         */
        public PosicionTrozoCamino(DireccionJugador entrada , DireccionJugador salida){
            this.entrada = entrada;
            this.salida = salida;
        }

    }
}
