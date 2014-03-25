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

    //private ArrayList<Boton> botons= new ArrayList <Boton>();

    Boton boton1 = new Boton(0,150);

    Boton boton2 = new Boton(0, 350);


    Boton boton3 = new Boton(300, 150);


    Boton boton4 = new Boton(300, 350);

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
        //System.out.println("Unidad selecionada");
        /*if (touchPos.x > 0 && touchPos.x < 64){
            if (touchPos.y > 0 && touchPos.y < 64){
                System.out.println("Unidad selecionada");
            }

        }*/

        //recorrer lista de botones
        for (Boton iterador : botons){
            if (touchPos.x>iterador.getPosX() && touchPos.x < 64 ){
                if (touchPos.y > iterador.getPosY() && touchPos.y < 64){
                    if (boton1.esSeleccionado(touchPos.x,touchPos.y)){
                    campo.accionEntrada(Imput.boton1);
                }
            }
         }else{
                botonpulsado= false;
            }
        }
        if (botonpulsado ==false) {
            campo.accionEntrada(Imput.click,(int)touchPos.x, (int)touchPos.y );
        }




        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
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
