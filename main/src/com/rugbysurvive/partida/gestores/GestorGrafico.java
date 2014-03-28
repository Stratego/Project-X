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
    protected HashMap<Integer,Dibujable> dibujables ;
    protected SpriteBatch sprite;
    protected Camara camara;
    protected int tamañoCasilla;
    protected ArrayList <Integer> listaDibujables;



    public GestorGrafico(ArrayList<String> nombresTexturas,int tamañoCasilla)
    {
        this.contador = 0;
        this.camara =  new Camara(1000,1000);
        this.sprite = new SpriteBatch();
        this.manager =  new AssetManager();
        this.dibujables = new HashMap<Integer, Dibujable>();
        this.generarTexturas(nombresTexturas);
        this.tamañoCasilla = tamañoCasilla;
        this.listaDibujables = new ArrayList<Integer>();

    }



    public void dibujar() {

        this.sprite.begin();
        Iterator it = this.dibujables.values().iterator();


        for(int i=0;i<this.dibujables.size();i++)
        {
            Dibujable dibujable =  this.dibujables.get(this.listaDibujables.get(i));
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
        this.listaDibujables.remove(this.dibujables.get(ID));
        this.dibujables.remove(ID);
    }
    @Override
    public int añadirDibujable(Dibujable dibujable)
    {
        this.dibujables.put(this.contador,dibujable);
        this.listaDibujables.add(this.contador);
        this.contador++;
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

