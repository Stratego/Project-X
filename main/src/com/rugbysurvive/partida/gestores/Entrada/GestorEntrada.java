package com.rugbysurvive.partida.gestores.Entrada;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Lista;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;
import com.rugbysurvive.partida.tablero.Campo;

import java.util.ArrayList;

/**
 * Esta clase recibira nuestras acciones en la pantalla del telefono y en funcion de cada accion ejecuara lo que le hallamos definido
 * Created by Victor on 24/03/14.
 */
public class GestorEntrada implements GestureDetector.GestureListener {

    /**
     * definimos un elemento camara que usaremos para hacer la traslación entre camara y terreno de juego
     */
    private OrthographicCamera camera;

    /**
     * creamos el campo de juego
     */
    Campo campo;

    /**
     * variable que nos indicara si se ha pulsado un boton
     */
    boolean longclick=false;

    /**
     * colecion de botones que nuestro juego mostrara en pantalla
     */
    private ArrayList<Boton> botons= new ArrayList <Boton>();


    Dibujante dibujante;

    //private Equipo equipo=new Equipo();

    Lista lista = new Lista();

    /**
     * constructor del elemento GestorImput
     * @param camera camare que visualizara el juego
     * @param botons coleccion de botones del juego
     */
    public GestorEntrada(OrthographicCamera camera, ArrayList<Boton> botons,Dibujante dibujante) {

        this.camera = camera;
        this.botons = botons;
        this.dibujante = dibujante;
        campo = ComponentesJuego.getComponentes().getCampo();

    }

    @Override
    /**
     * acciones que se realizan al hacer longpress
     */
    public boolean longPress (float screenX, float screenY) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        //System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        //System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        //recorrer lista de botones
        for (Boton iterador : botons){
            if (iterador.esSeleccionado(screenX,screenY)){
                return false;
            }
        }
        campo.accionEntrada(Entrada.clicklargo,touchPos.x, touchPos.y );
        longclick=true;

        return false;
    }

    @Override
    /**
     * acciones que se ejecutaran al realizar un click en la pantalla
     */
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {

        /*Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        //System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        //System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        for (Boton iterador : botons){
            if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                campo.accionEntrada(iterador.obtenerEntrada());
                return false;
            }
        }

        campo.accionEntrada(Entrada.clic,touchPos.x, touchPos.y );*/
        return false;
    }

    @Override
    public boolean tap(float screenX, float screenY, int i, int i2) {
        if (longclick==false){
            Vector3 touchPos = new Vector3();
            touchPos.set(screenX, screenY,0);
            camera.unproject(touchPos);
            //System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
            //System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

            for (Boton iterador : botons){
                if (iterador.esSeleccionado(screenX,screenY)==true){
                    lista.crearLista(iterador.obtenerEntrada());

                    return false;
                }
            }
            //si hay lista comprueba que se haya clicado en uno se sus botones
            if (lista.hayLista()){


                //System.out.println("entra en bucle de listas");
                for (Boton iteradorLista : lista.listaActiva()){
                    //System.out.println("itera bucle de listas");
                    if (iteradorLista.esSeleccionado(screenX,screenY)){
                        lista.crearLista(iteradorLista.obtenerEntrada());
                        return false;
                    }
                }

                if(this.lista.getBotonAbajo() != null) {
                    if(this.lista.getBotonAbajo().esSeleccionado(screenX,screenY)){return true;}
                }

                if(this.lista.getBotonArriba() != null) {
                    if(this.lista.getBotonArriba().esSeleccionado(screenX,screenY)){return true;}
                }
            }
            // comprieba si hay lista y no se ha clicado en sus botones se elimina

            lista.eliminarListaObjetos();
            lista.eliminarListaSuplentes();
            lista.reiniciarPosicionamientoLista();

            campo.accionEntrada(Entrada.clic,touchPos.x, touchPos.y );

        }else{
            longclick=false;
        }

        return false;
    }



    @Override
    public boolean fling(float v, float v2, int i) {
        return false;
    }

    @Override
    /**
     * acciones que se realizan al ejecutar un arrastre por la pantalla
     */
    public boolean pan(float screenX, float screenY, float v3, float v4) {

        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        //System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );

        campo.accionEntrada(Entrada.arrastrar, touchPos.x, touchPos.y);
        return false;
    }

    @Override
    public boolean panStop(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean zoom(float v, float v2) {
        //System.out.println("ZOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOM");
        float distancia = (v2-v);
        //System.out.println("distancia : "+distancia );

        if( Gdx.input.isTouched(1)){
            if(distancia >= 0)
            {
                for(int i=0 ;i<distancia/100;i++){
                    ConstantesJuego.variables().sumarMultiplicado();
                }
            }
            else
            {
                for(int i=0 ;i<-(distancia/100);i++){
                    ConstantesJuego.variables().restarMultiplicado();
                }
            }
        }
        return true;
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {

        return false;
    }

}