package com.rugbysurvive.partida.gestores;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by aitor on 25/03/14.
 */
public class GestorGrafico implements Dibujante{

    protected int contador;
    protected AssetManager manager;
    protected HashMap<Integer,InformacionTextura> texturasActivas;
    protected ArrayList<Dibujable> dibujables ;
    protected SpriteBatch sprite;
    protected Camara camara;

    public GestorGrafico(ArrayList<String> nombresTexturas)
    {
        this.contador = 0;
        this.camara =  new Camara(1000,1000);
        this.sprite = new SpriteBatch();
        this.texturasActivas = new HashMap<Integer, InformacionTextura>();
        this.manager =  new AssetManager();
        this.dibujables = new ArrayList<Dibujable>();
        this.generarTexturas(nombresTexturas);

    }


    public void eliminarTextura(String nombreTextura) {
        //Falta implementar
    }


    public void dispose(){
        this.sprite.dispose();
    }



    public void generarTexturas(ArrayList<String> texturas) {


        Iterator it = texturas.iterator();

        while (it.hasNext()) {
            String nombre =  (String)it.next();
            this.manager.load(nombre,Texture.class);
        }
        this.manager.finishLoading();
    }

    /*

     */
    public void dibujar() {

        this.sprite.begin();
        Iterator it = this.dibujables.iterator();


        while (it.hasNext()) {
            Dibujable dibujable =  (Dibujable)it.next();
            Texture textura = this.manager.get(dibujable.getTextura());
            this.sprite.draw(textura,dibujable.getPosicionX(),dibujable.getPosicionY());
        }
        this.camara.render(this.sprite);
        this.sprite.end();
    }


    public Camara getCamara()
    {
        return this.camara;
    }


    @Override
    public void añadirDibujable(Dibujable dibujable)
    {
        this.dibujables.add(dibujable);
    }



}

