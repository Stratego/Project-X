package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by Victor on 27/03/14.
 */
public class Jugador implements GestionEntrada, Dibujable {

    private Estado estado;
    private Accion accion = null;
    /*Le asignamos aquí tambien la posicion en la que se encuentra el jugador?*/
    private Casilla casilla = null;

    /*Estas dos variables las ponemos aquí y no en estado, ya que si cambiamos el estado perdemos el valor de las variables*/
    public boolean seleccionado = false;
    public boolean bloqueado = false;

    private ArrayList<PowerUP> powerup;

    public int Fuerza;

    public int Vida;

    public int Defensa;

    public Jugador()
    {

    }

    public Jugador(Casilla casilla)
    {
        this.estado = new ConPelota();
        //this.estado = new EnMovimiento(8);
        this.casilla = casilla;
        GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        this.estado.setBloqueado(false);
        this.getEstado().setSeleccionado(false);
    }

    public Jugador(Casilla casilla, int fuerza,int vida, int defensa, ArrayList<PowerUP> powerup)
    {
        this.Fuerza= fuerza;
        this.Vida = vida;
        this.Defensa = defensa;

        if(powerup.size() <= 4)
        {
            this.powerup = powerup;
        }

        this.estado = new ConPelota();
        //this.estado = new EnMovimiento(8);
        //this.estado = estado;

        this.casilla = casilla;
        this.estado.setBloqueado(false);
        this.getEstado().setSeleccionado(false);


        GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
    }

    public ArrayList<PowerUP> getPowerUP()
    {
        return this.powerup;
    }

    public PowerUP getPowerUP(int index)
    {
        if(index>=0 && index<4)
        {
            return this.powerup.get(index);
        }

        return null;
    }

    public void setPowerUP(PowerUP powerup)
    {
        if(this.powerup.size() <= 4)
        {
            this.powerup.add(powerup);
        }
    }

    public void setPowerUP(PowerUP powerup, int index)
    {
        if(index>=0 && index<4)
        {
            this.powerup.add(index, powerup);
        }
    }

    public void generarAccion(int posX, int posY)
    {
        this.estado.generarAccion(this, (int)posX, (int)posY);
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

    public boolean getSeleccionado()
    {
        return this.seleccionado;
    }

    public void setSeleccionado(boolean seleccionado)
    {
        this.seleccionado = seleccionado;
    }

    public boolean getBloqueado()
    {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado)
    {
        this.bloqueado = bloqueado;
    }


    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla[][] casillas)
    {
        boolean accionGenerada = false;

        GestorGrafico.getCamara().bloquear();

        //Falta hacer una comprobacion de seleccionados para ver si un jugador hace un pase o otra accion

        /**
         * - Recorremos todas las casillas y verificamos si en alguna de ellas hay un jugador con estado seleccionado == true
         * - Si hay un jugador seleccionado, procedemos a verificar cual de ellos es
         * - Una vez localizado se le asigna el pase
         */

        if(this.getBloqueado() == false)
        {

            for (int i = 0; i < 20; i++)
            {
                for (int j = 0; j < 30; j++)
                {
                    if(this.getSeleccionado() == true)
                    {
                        /*
                        * Identificamos la casilla que ha lanzado el evento comparando con la X y la Y
                        */
                        if(casillas[i][j].getPosX() == posX && casillas[i][j].getPosY() == posY)
                        {
                            if(entrada == Entrada.clicklargo || entrada == Entrada.arrastrar)
                            {
                                if(this.getSeleccionado() == true)
                                {
                                    accionGenerada = this.getEstado().generarAccion(this,(int)posX,(int)posY, entrada);
                                    i=20;
                                    j=30;
                                }
                            }
                        }
                    }
                }

            }
            if(accionGenerada == true)
            {
                this.setEstado(new SinPelota());
                System.out.println("me quedo sin pelota");
            }





            if(this.getBloqueado() == false)
            {
                if(this.getSeleccionado() == false)
                {
                    if(this.casilla.getPosX() == posX && this.casilla.getPosY() == posY)
                    {
                        if(entrada == Entrada.clic)
                        {
                            this.setSeleccionado(true);
                            System.out.println(">---------Me seleccionan-------------<");
                        }
                    }
                }
                else
                {
                    if(entrada == Entrada.clic)
                    {
                        this.setSeleccionado(false);
                        System.out.println("<---------Me deseleccionan------------->");
                    }

                }
            }
            else
            {
                System.out.println("<---------Estoy bloqueado-1------------>");
            }
        }
       /* else
        {
            System.out.println("<---------Estoy bloqueado-2------------>");
        }*/
    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    @Override
    public void accionEntrada(Entrada entrada) {
        System.out.println("---------------------------------Tipo entrada:"+entrada);
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

    public int getFuerza() {
        return Fuerza;
    }

    public void setFuerza(int fuerza) {
        Fuerza = fuerza;
    }

    public int getVida() {
        return Vida;
    }

    public void setVida(int vida) {
        Vida = vida;
    }

    public int getDefensa() {
        return Defensa;
    }

    public void setDefensa(int defensa) {
        Defensa = defensa;
    }


    //public void recibirImput();
}
