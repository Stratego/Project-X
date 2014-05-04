package com.rugbysurvive.partida.tablero;

        import com.badlogic.gdx.Gdx;
        import com.rugbysurvive.partida.ConstantesJuego;
        import com.rugbysurvive.partida.Dibujables.TipoDibujo;
        import com.rugbysurvive.partida.Jugador.Jugador;
        import com.rugbysurvive.partida.elementos.ComponentesJuego;
        import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
        import com.rugbysurvive.partida.gestores.Dibujable;
        import com.rugbysurvive.partida.gestores.Entrada.Entrada;
        import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
        import com.rugbysurvive.partida.gestores.GestorGrafico;
        import com.rugbysurvive.partida.gestores.Procesos.Proceso;
        import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;

        import java.util.ArrayList;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public abstract class Boton implements GestionEntrada,Dibujable,Proceso{


    protected float posX;

    protected float posY;

    protected float posYOriginal;

    protected float posXOriginal;

    protected Entrada entrada;

    protected boolean selecionado;

    protected int ID;

    protected String textura;

    protected int posicion;

    protected int ancho;

    protected int alto;

    protected boolean escondido;



    protected boolean procesando;


    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param entrada tipo de boton que sera
     */
    public Boton(float posX, float posY, Entrada entrada,String textura, int posicion,int ancho,int alto) {
        this.posY = posY;
        this.posX = posX;
        this.posXOriginal = posX;
        this.posYOriginal = posY;
        this.entrada = entrada;
        this.textura = textura;
        this.posicion = posicion;
        ID=GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
        this.ancho =ancho;
        this.alto = alto;
        this.escondido = false;
        this.procesando = false;
    }



    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {


        if (posX >= this.posX && posX <= this.posX+this.ancho){
            if (posY >= Gdx.graphics.getHeight() - this.posY -this.alto && posY <= Gdx.graphics.getHeight()  -this.posY){
                accionEntrada(this.entrada);
                selecionado=true;
            }else{
                selecionado=false;
            }

        }else {
            selecionado=false;
        }

        return selecionado;
    }

    public Entrada obtenerEntrada()
    {
        return this.entrada;
    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {



    }


    public void borrar()
    {
        GestorGrafico.generarDibujante().eliminarTextura(this.getID());
    }

    @Override
    public abstract void accionEntrada(Entrada entrada);

    public int getID(){
        return ID;
    }

    public int getPosicion(){
        return this.posicion;
    }

    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return (int)this.posX;
    }

    @Override
    public int getPosicionY() {
        return (int)this.posY;
    }

    @Override
    public boolean procesar(){

       if(!this.escondido){
           if(this.posY > -140){
                this.posY = posY -10;
           }

           else {
               this.escondido = true;
               this.procesando = false;
               return true;
           }
       }

       else{
           if(this.posY <= posYOriginal){
               this.posY = posY +10;
           }

           else {
               this.escondido = false;
               this.procesando = false;
               return true;
           }
       }
       return false;
    }

    public void esconder(){
        if(!this.escondido){
            this.procesando = true;
            ProcesosContinuos.añadirProceso(this);
        }

    }

    public void mostrar() {
        if(this.escondido) {
            this.procesando = true;
            ProcesosContinuos.añadirProceso(this);
        }
    }

    public boolean isEscondido() {
        return escondido;
    }

    public void setEscondido(boolean escondido) {
        this.escondido = escondido;
    }

    public boolean isProcesando() {
        return procesando;
    }
}