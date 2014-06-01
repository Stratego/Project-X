package com.rugbysurvive.partida.gestores.Entrada;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Lista;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.tablero.Boton;
import com.rugbysurvive.partida.tablero.Campo;

import java.util.ArrayList;

/**
 * Created by Victor on 24/03/14.
 * Clase que permite recibir las acciones que se recibiran en la pantalla del dispositivo
 * como click, long click, arrastre o pinza. en funcion de cada accion
 * podemos definir un comportamiento para ella.
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

    /**
     * Definicion de la interfaz que permite dibujar elementos
     */
    Dibujante dibujante;


    /**
     * crea un elemento de tipo lista para guardar suplentes o objetos
     */
    Lista lista = new Lista();

    private Sound botonsound ;

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
        this.botonsound = Gdx.audio.newSound(Gdx.files.internal("sonido/botones/Boton.mp3"));

    }

    @Override
    /**
     * Al hacer longpress mandamos la accion hacia el campo que lo mandara a la casilla donde se
     * tratara la informacion. tambien permite seleccioar los botones de la interfaz
     * @param screenX posicion X donde hemos puldado en la pantalla
     * @param screenY posicion X donde hemos puldado en la pantalla
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
        return false;
    }

    @Override
    /**
     * Al levantar el dedo al hacer click mandamos la accion hacia el campo que lo mandara a la casilla donde se
     * tratara la informacion. tambien permite seleccioar los botones de la interfaz o de las listas
     * de suplentes o de objetos
     * @param screenX posicion X donde hemos puldado en la pantalla
     * @param screenY posicion X donde hemos puldado en la pantalla
     */
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
                    this.botonsound.play();

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
                        this.botonsound.play();
                        return false;
                    }
                }

                if(this.lista.getBotonAbajo() != null) {
                    if(this.lista.getBotonAbajo().esSeleccionado(screenX,screenY)){
                        this.botonsound.play();
                        return true;}
                }

                if(this.lista.getBotonArriba() != null) {
                    if(this.lista.getBotonArriba().esSeleccionado(screenX,screenY)){
                        this.botonsound.play();
                        return true;}
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
     * Al arrastrar el dedo por la pantalla mandamos la accion hacia el campo que lo mandara a la casilla donde se
     * tratara la informacion.
     * @param screenX posicion X donde hemos puldado en la pantalla
     * @param screenY posicion X donde hemos puldado en la pantalla
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