package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.objetos.Objeto;

/**
 * Created by aitor on 11/04/14.
 */
public class PowerUP extends Objeto {


    public PowerUP(int vida) { // Aqui falta añadir todos los elementos que se desean
                               // includir en el objeto para hacer el efecto
        super(vida,"");
    }



    @Override
    protected void iniciar() {
        System.out.println("INICIAR");
    }

    @Override
    protected void desactivar() {

        System.out.println("FINALIZAR");

    }
}
