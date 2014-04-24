package com.rugbysurvive.partida.elementos;

import com.badlogic.gdx.Gdx;
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
        this.puntuacionGraficaEquipo1.dibujar(Gdx.graphics.getWidth()/2 -60,Gdx.graphics.getHeight()-20);
        this.puntuacionGraficaEquipo2.dibujar(Gdx.graphics.getWidth()/2+10,Gdx.graphics.getHeight()-20);
        this.banderaEquipo1 = new ElementoDibujable(TipoDibujo.interficieUsuario,"banderas/logo1.png");
        this.banderaEquipo2 = new ElementoDibujable(TipoDibujo.interficieUsuario,"banderas/logo4.png");
        this.banderaEquipo1.dibujar(Gdx.graphics.getWidth()/2 -140,Gdx.graphics.getHeight()-70);
        this.banderaEquipo2.dibujar(Gdx.graphics.getWidth()/2 +70,Gdx.graphics.getHeight()-70);
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;


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
        return Gdx.graphics.getWidth()/2 -64;
    }

    @Override
    public int getPosicionY() {
        return Gdx.graphics.getHeight()-64;
    }
}
