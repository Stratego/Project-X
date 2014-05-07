package com.rugbysurvive.partida.tablero;



import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;


/**
 * Definicion de la casilla, elemento basico del que se compone el tablero de juego
 * Created by Victor on 24/03/14.
 */
public class Casilla implements GestionEntrada ,Dibujable{
    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;


    private Jugador jugador ;
    private ObjetoCampo objeto;
    private Arbitro arbitro;
    private String texturaSeleccionada;
    private int id = -1;
    /**
     * indicara si el elemento esta seleccionado
     */
    private boolean selecionado;

    private boolean existePelota;
    private ElementoDibujable pelota;

    public void colocarPelota() {
        this.existePelota = true;
        this.pelota = new ElementoDibujable(TipoDibujo.elementosJuego, "pelota.png");
        this.pelota.dibujar(this.getPosicionX(), this.getPosicionY());
    }

    public void quitarPelota(){
        this.existePelota = false;
        this.pelota.borrar();
    }

    /**
     *
     * @return Boolean existe pelota
     */
    public boolean hayPelota(){
        return this.existePelota;
    }

    Dibujante dibujante;

    /**
     * Constructor de  casilla
     * @param posX posicion X del la casilla
     * @param posY posicion Y de la casilla
     */
    public Casilla (float posX, float posY) {
        this.posY = posY;
        this.posX = posX;
        this.jugador = null;
        this.objeto = null;
        this.arbitro = null;
        this.texturaSeleccionada = "casellalila.png";
        this.dibujante = dibujante;

    }

    /**
     * Añade un jugador a la casilla en caso de que no exista
     * ningun otro elemento colocado anteriormente
     *
     * En caso que existe algun elemento devolvera false y el
     * jugador no sera colocado , si esta vacia se colocara
     * sin problemas
     *
     * @param jugador elemento que se situa en esta casilla
     * @return si se ha podido colocar el jugador
     */
    public boolean añadirElemento(Jugador jugador)
    {
        if(this.jugador == null && this.objeto == null)
        {
            this.jugador = jugador;
            this.jugador.colocar(this);
            return true;
        }
        return false;
    }

    /**
     * Añade un objeto a la casilla en caso de que no exista
     * ningun otro elemento colocado anteriormente
     *
     * En caso que existe algun elemento devolvera false y el
     * objeto no sera colocado , si esta vacia se colocara
     * sin problemas
     *
     * @param objeto elemento que se situa en esta casilla
     * @return si se ha podido colocar el objeto
     */
    public boolean añadirElemento(ObjetoCampo objeto)
    {
        if(this.jugador == null && this.objeto == null)
        {
            this.objeto = objeto;
            return true;
        }
        return false;
    }


    /**
     * Añade un arbitro a la casilla en caso de que no exista
     * ningun otro elemento colocado anteriormente
     *
     * En caso que existe algun elemento devolvera false y el
     * objeto no sera colocado , si esta vacia se colocara
     * sin problemas
     *
     * @param arbitro elemento que se coloca en la casilla
     * @return si se ha podido colocar o no
     */
    public boolean añadirElemento(Arbitro arbitro)
    {
        if(this.jugador == null && this.objeto == null && arbitro == null)
        {
            this.arbitro = arbitro;
            return true;
        }
        return false;
    }
    /**
     * Indica si no hay ningun elemento situado en esta casilla
     *
     * @return cierto si la casilla esta libre
     */
    public boolean casillaLibre() {
        if(this.jugador == null && this.objeto == null && this.arbitro == null) {
            return true;
        }
        return false;
    }

    /**
     * Elimina el elemento situado en la casilla
     */
    public void eliminarElemento()
    {
        if(jugador != null) {
            this.jugador.quitar();
        }
        this.jugador = null;
        this.objeto = null;
        this.arbitro = null;
    }

    /**
     * Indica si hay un objeto en la casilla
     * @return devuelve cierto en caso que no haya
     * falso en caso contrario
     */
    public boolean sinObjeto()
    {
        if(this.objeto == null) {
            return true;
        }
       return false;
    }

    /**
     * Indica si hay un jugador en la casilla
     * @return devuelve cierto en caso que no haya ,
     * falso en caso contrario
     */
    public boolean sinJugador()
    {
        if(this.jugador == null) {
            return true;
        }
        return false;
    }

    /**
     * Indica si hay un arbitro en la casilla
     * @return devuelve cierto en caso que no haya ,
     * falso en caso contrario
     */
    public boolean sinArbitro()
    {
        if(this.arbitro == null) {
            return true;
        }
        return false;
    }


    public ObjetoCampo getObjeto(){return this.objeto;}


    public void seleccionar(){
        if(id == -1) {
            id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.fondo);
        }
    }
    public void desSeleccionar(){
        if(id!=-1)
        {
            GestorGrafico.generarDibujante().eliminarTextura(id);
             this.id = -1;
        }
    }

    public void setJugador(Jugador jugador)
    {
        this.jugador = jugador;
    }


    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla [][] casillas) {
        //System.out.println(entrada + " x: "+ posX + "y: " + posY);
        if(this.jugador != null)
        {
            //System.out.println("jugador "+entrada + "x: "+ posX + "y: " + posY);
            this.jugador.accionEntrada(entrada, posX, posY, casillas);
        }

    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    @Override
    public void accionEntrada(Entrada entrada) {


    }

    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {

        if (posX == this.posX){
            if (posY == this.posY){
                selecionado=true;
            }
        }else {

            selecionado=false;
        }
        return selecionado;
    }

    public Jugador getJugador()
    {
        return this.jugador;
    }



    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }


    @Override
    public String getTextura() {
        return this.texturaSeleccionada;
    }

    @Override
    public int getPosicionX() {
        return (int)this.getPosX();
    }

    @Override
    public int getPosicionY() {
        return (int)this.getPosY();
    }


}