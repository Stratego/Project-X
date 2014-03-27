package com.rugbysurvive.partida.gestores;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by aitor on 25/03/14.
 *
 *
 */
public class GestorGrafico implements Dibujante{

    protected int contador;
    protected AssetManager manager;
    protected HashMap<Integer,InformacionTextura> texturasActivas;
    protected HashMap<Integer,Dibujable> dibujables ;
    protected SpriteBatch sprite;
    protected Camara camara;
    protected int tamañoCasilla;



    public GestorGrafico(ArrayList<String> nombresTexturas,int tamañoCasilla)
    {
        this.contador = 0;
        this.camara =  new Camara(1000,1000);
        this.sprite = new SpriteBatch();
        this.texturasActivas = new HashMap<Integer, InformacionTextura>();
        this.manager =  new AssetManager();
        this.dibujables = new HashMap<Integer, Dibujable>();
        this.generarTexturas(nombresTexturas);
        this.tamañoCasilla = tamañoCasilla;

    }



    public void dibujar() {

        this.sprite.begin();
        Iterator it = this.dibujables.values().iterator();


        while (it.hasNext()) {
            Dibujable dibujable =  (Dibujable)it.next();
            Texture textura = this.manager.get(dibujable.getTextura());
            int posicionX = this.filtroX(dibujable.getPosicionX());
            int posicionY = this.filtroY(dibujable.getPosicionY());
            this.sprite.draw(textura,posicionX,posicionY);
        }
        this.camara.render(this.sprite);
        this.sprite.end();
    }

    public void dispose(){
        this.sprite.dispose();
    }
    public Camara getCamara()
    {
        return this.camara;
    }


    @Override
    public void eliminarTextura(int ID) {
        this.dibujables.remove(ID);
    }
    @Override
    public int añadirDibujable(Dibujable dibujable)
    {
        this.contador++;
        this.dibujables.put(this.contador,dibujable);
        return this.contador;
    }

    private void generarTexturas(ArrayList<String> texturas) {


        Iterator it = texturas.iterator();

        while (it.hasNext()) {
            String nombre =  (String)it.next();
            this.manager.load(nombre,Texture.class);
        }
        this.manager.finishLoading();
    }

    private int filtroX(int posicionX)
    {
        if(posicionX > 0 && posicionX < this.tamañoCasilla){
            posicionX = posicionX * tamañoCasilla;
        }
        return posicionX;
    }

    private int filtroY(int posicionY)
    {
        if(posicionY > 0 && posicionY < this.tamañoCasilla){
            posicionY = posicionY * tamañoCasilla;
        }
        return posicionY;
    }

}

