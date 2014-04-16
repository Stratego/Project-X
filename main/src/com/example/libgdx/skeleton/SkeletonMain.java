package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.ResolucionPantalla;
import com.rugbysurvive.partida.Simulador.Movimiento;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.GestorObjetos;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestorEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Prueba;
import com.rugbysurvive.partida.gestores.Texto;
import com.rugbysurvive.partida.tablero.*;


import java.util.ArrayList;


public class SkeletonMain extends Game {


    InputMultiplexer multiplexer;
    GestorGrafico gestorGrafico;

    GestureDetector gestureDetector;

    GestorEntrada gestorEntrada;

    private ArrayList<Boton> botons= new ArrayList <Boton>();
    ConstantesJuego constantes;
    Prueba prueba2;
    GestorObjetos gestorObjetos;
    int contador = 0;
    ComponentesJuego componentesJuego;
    Simulador simulador;


    @Override
    public void create() {

        this.simulador = new Simulador();
        this.simulador.iniciarSimulacion();

        this.constantes = new ConstantesJuego();
        this.constantes.setResolucionPantalla(ResolucionPantalla.pequeña);
        ArrayList<String> nombresTexturas = new ArrayList<String>();
        nombresTexturas.add("jugador1.png");
        nombresTexturas.add("campo1.png");
        nombresTexturas.add("casellalila.png");
        nombresTexturas.add("boto.png");
        nombresTexturas.add("listaprueba.png");
        nombresTexturas.add("casilla.png");
        nombresTexturas.add("jugador/seleccionado.png");
        nombresTexturas.add("jugador/jugador1.png");
        nombresTexturas.add("jugador/jugador2.png");
        nombresTexturas.add("jugador/jugador4.png");
        nombresTexturas.add("jugador/jugador5.png");


        this.gestorGrafico = new GestorGrafico(nombresTexturas,64);
        botons.add(new Boton(450,0, Entrada.pase,"boto.png",20));
        botons.add(new Boton(650,0, Entrada.objeto,"boto.png",20));
        botons.add(new Boton(850,0, Entrada.cambiar,"boto.png",20));
        botons.add(new Boton(1050,0, Entrada.finalizar,"boto.png",20));

        this.componentesJuego = new ComponentesJuego();


        this.gestorEntrada = new GestorEntrada(this.gestorGrafico.getCamara().getOrthographicCamera(),botons,this.gestorGrafico);

        this.multiplexer = new InputMultiplexer();
        this.gestorObjetos = new GestorObjetos();
        gestureDetector = new GestureDetector(20, 0.5f, 1, 0.5f,this.gestorEntrada);
        multiplexer.addProcessor(gestureDetector);
        multiplexer.addProcessor(this.gestorGrafico.getCamara());

        Gdx.input.setInputProcessor(multiplexer);



        //this.prueba2 = new Prueba(100,100,100,"holaa");

     //   campoDibujable = new CampoDibujable(this.gestorGrafico,0,0);

         //new Texto(20,40, "prueba");

        //this.prueba3 = new Prueba(this.gestorGrafico,1,4,300,"casilla.png");

        //Simulador.getInstance().simular();

    }

    @Override
    public void dispose() {
        this.gestorGrafico.dispose();
    }

    @Override
    public void render() {

       this.simulador.simular();
       if(contador %100 == 0 ){
             this.gestorObjetos.procesar();
       }
     this.gestorGrafico.dibujar();

    this.contador++;
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


