package com.rugbysurvive.partida.elementos.objetos.objetosCampo;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;



/**
 * Objeto colocable en el campo que bajara la vida
 * al jugador enemigo que lo pise, se ejecutara una
 * animacion de explosion
 * Created by Victor on 15/04/14.
 */
public class MinaCampo extends ObjetoCampo {

    /**
     * tiempo entre el cambio de texturas de la animacion
     */
    private static final int TIEMPO_ENTRE_TEXTURAS = 5;

    /**
     * tiempo que se ejecutara la animacion
     */
    private int tiempo;

    /**
     * dibujo de la animacion en el campo
     */
    private ElementoDibujable animacion;


    private int contador;

    /**
     * indica si se esta ejecutando la animacion
     */
    public boolean animando;

    /**
     * constructor de la mina campo
     * @param textura textura que representara graficamente la mina en el campo
     * @param jugador jugador que ha colocado la mina
     */
    public MinaCampo(String textura,Jugador jugador){
        super(textura,jugador);
        this.tiempo = 0;
        this.animando = false;
        this.contador = 1;

    }
    @Override
    public void efecto(Jugador jugador,boolean animacionParada) {
        jugador.lesionar(100);

       if(!animacionParada){
             Gdx.audio.newMusic(Gdx.files.internal("sonido/acciones/explosion.mp3")).play();
            this.animando = true;
        }
        quitar();
    }

    @Override
    protected boolean animacion() {

        if(this.animando){
       if(tiempo%TIEMPO_ENTRE_TEXTURAS == 0 && tiempo >0) {
               this.animacion.borrar();
               this.animacion = new ElementoDibujable(TipoDibujo.elementosJuego,"objetos/explosion/explosio"+contador+".png");
               this.animacion.dibujar(this.getPosicionX(),this.getPosicionY());
            System.out.println("==================INICIANDO EXPLOSION=========================");

           this.contador++;
       }
       else if(tiempo == 0){
               this.animacion = new ElementoDibujable(TipoDibujo.elementosJuego,"objetos/explosion/explosio"+contador+".png");
               this.animacion.dibujar(this.getPosicionX(),this.getPosicionY());
               this.contador++;
           System.out.println("==================EXPLOSION=========================");
       }

       if(this.tiempo%30 == 0 && tiempo >0) {
           this.animacion.borrar();
           return true;
       }
       this.tiempo++;
       return false;
    }
        return false;
    }



}
