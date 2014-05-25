package com.rugbysurvive.partida.elementos.objetos.objetosCampo;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;


/**
 * Created by aitor on 15/04/14.
 */
public class Hielo  extends ObjetoCampo {
        boolean animacion;

    public Hielo(String textura, Jugador jugador) {
        super(textura, jugador);
        this.animacion = false;
    }

    @Override
    public void efecto(Jugador jugador,boolean animacionParada) {
        this.animacion = true;
        if(!animacionParada){
            Gdx.audio.newMusic(Gdx.files.internal("sonido/acciones/hielo.mp3")).play();
        }
        quitar();
    }

    @Override
    protected boolean animacion() {
       return this.animacion;
    }



}
