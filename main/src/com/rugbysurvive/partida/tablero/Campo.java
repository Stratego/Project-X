package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.jugadores.Posicionamiento;

/**
 * Clase que crea y define el comportamiente del terreno del juego.
 * Agrupa el conjunto de casillas y filtra las acciones de entrada
 * del usuario de manera que se identifique facilmente
 * donde se ha realizado la accion.
 * Ademas realiza las mismas funcionalidades que la casilla
 * añadiendo el factor de posicion .
 * Created by Victor on 24/03/14.
 */
public class Campo implements GestionEntrada,Dibujable {

    // permite el uso del campo como singleton
    private static Campo campo;

    Casilla [][] casillas= new Casilla [20][30];
    Dibujante dibujante;



    public Campo()  {
        this.dibujante = GestorGrafico.generarDibujante();
        dibujante.añadirDibujable(this,TipoDibujo.fondo);
        this.dibujarTablero();
        campo = this;
    }

    /**
     * Coloca la pelota en una casilla del tablero
     * indicada por los ejes de coordenadas
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     */
    public void colocarPelota(int posicionX, int posicionY){
        this.casillas[posicionX][posicionY].colocarPelota();
    }

    /**
     * Quita la pelota en una casilla del tablero
     *  indicada por los ejes de coordenadas
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     */
     public void quitarPelota(int posicionX, int posicionY){
        this.casillas[posicionX][posicionY].quitarPelota();
    }

    /**
     * Indica la existencia de una pelota  en una casilla del tablero
     *  indicada por los ejes de coordenadas
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     */
    public void hayPelota(int posicionX, int posicionY){
        this.casillas[posicionX][posicionY].hayPelota();
    }


    /**
     * marca la casilla indicada por los ejes de coordenadas
     * como seleccionada Solo funciona si hay un jugador
     * que puede ser seleccionado
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     */
     public void seleccionarCasilla(int posicionX,int posicionY) {
        this.casillas[posicionY][posicionX].seleccionar();
     }

    /**
     * marca la casilla indicada por los ejes de coordenadas
     * como deseleccionada
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     */
    public void desSeleccionarCasilla(int posicionX,int posicionY)  {
        this.casillas[posicionY][posicionX].desSeleccionar();
    }


    /**
     * Filtra la accion recibida por un usuario y la recalcula indicando
     * cual ha sido la casilla afectada. Ademas realiza un broadcast a todas
     * las casillas para que reciban la señal del usuario filtrada.
     * @param entrada tipo de entrada
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

       double anchura = ConstantesJuego.variables().getAnchoCasilla();
       double altura = ConstantesJuego.variables().getLargoCasilla();

       for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {

                casillas[i][j].accionEntrada(entrada,(int)(posX/anchura),(int)(posY/altura), casillas);

                }
            }

       }



    /**
     *Añade un jugador en la casilla indicada si y solo si no existe
     * ningun otro elemento .
     * Si la casilla esta llena devuelve falso .
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     * @param jugador jugador que se debe añadir en la casilla
     * @return indicador de si se ha podido colocar o no
     */
    public boolean añadirElemento(Jugador jugador, int posicionX,int posicionY) {
        return this.casillas[posicionX][posicionY].añadirElemento(jugador);
    }

    /**
     *Añade un jugador en la casilla indicada si y solo si no existe
     * ningun otro elemento .
     * Si la casilla esta llena devuelve falso .
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     * @param objeto que se debe añadir en la casilla
     * @return indicador de si se ha podido colocar o no
     */
    public boolean añadirElemento(ObjetoCampo objeto,int posicionX,int posicionY)  {
       return this.casillas[posicionX][posicionY].añadirElemento(objeto);
    }

    /**
     *Elimina el elemento existente en la casilla indicada
     *
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
    */
   public void eliminarElemento(int posicionX,int posicionY) {
       this.casillas[posicionX][posicionY].eliminarElemento();
   }

    /**
     * Obtiene el jugador de la casilla indicaada.
     * En caso que no exista tal jugador devuelve falso
     *  @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     * @return jugador situado en la casilla
     */
    public Jugador getJugador(int posicionX,int posicionY) {
        return this.casillas[posicionX][posicionY].getJugador();
    }

    /**
     * Obtiene el objeto de la casilla indicaada.
     * En caso que no exista tal jugador devuelve falso
     * @param posicionX posicion de la casilla en el eje X
     * @param posicionY posicion de la casilla en el eje Y
     * @return objeto situado en la casilla
     */
    public ObjetoCampo getObjeto(int posicionX,int posicionY)
    {
        return this.casillas[posicionX][posicionY].getObjeto();
    }

    /**
     * Añade todos los jugadores del equipo
     * al campo.
     * @param equipo equipo que se desean añadir los jugadores
     */
    public void dibujarEquipo(Equipo equipo) {
        for (Jugador iter :equipo.getJugadores()) {
            this.añadirElemento(iter, iter.getPosicionX(), iter.getPosicionY());

        }
    }

    /**
     * Elimina todos los jugadores de este equipo en el campo
     * @param equipo equipo que se desean eliminar los jugadores
     */
    public void borrarEquipo (Equipo equipo){
        for (Jugador iter :equipo.getJugadores()){
            if(iter.getEnJuego())
            {
                this.eliminarElemento(iter.getPosicionY(), iter.getPosicionX());
            }
        }
    }

    /**
     * Devuelve la casilla donde esta situada la pelota.
     * tanto si la tiene el jugador como la casilla.
     * @return casilla donde esta situada la pelota
     */
    public Casilla posicionPelota(){
        Casilla casilla = null;

        // Se busca en el tablero
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {

                if(casillas[i][j].hayPelota()){
                    casilla = casillas[i][j];
                }

            }
        }

        // Si no existe en el tablero se busca en los jugadores
        if (casilla==null){
            for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo1().listaJugadoresCampo()){

                if (jugador.getEstado() instanceof ConPelota){
                    casilla = jugador.getCasilla();
                }
            }
            for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

                if (jugador.getEstado() instanceof ConPelota){
                    casilla = jugador.getCasilla();
                }
            }
        }

        return casilla;
    }
    /**
     *Se elimina la pelota del tablero
     */
    public void quitarPelotaTablero(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {

                if (casillas[i][j].hayPelota()){
                    casillas[i][j].quitarPelota();
                }
            }
        }
    }

    /**
     * Proceso de inicializavion de las casillas
     * del tablero de juego
     */
    private void dibujarTablero(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                casillas[i][j]=new Casilla(j,i);
            }


        }
    }


    public Casilla [][] getTablero(){
        return casillas;
    }

    @Override
    public void accionEntrada(Entrada entrada) {

    }

    @Override
    public String getTextura() {
        return "tablero/campo.png";
    }

    @Override
    public int getPosicionX() {
        return 0;
    }

    @Override
    public int getPosicionY() {
        return 0;
    }

    public void recolocarJugadoresDespuesDelPunto(Jugador jugador) {
        Posicionamiento.generarSaquePredeterminado(jugador);
    }
    /**
     *Devuelve la Instancia del campo
     * @return instanca del campo
     */
    public static Campo getInstanciaCampo(){return campo;}

    public Casilla getCasilla(int X, int Y) {
        return this.casillas[X][Y];
    }

}
