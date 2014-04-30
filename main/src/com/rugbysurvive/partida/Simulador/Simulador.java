package com.rugbysurvive.partida.Simulador;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 27/03/14.
 */

/*Esta clase es un Singleton*/
public class Simulador {
    private static final int ACCIONES_SEGUNDO = 1;
    private static final int ITERACIONES_SEGUNDO = 50;
    private static final double TIEMPO_EJECUCION = ITERACIONES_SEGUNDO / ACCIONES_SEGUNDO;
    private int contador;
    private int posicion;
    private static Simulador instance;
    private List<Accion> acciones;
    private boolean simulando;
    private boolean accionFinalizada;

    private Simulador()
    {
        this.contador =0;
        this.simulando= false;
        this.acciones = new ArrayList();
    }

    public static Simulador getInstance()
    {
        if(instance == null)
        {
            instance = new Simulador();
        }
        return instance;
    }

    public void añadirAccion(Accion accion)
    {
        /*En caso de no existir ninguna lista se crea una*/
        if(this.acciones == null)
        {
            this.acciones = new ArrayList();
            this.simulando = false;
            this.contador =0;
            this.posicion =0;
        }
        this.acciones.add(accion);
    }
    public void iniciarSimulacion()
    {
        this.simulando = true;
    }

    public void forzarFinal(){
        while(!simular());
    }

    public void finalizarAccion(){
        while(!accionFinalizada){
            simular();
        }
    }

    public boolean simular()
    {
        if(simulando && this.acciones.size() > 0){
            accionFinalizada = false;
            this.contador++;
            if(contador == TIEMPO_EJECUCION)
            {

                accionFinalizada = this.acciones.get(0).simular();
                this.contador =0;
                if(accionFinalizada){
                    System.out.println("Accion finalizada");
                    this.acciones.remove(0);
                }
            }
            if(this.acciones.size() == 0){
                System.out.println("Simulacion finalizada");
                return true;
            }
            else {
                return false;
            }

        }
        return false;
    }

    public int listSize(){
        return this.acciones.size();
    }
}
