package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Casilla;

/**
 * Created by Victor on 27/03/14.
 */
public class Jugador implements GestionEntrada, Dibujable{

    private Estado estado;
    private Accion accion = null;
    /*Le asignamos aquí tambien la posicion en la que se encuentra el jugador?*/
    private Casilla casilla = null;

    public Jugador()
    {

    }

    public Jugador(Casilla casilla)
    {
        this.casilla = casilla;
        GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
    }

    public void generarAccion()
    {
        this.estado.generarAccion(this);
    }

    public Estado getEstado()
    {
        return this.estado;
    }

    public void setEstado(Estado estado)
    {
        this.estado = estado;
    }

    public Accion getAccion()
    {
        return this.accion;
    }

    public void setAccion(Accion accion)
    {
        this.accion = accion;
    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    @Override
    public void accionEntrada(Entrada entrada) {

    }


    /*Métodos de dibujable, que habra que quitars*/
    @Override
    public String getTextura() {
        return "jugador1.png";
    }

    @Override
    public int getPosicionX() {
        return (int)this.casilla.getPosX();
    }

    @Override
    public int getPosicionY() {
        return (int)this.casilla.getPosY();
    }

    //public void recibirImput();
}
