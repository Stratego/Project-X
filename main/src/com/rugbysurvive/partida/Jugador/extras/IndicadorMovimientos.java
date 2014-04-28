package com.rugbysurvive.partida.Jugador.extras;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;

import java.util.ArrayList;

/**
 * Created by aitor on 16/04/14.
 */
public class IndicadorMovimientos {

    private static final String CRUZ = "indicadoresMovimiento/cruzDiagonal.png";
    private static final String ABAJO_DERECHA ="indicadoresMovimiento/curvaAbajoDerecha.png";
    private static final String ARRIBA_DERECHA ="indicadoresMovimiento/curvaArribaDerecha.png";
    private static final String IZQUIERDA_ABAJO ="indicadoresMovimiento/curvaIzquierdaAbajo.png";
    private static final String IZQUIERDA_ARRIBA ="indicadoresMovimiento/curvaIzquierdaArriba.png";
    private static final String HORIZONTAL ="indicadoresMovimiento/rectaHorizontal.png";
    private static final String VERTICAL ="indicadoresMovimiento/rectaVertical.png";

    ArrayList<ElementoDibujable> recorridoGrafico;

    int camino[][];
    Jugador jugador;
    DireccionJugador direccion;
    ArrayList<PosicionTrozoCamino> direccionCamino;
    int longitud;
    public IndicadorMovimientos(Jugador jugador,int camino[][],int longitud)
    {
        System.out.println("LONGITUD:" + longitud);
        this.direccionCamino = new ArrayList<PosicionTrozoCamino>();
        this.jugador = jugador;
        this.camino = camino;
        this.direccion = this.jugador.getDireccion();
        this.longitud = longitud;
        this.recorridoGrafico = new ArrayList<ElementoDibujable>();
    }

    public void procesar(){

       this.generarPrimerElemeno();


       for(int i=2; i<camino.length; i++)
       {
           if(longitud > i )
           {
               if(longitud -1 > i)
               {
                    DireccionJugador entrada = this.generarEntrada();
                    DireccionJugador salida = this.generarSalida(i);
                    this.direccionCamino.add(new PosicionTrozoCamino(entrada,salida));
                   System.out.println("camino: "+entrada+","+salida);
               }
               else
               {
                  this.direccionCamino.add(new PosicionTrozoCamino(DireccionJugador.derecha,DireccionJugador.derecha));
               }

           }

       }

        this.generarCamino();
    }


