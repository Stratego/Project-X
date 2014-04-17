package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.Objeto;

import com.rugbysurvive.partida.elementos.objetos.objetosCampo.MinaCampo;
import com.rugbysurvive.partida.jugadores.Habilidades;



/**
 * Created by Victor on 15/04/14.
 */
public class Mina extends Objeto {


    //private final int vida;
    protected int vida;
    protected int id;
    protected String imagen;
    protected Habilidades habilidad;
    protected int modificacion;
    protected Jugador jugador;
    protected MinaCampo mina;
    boolean colocable = false;


    public Mina(int id,int vida,String imagen, Habilidades habilidad,int modificacion,Jugador jugador) {

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


        if (super.controlPosicion()==true){
            mina = new MinaCampo(super.getX(),super.getY(),this.textura);
            colocable=true;
        }else{
            System.out.println("elemento no colocado");
        }

        System.out.println("INICIAR");

    }

    @Override
    protected void desactivar() {
        if (colocable==true){
        mina.quitar();
        colocable=false;
        }
        System.out.println("FINALIZAR");

    }


}