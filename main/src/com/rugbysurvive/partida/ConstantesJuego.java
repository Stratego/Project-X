package com.rugbysurvive.partida;


import com.badlogic.gdx.Gdx;

/**
 * Created by aitor on 2/04/14.
 * Gestiona todas las constantes utilizadas en el juego que no son
 * exclusivas de una clase en concreto y que pueden ser usadas
 * en diferentes partes del codigo generado.
 * Ademas ofrece algunas funciones auxiliares para gestionar
 * estas constantes.
 *
 */
public class ConstantesJuego {
    /*
    * No se sigue el orden estipulado por el estilo de codigo para tener las constantes
    * agrupadas por tematica. Ademas hay ciertas variables constantes que dependen de otras
    * y deben seguir un orden concreto
    * */

    // Constantes de zoom y rescalado segun la resolucion
    private static double multiplicado = 1;
    public static double constanteRescalado = 0.1777777778* getWidth()/128;
    private static final double  MAX_MULTIPLICADOR =2.0;
    private static final double MIN_MULTIPLICADOR = 0.75;


    // Botones de la interfaz de usuario

    private static final int ANCHO_BOTON_REAL = 128;
    public static  double separacionBotones = (ANCHO_BOTON_REAL+78)*constanteRescalado;
    public static final int ANCHO_CASILLA = 64;
    public static final int ALTO_CASILLA = 64;
    public static final int ALTO_BOTON = (int)(128 *constanteRescalado);
    public static final int ANCHO_BOTON = (int)(128 *constanteRescalado);
    private static final int ALTO_BOTON_REAL = 128;
    public static  int POSICION_BOTON_FINALIZAR = (int)(getHeight() -separacionBotones);
    public static final int POSICION_BOTON_SUPLENTE = (int)(getHeight() - (separacionBotones*2));
    public static final int POSICION_BOTON_OBJETOS = (int)(getHeight() - (separacionBotones*3));
    public static final int POSICION_BOTON_CHUTEPASE = (int)(getHeight() - (separacionBotones*4));
    public static final int POSICION_BOTON_ESCONDIDO = -(int)(160 *constanteRescalado);
    public static final int ALTO_BOTON_OBJETOS = (int)(64 *constanteRescalado);
    public static final int ANCHO_BOTON_OBJETOS = (int)(64*constanteRescalado);
    public static  int POSICION_INICIAL_Y_BOTON_SUPLENTES=(int)((ALTO_BOTON_REAL * constanteRescalado)+2);
    public static  int POSICION_INICIAL_Y_BOTON_OBJETOS=(int)(((ALTO_BOTON_REAL * constanteRescalado)+2)+(ALTO_BOTON_OBJETOS*constanteRescalado *2)+((ALTO_BOTON_OBJETOS*constanteRescalado)/2));
    public static final int POSICION_INICIAL_X_BOTON_OBJETOS=POSICION_BOTON_CHUTEPASE +32;


    // Animacion cambio de turno
    public static final int POS_BANDERA_CAMBIO_TURNO_X = (int)(520*constanteRescalado);
    public static final int POS_BANDERA_CAMBIO_TURNO_Y = (int)(226*constanteRescalado);



    // Variables sobre el tablero y elementos del tablero
    public static final int JUGADORES_CAMPO = 8;
    public static final int ID_BOTON = 1000;
    public static final int NUMERO_CASILLAS_ANCHO_TABLERO = 20;
    public static final int LIMITE_CASILLAS_ANCHO_TABLERO = 19;
    public static final int NUMERO_CASILLAS_LARGO_TABLERO = 30;
    public static final int LIMITE_CASILLAS_LARGO_TABLERO = 29;
    public static final int FUERA_CAMPO_ABAJO = 1;
    public static final int FUERA_CAMPO_ARRIBA = 28;


    //Gestionan la posicion de todos los elementos del marcador
    public static final int POSICION_X_MARCADOR = (int)((Gdx.graphics.getWidth()/2) -64*constanteRescalado);
    public static final int POSICION_Y_MARCADOR = (int)((Gdx.graphics.getHeight())-80*constanteRescalado);
    public static final int POSICION_X_PUNTUACION_EQUIPO1 = (int)((Gdx.graphics.getWidth()/2)-50*constanteRescalado);
    public static final int POSICION_X_PUNTUACION_EQUIPO2 = (int)((Gdx.graphics.getWidth()/2)+20*constanteRescalado);
    public static final int POSICION_Y_PUNTUACION = (int)((Gdx.graphics.getHeight()-20*constanteRescalado));
    public static final int POSICION_X_ESCUDO_EQUIPO1 = (int)((Gdx.graphics.getWidth()/2 -140));
    public static final int POSICION_X_ESCUDO_EQUIPO2 = (int)((Gdx.graphics.getWidth()/2 +70));
    public static final int POSICION_Y_ESCUDO = (int)((Gdx.graphics.getHeight()-70));

    //Gestionan el limite donde se debe efectuar el saque de banda
    public static final int POSICION_SAQUE_BANDA_INFERIOR = 1;
    public static final int POSICION_SAQUE_BANDA_SUPERIOR = 18;

