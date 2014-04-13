package com.rugbysurvive.partida;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Texto;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Clase que dibuja las listas de jugadores suplentes y objetos, mediante el uso de botones y texto
 * Created by Victor on 11/04/14.
 */
public class Lista {

    /**
     * Lista de jugadores suplentes que mostrar en la lista de cambio
     */
    private ArrayList<Boton> listaSuplentes= new ArrayList <Boton>();

    /**
     * Lista de objetos que mostrara la lista de objetos
     */
    private ArrayList<Boton> listaObjetos= new ArrayList <Boton>();


    /**
     * Lista de textos que se mostraran en pantalla
     */
    private ArrayList<Texto> jugadores= new ArrayList <Texto>();

    /**
     * Indica si la lista de suplentes es visible
     */
    private boolean estadoSuplente =false;

    /**
     * Indica si la lista de objetos es visible
     */
    private boolean estadoObjeto =false;

    /**
     * Obtenemos el equipo donde obtendremos los datos
     */
    private Equipo equipo = new Equipo();


    /**
     * Obtiene la lista de suplentes del equipo y la dibuja en pantalla
     */
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

    /**
     * elimina de la pantalla la lista de suplentes
     */
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

    /**
     * Crea la lista de objetos que el jugador tiene y los muestra en pantalla
     */
    public void ListaObjetos(){

        //int x = jugador.getPosicionX();
        //int y = jugador.getPosicionY();
        int x =320;
        int y =640;
        int posicion = 0;
        for (int i = 0; i<3;i++){
            if (i==0 ||i==2){
                for (int p = 0; p<3;p++){
                    if (p==0 ||p==2){
                        listaObjetos.add(new Boton(x,y, Entrada.listaobjetos,"casellalila.png",posicion));
                        x +=64;
                        posicion +=1;
                    }else{
                        x +=64;
                    }
                }
                x=320;
                y -=128;
            }
        }

        estadoObjeto=true;

    }

    /**
     * elimina de la pantalla la lista de objetos
     */
    public void eliminarListaObjetos(){
        int ID;

        for (Boton iterador : listaObjetos ){
            ID = iterador.getID();
            GestorGrafico.generarDibujante().eliminarTextura(ID);
        }
        listaObjetos.clear();
        estadoObjeto=false;
    }

    /**
     *  en funcion de la entrada se crea una lista de suplentes  de objetos
     * @param entrada
     */
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

    /**
     * indica si hay una lista mostrandose
     * @return lista mostrandose o no
     */
    public boolean hayLista(){
        boolean listaEnUso=false;
        if (estadoSuplente ==true || estadoObjeto ==true){
            listaEnUso =true;
        }
        return listaEnUso;
    }

    /**
     * debuelve la lista que esta activa en ese momento
     * @return lista activa
     */
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
