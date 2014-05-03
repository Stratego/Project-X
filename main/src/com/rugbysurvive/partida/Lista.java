package com.rugbysurvive.partida;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Texto;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;
import com.rugbysurvive.partida.tablero.Botones.BotonDesplazamiento;
import com.rugbysurvive.partida.tablero.Botones.BotonObjeto;
import com.rugbysurvive.partida.tablero.Botones.BotonSuplente;

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
    private Equipo equipo;

    //int idPlantillaObjetos=0;

    ElementoDibujable plantillaObjetos;


    private static int posicionInicial = 0;
    private static int posicionFinal = 0;




    private Boton botonArriba = null;


    private Boton botonAbajo =null;

    /**
     * Obtiene la lista de suplentes del equipo y la dibuja en pantalla
     */
    public void listaSuplentes(){



        int y = ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES;

        listaSuplentes = new ArrayList<Boton>();
        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        Equipo equipoSeleccionado = equipo2;

        if(equipo1.hayJugadorSelecionado()){
            equipoSeleccionado  = equipo1;
        }
        estadoSuplente =true;

        if (equipoSeleccionado.hayJugadorSelecionado()){

            ArrayList<Jugador> suplentes= equipoSeleccionado.listaSuplentes();
            int posicion = posicionInicial;


             for (Jugador iterador : suplentes) {

                 if(posicion < posicionInicial +3) {
                    listaSuplentes.add(new BotonSuplente(ConstantesJuego.POSICION_BOTON_CHUTEPASE,y,
                                        Entrada.listasuplente,"TauloCanviJugadors.png",iterador));
                    y += ConstantesJuego.ANCHO_TABLON_SUSTITUCION;
                    posicion++;
                 }
             }



            if(posicionInicial >0){
                this.botonAbajo = new BotonDesplazamiento(ConstantesJuego.POSICION_BOTON_CHUTEPASE-100,100,Entrada.listasuplente,
                        "boto.png",1,this);
                this.botonArriba.mostrar();
            }
            if(posicionInicial +3 < suplentes.size()- 1){
                this.botonArriba = new BotonDesplazamiento(ConstantesJuego.POSICION_BOTON_CHUTEPASE-100,200,Entrada.listasuplente,
                        "boto.png",0,this);
                this.botonArriba.mostrar();
            }




        }
    }

    /**
     * elimina de la pantalla la lista de suplentes
     */
    public void eliminarListaSuplentes(){
        int ID;

        for (Boton iterador : listaSuplentes ){
            iterador.borrar();
        }

        if(this.botonAbajo != null){
            this.botonAbajo.borrar();
        }
        if(this.botonArriba != null){
            this.botonArriba.borrar();
        }

        jugadores.clear();
        listaSuplentes.clear();
        estadoSuplente =false;

    }

    /**
     * Crea la lista de objetos que el jugador tiene y los muestra en pantalla
     */
    public void ListaObjetos(){

        equipo = ComponentesJuego.getComponentes().getEquipo1();

        ArrayList<ObjetoJugador> objetosJugador = equipo.objetosJugador();
        int x =ConstantesJuego.POSICION_INICIAL_X_BOTON_OBJETOS;
        int y =ConstantesJuego.POSICION_INICIAL_Y_BOTON_OBJETOS;
        //int y = ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES;


        if (objetosJugador.size()  > 0){


            this.plantillaObjetos = new ElementoDibujable(TipoDibujo.interficieUsuario,"taulellObjectes.png");
            this.plantillaObjetos.dibujar(ConstantesJuego.POSICION_BOTON_CHUTEPASE,ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES);
            //idPlantillaObjetos = GestorGrafico.generarDibujante().añadirDibujable(new PlantillaObjetos(ConstantesJuego.POSICION_BOTON_CHUTEPASE,ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES,"plantillaobjetos.png"), TipoDibujo.interficieUsuario);

            x = ConstantesJuego.POSICION_BOTON_CHUTEPASE;
            y = ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES;

            listaObjetos.add(new BotonObjeto(x +30, y +115, Entrada.listaobjetos, objetosJugador.get(0).getTextura(),objetosJugador.get(0).getId()));

            if(objetosJugador.size()>=2){
                listaObjetos.add(new BotonObjeto(x+115, y+115, Entrada.listaobjetos, objetosJugador.get(0).getTextura(),objetosJugador.get(1).getId()));
            }
            if(objetosJugador.size()>=3){
                listaObjetos.add(new BotonObjeto(x+30, y+30, Entrada.listaobjetos, objetosJugador.get(0).getTextura(),objetosJugador.get(2).getId()));
            }
            if(objetosJugador.size()>=4){
                listaObjetos.add(new BotonObjeto(x+115, y+30, Entrada.listaobjetos, objetosJugador.get(0).getTextura(),objetosJugador.get(3).getId()));
            }

            estadoObjeto=true;
        }else{
            estadoObjeto=false;
        }



    }

    /**
     * elimina de la pantalla la lista de objetos
     */
    public void eliminarListaObjetos(){
        int ID;
        //GestorGrafico.generarDibujante().eliminarTextura(idPlantillaObjetos);
        if (this.plantillaObjetos!=null){
            this.plantillaObjetos.borrar();
        }

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
        if (entrada ==Entrada.cambiar || entrada == Entrada.listasuplente ){
            System.out.println(entrada);
            if (estadoSuplente ==false){
                listaSuplentes();
                estadoSuplente =true;
                eliminarListaObjetos();
                estadoObjeto =false;
            }else {
                eliminarListaSuplentes();
                estadoSuplente =false;
            }
        } else if (entrada ==Entrada.objeto|| entrada == Entrada.listaobjetos){
            if (estadoObjeto ==false){
                ListaObjetos();
                estadoObjeto =true;
                eliminarListaSuplentes();
                estadoSuplente =false;
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

        if (estadoSuplente){
          return this.listaSuplentes;
        }
        if (estadoObjeto){
            return this.listaObjetos;
        }
        return new ArrayList<Boton>();

    }

    public void reiniciarPosicionamientoLista(){
        posicionInicial =0;
    }

    public void setPosicionListaSuplentesInicial(int posicion){
       posicionInicial = posicion;
    }
    public int getPosicionListaSuplentesInicial(){
        return posicionInicial;
    }
    public Boton getBotonArriba() {
        return botonArriba;
    }
    public Boton getBotonAbajo() {
        return botonAbajo;
    }


}
