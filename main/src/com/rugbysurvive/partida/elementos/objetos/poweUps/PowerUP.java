package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.Objeto;
import com.rugbysurvive.partida.jugadores.Habilidades;

/**
 * Created by aitor on 11/04/14.
 */
public class PowerUP extends Objeto {


    //private final int vida;
    protected int vida;
    protected int id;
    protected String imagen;
    protected Habilidades habilidad;
    protected int modificacion;
    protected Jugador jugador;

    public PowerUP(int id,int vida,String imagen, Habilidades habilidad,int modificacion,Jugador jugador) {

        super(id, vida, imagen, habilidad,modificacion, jugador);

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

        if (habilidad == Habilidades.vida){
            //valorActual=jugador.getVida();
            jugador.setVida(jugador.getVida()+modificacion);
        }


        System.out.println("INICIAR");

    }

    @Override
    protected void desactivar() {

        if (habilidad == Habilidades.vida){
            jugador.setVida(jugador.getVida()-modificacion);
        }

        System.out.println("vida jugador al finalizar objeto "+ jugador.getVida());
        System.out.println("FINALIZAR");

    }
}
