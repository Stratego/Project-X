package com.rugbysurvive.partida.gestores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;

import java.util.ArrayList;
import java.util.Collection;
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
    protected ArrayList<TipoImagen> dibujables ;
    protected SpriteBatch sprite;
    protected static Camara camara;
    protected int tamañoCasilla;

    protected String TAG = "GESTOR GRAFICO";
    protected int vueltas = 0;
    protected static Dibujante instancia = null;
    protected int ultimaPosicionFondos ;
    BitmapFont font;


    public GestorGrafico(ArrayList<String> nombresTexturas,int tamañoCasilla)
    {
        this.contador = 0;
        this.camara =  new Camara(1000,1000);
        this.sprite = new SpriteBatch();
        this.manager =  new AssetManager();
        this.dibujables = new ArrayList<TipoImagen>();
        this.generarTexturas(nombresTexturas);
        this.tamañoCasilla = tamañoCasilla;
        this.font=  new BitmapFont();


        instancia = (Dibujante)this;
        this.ultimaPosicionFondos = 0;
        this.configurarFuente();
    }


    public static Dibujante generarDibujante()
    {
        return instancia;
    }
    public void dibujar() {

        this.camara.render(this.sprite);
        this.sprite.begin();

        ArrayList<TipoDibujo> tiposDibujo = new ArrayList<TipoDibujo>();
        tiposDibujo.add(TipoDibujo.fondo);
        tiposDibujo.add(TipoDibujo.elementosJuego);
        tiposDibujo.add(TipoDibujo.interficieUsuario);
        tiposDibujo.add(TipoDibujo.texto);

        //Log.i(TAG,"num iteraciones: "+this.vueltas);
        ConstantesJuego constantes = ConstantesJuego.variables();
        for(int i=0;i<3;i++)
        {
            for(TipoImagen imagen : this.dibujables)
             {
                if(imagen.tipoDibujo == tiposDibujo.get(i))
                {
                    Texture textura = this.manager.get(imagen.dibujable.getTextura());



                    if(TipoDibujo.interficieUsuario == tiposDibujo.get(i)){

                        int posicionX = imagen.dibujable.getPosicionX();
                        int posicionY = imagen.dibujable.getPosicionY();
                        posicionX = posicionX - this.camara.getVariationX();
                        posicionY = posicionY + this.camara.getVariationY();
                        double ancho = textura.getWidth();
                        double alto = textura.getHeight();
                        this.sprite.draw(textura,posicionX,posicionY);

                    }
                    else
                    {
                        double posicionX = this.filtroX(imagen.dibujable.getPosicionX());
                        double posicionY = this.filtroY(imagen.dibujable.getPosicionY());

                        double multiplicador = constantes.getMultiplicador();
                        double ancho = textura.getWidth()*multiplicador;
                        double alto = textura.getHeight()*multiplicador;
                        this.sprite.draw(textura,(float)posicionX,(float)posicionY,(float)ancho,(float)alto);

                    }


                }
                 if(TipoDibujo.interficieUsuario== tiposDibujo.get(i) && imagen.tipoDibujo == TipoDibujo.texto){

                     font.draw(this.sprite,imagen.dibujable.getTextura(), imagen.dibujable.getPosicionX(),
                             imagen.dibujable.getPosicionX());
                 }


            }
        }
        this.sprite.end();


    }

    public void dispose(){
        this.sprite.dispose();
    }
    public static Camara getCamara()
    {
        return camara;
    }


    @Override
    public void eliminarTextura(int ID) {
        //Log.i("BORRAR","BORRANDO antes depurar: "+ID);
        for( TipoImagen tipoImagen:this.dibujables)
        {
            if(tipoImagen.ID == ID){
                this.dibujables.remove(tipoImagen);
            }
        }

            //Log.i("BORRAR","BORRANDO");

    }
    @Override
    public int añadirDibujable(Dibujable dibujable,TipoDibujo tipoDibujo)
    {
        this.contador++;
        this.dibujables.add(new TipoImagen(tipoDibujo,dibujable,this.contador));
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




    private double filtroX(double posicionX)
    {
        double tamañoCasilla = ConstantesJuego.variables().getAnchoCasilla();
        if(posicionX > 0 && posicionX < tamañoCasilla){
            posicionX = posicionX * tamañoCasilla;
        }
        return posicionX;
    }

    private double filtroY(double posicionY)
    {
        double tamañoCasilla = ConstantesJuego.variables().getLargoCasilla();
        if(posicionY > 0 && posicionY < tamañoCasilla){
            posicionY = posicionY * tamañoCasilla;
        }
        return posicionY;
    }

    private class TipoImagen
    {
        public TipoDibujo tipoDibujo;
        public Dibujable dibujable;
        public int ID;

        public TipoImagen(TipoDibujo tipoDibujo,Dibujable dibujable,int ID)
        {
            this.ID = ID;
            this.tipoDibujo = tipoDibujo;
            this.dibujable = dibujable;
        }
    }

    private void configurarFuente()
    {
        // Aqui se configura la fuente
        this.font.scale(2);
    }
}

