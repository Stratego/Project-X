package com.rugbysurvive.partida;


import com.badlogic.gdx.Gdx;

/**
 * Created by aitor on 2/04/14.
 */
public class ConstantesJuego {

    protected double multiplicado = 1;
    private static int multiplicador = 1;
    //private static double multiplicadorBoton = 0.17 * Gdx.graphics.getHeight());
    public static final int ANCHO_CASILLA = 64;
    public static final int ALTO_CASILLA = 64;
    //public static final int ANCHO_BOTON = 128;
    public static final int ALTO_BOTON = 128;
    public static final int ANCHO_BOTON = (int)(0.17 * Gdx.graphics.getHeight());

    public static  double separacionBotones = (ANCHO_BOTON+78);
    public static  int POSICION_BOTON_FINALIZAR = (int)(Gdx.graphics.getWidth() -separacionBotones);

    public static  int POSICION_BOTON_SUPLENTE = (int)(Gdx.graphics.getWidth() - (separacionBotones*2));
    public static  int POSICION_BOTON_OBJETOS = (int)(Gdx.graphics.getWidth() - (separacionBotones*3));
    public static  int POSICION_BOTON_CHUTEPASE = (int)(Gdx.graphics.getWidth() - (separacionBotones*4));

    //public static final int POSICION_BOTON_SUPLENTE = POSICION_BOTON_FINALIZAR-separacionBotones;
    //public static final int POSICION_BOTON_OBJETOS = POSICION_BOTON_SUPLENTE - separacionBotones;
    //public static final int POSICION_BOTON_CHUTEPASE = POSICION_BOTON_OBJETOS - separacionBotones;

    public static final int ALTO_BOTON_OBJETOS = 64;
    public static final int ANCHO_BOTON_OBJETOS = 64;
    public static final int ALTO_BOTON_SUPLENTES = 64;
    public static final int ANCHO_BOTON_SUPLENTES = 768;
    public static  int POSICION_INICIAL_Y_BOTON_SUPLENTES=(ALTO_BOTON * multiplicador)+2;
    //public static final int POSICION_INICIAL_Y_BOTON_OBJETOS=Gdx.graphics.getHeight()-80;
   // public static final int POSICION_INICIAL_X_BOTON_OBJETOS=Gdx.graphics.getWidth()-230;
    public static  int POSICION_INICIAL_Y_BOTON_OBJETOS=((ALTO_BOTON * multiplicador)+2)+(ALTO_BOTON_OBJETOS*multiplicador *2)+((ALTO_BOTON_OBJETOS*multiplicador)/2);
    //public static final int POSICION_INICIAL_X_BOTON_OBJETOS=Gdx.graphics.getWidth() - 798;
    public static final int POSICION_INICIAL_X_BOTON_OBJETOS=POSICION_BOTON_CHUTEPASE +32;
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
    private static ResolucionPantalla resolucionPantalla = ResolucionPantalla.pequeña;




    public ConstantesJuego()
    {
        // la inicializacion no funciona correctamente
        //resolucionPantalla = calcularResolucion();
        //System.out.println(resolucionPantalla);

       // multiplicador =  multiplicador();
       // System.out.println(multiplicador);

        constantes = this;

    }
    public static ConstantesJuego variables(){return constantes;}
    public void setResolucionPantalla(ResolucionPantalla resolucionPantalla){this.resolucionPantalla = resolucionPantalla;}
    public double getAnchoCasilla(){return ANCHO_CASILLA * this.multiplicado;}
    public double getLargoCasilla(){return (double)(ALTO_CASILLA* this.multiplicado);}

    public static int getAltoBotonObjetos() {
        return generarTamaño(ALTO_BOTON_OBJETOS);
    }

    public static  int getAnchoBotonObjetos() {
        return generarTamaño(ANCHO_BOTON_OBJETOS);
    }

    public static int getAltoBotonSuplentes() {
        return generarTamaño(ALTO_BOTON_SUPLENTES);
    }

    public static int getAnchoBotonSuplentes() {
        return generarTamaño(ANCHO_BOTON_SUPLENTES);
    }

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
    public int getMultiplicado(){return this.multiplicador;}
    public int getAnchoBoton(){return generarTamaño(ANCHO_BOTON);}
    public int getAltoBoton(){return generarTamaño(ALTO_BOTON);}
    public double getMultiplicador(){return this.multiplicado;}
    public int getAnchoTablero(){return ANCHO_TABLERO;}
    public int getAltoTablero(){return ALTO_TABLERO;}
    public static ResolucionPantalla getResolucionPantalla() {
        return resolucionPantalla;
    }

    public static void setMultiplicador(int multiplicador) {
        ConstantesJuego.multiplicador = multiplicador;
    }

    public static int generarTamaño(int tamaño)
    {

        switch(resolucionPantalla)
        {

           case pequeña:
                //System.out.println("cambiando a base");
                return tamaño * TAMAÑO_BASE;
           case mediana:
               //System.out.println("cambiando a mediano");
               return tamaño * TAMAÑO_MEDIANO;
            default:
               //System.out.println("cambiando a grande");
                return tamaño * TAMAÑO_GRANDE;

        }

    }



    public int multiplicador ()
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




    public ResolucionPantalla calcularResolucion(){

        //System.out.println(Gdx.graphics.getHeight());
        //System.out.println(Gdx.graphics.getWidth());
        /*if (Gdx.graphics.getHeight()<=720&&Gdx.graphics.getWidth()<=1280){
            resolucionPantalla= ResolucionPantalla.pequeña;
            multiplicador=1;
        }else{
            resolucionPantalla= ResolucionPantalla.mediana;
            multiplicador=2;
        }*/

        return resolucionPantalla;
    }




}
