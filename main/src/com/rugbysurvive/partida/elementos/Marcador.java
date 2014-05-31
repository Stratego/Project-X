package com.rugbysurvive.partida.elementos;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;

/**
 * Created by aitor on 18/04/14.
 * Contiene todos los procesos necesarios para generar la puntuacion
 * del partido . Ademas muestra el resultado por pantalla.
 */
public class Marcador implements Dibujable{

    private ElementoDibujable puntuacionGraficaEquipo1;
    private ElementoDibujable puntuacionGraficaEquipo2;
    private ElementoDibujable banderaEquipo1;
    private ElementoDibujable banderaEquipo2;

    private static int puntuacionEquipo1 = 0;
    private static int puntuacionEquipo2= 0;
    private  Equipo equipo1 ;
    private   Equipo equipo2;
    private String textura;

    private static Marcador marcador;

    /**
     *
     * @param equipo1 Equipo que se mostrara el resultado en la izquierda del marcador
     * @param equipo2 Equipo qeu se mostrara el resultado en la derecha del marcador
     */
    public Marcador(Equipo equipo1 ,Equipo equipo2) {

        GestorGrafico.generarDibujante().añadirDibujable(this,TipoDibujo.interficieUsuario);
        this.textura = "Menu/marcador.png";
        this.puntuacionGraficaEquipo1 = new ElementoDibujable(TipoDibujo.texto,"0");
        this.puntuacionGraficaEquipo2 = new ElementoDibujable(TipoDibujo.texto,"0");
        this.puntuacionGraficaEquipo1.dibujar(ConstantesJuego.POSICION_X_PUNTUACION_EQUIPO1,ConstantesJuego.POSICION_Y_PUNTUACION );
        this.puntuacionGraficaEquipo2.dibujar(ConstantesJuego.POSICION_X_PUNTUACION_EQUIPO2,ConstantesJuego.POSICION_Y_PUNTUACION);
        this.banderaEquipo1 = new ElementoDibujable(TipoDibujo.interficieUsuario,equipo1.getLogo());
        this.banderaEquipo2 = new ElementoDibujable(TipoDibujo.interficieUsuario,equipo2.getLogo());
        this.banderaEquipo1.dibujar(ConstantesJuego.POSICION_X_ESCUDO_EQUIPO1,ConstantesJuego.POSICION_Y_ESCUDO);
        this.banderaEquipo2.dibujar(ConstantesJuego.POSICION_X_ESCUDO_EQUIPO2,ConstantesJuego.POSICION_Y_ESCUDO);
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        marcador = this;


    }

    public static Marcador getInstanceMarcador()
    {
        return marcador;
    }
    public static int resultadoEquipo1(){
        return puntuacionEquipo1;
    }
    public static int resultadoEquipo2(){
        return puntuacionEquipo2;
    }
    /**
     * Añade la puntuacion al equipo al que pertenece el jugador.
     *
     * @param puntuacion cantidad que sera añadida a la puntuacion existente
     * @param jugador Jugador que ha conseguido realizar el punto
     */
    public void sumarPuntuacion(int puntuacion,Jugador jugador)  {
        if(equipo1.jugadorEnEquipo(jugador)){
            puntuacionEquipo1  = puntuacionEquipo1 + puntuacion;
        }
        else {
            puntuacionEquipo2 = puntuacionEquipo2 + puntuacion;
        }
        this.dibujarMarcador();
    }

    /**
     * Realiza el proceso de dibujado del marcador
     */
    private void dibujarMarcador()  {
        this.puntuacionGraficaEquipo2.borrar();
        this.puntuacionGraficaEquipo1.borrar();
        this.puntuacionGraficaEquipo1 = new ElementoDibujable(TipoDibujo.texto,""+puntuacionEquipo1);
        this.puntuacionGraficaEquipo2 = new ElementoDibujable(TipoDibujo.texto,""+puntuacionEquipo2);
        this.puntuacionGraficaEquipo1.dibujar(Gdx.graphics.getWidth()/2 -60,Gdx.graphics.getHeight()-20);
        this.puntuacionGraficaEquipo2.dibujar(Gdx.graphics.getWidth()/2+10,Gdx.graphics.getHeight()-20);

    }


    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return ConstantesJuego.POSICION_X_MARCADOR;
    }

    @Override
    public int getPosicionY() {
        return ConstantesJuego.POSICION_Y_MARCADOR;
    }
}
