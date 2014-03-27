package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Prueba;

import java.util.ArrayList;

import com.partido.Boton;
import com.partido.Campo;
import com.partido.GestorImput;

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
        this.gestorGrafico = new GestorGrafico(nombresTexturas,64);
       // this.gestorGrafico.cargarTextura("tablero/campo1.png");

        //this.gestorGrafico.actualizar("tablero/campo1.png",0,0);
        this.multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.gestorGrafico.getCamara());
        //multiplexer.addProcessor(new GestureDetector(gestorImput)  );
        Gdx.input.setInputProcessor(multiplexer);
        this.prueba = new Prueba(this.gestorGrafico,0,0,100);


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



/*package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.partido.Boton;
import com.partido.Campo;
import com.partido.GestorImput;

import java.util.ArrayList;

public class SkeletonMain extends Game {


    private OrthographicCamera cam;

    int width = 1000;

    int height = 500;

    private Texture casillas;

    private Texture botones;

    private SpriteBatch spritebatch;

    private InputMultiplexer multiplexer;

    Campo campo = new Campo();

    private ArrayList<Boton> botons= new ArrayList <Boton>();

    @Override
    public void create() {
        System.out.println("Application started");

        cam = new OrthographicCamera(width,height);
        cam.position.set(width,height,0);
        cam.update();
        cam.apply(Gdx.graphics.getGL10());


        spritebatch = new SpriteBatch();


        // carga de sprites
        botones = new Texture (Gdx.files.internal("boton.png"));

        casillas = new Texture (Gdx.files.internal("casilla.png"));

        multiplexer = new InputMultiplexer();



        Boton boton1 = new Boton(0,150,"boton1");

        botons.add(boton1);

        Boton boton2 = new Boton(0, 350,"boton2");

        botons.add(boton2);

        Boton boton3 = new Boton(300, 150,"boton3");

        botons.add(boton3);

        Boton boton4 = new Boton(300, 350,"boton4");

        botons.add(boton4);

        GestorImput gestorImput = new GestorImput(cam,botons,campo);

        multiplexer.addProcessor(gestorImput);

        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void dispose() {
        spritebatch.dispose();
        botones.dispose();
        casillas.dispose();
    }

    @Override
    public void render() {

        cam.update();
        cam.apply(Gdx.graphics.getGL10());

        spritebatch.begin();

        spritebatch.draw(casillas, 0, 0);

        spritebatch.draw(botones, 0, 150);

        spritebatch.draw(botones, 0, 350);

        spritebatch.draw(botones, 300, 150);

        spritebatch.draw(botones, 300, 350);

        spritebatch.end();


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

    public ArrayList<Boton> getbotenes (){

        return botons;
    }
}*/
