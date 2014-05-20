package com.rugbysurvive.partida.elementos;

import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.objetos.Objeto;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.Agujero;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.Hielo;
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
    private Campo campo;
    private Equipo equipo1;
    private Equipo equipo2;
    private Marcador marcador;

    private static ComponentesJuego componentesJuego;




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
        Arbitro arbitro = new Arbitro();


    }
    public static ComponentesJuego getComponentes(){return componentesJuego;}



    private void generarEquipos()
    {

        // falta añadir los objetos a cada jugador
        Jugador jugador = new Jugador(80, 90, 100, 90, 80, 70, equipo1);

        jugador.setDireccion(DireccionJugador.derecha);

        PowerUP objeto3 = new PowerUP(1,10,Habilidades.defensa,50,jugador);
        ColocadorObjetosCampo objeto4 = new ColocadorObjetosCampo(2,10,"mina.png",jugador,new MinaCampo("mina.png",jugador));
        ColocadorObjetosCampo objeto2 = new ColocadorObjetosCampo(3,10,"objetos/agujero.png",jugador,new Agujero("objetos/agujero.png",jugador));
        ColocadorObjetosCampo objeto = new ColocadorObjetosCampo(3,10,"objetos/hielo.png",jugador,new Hielo("objetos/hielo.png",jugador));
        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);
        jugador.añadirObjeto(objeto3);
        jugador.añadirObjeto(objeto4);

        this.equipo1.añadirJugador(jugador,7,6);
        //this.equipo1.añadirJugador(jugador,17,22);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.izquierda);
        this.equipo1.añadirJugador(jugador, 6, 6);
        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.arriba);
        this.equipo1.añadirJugador(jugador, 7, 7);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.abajo);
        //jugador.setEstado(new ConPelota(jugador));
        this.equipo1.añadirJugador(jugador,9,22);


        this.equipo1.añadirJugador(new Jugador(80, 90, 34, 90, 80, 70, this.equipo1),9,9);
        this.equipo1.añadirJugador(new Jugador(80, 90, 12, 90, 80, 70, this.equipo1),11,25);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),11,11);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 13, 70, this.equipo1),12,12);
        this.equipo1.añadirJugador(new Jugador(80, 68, 100, 90, 80, 70, this.equipo1),13,13);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),14,14);
        this.equipo1.añadirJugador(new Jugador(80, 90, 51, 90, 80, 70, this.equipo1),15,15);
        this.equipo1.añadirJugador(new Jugador(80, 34, 56, 22, 80, 70, this.equipo1),16,16);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),17,17);
        this.equipo1.añadirJugador(new Jugador(80, 90, 56, 90, 80, 70, this.equipo1),12,12);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),4,4);


        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),3,12);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),4,13);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),5,14);




        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),12,19);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),13,20);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),14,21);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),15,22);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),16,23);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),17,24);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo2);
        //jugador.setEstado(new ConPelota(jugador));
        this.equipo2.añadirJugador(jugador,5,5);


        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo2);
        PowerUP objeto5 = new PowerUP(0,10,"casilla.png", Habilidades.vida,50,jugador);
        objeto2 = new ColocadorObjetosCampo(1,10,"mina.png",jugador,new MinaCampo("mina.png",jugador));

        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);
        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto);
        this.equipo2.añadirJugador(jugador,2,3);
       // this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),19,27);
        //this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),21,21);


    }

    public void generarSaque(){


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
