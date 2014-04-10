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
public class Jugador implements GestionEntrada, Dibujable {

    private Estado estado;
    private Accion accion = null;
    /*Le asignamos aquí tambien la posicion en la que se encuentra el jugador?*/
    private Casilla casilla = null;



    public Jugador()
    {

    }

    public Jugador(Casilla casilla)
    {
        this.estado = new ConPelota();
        this.casilla = casilla;
        GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        this.estado.setBloqueado(false);
        this.getEstado().setSeleccionado(false);
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


    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla[][] casillas)
    {
        boolean accionGenerada = false;

        //Falta hacer una comprobacion de seleccionados para ver si un jugador hace un pase o otra accion

        /**
         * - Recorremos todas las casillas y verificamos si en alguna de ellas hay un jugador con estado seleccionado == true
         * - Si hay un jugador seleccionado, procedemos a verificar cual de ellos es
         * - Una vez localizado se le asigna el pase
         */

        if(this.estado.getBloqueado() == false)
        {
            for (int i = 0; i < 20; i++)
            {
                for (int j = 0; j < 30; j++)
                {
                    if(this.getEstado().getSeleccionado() == true)
                    {
                        /*
                        * Identificamos la casilla que ha lanzado el evento comparando con la X y la Y
                        */
                        if(casillas[i][j].getPosX() == posX && casillas[i][j].getPosY() == posY)
                        {
                            if(entrada == Entrada.clicklargo)
                            {
                                accionGenerada = this.getEstado().generarAccion(this,(int)posX,(int)posY);
                                i=20;
                                j=30;
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



            if(this.estado.getBloqueado() == false)
            {
                if(this.getEstado().getSeleccionado() == false)
                {
                    if(this.casilla.getPosX() == posX && this.casilla.getPosY() == posY)
                    {
                        if(entrada == Entrada.clic)
                        {
                            if(this.getEstado().getSeleccionado() == false)
                            {
                                this.getEstado().setSeleccionado(true);
                                System.out.println(">---------Me seleccionan-------------<");
                            }
                        }
                    }
                }
                else
                {
                    this.getEstado().setSeleccionado(false);
                    System.out.println("<---------Me deseleccionan------------->");

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

    //public void recibirImput();
}
