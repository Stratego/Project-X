package com.rugbysurvive.partida.elementos;

import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.Mina;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.jugadores.Habilidades;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by aitor on 15/04/14.
 */
public class ComponentesJuego {
    protected Campo campo;
    protected Equipo equipo1;
    protected Equipo equipo2;
    protected Marcador marcador;
    protected static ComponentesJuego componentesJuego;




    public ComponentesJuego()
    {
        this.equipo1 = new Equipo();
        this.equipo2 = new Equipo();
        this.campo = new Campo();
        campo = new Campo();
        this.generarEquipos();
        this.marcador = new Marcador(this.equipo1,this.equipo2);
        componentesJuego = this;


    }
    public static ComponentesJuego getComponentes(){return componentesJuego;}
    private void generarEquipos()
    {

        // falta añadir los objetos a cada jugador
        Jugador jugador = new Jugador(80, 90, 100, null);

        jugador.setDireccion(DireccionJugador.derecha);
        PowerUP objeto = new PowerUP(0,10,"casilla.png", Habilidades.vida,50,jugador);

        Mina objeto2 = new Mina(1,10,"casellalila.png", Habilidades.vida,50,jugador);

        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);
        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto);

        /*this.equipo1.añadirJugador(jugador,0,1);
        jugador = new Jugador(80, 90, 100,this.equipo1);
        jugador.setDireccion(DireccionJugador.izquierda);
        this.equipo1.añadirJugador(jugador, 1, 4);
        jugador = new Jugador(80, 90, 100,this.equipo1);
        jugador.setDireccion(DireccionJugador.arriba);
        this.equipo1.añadirJugador(jugador, 2, 3);
        jugador = new Jugador(80, 90, 100,this.equipo1);
        jugador.setDireccion(DireccionJugador.abajo);
        jugador.setEstado(new ConPelota());
        this.equipo1.añadirJugador(jugador,4,7);*/

        this.equipo1.añadirJugador(new Jugador(80, 90, 100,this.equipo1),8,7);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100,this.equipo1),8,3);
        /*this.equipo1.añadirJugador(new Jugador(80, 90, 100,this.equipo1),6,4);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100,this.equipo1),7,3);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100,this.equipo1),8,7);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100,this.equipo1),9,5);*/

        this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),11,21);
        /*this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),1,4);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),2,3);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),4,7);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),0,5);*/

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
    public Marcador getMarcador(){return this.marcador;}

}
