package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rugbysurvive.partida.gestores.Camara;
import com.rugbysurvive.partida.gestores.GestorGrafico;

public class SkeletonMain extends Game {

<<<<<<< HEAD
    InputMultiplexer multiplexer;
    GestorGrafico gestorGrafico;

    @Override
    public void create() {

        this.gestorGrafico = new GestorGrafico();
       // this.gestorGrafico.cargarTextura("tablero/campo1.png");
        //this.gestorGrafico.actualizar("tablero/campo1.png",0,0);
        this.multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.gestorGrafico.getCamara());
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void dispose() {
        this.gestorGrafico.dispose();
    }

    @Override
    public void render() {

    this.gestorGrafico.dibujar();


    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
=======
	@Override
	public void create() {
		System.out.println("Application started");
        
	}
>>>>>>> a459603f127b7a7dcf20b4abc14450e01ea60686
}


