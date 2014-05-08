package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Created by Victor on 24/04/14.
 */
public class BotonSuplente extends Boton {


    private final static String NIVEL_1 = "Menu/Habilidades/nivel1.png";
    private final static String NIVEL_2 = "Menu/Habilidades/nivel2.png";
    private final static String NIVEL_3 = "Menu/Habilidades/nivel3.png";
    private final static String FUERZA = "Menu/Habilidades/fuerza.png";

    private Jugador jugador;

    ArrayList<ElementoDibujable> habilidades;
    ArrayList<ElementoDibujable> capacidadHabilidades;
    ArrayList <ElementoDibujable> dibujoJugador;

    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     */
    public BotonSuplente(float posX, float posY, Entrada entrada, String textura,Jugador jugador) {


        super(posX, posY, entrada, textura,0,ConstantesJuego.LARGO_TABLON_SUSITUCION,ConstantesJuego.ANCHO_TABLON_SUSTITUCION);
        this.jugador = jugador;
        this.habilidades = new ArrayList<ElementoDibujable>();
        this.capacidadHabilidades = new ArrayList<ElementoDibujable>();
        this.dibujoJugador = new ArrayList<ElementoDibujable>();
        this.mostrarHabilidades();

    }

    @Override
    public void accionEntrada(Entrada entrada) {
        System.out.println("INTERCAMBIO De JUGADOres");
        //obteniendo la instansacion de equipo y realizar cambio en la lista de jugadores
        ComponentesJuego.getComponentes().getEquipo1().intercambioJugadores(this.jugador);
        ComponentesJuego.getComponentes().getEquipo2().intercambioJugadores(this.jugador);
    }

    private void mostrarHabilidades(){

        this.habilidades = new ArrayList<ElementoDibujable>();
        this.capacidadHabilidades = new ArrayList<ElementoDibujable>();
        this.dibujoJugador = new ArrayList<ElementoDibujable>();

        this.capacidadHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,NIVEL_1));
        this.capacidadHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,NIVEL_3));
        this.capacidadHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,NIVEL_2));
        this.capacidadHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,NIVEL_2));
        this.capacidadHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,NIVEL_1));

        int posicionX = this.getPosicionX()+ (int)(ConstantesJuego.LARGO_TABLON_SUSITUCION/3);
        int posicionY = this.getPosicionY() + 5;

        dibujoJugador = jugador.getTexturasMuestreo();
        for(ElementoDibujable dibujo : this.dibujoJugador) {
            dibujo.dibujar(this.getPosicionX() + ConstantesJuego.LARGO_TABLON_SUSITUCION / 9, posicionY);
        }


        for(ElementoDibujable elemento : this.capacidadHabilidades) {
            elemento.dibujar(posicionX, posicionY);
            posicionX = posicionX + ConstantesJuego.LARGO_TABLON_SUSITUCION/8;
        }

        for(int i =0 ;i<5 ;i++){
            this.habilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,FUERZA));

        }
        posicionX = this.getPosicionX()+ 50;
        posicionY = this.getPosicionY() + 5;

        /* for(ElementoDibujable elemento : this.habilidades) {
            elemento.dibujar(posicionX, posicionY);
            posicionX = posicionX + 100;
        }*/

    }

    @Override
    public void borrar(){
        super.borrar();
        for(ElementoDibujable elemento : this.capacidadHabilidades){
            elemento.borrar();
        }
        for(ElementoDibujable elemento : this.dibujoJugador){
            elemento.borrar();
        }


    }
}