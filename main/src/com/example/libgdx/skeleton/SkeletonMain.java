package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Prueba;

import java.util.ArrayList;

public class SkeletonMain extends Game {


    InputMultiplexer multiplexer;
    GestorGrafico gestorGrafico;
    Prueba prueba ;
    Prueba prueba2 ;
    Prueba prueba3 ;

    @Override
    public void create() {

        ArrayList<String> nombresTexturas = new ArrayList<String>();
        nombresTexturas.add("jugador1.png");
        this.gestorGrafico = new GestorGrafico(nombresTexturas);
       // this.gestorGrafico.cargarTextura("tablero/campo1.png");
        //this.gestorGrafico.actualizar("tablero/campo1.png",0,0);
        this.multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.gestorGrafico.getCamara());
        Gdx.input.setInputProcessor(multiplexer);
        this.prueba = new Prueba(this.gestorGrafico,0,0);
        this.prueba2 = new Prueba(this.gestorGrafico,30,0);
        this.prueba3 = new Prueba(this.gestorGrafico,0,30);


    }

    @Override
    public void dispose() {
        this.gestorGrafico.dispose();
    }

    @Override
    public void render() {

    this.gestorGrafico.dibujar();


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