    private void generarCamino()
    {
        ElementoDibujable elementoDibujable;

        for(int i=0; i<this.direccionCamino.size(); i++)
        {

            if(this.direccionCamino.size()-1 > i)
                {
                if(this.esHorizontal(i))
                {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,HORIZONTAL);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }
                else if(this.esVertical(i))
                {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,VERTICAL);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }
                else if(this.esDesvioIzquierdaAbajo(i))
                {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,IZQUIERDA_ABAJO);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }
                else if(this.esDesvioIzquierdaArriba(i))
                {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,IZQUIERDA_ARRIBA);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }
                else if(this.esDesvioDerechaAbajo(i))
                {
                    elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,ABAJO_DERECHA);
                    elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                    this.recorridoGrafico.add(elementoDibujable);
                }
                else if(this.esDesvioDerechaArriba(i))
                 {
                     elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,ARRIBA_DERECHA);
                     elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                     this.recorridoGrafico.add(elementoDibujable);
                 }
               }
            else if(this.direccionCamino.size()-1 == i)
            {
                elementoDibujable = new ElementoDibujable(TipoDibujo.fondo,CRUZ);
                elementoDibujable.dibujar(this.camino[i+1][0],this.camino[i+1][1]);
                this.recorridoGrafico.add(elementoDibujable);
            }
        }


    }

    private boolean esHorizontal(int i)
    {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.izquierda
                && this.direccionCamino.get(i).salida == DireccionJugador.derecha)
                || (this.direccionCamino.get(i).salida == DireccionJugador.izquierda
                && this.direccionCamino.get(i).entrada== DireccionJugador.derecha))
        {
            return true;
        }
        return false;
    }
    private boolean esVertical(int i)
    {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.abajo
                && this.direccionCamino.get(i).salida== DireccionJugador.arriba)
                || (this.direccionCamino.get(i).salida == DireccionJugador.abajo
                && this.direccionCamino.get(i).entrada== DireccionJugador.arriba))
        {
            return true;
        }
        return false;
    }

    private boolean esDesvioIzquierdaAbajo(int i)
    {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.izquierda
                && this.direccionCamino.get(i).salida == DireccionJugador.abajo)
            ||(this.direccionCamino.get(i).entrada== DireccionJugador.abajo
                && this.direccionCamino.get(i).salida == DireccionJugador.izquierda))

        {
            return true;
        }
        return false;
    }

    private boolean esDesvioIzquierdaArriba(int i)
    {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.izquierda
                && this.direccionCamino.get(i).salida == DireccionJugador.arriba)
        ||(this.direccionCamino.get(i).entrada== DireccionJugador.arriba
            && this.direccionCamino.get(i).salida == DireccionJugador.izquierda))

        {
            return true;
        }
        return false;
    }
    private boolean esDesvioDerechaArriba(int i)
    {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.derecha
                && this.direccionCamino.get(i).salida == DireccionJugador.arriba)
        ||(this.direccionCamino.get(i).entrada== DireccionJugador.arriba
            && this.direccionCamino.get(i).salida == DireccionJugador.derecha))

        {
            return true;
        }
        return false;
    }
    private boolean esDesvioDerechaAbajo(int i)
    {
        if((this.direccionCamino.get(i).entrada== DireccionJugador.derecha
                && this.direccionCamino.get(i).salida == DireccionJugador.abajo)
        ||(this.direccionCamino.get(i).entrada== DireccionJugador.abajo
            && this.direccionCamino.get(i).salida == DireccionJugador.derecha))

        {
            return true;
        }
        return false;
    }





    private void generarPrimerElemeno()
    {

        DireccionJugador salida = null;
        DireccionJugador entrada = null;

       if(camino[0][0] == camino[1][0]-1)
       {
           entrada = DireccionJugador.izquierda;
       }
        if(camino[0][0] == camino[1][0]+1)
        {
            entrada = DireccionJugador.derecha;
        }
        if(camino[0][1] == camino[1][1]-1)
        {
            entrada = DireccionJugador.abajo;
        }
        if(camino[0][1] == camino[1][1]+1)
        {
            entrada = DireccionJugador.arriba;
        }


        if(camino[1][0] == camino[2][0]-1)
        {
            salida = DireccionJugador.derecha;
        }
        if(camino[1][0] == camino[2][0]+1)
        {
            salida = DireccionJugador.izquierda;
        }
        if(camino[1][1] == camino[2][1]-1)
        {
            salida = DireccionJugador.arriba;
        }
        if(camino[1][1] == camino[2][1]+1)
        {
            salida = DireccionJugador.abajo;
        }

        System.out.println(entrada+"'"+salida);
        this.direccionCamino.add(new PosicionTrozoCamino(entrada,salida));

    }

    private DireccionJugador generarEntrada()
    {
        DireccionJugador entrada = null;


        switch (this.direccionCamino.get(this.direccionCamino.size()-1).salida)
        {

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
    private DireccionJugador generarSalida(int i)
    {
        DireccionJugador salida = null;

        if(camino[i][0] == camino[i+1][0] - 1){
            salida = DireccionJugador.derecha;
        }
        else if(camino[i][0] == camino[i+1][0] + 1){
            salida = DireccionJugador.izquierda;
        }

        else if(camino[i][1] == camino[i+1][1] - 1){
            salida = DireccionJugador.arriba;
        }
        else if(camino[i][1] == camino[i+1][1] + 1){
            salida = DireccionJugador.abajo;
        }
        return salida;
    }
    public class PosicionTrozoCamino
    {
        public PosicionTrozoCamino(DireccionJugador entrada , DireccionJugador salida){
            this.entrada = entrada;
            this.salida = salida;
        }
        DireccionJugador entrada;
        DireccionJugador salida;
    }
}
