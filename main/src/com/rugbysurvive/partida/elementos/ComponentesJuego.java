package com.rugbysurvive.partida.elementos;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Lista;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.Mina;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.jugadores.Habilidades;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Casilla;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.ArrayList;

/**
 * Created by aitor on 15/04/14.
 */
public class ComponentesJuego {
    protected Campo campo;
    protected Equipo equipo1;
    protected Equipo equipo2;
    protected static ComponentesJuego componentesJuego;




    public ComponentesJuego()
    {
        this.equipo1 = new Equipo();
        this.equipo2 = new Equipo();
        this.campo = new Campo();
        campo = new Campo();
        this.generarEquipos();
        componentesJuego = this;

    }
    public static ComponentesJuego getComponentes(){return componentesJuego;}
    private void generarEquipos()
    {

        // falta añadir los objetos a cada jugador
        Jugador jugador = new Jugador(80, 90, 100);
        this.equipo1.añadirJugador(jugador,0,1);


        PowerUP objeto = new PowerUP(0,10,"casilla.png", Habilidades.vida,50,jugador);

        Mina objeto2 = new Mina(1,10,"casellalila.png", Habilidades.vida,50,jugador);

        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);

        this.equipo1.añadirJugador(new Jugador(80, 90, 100),1,4);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),2,3);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),4,7);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),0,5);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),5,1);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),6,4);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),7,3);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),8,7);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100),9,5);

        this.equipo2.añadirJugador(new Jugador(80, 90, 100),0,1);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100),1,4);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100),2,3);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100),4,7);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100),0,5);

        Posicionamiento.generarSaqueCampo(this.campo,this.equipo1, Lado.izquierda);
        Posicionamiento.generarSaqueCampo(this.campo,this.equipo2, Lado.derecha);
    }


    public Campo getCampo(){
        return this.campo;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2()
    {
        return equipo2;
    }

}
