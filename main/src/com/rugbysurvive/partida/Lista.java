package com.rugbysurvive.partida;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Texto;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Clase que dibuja las listas de jugadores suplentes y objetos
 * Created by Victor on 11/04/14.
 */
public class Lista {

    private ArrayList<Boton> listaSuplentes= new ArrayList <Boton>();
    private ArrayList<Boton> listaObjetos= new ArrayList <Boton>();

    //private ArrayList<Boton> listaActiva= new ArrayList <Boton>();

    private ArrayList<Texto> jugadores= new ArrayList <Texto>();

    private boolean estadoSuplente =false;
    private boolean estadoObjeto =false;

    private Equipo equipo = new Equipo();

    public void listaSuplentes(){

        int y = 130;
        int posicion = 0;



        //equipo.crearEquipo();

        ArrayList<Jugador> suplentes= equipo.listaSuplentes();
        //suplentes = equipo.listaSuplentes();

        System.out.println("entrada bucle");
        System.out.println(suplentes.size());

        for (Jugador iterador : suplentes){

            System.out.println("iteracion:" + posicion);

            listaSuplentes.add(new Boton(450,y, Entrada.listasuplente,"listaprueba.png",posicion));
            jugadores.add(new Texto(450,y+64,"jugador 1 fuerza:"+iterador.getFuerza()+" vida:"+iterador.getVida()+" defensa:"+iterador.getDefensa()));

            y += 64;
            posicion += 1;
        }
        suplentes.clear();
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

    public void ListaObjetos(){

        //int x = jugador.getPosicionX();
        //int y = jugador.getPosicionY();
        int x =320;
        int y =640;
        int posicion = 0;
        for (int i = 0; i<4;i++){
            listaObjetos.add(new Boton(x,y, Entrada.listasuplente,"listaprueba.png",posicion));
            posicion +=1;
        }

        estadoObjeto=true;

    }

    public void eliminarListaObjetos(){
        int ID;

        for (Boton iterador : listaObjetos ){
            ID = iterador.getID();
            GestorGrafico.generarDibujante().eliminarTextura(ID);
        }
        listaObjetos.clear();
        estadoObjeto=false;
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
            if (estadoObjeto ==false){
                ListaObjetos();
                estadoObjeto =true;
            }else {
                eliminarListaObjetos();
                estadoObjeto =false;
            }
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
        ArrayList<Boton> listaActiva= new ArrayList <Boton>();

        if (estadoSuplente){
          listaActiva=listaSuplentes;
        }
        if (estadoObjeto){
            listaActiva = listaObjetos;
        }
        return listaActiva;

    }



}
