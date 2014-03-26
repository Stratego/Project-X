package com.rugbysurvive.partida.gestores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by aitor on 25/03/14.
 */
public class GestorGrafico {

    protected AssetManager manager;

    protected HashMap<String,InformacionTextura> texturasActivas;
    protected SpriteBatch sprite;
    protected Camara camara;

    public GestorGrafico()
    {
        this.camara =  new Camara(1000,1000);
        this.sprite = new SpriteBatch();
        this.texturasActivas = new HashMap<String, InformacionTextura>();
        this.manager =  new AssetManager();
        // INTRODUCIR TODAS LAS TEXTURAS NECESARIAS PARA EL JUEGO A PARTIR DE LOS EQUIPOS
        this.manager.load("jugador1.png",Texture.class);
        this.manager.finishLoading();
    }


    public void cargarTextura(String nombreTextura){
        if(manager.isLoaded(nombreTextura))
        {
            //this.manager.lo
        }
    }
    public void eliminarTextura(String nombreTextura){
        //Falta implementar
    }


    public void dispose(){
        this.sprite.dispose();
    }

    /*

     */
    public void actualizar(String nombretextura ,int posicionX,int posicionY){
        if(this.texturasActivas.containsKey(nombretextura)){
            this.texturasActivas.remove(nombretextura);
            InformacionTextura info = new InformacionTextura(nombretextura,posicionX,posicionY);
            this.texturasActivas.put(nombretextura,info);
        }
    }

    public void dibujar(){

        this.sprite.begin();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Iterator it = this.texturasActivas.entrySet().iterator();

        this.sprite.draw(this.manager.get("jugador1.png",Texture.class),0,0);
        System.out.print("HOLAAAAAAAAAAAAAAAa");
       /* while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            InformacionTextura iTextura = (InformacionTextura)e.getValue();
            Texture textura = this.manager.get(iTextura.nombretextura, Texture.class);
            float posicionX = (float)iTextura.posicionX;
            float posicionY = (float)iTextura.posicionY;
            this.sprite.draw(textura,posicionX,posicionY);
        }*/
        this.camara.render(this.sprite);
        this.sprite.end();
    }


    public Camara getCamara()
    {
        return this.camara;
    }
    private class InformacionTextura
    {
        public String nombretextura;
        public int posicionX;
        public int posicionY;

        public InformacionTextura(String nombretextura ,int posicionX,int posicionY){
            this.nombretextura = nombretextura;
            this.posicionX = posicionX;
            this.posicionY = posicionY;
        }

    }

}

