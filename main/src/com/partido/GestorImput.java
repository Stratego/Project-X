package com.partido;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Esta clase recibira nuestras acciones en la pantalla del telefono y en funcion de cada accion ejecuara lo que le hallamos definido
 * Created by Victor on 24/03/14.
 */
public class GestorImput implements GestureDetector.GestureListener {

    /**
     * definimos un elemento camara que usaremos para hacer la traslación entre camara y terreno de juego
     */
    private OrthographicCamera camera;


    /**
     * creamos el campo de juego
     */
    Campo campo = new Campo();


    /**
     * variable que nos indicara si se ha pulsado un boton
     */
    boolean botonpulsado=true;


    /**
     * colecion de botones que nuestro juego mostrara en pantalla
     */
    private ArrayList<Boton> botons= new ArrayList <Boton>();

    /**
     * constructor del elemento GestorImput
     * @param camera camare que visualizara el juego
     * @param botons coleccion de botones del juego
     * @param campo campo de nuestro juego
     */
    public GestorImput(OrthographicCamera camera,ArrayList<Boton> botons,Campo campo) {

        this.camera = camera;
        this.botons = botons;
        this.campo = campo;

    }




    @Override
    /**
     * acciones que se realizan al hacer longpress
     */
    public boolean longPress (float screenX, float screenY) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        //recorrer lista de botones
        for (Boton iterador : botons){
            if (touchPos.x>iterador.getPosX() && touchPos.x < 64 ){
                if (touchPos.y > iterador.getPosY() && touchPos.y < 64){

                    if(iterador.getNombre().equals("boton1")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton1);
                        }
                    }else if(iterador.getNombre().equals("boton2")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton2);
                        }
                    }else if(iterador.getNombre().equals("boton3")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }else if(iterador.getNombre().equals("boton4")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }
                }
            }else{
                botonpulsado= false;
            }
        }
        if (botonpulsado ==false) {
            campo.accionEntrada(Imput.longclick,touchPos.x, touchPos.y );
            botonpulsado = true;
        }

        return false;
    }




    @Override
    /**
     * acciones que se ejecutaran al realizar un click en la pantalla
     */
    public boolean touchDown(float screenX, float screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        //recorrer lista de botones
        for (Boton iterador : botons){
            if (touchPos.x>iterador.getPosX() && touchPos.x < 64 ){
                if (touchPos.y > iterador.getPosY() && touchPos.y < 64){

                    if(iterador.getNombre().equals("boton1")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton1);
                        }
                    }else if(iterador.getNombre().equals("boton2")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton2);
                        }
                    }else if(iterador.getNombre().equals("boton3")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }else if(iterador.getNombre().equals("boton4")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }
                }
            }else{
                botonpulsado= false;
            }
        }
        if (botonpulsado ==false) {
            campo.accionEntrada(Imput.click,touchPos.x, touchPos.y );
            botonpulsado = true;
        }


        return false;
    }

    @Override
    public boolean tap(float v, float v2, int i, int i2) {

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
        System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        campo.accionEntrada(Imput.arrastre, touchPos.x, touchPos.y);
        return false;
    }

    @Override
    public boolean panStop(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean zoom(float v, float v2) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        return false;
    }

    /*
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        //recorrer lista de botones
        for (Boton iterador : botons){
            if (touchPos.x>iterador.getPosX() && touchPos.x < 64 ){
                if (touchPos.y > iterador.getPosY() && touchPos.y < 64){

                    if(iterador.getNombre().equals("boton1")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton1);
                        }
                    }else if(iterador.getNombre().equals("boton2")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton2);
                        }
                    }else if(iterador.getNombre().equals("boton3")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }else if(iterador.getNombre().equals("boton4")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }
                }
            }else{
                botonpulsado= false;
            }
        }
        if (botonpulsado ==false) {
            campo.accionEntrada(Imput.click,touchPos.x, touchPos.y );
        }




        return false;
    }*/

    /*
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY,0);
        camera.unproject(touchPos);
        System.out.println("Posicion tocada: x: " + screenX + " y: "+ screenY );
        System.out.println("Posicion mundo: x: " + touchPos.x + " y: "+ touchPos.y );

        //recorrer lista de botones
        /*for (Boton iterador : botons){
            if (touchPos.x>iterador.getPosX() && touchPos.x < 64 ){
                if (touchPos.y > iterador.getPosY() && touchPos.y < 64){

                    if(iterador.getNombre().equals("boton1")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton1);
                        }
                    }else if(iterador.getNombre().equals("boton2")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton2);
                        }
                    }else if(iterador.getNombre().equals("boton3")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }else if(iterador.getNombre().equals("boton1")){
                        if (iterador.esSeleccionado(touchPos.x,touchPos.y)){
                            campo.accionEntrada(Imput.boton3);
                        }
                    }
                }
            }else{
                botonpulsado= false;
            }
        }
        if (botonpulsado ==false) {*/
    // campo.accionEntrada(Imput.arrastre, touchPos.x, touchPos.y);
    //}
    //return false;
    //}
}
