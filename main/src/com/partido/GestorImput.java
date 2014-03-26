package com.partido;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by Victor on 24/03/14.
 */
public class GestorImput implements InputProcessor {

    private OrthographicCamera camera;



    Campo campo = new Campo();
    boolean botonpulsado=true;

    private ArrayList<Boton> botons= new ArrayList <Boton>();



    public GestorImput(OrthographicCamera camera,ArrayList<Boton> botons,Campo campo) {

        this.camera = camera;
        this.botons = botons;
        this.campo = campo;

    }
    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

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
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

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
            campo.accionArrastre(touchPos.x, touchPos.y);
       //}
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
