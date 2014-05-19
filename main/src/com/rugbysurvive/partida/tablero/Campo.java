package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;

/**
 * Clase que crea y define el comportamiente del terreno del juego
 * Created by Victor on 24/03/14.
 */
public class Campo implements GestionEntrada,Dibujable {

    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;


    private static Campo campo;
    Casilla [][] casillas= new Casilla [20][30];



    Dibujante dibujante;


    public Campo()  {
        this.dibujante = GestorGrafico.generarDibujante();
        dibujante.añadirDibujable(this,TipoDibujo.fondo);
        this.dibujarTablero();
        campo = this;
    }

    public void colocarPelota(int posicionX, int posicionY){
        this.casillas[posicionX][posicionY].colocarPelota();
    }

    public void quitarPelota(int posicionX, int posicionY){
        this.casillas[posicionX][posicionY].quitarPelota();
    }

    public void hayPelota(int posicionX, int posicionY){
        this.casillas[posicionX][posicionY].hayPelota();
    }



     public void seleccionarCasilla(int posicionX,int posicionY)
     {
        this.casillas[posicionY][posicionX].seleccionar();
     }

    public void desSeleccionarCasilla(int posicionX,int posicionY)
    {
        this.casillas[posicionY][posicionX].desSeleccionar();
    }
    /**
     * Dibujamos el tablero de juego
     */
    protected void dibujarTablero(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                casillas[i][j]=new Casilla(j,i);
            }


        }
    }


    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

       double anchura = ConstantesJuego.variables().getAnchoCasilla();
       double altura = ConstantesJuego.variables().getLargoCasilla();

        System.out.println("EN campo llega:"+entrada);
       for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                        //Filtro
                       // if (casillas[i][j].esSeleccionado((int)(posX/anchura),(int)(posY/altura))){
                            casillas[i][j].accionEntrada(entrada,(int)(posX/anchura),(int)(posY/altura), casillas);
                       // }

                }
            }

       }

    /**
     * Retorna la Instancia del campo
     * @return instanca del campo
     */
    public static Campo getInstanciaCampo(){return campo;}

    /**
     *
     * @param jugador
     * @param posicionX
     * @param posicionY
     * @return
     */
    public boolean añadirElemento(Jugador jugador, int posicionX,int posicionY)
    {

        return this.casillas[posicionX][posicionY].añadirElemento(jugador);
    }

    /**
     *
     * @param objeto
     * @param posicionX
     * @param posicionY
     * @return
     */
    public boolean añadirElemento(ObjetoCampo objeto,int posicionX,int posicionY)
    {
       return this.casillas[posicionX][posicionY].añadirElemento(objeto);
    }

    public Casilla getCasilla(int X, int Y)
    {
        return this.casillas[X][Y];
    }
    /**
     *
     * @param posicionX
     * @param posicionY
     */
   public void eliminarElemento(int posicionX,int posicionY)
   {
       this.casillas[posicionX][posicionY].eliminarElemento();
   }

    /**
     *
     * @param posicionX
     * @param posicionY
     * @return
     */
    public Jugador getJugador(int posicionX,int posicionY)
    {
        return this.casillas[posicionX][posicionY].getJugador();
    }

    /**
     *
     * @param posicionX
     * @param posicionY
     * @return
     */
    public ObjetoCampo getObjeto(int posicionX,int posicionY)
    {
        return this.casillas[posicionX][posicionY].getObjeto();
    }


    public void dibujarEquipo(Equipo equipo){
        for (Jugador iter :equipo.getJugadores()){
            this.añadirElemento(iter, iter.getPosicionX(), iter.getPosicionY());

        }
    }

    /**
     * Elimina todos los jugadores de este equipo en el campo
     * @param equipo
     */
    public void borrarEquipo (Equipo equipo){
        for (Jugador iter :equipo.getJugadores()){
            if(iter.getEnJuego())
            {
                this.eliminarElemento(iter.getPosicionY(), iter.getPosicionX());
            }
        }
    }

    public Casilla posicionPelota(){
        Casilla casilla = null;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {

                if(casillas[i][j].hayPelota()==true){
                    casilla = casillas[i][j];
                }

            }
        }

        if (casilla==null){
            for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo1().listaJugadoresCampo()){

                if (jugador.getEstado() instanceof ConPelota){
                    casilla = jugador.getCasilla();
                }
            }
        }

        return casilla;
    }

    /**
     * retorna la lista de casillas que forman el tablero, necesario, para la IA
     * @return
     */
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

    public void recolocarJugadoresDespuesDelPunto(Jugador jugador)
    {
        Jugador jugadorChute;

        if(jugador.getMiEquipo().getLado() == Lado.izquierda)
        {
            jugadorChute = recolocarIzquierda();
            int fuerzaDecimal = obtenerHabilidadValorDecimal(jugador.getFuerza());
            Simulador.getInstance().eliminarAccionsSimulador();
            Chute chute = new Chute(jugadorChute, jugadorChute.getPosicionX()-fuerzaDecimal, 9);
            Simulador.getInstance().iniciarSimulacion();
            chute.simular();
        }
        else
        {
            jugadorChute = recolocarDerecha();
            int fuerzaDecimal = obtenerHabilidadValorDecimal(jugador.getFuerza());
            Simulador.getInstance().eliminarAccionsSimulador();
            Chute chute = new Chute(jugadorChute, jugadorChute.getPosicionX()+fuerzaDecimal, 9);
            Simulador.getInstance().iniciarSimulacion();
            chute.simular();
        }

    }

    public int obtenerHabilidadValorDecimal(int valor)
    {
        int valorDecimal = valor/10;

        if(valorDecimal <= 0)
        {
            valorDecimal = 1;
        }

        return valorDecimal;
    }

    public Jugador recolocarIzquierda()
    {
        Jugador jugadorChuta = null;

        int posX = 13;
        int posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo1().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(getCasilla(posY,posX));
            jugador.setEstado(new SinPelota());

            posY += 1;
        }

        posX = 16;
        posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(getCasilla(posY,posX));

            if(posX == 18)
            {
                jugador.setEstado(new ConPelota(jugador));
                jugadorChuta = jugador;
            }
            else
            {
                jugador.setEstado(new SinPelota());
            }

            if(posY == 12)
            {
                posX = 18;
                posY = 9;
            }
            else
            {
                posY += 1;
            }


        }
        return jugadorChuta;
    }

    public Jugador recolocarDerecha()
    {
        Jugador jugadorChuta = null;

        int posX = 16;
        int posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(getCasilla(posY,posX));
            jugador.setEstado(new SinPelota());
            posY += 1;

        }

        posX = 13;
        posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo1().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(getCasilla(posY,posX));

            if(posX == 11)
            {
                jugador.setEstado(new ConPelota(jugador));
                jugadorChuta = jugador;
            }
            else
            {
                jugador.setEstado(new SinPelota());
            }

            if(posY == 12)
            {
                posX = 11;
                posY = 9;
            }
            else
            {
                posY += 1;
            }
        }
        return jugadorChuta;
    }

}