   // Gestionan los menus de seleccion de objetos y jugadores
    public static final int DISTANCIA_OBJETO_PRIMERA_LINIA = (int)(43*constanteRescalado);
    public static final int DISTANCIA_OBJETO_SEGUNDA_LINIA = (int)(43*2*constanteRescalado+ANCHO_BOTON_OBJETOS);
    public static final int ANCHO_TABLON_SUSTITUCION = (int) (96 * constanteRescalado);
    public static final int LARGO_TABLON_SUSITUCION = (int) (768 * constanteRescalado);
    protected  static final int ANCHO_TABLERO = ANCHO_CASILLA*NUMERO_CASILLAS_ANCHO_TABLERO;
    protected  static final int ALTO_TABLERO = ALTO_CASILLA*NUMERO_CASILLAS_LARGO_TABLERO;
    public static final int LARGO_TABLON_HABILIDADES = (int)(650*constanteRescalado);
    public static int ANCHO_TABLERO_CON_ZOOM =  (int)(ANCHO_TABLERO*(1/multiplicado));
    public static int ALTO_TABLERO_CON_ZOOM =  (int)(ALTO_TABLERO*(1/multiplicado));
    public static final int POSICION_INICIAL_X_PLANTILLAOBJETOS=POSICION_INICIAL_X_BOTON_OBJETOS-32;
    public static final int POSICION_INICIAL_Y_PLANTILLAOBJETOS=Gdx.graphics.getHeight()-256;

    // SIGLETON
    protected static ConstantesJuego constantes ;


    private static ResolucionPantalla resolucionPantalla = ResolucionPantalla.pequeña;

    public static final int TAMAÑO_PUÑO = (int)(512* constanteRescalado);


    public static boolean RIVAL_IA = true;


    /**
     * Inicia la instanciacion  para las funciones que no sean estaticas
     * y permite el uso del singleton
     */
    public ConstantesJuego()
    {
        constantes = this;
    }

    /**
     * Singleton de la clase.
     * Retorna la instancia de forma estatica
     * @return instancia de la clase.
     */
    public static ConstantesJuego variables(){
        return constantes;
    }

    /**
     * Ancho de una casilla del tablero teniendo en cuenta
     * el factor multiplicador
     * @return ancho casilla con resolucion
     */
    public double getAnchoCasilla(){
        return ANCHO_CASILLA * this.multiplicado;
    }

    /**
     * Largo de una casilla del tablero teniendo en cuenta
     * el factor multiplicador
     * @return largo casilla con resolucion
     */
    public double getLargoCasilla(){
        return ALTO_CASILLA* this.multiplicado;
    }


    /**
     * Aumenta el zoom del juego .
     * Cada vez que se realiza
     * una llamada a la funcion todos los elementos del juego
     * se ven mas cerca.
     * Los elementos de la interfaz del usuario no se ven afectadas.
     */
    public void sumarMultiplicado(){
        if(this.multiplicado < MAX_MULTIPLICADOR) {
            this.multiplicado = this.multiplicado + 0.002;
        }
    }

    /**
     * Aumenta el zoom del juego .
     * Cada vez que se realiza
     * una llamada a la funcion todos los elementos del juego
     * se ven mas cerca.
     * Los elementos de la interfaz del usuario no se ven afectadas.
     */
    public void restarMultiplicado(){
        if(this.multiplicado > MIN_MULTIPLICADOR) {
            this.multiplicado = this.multiplicado - 0.002;
        }
    }

    /**
     * recalcula el nuevo tamaño que debe contener el elemento
     * teniendo en cuenta el reescalado que se debe hacer por resolucion.
     *
     * @param tamaño Antes de realizar el reescalado
     * @return tamaño nuevo una vez realizado el reescalado
     */
    public static int generarTamaño(int tamaño)
    {
        return (int)(tamaño * constanteRescalado);

    }

    /**
     * Indica si se esta utilizando la opcion de inteligencia
     * artificial
     * @param ia Indicador de uso de inteligencia artifical
     */
    public static void setRIVAL_IA (boolean ia){
        RIVAL_IA=ia;
    }

    /**
     *Rectificador de altura de la pantalla ya que libgdx devuelve
     *a veces el valor mas pequeño. Aqui se asegura que la altura
     *siempre haga referencia al lado del movil mayor
     * @return resolucion del lado de la pantalla mas larga
     */
    public static int getHeight()
    {
        if(Gdx.graphics.getHeight() > Gdx.graphics.getWidth()) {
            return Gdx.graphics.getHeight();
        }
        else {
            return Gdx.graphics.getWidth();
        }
    }

    /**
     *Rectificador de anchura de la pantalla ya que libgdx devuelve
     *a veces el valor mas grande. Aqui se asegura que la altura
     *siempre haga referencia al lado del dispositivo mas corto
     * @return resolucion del lado de la pantalla mas corta
     */
    public static int getWidth()
    {
        if(Gdx.graphics.getHeight() < Gdx.graphics.getWidth()) {
            return Gdx.graphics.getHeight();
        }
        else {
            return Gdx.graphics.getWidth();
        }
    }

    /**
     * Indica el nivel de resolucion solicitada por el usuario.
     * Todos los elementos del juego que no sean la interfaz deben usar
     * esta variable para reescalarse en caso necesario.
     * @return
     */
    public double getMultiplicador(){return this.multiplicado;}

    /**
     * Indica la anchura del boton
     * @return anchura del boton
     */
    public int getAnchoBoton(){return generarTamaño(ANCHO_BOTON);}

    /**
     *Indica la altura del boton
     * @return altura del boton
     */
    public int getAltoBoton(){return generarTamaño(ALTO_BOTON);}

    /**
     * ancho del tablero
     * @return indica el ancho del tablero
     */
    public int getAnchoTablero(){return ANCHO_TABLERO;}

    /**
     * alto del tablero
     * @return indica el alto del tablero
     */
    public int getAltoTablero(){return ALTO_TABLERO;}








}

