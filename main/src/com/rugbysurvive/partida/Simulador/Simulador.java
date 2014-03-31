package com.rugbysurvive.partida.Simulador;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 27/03/14.
 */

/*Esta clase es un Singleton*/
public class Simulador {
    private static Simulador instance;
    private List<Accion> listaAcciones;

    private Simulador()
    {
    }

    public static Simulador getInstance()
    {
        if(instance == null)
        {
            instance = new Simulador();
        }
        return instance;
    }

    public void addAccionesSimulador(Accion accion)
    {
        /*En caso de no existir ninguna lista se crea una*/
        if(this.listaAcciones == null)
        {
            this.listaAcciones = new ArrayList();
        }
        this.listaAcciones.add(accion);
    }

    public void simular()
    {
        for (int i = 0; i < this.listaAcciones.size(); i++) {
            listaAcciones.get(i).simular();
        }

        //Como ya se han generado todas las simulaciones, volvemos a poner la lista a null
        this.listaAcciones = null;
    }
}
