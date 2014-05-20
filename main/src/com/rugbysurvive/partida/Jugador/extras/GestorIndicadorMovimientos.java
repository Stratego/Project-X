package com.rugbysurvive.partida.Jugador.extras;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;

import java.util.ArrayList;

/**
 * Created by aitor on 16/04/14.
 */
public class GestorIndicadorMovimientos {

    private static GestorIndicadorMovimientos gestor = new GestorIndicadorMovimientos();
    private ArrayList<IndicadorMovimientos> indicadores;
    private GestorIndicadorMovimientos()
    {
        this.indicadores = new ArrayList<IndicadorMovimientos>();
    }

    public static GestorIndicadorMovimientos getInstancia(){
       return gestor;
    }

    public void añadirIndicadorMovimientos(IndicadorMovimientos indicador)
    {
        this.indicadores.add(indicador);
    }

    public void Borrar()
    {
        for(IndicadorMovimientos movimiento : this.indicadores){
            movimiento.borrar();
        }
        this.indicadores = new ArrayList<IndicadorMovimientos>();
    }

}
