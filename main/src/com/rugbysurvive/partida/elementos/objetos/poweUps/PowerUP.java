package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.Objeto;
import com.rugbysurvive.partida.jugadores.Habilidades;


/**
 * Created by aitor on 11/04/14.
 */
public class PowerUP extends Objeto {


    protected int vida;
    protected int id;
    protected String imagen;
    protected Habilidades habilidad;
    protected int modificacion;
    protected Jugador jugador;

    public PowerUP(int id,int vida,String imagen, Habilidades habilidad,int modificacion,Jugador jugador) {

        super(id, vida, imagen,jugador);

        this.id=id;
        this.vida = vida;
        this.imagen = imagen;
        this.habilidad = habilidad;
        this.modificacion = modificacion;
        this.jugador = jugador;

    }



    @Override
    protected void iniciar() {
        //int valorActual = 0;
     switch (habilidad)
        {
            case vida:

                jugador.setVida(jugador.getVida()+modificacion);
                break;

            case fuerza:

                jugador.setFuerza(jugador.getFuerza() + modificacion);
                break;
            case defensa:

                jugador.setDefensa(jugador.getDefensa() + modificacion);
                break;

        }



        System.out.println("INICIAR");

    }

    @Override
    protected void desactivar() {
        switch (habilidad)
        {
            case vida:

                jugador.setVida(jugador.getVida()-modificacion);
                break;

            case fuerza:

                jugador.setFuerza(jugador.getFuerza()-modificacion);
                break;

            case defensa:

                jugador.setDefensa(jugador.getDefensa()-modificacion);
                break;

        }

        System.out.println("vida jugador al finalizar objeto "+ jugador.getVida());
        System.out.println("FINALIZAR");

    }
}
