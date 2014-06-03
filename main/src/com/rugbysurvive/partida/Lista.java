package com.rugbysurvive.partida;


import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Texto;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;
import com.rugbysurvive.partida.tablero.Botones.BotonDesplazamiento;
import com.rugbysurvive.partida.tablero.Botones.BotonObjeto;
import com.rugbysurvive.partida.tablero.Botones.BotonSuplente;

import java.util.ArrayList;

/**
 * Created by Victor on 11/04/14.
 * Gestiona la lista de jugadores y objetos.
 * Permite a un usuario seleccionar cualquiera de las dos listas
 * y seleccionar cualqueir objeto o jugador.
 * Actualiza todos los elementos a lo largo de la partida segun
 * su existencia o uso.
 *
 */
public class Lista {


    //Constantes especificas para colocar los elementos de los menus
    private static final int POSICION_TABLON_INFORMACION=  ConstantesJuego.getHeight()-ConstantesJuego.LARGO_TABLON_SUSITUCION -(int)(20*ConstantesJuego.constanteRescalado)+50;
    private static final int POSICION_TABLON_JUGADOR= ConstantesJuego.getHeight()-ConstantesJuego.LARGO_TABLON_SUSITUCION -(int)(20*ConstantesJuego.constanteRescalado);
    private static final int  POSICION_BOTON_DESPLAZAMIENTO = POSICION_TABLON_JUGADOR-(int)(120* ConstantesJuego.constanteRescalado);
    private static int posicionInicial = 0;
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
   private  ElementoDibujable plantillaObjetos;
   private ArrayList<ElementoDibujable> tablonInformacionHabilidades = new ArrayList<ElementoDibujable>();;
   private Boton botonArriba = null;
   private Boton botonAbajo =null;





