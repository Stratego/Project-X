package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.arbitro.UsoObjetos;
import com.rugbysurvive.partida.elementos.objetos.Objeto;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.MinaCampo;
import com.rugbysurvive.partida.jugadores.Habilidades;



/**
 * Coloca los objetos de campo en el campo de juego, indica al arbitro si
 * se han colocado y tambien los elimina del campo
 * Created by Victor on 15/04/14.
 */
public class ColocadorObjetosCampo extends Objeto {


    /**
     * tiempo de vida del objeto
     */
    protected int vida;

    /**
     * identificacon del objeto
     */
    protected int id;

    /**
     * representacion grafica del objeto
     */
    protected String imagen;

    /**
     * jugador que va a usar el objeto
     */
    protected Jugador jugador;

    /**
     * el objeto es colocable en campo o no
     */
    boolean colocable = false;

    /**
     * instancia de objeto campo
     */
    private ObjetoCampo objetoCampo;

    /**
     * instancia del uso de objetos
     */
    UsoObjetos usoObjetos;

    /**
     * Constructor de objetos campo
     *
     * @param id identificacon del objeto
     * @param vida tiempo de vida del objeto
     * @param imagen representacion grafica del objeto
     * @param jugador jugador que va a usar el objeto
     * @param objetoCampo objeto que se va a usar en el campo
     */
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
            usoObjetos.arbitrar();
            this.objetoCampo.colocar(super.getX(), super.getY());

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