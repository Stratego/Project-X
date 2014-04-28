package com.rugbysurvive.partida.Jugador.extras;

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

    public GestorIndicadorMovimientos getInstancia(){
       return gestor;
    }

    public void añadirIndicadorMovimientos(IndicadorMovimientos indicador)
    {
        this.indicadores.add(indicador);
    }

    public void Borrar()
    {
        this.indicadores = new ArrayList<IndicadorMovimientos>();
    }

}
