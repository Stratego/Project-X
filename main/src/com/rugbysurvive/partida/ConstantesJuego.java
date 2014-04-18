package com.rugbysurvive.partida;


import com.badlogic.gdx.Gdx;

/**
 * Created by aitor on 2/04/14.
 */
public class ConstantesJuego {


    public static final int ANCHO_CASILLA = 64;
    public static final int ALTO_CASILLA = 64;
    public static final int ANCHO_BOTON = 128;
    public static final int ALTO_BOTON = 128;
    public static final int POSICION_BOTON_CHUTEPASE = Gdx.graphics.getWidth() - 830;
    public static final int POSICION_BOTON_OBJETOS = Gdx.graphics.getWidth() - 630;
    public static final int POSICION_BOTON_SUPLENTE = Gdx.graphics.getWidth() - 430;
    public static final int POSICION_BOTON_FINALIZAR = Gdx.graphics.getWidth() - 230;
    public static final int ALTO_BOTON_OBJETOS = 64;
    public static final int ANCHO_BOTON_OBJETOS = 64;
    public static final int ALTO_BOTON_SUPLENTES = 64;
    public static final int ANCHO_BOTON_SUPLENTES = 768;
    public static final int POSICION_INICIAL_Y_BOTON_SUPLENTES=130;
    public static final int POSICION_INICIAL_Y_BOTON_OBJETOS=Gdx.graphics.getHeight()-80;
    public static final int POSICION_INICIAL_X_BOTON_OBJETOS=Gdx.graphics.getWidth()-230;
    public static final int POSICION_INICIAL_X_PLANTILLAOBJETOS=POSICION_INICIAL_X_BOTON_OBJETOS-32;
    public static final int POSICION_INICIAL_Y_PLANTILLAOBJETOS=Gdx.graphics.getHeight()-256;


    public static final int JUGADORES_CAMPO = 7;
    public static final int ID_BOTON = 1000;
    public static final int NUMERO_CASILLAS_ANCHO_TABLERO = 20;
    public static final int LIMITE_CASILLAS_ANCHO_TABLERO = 19;
    public static final int NUMERO_CASILLAS_LARGO_TABLERO = 30;
    public static final int LIMITE_CASILLAS_LARGO_TABLERO = 29;
    protected  static final int ANCHO_TABLERO = ANCHO_CASILLA*NUMERO_CASILLAS_ANCHO_TABLERO;
    protected  static final int ALTO_TABLERO = ALTO_CASILLA*NUMERO_CASILLAS_LARGO_TABLERO;
    protected  static final int TAMAÑO_BASE = 1;
    protected  static final int TAMAÑO_MEDIANO = 2;
    protected  static final int TAMAÑO_GRANDE = 3;
    protected static final double  MAX_MULTIPLICADOR =2.0;
    protected static final double MIN_MULTIPLICADOR = 0.75;
    protected static ConstantesJuego constantes ;
    protected ResolucionPantalla resolucionPantalla;
    protected double multiplicado = 1;



    public ConstantesJuego()
    {
        this.resolucionPantalla = ResolucionPantalla.pequeña;
        constantes = this;
    }
    public static ConstantesJuego variables(){return constantes;}
    public void setResolucionPantalla(ResolucionPantalla resolucionPantalla){this.resolucionPantalla = resolucionPantalla;}
    public double getAnchoCasilla(){return ANCHO_CASILLA * this.multiplicado;}
    public double getLargoCasilla(){return (double)(ALTO_CASILLA* this.multiplicado);}
    public void sumarMultiplicado(){
        if(this.multiplicado < MAX_MULTIPLICADOR){
            this.multiplicado = this.multiplicado + 0.002;
        }
    }
    public void restarMultiplicado(){
        if(this.multiplicado > MIN_MULTIPLICADOR){
            this.multiplicado = this.multiplicado - 0.002;
        }
    }
    public double getMultiplicado(){return this.multiplicado;}
    public int getAnchoBoton(){return generarTamaño(ANCHO_BOTON);}
    public int getAltoBoton(){return generarTamaño(ALTO_BOTON);}
    public double getMultiplicador(){return this.multiplicado;}
    public int getAnchoTablero(){return ANCHO_TABLERO;}
    public int getAltoTablero(){return ALTO_TABLERO;}

    protected int generarTamaño(int tamaño)
    {
        switch(this.resolucionPantalla)
        {
           case pequeña:
            return tamaño * TAMAÑO_BASE;
           case mediana:
               return tamaño * TAMAÑO_MEDIANO;
            default:
                return tamaño * TAMAÑO_GRANDE;

        }
    }

    protected int multiplicador ()
    {

        switch(this.resolucionPantalla)
        {
            case pequeña:
                return TAMAÑO_BASE;
            case mediana:
                return TAMAÑO_MEDIANO;
            default:
                return TAMAÑO_GRANDE;

        }
    }




}
