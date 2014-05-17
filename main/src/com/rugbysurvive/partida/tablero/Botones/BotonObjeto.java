package com.rugbysurvive.partida.tablero.Botones;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Boton;

import java.util.ArrayList;

/**
 * Created by Victor on 24/04/14.
 */
public class BotonObjeto extends Boton {


    private boolean objetoElegido;
    private ElementoDibujable fondo;
    private ElementoDibujable flecha;

    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     * @param posicion
     */
    public BotonObjeto(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada,textura,posicion,ConstantesJuego.ANCHO_BOTON_OBJETOS,ConstantesJuego.ALTO_BOTON_OBJETOS);
        this.objetoElegido = false;
        GestorGrafico.generarDibujante().eliminarTextura(this.ID);
        this.fondo = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/fondo.png");
        this.flecha = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/positivo.png");
        this.fondo.dibujar(this.getPosicionX(),this.getPosicionY());
        this.flecha.dibujar(this.getPosicionX(),this.getPosicionY());
        this.ID = GestorGrafico.generarDibujante().añadirDibujable(this,TipoDibujo.interficieUsuario);

    }

    @Override
    public void accionEntrada(Entrada entrada) {
        //obtenemos el elemento de la lista mediante la posicion le dimos al crear el boton
            if(!objetoElegido) {

                Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
                Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
                Equipo equipoSeleccionado = equipo2;

                if(equipo1.hayJugadorSelecionado()){
                    equipoSeleccionado  = equipo1;
                }
                 Jugador jugador = equipoSeleccionado.getJugadorActivo();
                 ArrayList<ObjetoJugador> objetos = jugador.getPowerUP();
                 //activamos y eliminamos el objeto de la lista
                 for (ObjetoJugador iter: objetos){
                     if (iter.getId()==this.posicion){
                             iter.activar();
                             jugador.getPowerUP().remove(iter);
                             this.objetoElegido = true;
                             this.flecha.borrar();
                             this.fondo.borrar();
                             GestorGrafico.generarDibujante().eliminarTextura(this.ID);
                             jugador.setBloqueado(true);
                             jugador.setSeleccionado(false);
                             break;
                     }
                 }
            }

    }

    @Override
    public void borrar() {
        super.borrar();
        this.fondo.borrar();
        this.flecha.borrar();
    }
}