    /**
     * Obtiene la lista de suplentes del equipo y la dibuja en pantalla
     * Genera tanto el tablero de informacion de las habilidades  como una lista
     * de tres jugadores.
     * Si hay mas de tres jugadores genera un boton ascendente para navegar hacia
     * el resto de jugadores
     * Si se navega por la lista con otro boton descendente permite navegar hacia atras.
     * Si no hay ningun jugador disponible la lista no se muestra.
     */
    public void listaSuplentes() {

        int y = ConstantesJuego.ALTO_BOTON +(int)(30*ConstantesJuego.constanteRescalado);
        this.eliminarListaSuplentes();
        listaSuplentes = new ArrayList<Boton>();

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        Equipo equipoSeleccionado = equipo2;

        if(equipo1.hayJugadorSelecionado()){
            equipoSeleccionado  = equipo1;
        }
        estadoSuplente =true;

        if (equipoSeleccionado.hayJugadorSelecionado() && equipoSeleccionado.listaSuplentes().size()>0 ){

            ArrayList<Jugador> suplentes= equipoSeleccionado.listaSuplentes();
            int posicion = 0;


             for (Jugador iterador : suplentes) {

                 if(posicion >= posicionInicial && posicion < posicionInicial +3) {
                    listaSuplentes.add(new BotonSuplente(POSICION_TABLON_JUGADOR,y,
                                        Entrada.listasuplente,"Menu/tauloCanvi.png",iterador));
                    y += ConstantesJuego.ANCHO_TABLON_SUSTITUCION;
                 }
                 posicion++;
             }

            this.tablonInformacionHabilidades = new ArrayList<ElementoDibujable>();

            this.tablonInformacionHabilidades.add( new ElementoDibujable(TipoDibujo.interficieUsuario,"Menu/Habilidades/taulohabilitats.png"));


            this.tablonInformacionHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,"Menu/Habilidades/atac.png"));
            this.tablonInformacionHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,"Menu/Habilidades/defensa.png"));
            this.tablonInformacionHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,"Menu/Habilidades/fuerza.png"));
            this.tablonInformacionHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,"Menu/Habilidades/resistencia.png"));
            this.tablonInformacionHabilidades.add(new ElementoDibujable(TipoDibujo.interficieUsuario,"Menu/Habilidades/habilidad.png"));
           int posicionX = POSICION_TABLON_JUGADOR+ (int)( ConstantesJuego.LARGO_TABLON_SUSITUCION/3);

          // DIBUJAR TODAS LAS HABILIDADES DEL TABLO DE INFORMACION
           for(ElementoDibujable dibujo : this.tablonInformacionHabilidades) {

                if(this.tablonInformacionHabilidades.indexOf(dibujo)==0) {
                    this.tablonInformacionHabilidades.get(0).dibujar(POSICION_TABLON_INFORMACION ,y);
                }
                else {
                    dibujo.dibujar(posicionX,y+10);
                    posicionX = posicionX + ConstantesJuego.LARGO_TABLON_SUSITUCION/8;
                }
           }

            int posicionBotonAbajo =ConstantesJuego.ALTO_BOTON +(int)(60*ConstantesJuego.constanteRescalado);
            int posicionBotonArriba = posicionBotonAbajo+(int)(140*ConstantesJuego.constanteRescalado);

            if(posicionInicial >0){
                this.botonAbajo = new BotonDesplazamiento(POSICION_BOTON_DESPLAZAMIENTO,posicionBotonAbajo,Entrada.listasuplente,
                        "Menu/fletxaAvall.png",1,this);
                System.out.println("BOTON ABAJO MOSTRANDOSE");
                this.botonAbajo.mostrar();
            }
            if(posicionInicial +2 < suplentes.size()- 1){

                this.botonArriba = new BotonDesplazamiento(POSICION_BOTON_DESPLAZAMIENTO,posicionBotonArriba,Entrada.listasuplente
                        ,"Menu/fletxaAmunt.png",0,this);
                this.botonArriba.mostrar();
            }

        }
    }


    /**
     * Crea la lista de objetos que el jugador tiene y los muestra en pantalla
     * Si no hay ningun objeto la lista no se muestra
     * Cada objeto creado se corresponde a un boton que permite ser seleccionado.
     */
    public void ListaObjetos(){

        int x;
        int y;

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        Equipo equipoSeleccionado = equipo2;

        if(equipo1.hayJugadorSelecionado()){
            equipoSeleccionado  = equipo1;
        }
        ArrayList<ObjetoJugador> objetosJugador = equipoSeleccionado.objetosJugador();

        if (objetosJugador.size()  > 0){


            this.plantillaObjetos = new ElementoDibujable(TipoDibujo.interficieUsuario,"taulellObjectes.png");
            this.plantillaObjetos.dibujar(ConstantesJuego.POSICION_BOTON_CHUTEPASE,ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES);

            x = ConstantesJuego.POSICION_BOTON_CHUTEPASE;
            y = ConstantesJuego.POSICION_INICIAL_Y_BOTON_SUPLENTES;
            int distanciaObjetoCercano = ConstantesJuego.DISTANCIA_OBJETO_PRIMERA_LINIA;
            int distanciaObjetoLejano = ConstantesJuego.DISTANCIA_OBJETO_SEGUNDA_LINIA;

            listaObjetos.add(new BotonObjeto(x +distanciaObjetoCercano, y +distanciaObjetoLejano, Entrada.listaobjetos, objetosJugador.get(0).getTextura(),objetosJugador.get(0).getId()));

            if(objetosJugador.size()>=2){
                listaObjetos.add(new BotonObjeto(x+distanciaObjetoLejano, y+distanciaObjetoLejano, Entrada.listaobjetos, objetosJugador.get(1).getTextura(),objetosJugador.get(1).getId()));
            }
            if(objetosJugador.size()>=3){
                listaObjetos.add(new BotonObjeto(x+distanciaObjetoCercano, y+distanciaObjetoCercano, Entrada.listaobjetos, objetosJugador.get(2).getTextura(),objetosJugador.get(2).getId()));
            }
            if(objetosJugador.size()>=4){
                listaObjetos.add(new BotonObjeto(x+distanciaObjetoLejano, y+distanciaObjetoCercano, Entrada.listaobjetos, objetosJugador.get(3).getTextura(),objetosJugador.get(3).getId()));
            }

            estadoObjeto=true;
        }else{
            estadoObjeto=false;
        }

    }


    /**
     * elimina de la pantalla la lista de suplentes .
     */
    public void eliminarListaSuplentes(){
        int ID;

        for (Boton iterador : listaSuplentes ){
            iterador.borrar();
        }
        for(ElementoDibujable elemento : this.tablonInformacionHabilidades){
            elemento.borrar();
        }
        this.tablonInformacionHabilidades = new ArrayList<ElementoDibujable>();

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
     * elimina de la pantalla la lista de objetos
     */
    public void eliminarListaObjetos(){
        int ID;
        //GestorGrafico.generarDibujante().eliminarTextura(idPlantillaObjetos);
        if (this.plantillaObjetos!=null){
            this.plantillaObjetos.borrar();
        }

        for (Boton iterador : listaObjetos ){
            iterador.borrar();
        }
        listaObjetos.clear();
        estadoObjeto=false;
    }

    /**
     * En funcion de la entrada se crea una lista de suplentes  de objetos
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
     * Devuelve la lista que esta activa en ese momento
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

    /**
     * Coloca la posicion de la lista de jugadores
     * a la posicion inicial .
     */
    public void reiniciarPosicionamientoLista(){
        posicionInicial =0;
    }

    /**
     * Indica la posicion de la lista de suplentes
     * por la cual se debe iniciar el muestreo
     * @param posicion posicion de la lista de suplentes
     */
    public void setPosicionListaSuplentesInicial(int posicion){
       posicionInicial = posicion;
    }

    /**
     * Obtiene la posicion de la lista de suplentes
     * por la cual ha iniciado el muestreo
     * @return
     */
    public int getPosicionListaSuplentesInicial(){
        return posicionInicial;
    }

    /**
     * Devuelve el boton de la lista de suplentes que permite
     * desplazarse hacia arriba en la lista.
     * @return boton de desplazamiento ascendente
     */
    public Boton getBotonArriba() {
        return botonArriba;
    }

    /**
     * Devuelve el boton de la lista de suplentes que permite
     * desplazarse hacia abajo en la lista.
     * @return boton de desplazamiento descendiente
     */
    public Boton getBotonAbajo() {
        return botonAbajo;
    }


}
