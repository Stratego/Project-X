package com.rugbysurvive.partida.tablero;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Dibujante;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;

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




    public Campo(Dibujante dibujante)  {
        this.dibujante = dibujante;
        dibujante.añadirDibujable(this,TipoDibujo.fondo);
        this.dibujarTablero();
        campo = this;
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

       for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                        if (casillas[i][j].esSeleccionado((int)(posX/anchura),(int)(posY/altura))){
                            casillas[i][j].accionEntrada(entrada,(int)(posX/anchura),(int)(posY/altura), casillas);
                        }

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



    @Override
    public void accionEntrada(Entrada entrada) {

    }

    @Override
    public String getTextura() {
        return "campo1.png";
    }

    @Override
    public int getPosicionX() {
        return 0;
    }

    @Override
    public int getPosicionY() {
        return 0;
    }
}
