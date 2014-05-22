package com.rugbysurvive.partida;


import com.badlogic.gdx.Gdx;

/**
 * Created by aitor on 2/04/14.
 */
public class ConstantesJuego {

    protected static double multiplicado = 1;
    //private static int multiplicador = 1;


    public static double constanteRescalado = 0.1777777778* getWidth()/128;

    public static final int ANCHO_CASILLA = 64;
    public static final int ALTO_CASILLA = 64;
    private static final int ANCHO_BOTON_REAL = 128;
    private static final int ALTO_BOTON_REAL = 128;

    public static final int POS_BANDERA_CAMBIO_TURNO_X = (int)(520*constanteRescalado);
    public static final int POS_BANDERA_CAMBIO_TURNO_Y = (int)(226*constanteRescalado);

    public static final int ALTO_BOTON = (int)(128 *constanteRescalado);
    public static final int ANCHO_BOTON = (int)(128 *constanteRescalado);

    public static  double separacionBotones = (ANCHO_BOTON_REAL+78)*constanteRescalado;


    public static  int POSICION_BOTON_FINALIZAR = (int)(getHeight() -separacionBotones);

    public static  int POSICION_BOTON_SUPLENTE = (int)(getHeight() - (separacionBotones*2));
    public static  int POSICION_BOTON_OBJETOS = (int)(getHeight() - (separacionBotones*3));
    public static  int POSICION_BOTON_CHUTEPASE = (int)(getHeight() - (separacionBotones*4));



    //public static final int POSICION_BOTON_SUPLENTE = POSICION_BOTON_FINALIZAR-separacionBotones;
    //public static final int POSICION_BOTON_OBJETOS = POSICION_BOTON_SUPLENTE - separacionBotones;
    //public static final int POSICION_BOTON_CHUTEPASE = POSICION_BOTON_OBJETOS - separacionBotones;

    public static final int ALTO_BOTON_OBJETOS = (int)(64 *constanteRescalado);
    public static final int ANCHO_BOTON_OBJETOS = (int)(64*constanteRescalado);

    public static  int POSICION_INICIAL_Y_BOTON_SUPLENTES=(int)((ALTO_BOTON_REAL * constanteRescalado)+2);

    public static  int POSICION_INICIAL_Y_BOTON_OBJETOS=(int)(((ALTO_BOTON_REAL * constanteRescalado)+2)+(ALTO_BOTON_OBJETOS*constanteRescalado *2)+((ALTO_BOTON_OBJETOS*constanteRescalado)/2));
    public static final int POSICION_INICIAL_X_BOTON_OBJETOS=POSICION_BOTON_CHUTEPASE +32;


    public static final int POSICION_INICIAL_X_PLANTILLAOBJETOS=POSICION_INICIAL_X_BOTON_OBJETOS-32;
    public static final int POSICION_INICIAL_Y_PLANTILLAOBJETOS=Gdx.graphics.getHeight()-256;


    public static final int JUGADORES_CAMPO = 8;
    public static final int ID_BOTON = 1000;
    public static final int NUMERO_CASILLAS_ANCHO_TABLERO = 20;
    public static final int LIMITE_CASILLAS_ANCHO_TABLERO = 19;
    public static final int NUMERO_CASILLAS_LARGO_TABLERO = 30;
    public static final int LIMITE_CASILLAS_LARGO_TABLERO = 29;

    public static final int FUERA_CAMPO_ABAJO = 1;
    public static final int FUERA_CAMPO_ARRIBA = 28;


    public static final int POSICION_X_MARCADOR = (int)((Gdx.graphics.getWidth()/2 -64) );
    public static final int POSICION_Y_MARCADOR = (int)((Gdx.graphics.getHeight()-64));
    public static final int POSICION_X_PUNTUACION_EQUIPO1 = (int)((Gdx.graphics.getWidth()/2 -60));
    public static final int POSICION_X_PUNTUACION_EQUIPO2 = (int)((Gdx.graphics.getWidth()/2+10));
    public static final int POSICION_Y_PUNTUACION = (int)((Gdx.graphics.getHeight()-20));

    public static final int POSICION_X_ESCUDO_EQUIPO1 = (int)((Gdx.graphics.getWidth()/2 -140));
    public static final int POSICION_X_ESCUDO_EQUIPO2 = (int)((Gdx.graphics.getWidth()/2 +70));
    public static final int POSICION_Y_ESCUDO = (int)((Gdx.graphics.getHeight()-70));

    public static final int POSICION_SAQUE_BANDA_INFERIOR = 1;
    public static final int POSICION_SAQUE_BANDA_SUPERIOR = 18;

    public static final int DISTANCIA_OBJETO_PRIMERA_LINIA = (int)(43*constanteRescalado);
    public static final int DISTANCIA_OBJETO_SEGUNDA_LINIA = (int)(43*2*constanteRescalado+ANCHO_BOTON_OBJETOS);

    public static final int ANCHO_TABLON_SUSTITUCION = (int) (96 * constanteRescalado);
    public static final int LARGO_TABLON_SUSITUCION = (int) (768 * constanteRescalado);
    protected  static final int ANCHO_TABLERO = ANCHO_CASILLA*NUMERO_CASILLAS_ANCHO_TABLERO;
    protected  static final int ALTO_TABLERO = ALTO_CASILLA*NUMERO_CASILLAS_LARGO_TABLERO;

    protected static final double  MAX_MULTIPLICADOR =2.0;
    protected static final double MIN_MULTIPLICADOR = 0.75;

    protected static ConstantesJuego constantes ;
    private static ResolucionPantalla resolucionPantalla = ResolucionPantalla.pequeña;

    public static final int POSICION_BOTON_ESCONDIDO = -(int)(160 *constanteRescalado);


    public static int ANCHO_TABLERO_CON_ZOOM =  (int)(ANCHO_TABLERO*(1/multiplicado));
    public static int ALTO_TABLERO_CON_ZOOM =  (int)(ALTO_TABLERO*(1/multiplicado));

    public static final int TAMAÑO_PUÑO = (int)(512* constanteRescalado);

    public static boolean RIVAL_IA = true;



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
    //public int getMultiplicado(){return this.multiplicador;}
    public int getAnchoBoton(){return generarTamaño(ANCHO_BOTON);}
    public int getAltoBoton(){return generarTamaño(ALTO_BOTON);}
    public double getMultiplicador(){return this.multiplicado;}
    public int getAnchoTablero(){return ANCHO_TABLERO;}
    public int getAltoTablero(){return ALTO_TABLERO;}
    public static ResolucionPantalla getResolucionPantalla() {
        return resolucionPantalla;
    }

    /*public static void setMultiplicador(int multiplicador) {
        ConstantesJuego.multiplicador = multiplicador;
    }*/

    public static int generarTamaño(int tamaño)
    {

        return (int)(tamaño * constanteRescalado);

    }
    public static void setRIVAL_IA (boolean ia){
        RIVAL_IA=ia;
    }
    public static int getHeight()
    {
        if(Gdx.graphics.getHeight() > Gdx.graphics.getWidth()) {
            return Gdx.graphics.getHeight();
        }
        else {
            return Gdx.graphics.getWidth();
        }
    }
    public static int getWidth()
    {
        if(Gdx.graphics.getHeight() < Gdx.graphics.getWidth()) {
            return Gdx.graphics.getHeight();
        }
        else {
            return Gdx.graphics.getWidth();
        }
    }









}

