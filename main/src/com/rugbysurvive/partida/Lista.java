package com.rugbysurvive.partida;

import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Texto;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Clase que dibuja las listas de jugadores suplentes y objetos
 * Created by Victor on 11/04/14.
 */
public class Lista {

    private ArrayList<Boton> listaSuplentes= new ArrayList <Boton>();
    private ArrayList<Boton> listaObjetos= new ArrayList <Boton>();

    private ArrayList<Boton> listaActiva= new ArrayList <Boton>();

    private ArrayList<Texto> jugadores= new ArrayList <Texto>();

    private boolean estadoSuplente =false;
    private boolean estadoObjeto =false;


    public void listaSuplentes(){

        int y = 130;
        int posicion = 0;


        listaSuplentes.add(new Boton(450,130, Entrada.listasuplente,"listaprueba.png",posicion));
        jugadores.add(new Texto(450,194,"jugador 1 fuerza:10 vida:20 defensa:30"));
        //jugadores.add(new Texto(850,194,"jugador 2 fuerza:10 vida:20 defensa:30"));
        //jugadores.add(new Texto(850,258,"jugador 3 fuerza:10 vida:20 defensa:30"));
        //jugadores.add(new Texto(850,322,"jugador 4 fuerza:10 vida:20 defensa:30"));

        /*jugadores.add(new Texto(0,0,"jugador 1 fuerza:10 vida:20 defensa:30"));
        jugadores.add(new Texto(0,50,"jugador 2 fuerza:10 vida:20 defensa:30"));
        jugadores.add(new Texto(0,100,"jugador 3 fuerza:10 vida:20 defensa:30"));
        jugadores.add(new Texto(0,150,"jugador 4 fuerza:10 vida:20 defensa:30"));*/
/*
        System.out.println("entrada bucle");
        for (Texto iterador : jugadores){

            System.out.println("iteracion:" + posicion);

            listaSuplentes.add(new Boton(850,y, Entrada.listasuplente,"listaprueba.png",posicion));

            y += 64;
            posicion += 1;
        }

        y = 130;
        posicion = 0;*/

        estadoSuplente =true;

    }

    public void eliminarListaSuplentes(){
        int ID;

        for (Boton iterador : listaSuplentes ){
            ID = iterador.getID();
            GestorGrafico.generarDibujante().eliminarTextura(ID);
        }
        for (Texto iteradortexto : jugadores ){
            ID = iteradortexto.getID();
            GestorGrafico.generarDibujante().eliminarTextura(ID);
        }
        jugadores.clear();
        listaSuplentes.clear();
        estadoSuplente =false;

    }

    public void crearLista(Entrada entrada){
        if (entrada ==Entrada.cambiar){
            if (estadoSuplente ==false){
                listaSuplentes();
                estadoSuplente =true;
            }else {
                eliminarListaSuplentes();
                estadoSuplente =false;
            }
        } else if (entrada ==Entrada.objeto){
            //this.entrada = Entrada.pase;
        }
    }

    public boolean hayLista(){
        boolean listaEnUso=false;
        if (estadoSuplente ==true || estadoObjeto ==true){
            listaEnUso =true;
        }
        return listaEnUso;
    }

    public  ArrayList<Boton> listaActiva (){

        if (estadoSuplente){
          listaActiva=listaSuplentes;
        }
        if (estadoObjeto){
            listaActiva = listaObjetos;
        }
        return listaActiva;

    }



}
