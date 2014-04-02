package com.rugbysurvive.partida;


/**
 * Created by aitor on 2/04/14.
 */
public class ConstantesJuego {


    protected static final int ANCHO_CASILLA = 64;
    protected static final int ALTO_CASILLA = 64;
    protected static final int ANCHO_BOTON = 128;
    protected static final int ALTO_BOTON = 128;
    protected  static final int NUMERO_CASILLAS_ANCHO_TABLERO = 20;
    protected  static final int NUMERO_CASILLAS_LARGO_TABLERO = 30;
    protected  static final int ANCHO_TABLERO = ANCHO_CASILLA*NUMERO_CASILLAS_ANCHO_TABLERO;
    protected  static final int ALTO_TABLERO = ALTO_CASILLA*NUMERO_CASILLAS_LARGO_TABLERO;
    protected  static final int TAMAÑO_BASE = 1;
    protected  static final int TAMAÑO_MEDIANO = 2;
    protected  static final int TAMAÑO_GRANDE = 3;
    protected static ConstantesJuego constantes = new ConstantesJuego();
    protected ResolucionPantalla resolucionPantalla;
    public ConstantesJuego()
    {
        resolucionPantalla = ResolucionPantalla.pequeña;;
    }
    public static ConstantesJuego variables(){return constantes;}
    public void setResolucionPantalla(ResolucionPantalla resolucionPantalla){this.resolucionPantalla = resolucionPantalla;};
    public int getAnchoCasilla(){return generarTamaño(ANCHO_CASILLA);}
    public int getLargoCasilla(){return generarTamaño(ALTO_CASILLA);}

    public int getAnchoBoton(){return generarTamaño(ANCHO_BOTON);}
    public int getAltoBoton(){return generarTamaño(ALTO_BOTON);}
    public int getMultiplicador(){return this.multiplicador();}

    protected int generarTamaño(int tamaño)
    {
        switch(resolucionPantalla)
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

        switch(resolucionPantalla)
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
