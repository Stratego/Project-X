package com.rugbysurvive.partida.gestores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.ResolucionPantalla;
import com.rugbysurvive.partida.gestores.Entrada.DibujableEscalado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by aitor on 25/03/14.
 * Agrupa todas las texturas que tiene que ser dibujadas .
 * Cada textura sera dibujada acorde a las necesidades establecidas
 * por el usuario.
 *
 *
 */
public class GestorGrafico implements Dibujante{

    private int contador;
    private AssetManager manager;
    private ArrayList<TipoImagen> dibujables ;
    private SpriteBatch sprite;
    private static Camara camara;
    private int tamañoCasilla;

    private String TAG = "GESTOR GRAFICO";
    private int vueltas = 0;
    private static Dibujante instancia = null;
    private int ultimaPosicionFondos ;
    private BitmapFont font;
    private int posXPaseInicial = 0;
    private int posYPaseInicial = 0;
    private int posXPaseFinal = 0;
    private int posYPaseFinal = 0;

    private Pixmap pixmap;
    private Texture texture ;

    /**
     * Carga en memoria todas las texturas
     * Genera toda la estructura necesariapara el dibujado
     * @param nombresTexturas listado de texturas que se desean cargan
     * @param tamañoCasilla tamaño de una casilla del juego.
     */
    public GestorGrafico(ArrayList<String> nombresTexturas,int tamañoCasilla)
    {
        this.contador = 0;
        this.camara =  new Camara(ConstantesJuego.variables().getAnchoTablero(),ConstantesJuego.variables().getAltoTablero());
        this.sprite = new SpriteBatch();
        this.manager =  new AssetManager();
        this.dibujables = new ArrayList<TipoImagen>();
        this.generarTexturas(nombresTexturas);
        this.tamañoCasilla = tamañoCasilla;
        this.font=  new BitmapFont();

        pixmap = new Pixmap(2048,2048, Pixmap.Format.RGBA8888);
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

                        //constantes.setResolucionPantalla(constantes.calcularResolucion());
                        //constantes.setMultiplicador(constantes.multiplicador());


                    //    double ancho = constantes.generarTamaño(textura.getWidth());
                      //  double alto = constantes.generarTamaño(textura.getHeight());

                        double ancho = constantes.generarTamaño(textura.getWidth());
                        double alto = constantes.generarTamaño(textura.getHeight());

                        this.sprite.draw(textura,(float)posicionX,(float)posicionY,(float)ancho,(float)alto);

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

                     int posicionX = imagen.dibujable.getPosicionX();
                     int posicionY = imagen.dibujable.getPosicionY();
                     posicionX = posicionX - this.camara.getVariationX();
                     posicionY = posicionY + this.camara.getVariationY();


                     font.draw(this.sprite,imagen.dibujable.getTextura(), posicionX,posicionY);

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
        TipoImagen tipoImagenAux =null;
        for( TipoImagen tipoImagen:this.dibujables)
        {
            if(tipoImagen.ID == ID){
                tipoImagenAux = tipoImagen;
                break;
            }
        }
        if(tipoImagenAux != null) {
            this.dibujables.remove(tipoImagenAux);
        }
            //Log.i("BORRAR","BORRANDO");

    }

    @Override
    public void dibujarLinia(int posicionXInicial, int posicionYInicial, int posicionXFinal, int posicionYFinal) {

        System.out.println("Dibujar linia activado");
        this.posXPaseFinal = posicionXFinal;
        this.posXPaseInicial = posicionXInicial;
        this.posYPaseFinal = posicionYFinal;
        this.posYPaseInicial = posicionYInicial;



        double posIniX = this.filtroX((double)this.posXPaseInicial);
        double posIniY =  this.filtroY((double)this.posYPaseFinal);
        double posFiX = this.filtroX((double)this.posXPaseInicial);
        double posFiY =  this.filtroY((double)this.posYPaseFinal);

        Gdx.gl20.glLineWidth(20);
        pixmap.setColor(Color.BLUE);
        //pixmap.drawLine((int) posIniX, (int) posIniY, (int) posFiX, (int) posFiY);
        pixmap.drawLine(0,0, 500,500);

        texture = new Texture(pixmap);




    }

    @Override
    public int añadirDibujable(DibujableEscalado dibujable, TipoDibujo tipoDibujo) {
        this.contador++;
        this.dibujables.add(new TipoImagen(tipoDibujo,dibujable,this.contador));
        return this.contador;
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
        public double escalado;

        public TipoImagen(TipoDibujo tipoDibujo,Dibujable dibujable,int ID)
        {
            this.ID = ID;
            this.tipoDibujo = tipoDibujo;
            this.dibujable = dibujable;
            this.escalado = 1;
        }

        public TipoImagen(TipoDibujo tipoDibujo,DibujableEscalado dibujable,int ID)
        {
            this.ID = ID;
            this.tipoDibujo = tipoDibujo;
            this.dibujable = dibujable;
            this.escalado =dibujable.getEscalado();
        }
    }

    private void configurarFuente()
    {
        // Aqui se configura la fuente
            this.font.scale(2);
    }
}

