package com.rugbysurvive.partida.gestores;

import android.util.Log;
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
    protected String TAG = "GESTOR GRAFICO";
    protected int vueltas = 0;

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

        this.camara.render(this.sprite);
        this.sprite.begin();
        this.vueltas++;
        //Log.i(TAG,"num iteraciones: "+this.vueltas);
        for(int i=0;i<this.listaDibujables.size();i++)
        {
            Dibujable dibujable =  this.dibujables.get(this.listaDibujables.get(i));
            //Log.i(TAG,dibujable.getTextura());
            Texture textura = this.manager.get(dibujable.getTextura());
            int posicionX = this.filtroX(dibujable.getPosicionX());
            int posicionY = this.filtroY(dibujable.getPosicionY());
            this.sprite.draw(textura,posicionX,posicionY);
        }
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
        //Log.i("BORRAR","BORRANDO antes depurar: "+ID);
        if(this.dibujables.containsKey(ID)){
            this.borrarElementoLista(ID);
            this.dibujables.remove(ID);
            //Log.i("BORRAR","BORRANDO");
        }
    }
    @Override
    public int añadirDibujable(Dibujable dibujable)
    {
        this.contador++;
        this.dibujables.put(this.contador,dibujable);
        this.listaDibujables.add(this.contador);
        return this.contador;
    }

    private void borrarElementoLista(int ID)
    {
        boolean encontrado = false;
        int i = 0;
        int valor = 0;


        while(!encontrado && this.listaDibujables.size() > i ){
            valor = this.listaDibujables.get(i);
            if(valor == ID)
            {
                valor = i;
                encontrado = true;
            }
            i++;
        }
        if(encontrado)
        {
            this.listaDibujables.remove(valor);
        }
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

