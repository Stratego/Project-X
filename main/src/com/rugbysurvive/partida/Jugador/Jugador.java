package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;

/**
 * Created by Victor on 27/03/14.
 */
public class Jugador implements GestionEntrada, Dibujable {

    private static final int MAXIMO_OBJETOS = 4;
    private String textura ;
    private DireccionJugador direccion;
    private ElementoDibujable seleccion;
    private ElementoDibujable bloqueo;

    private Estado estado;
    private Accion accion = null;
    /*Le asignamos aquí tambien la posicion en la que se encuentra el jugador?*/
    private Casilla casilla = null;

    /*Estas dos variables las ponemos aquí y no en estado, ya que si cambiamos el estado perdemos el valor de las variables*/
    public boolean seleccionado = false;
    public boolean bloqueado = false;

    private ArrayList<ObjetoJugador> powerup;

    public int Fuerza;

    public int Vida;

    public int Defensa;

    public int id;

    public boolean enJuego;

    public Equipo miEquipo;



    public Jugador(int fuerza,int vida, int defensa, Equipo equipo)
    {
        this.Fuerza= fuerza;
        this.Vida = vida;
        this.Defensa = defensa;

        this.miEquipo = equipo;

        this.powerup= new ArrayList<ObjetoJugador>();

        this.estado = new ConPelota();

        this.estado.setBloqueado(false);
        this.setSeleccionado(false);
        this.id = -1;
        this.enJuego = false;
        this.textura = "jugador1.png";

    }

    public Equipo getMiEquipo()
    {
        return this.miEquipo;
    }

    public void añadirObjeto(ObjetoJugador objeto){
       if(this.powerup.size() < MAXIMO_OBJETOS)
       {
            this.powerup.add(objeto);
       }
    }

    /**
     * Coloca un jugador en una casilla determinada e inicia
     * el proceso de dibujado.
     * @param casilla casilla donde se situa el jugador
     */
    public void colocar(Casilla casilla){
        this.casilla= casilla;
        if(this.casilla != null)
        {
            this.enJuego = true;
            id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
            this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            this.bloqueo = new ElementoDibujable(TipoDibujo.elementosJuego,"casellalila.png");

        }
    }

    /**
     * Elimina el jugador de la casilla y finaliza el proceso de dibujado
     */
    public void quitar(){
        this.casilla = null;
        GestorGrafico.generarDibujante().eliminarTextura(id);
        this.seleccion.borrar();
        this.seleccion = null;
        id = -1;
        this.enJuego = false;

    }


    public ArrayList<ObjetoJugador> getPowerUP()
    {
        return this.powerup;
    }

    public ObjetoJugador getPowerUP(int index)
    {
        if(index>=0 && index<4)
        {
            return this.powerup.get(index);
        }

        return null;
    }

    public void setPowerUP(ObjetoJugador powerup)
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
        if(this.seleccionado == true)
        {
            if(this.seleccion == null) {
                this.seleccion= new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            }
            this.seleccion.dibujar((int)this.casilla.getPosX(),(int)this.casilla.getPosY());
            // Obligamos a dibjar la textura del jugador encima
            GestorGrafico.generarDibujante().eliminarTextura(this.id);
            GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.elementosJuego);
        }
        else
        {
            if(this.seleccion == null)
                this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            this.seleccion.borrar();
        }
    }

    public boolean getBloqueado()
    {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado)
    {

        this.bloqueado = bloqueado;
        if(this.bloqueado == true)
        {
            if(this.bloqueo == null) {
                    this.bloqueo = new ElementoDibujable(TipoDibujo.elementosJuego,"casellalila.png");;
            }
            this.bloqueo.dibujar((int)this.casilla.getPosX(),(int)this.casilla.getPosY());
        }
        else
        {
            if(this.bloqueo == null)
                this.bloqueo = new ElementoDibujable(TipoDibujo.elementosJuego,"casellalila.png");;
            this.bloqueo.borrar();
        }
    }


    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla[][] casillas)
    {
        boolean accionGenerada = false;

      //  GestorGrafico.getCamara().bloquear();

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
                                System.out.println("entrada:"+entrada);
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
                //this.setEstado(new SinPelota());
                //System.out.println("me quedo sin pelota");
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
                            GestorGrafico.getCamara().bloquear();
                        }
                    }
                }
                else
                {
                    if(entrada == Entrada.clic)
                    {
                        this.setSeleccionado(false);
                        this.estado = estado.getEstado();
                        System.out.println("<---------Me deseleccionan------------->");
                        GestorGrafico.getCamara().desbloquear();
                    }

                }
            }
            else
            {
                System.out.println("<---------Estoy bloqueado-1------------>");
                GestorGrafico.getCamara().desbloquear();
            }
        }
       /* else
        {
            System.out.println("<---------Estoy bloqueado-2------------>");
        }*/
    }

    public void setDireccion(DireccionJugador direccion)
    {
        this.direccion = direccion; 
    }
    public DireccionJugador getDireccion(){return this.direccion;}
    private void dibujarDireccion(DireccionJugador direccion)
    {
        switch (direccion)
        {
            case arriba:
                this.textura = "jugador/jugador4.png";
                this.textura = "jugador1.png";
                break;
            case abajo:
                this.textura = "jugador/jugador2.png";
                this.textura = "jugador1.png";
                break;
            case izquierda:
                this.textura = "jugador/jugador5.png";
                this.textura = "jugador1.png";
                break;
            case derecha:
                this.textura = "jugador/jugador1.png";
                this.textura = "jugador1.png";
                break;
        }
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
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        if(this.casilla != null)
        {
            return (int)this.casilla.getPosX();
        }
        else return -1;
    }

    @Override
    public int getPosicionY() {
        if(this.casilla != null)
        {
            return (int)this.casilla.getPosY();
        }
        else return -1;
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

    public boolean getEnJuego(){return enJuego;}

    //public void recibirImput();
}
