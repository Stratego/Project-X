package com.rugbysurvive.partida.elementos;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.MinaCampo;
import com.rugbysurvive.partida.elementos.objetos.poweUps.ColocadorObjetosCampo;
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
        this.equipo1.setColor(Color.rojo);
        this.equipo2.setColor(Color.azul);
        this.campo = new Campo();
        campo = new Campo();
        this.generarEquipos();
        this.marcador = new Marcador(this.equipo1,this.equipo2);
        componentesJuego = this;


    }
    public static ComponentesJuego getComponentes(){return componentesJuego;}
    private void generarEquipos()
    {
        Arbitro arbitro = new Arbitro();
        // falta añadir los objetos a cada jugador
        Jugador jugador = new Jugador(80, 90, 100, 90, 80, 70, null);

        jugador.setDireccion(DireccionJugador.derecha);
        PowerUP objeto = new PowerUP(0,10,"casilla.png", Habilidades.vida,50,jugador);
        ColocadorObjetosCampo objeto2 = new ColocadorObjetosCampo(1,10,"mina.png",jugador,new MinaCampo("mina.png"));

        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);
        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto);

        this.equipo1.añadirJugador(jugador,0,1);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.izquierda);
        this.equipo1.añadirJugador(jugador, 6, 6);
        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.arriba);
        this.equipo1.añadirJugador(jugador, 7, 7);
        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.abajo);
        jugador.setEstado(new ConPelota());

        this.equipo1.añadirJugador(jugador,8,8);

        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),9,9);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),10,10);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),11,11);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),12,12);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),13,13);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),14,14);



        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),15,22);
        //this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),1,4);
        //this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),2,3);
        /*this.equipo2.añadirJugador(new Jugador(80, 90, 100,this.equipo2),4,7);
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
