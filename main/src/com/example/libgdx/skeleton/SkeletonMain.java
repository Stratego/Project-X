package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;


import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.ResolucionPantalla;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestorEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Prueba;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;


public class SkeletonMain extends Game {


    InputMultiplexer multiplexer;
    GestorGrafico gestorGrafico;

    GestorEntrada gestorEntrada;

    private ArrayList<Boton> botons= new ArrayList <Boton>();
    ConstantesJuego constantes;

    Prueba prueba;
    Prueba prueba2;
    Prueba prueba3;



    @Override
    public void create() {


        this.constantes = new ConstantesJuego();
        this.constantes.setResolucionPantalla(ResolucionPantalla.pequeña);
        ArrayList<String> nombresTexturas = new ArrayList<String>();
        nombresTexturas.add("jugador1.png");
        nombresTexturas.add("campo1.png");
        nombresTexturas.add("casellalila.png");
        nombresTexturas.add("boto.png");


        this.gestorGrafico = new GestorGrafico(nombresTexturas,64);
        botons.add(new Boton(450,0, Entrada.pase));
        botons.add(new Boton(650,0, Entrada.objeto));
        botons.add(new Boton(850,0, Entrada.cambiar));
        botons.add(new Boton(1050,0, Entrada.finalizar));
        this.gestorEntrada = new GestorEntrada(this.gestorGrafico.getCamara().getOrthographicCamera(),botons,this.gestorGrafico);

        this.multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.gestorGrafico.getCamara());
        multiplexer.addProcessor(new GestureDetector(this.gestorEntrada));
        Gdx.input.setInputProcessor(multiplexer);

     //   campoDibujable = new CampoDibujable(this.gestorGrafico,0,0);
        //this.prueba2 = new Prueba(2,4,2000,"jugador1.png");

        //this.prueba3 = new Prueba(this.gestorGrafico,1,4,300,"casilla.png");

    }

    @Override
    public void dispose() {
        this.gestorGrafico.dispose();
    }

    @Override
    public void render() {

     this.gestorGrafico.dibujar();
        //this.prueba2.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}


