package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.arbitro.UsoObjetos;
import com.rugbysurvive.partida.elementos.objetos.Objeto;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.MinaCampo;
import com.rugbysurvive.partida.jugadores.Habilidades;



/**
 * Created by Victor on 15/04/14.
 */
public class ColocadorObjetosCampo extends Objeto {


    //private final int vida;
    protected int vida;
    protected int id;
    protected String imagen;
    protected Jugador jugador;
    boolean colocable = false;
    private ObjetoCampo objetoCampo;
    UsoObjetos usoObjetos;

    public ColocadorObjetosCampo(int id, int vida, String imagen, Jugador jugador, ObjetoCampo objetoCampo) {

        super(id, vida, imagen, jugador);

        this.id=id;
        this.vida = vida;
        this.imagen = imagen;
        this.jugador = jugador;
        this.objetoCampo = objetoCampo;

    }



    @Override
    protected void iniciar() {


        if (controlPosicion()){
            usoObjetos = new UsoObjetos(this.jugador);
            this.objetoCampo.colocar(super.getX(),super.getY());
        }else{
            System.out.println("elemento no colocado");
        }

        System.out.println("INICIAR");

    }

    @Override
    protected void desactivar() {
        if (colocable==true){
            this.objetoCampo.quitar();
        colocable=false;
        }
        System.out.println("FINALIZAR");

    }


}