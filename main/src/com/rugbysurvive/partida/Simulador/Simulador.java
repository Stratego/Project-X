package com.rugbysurvive.partida.Simulador;


import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 27/03/14.
 */

/*Esta clase es un Singleton*/
public class Simulador implements Proceso{
    private static final int TIEMPO_ESPERA =100;
    private static final int ACCIONES_SEGUNDO = 3;
    private static final int ITERACIONES_SEGUNDO = 50;
    private static final double TIEMPO_EJECUCION = ITERACIONES_SEGUNDO / ACCIONES_SEGUNDO;
    private int contador;
    private static Simulador instance;
    private List<Accion> acciones;
    private boolean simulando;
    private boolean accionFinalizada;
    private List<Accion> accionesEquipo1 = null;
    private List<Accion> accionesEquipo2 = null;
    private Equipo equipoInicial = null;
    private boolean iniciarParado;
    private boolean parado;
    private int tiempo;
    private Boolean eliminarAcciones = false;

    private Simulador()
    {

        this.contador =0;
        this.simulando= false;
        this.acciones = new ArrayList<Accion>();
        this.accionesEquipo1 = new ArrayList<Accion>();
        this.accionesEquipo2 = new ArrayList<Accion>();
        this.parado = false;
        this.iniciarParado = false;
        this.tiempo = 0;

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
        Equipo equipo1= ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2= ComponentesJuego.getComponentes().getEquipo2();
        /*En caso de no existir ninguna lista se crea una*/
        if(this.acciones == null)
        {
            this.acciones = new ArrayList<Accion>();
            this.simulando = false;
            this.contador =0;
        }

        // Elegimos el equipo que inicia la simulacion
        if(this.accionesEquipo1.size()==0 && this.accionesEquipo2.size() == 0){
            this.equipoInicial = equipo2;
            if(equipo1.isJugando()){
                this.equipoInicial = equipo1;
            }

        }

        // Añadimos segun el equipo que esta jugando y buscamos que sus jugadores
        if(equipo1.isJugando() && !equipo1.bloqueado()){
            this.accionesEquipo1.add(accion);
        }

        else {
            this.accionesEquipo2.add(accion);
        }
    }

    public void iniciarSimulacion()
    {

        Equipo equipo1= ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2= ComponentesJuego.getComponentes().getEquipo2();
        int posicion =1;

         if(this.accionesEquipo1.size() == 0 && this.accionesEquipo2.size() == 0){
             this.equipoInicial = equipo1;
         }
         if(this.equipoInicial.equals(equipo1)) {
                this.acciones.addAll(this.accionesEquipo1);

                for(Accion accion : this.accionesEquipo2){
                    if(this.acciones.size()>= posicion){
                        this.acciones.add(posicion,accion);
                        posicion = posicion +2;
                    }
                    else {
                       this.acciones.add(accion);
                    }
                }
             }


          else {
                this.acciones.addAll(this.accionesEquipo2);

                for(Accion accion : this.accionesEquipo1){
                    if(this.acciones.size()>= posicion){
                        this.acciones.add(posicion,accion);
                         posicion = posicion +2;
                }
                else{
                    this.acciones.add(accion);
                }

            }
        }


        this.simulando = true;
    }

    public void forzarFinal() {
        if(!(this.parado || this.iniciarParado)) {
            this.evitarAnimaciones();
            while(!simular());
            this.permitirAnimaciones();
        }
    }

    private void evitarAnimaciones() {
        for(Accion accion : this.acciones) {
            accion.evitarAnimacion();
        }
    }

    private void permitirAnimaciones() {
        for(Accion accion : this.acciones) {
            accion.permitirAnimacion();
        }
    }

    public void finalizarAccion(){
        if(!(this.parado || this.iniciarParado))
        {
             while(!accionFinalizada){
                simular();
             }
        }
    }


    public boolean simular()
    {
        if(!this.parado) {
            if(simulando && this.acciones.size() > 0) {
                accionFinalizada = false;
                this.contador++;

                if(contador == TIEMPO_EJECUCION)
                {
                    accionFinalizada = this.acciones.get(0).simular();


                    if(this.accionFinalizada && this.iniciarParado){
                        this.parado =true;
                        this.iniciarParado = false;
                    }
                    this.contador =0;
                    if(accionFinalizada && this.acciones.size() > 0){
                        this.acciones.remove(0);
                    }
                 }

                if(this.eliminarAcciones == true)
                {
                    this.eliminarAcciones = false;
                    this.acciones = new ArrayList<Accion>();
                    this.accionesEquipo1 = new ArrayList<Accion>();
                    this.accionesEquipo2 = new ArrayList<Accion>();

                    return true;
                }
                if(this.acciones.size() == 0){
                    this.accionesEquipo1 = new ArrayList<Accion>();
                    this.accionesEquipo2 = new ArrayList<Accion>();
                    return true;
                }
                else {
                    return false;
                }

            }
            else if(this.simulando && this.acciones.size() == 0){

                if(tiempo == 0) {
                    ProcesosContinuos.añadirProceso(this);
                }

                else if (this.tiempo == TIEMPO_ESPERA) {
                    return true;
                }

                return false;
            }

        }


        return false;
    }



    public void reiniciar() {
        this.acciones = new ArrayList<Accion>();
    }

    public void eliminarAcciones() {
       this.eliminarAcciones = true;
    }

    public int listSize(){
        return this.acciones.size();
    }

    public boolean isParado() {
        return parado;
    }

    /**
     * Si se envia cierto se para el simulador
     * hasta que se vuelve a enviar falso
     * El parado se realiza una vez finaliaza la accion
     * que se esta realizando..
     * @param parado indica si se para el simulador
     */
    public void setParado(boolean parado) {
        if(!this.parado && parado) {
            this.iniciarParado = parado;
        }
        else if(!parado){
            this.parado = false;
        }
    }


    @Override
    public boolean procesar() {
        if(this.tiempo > TIEMPO_ESPERA){
            this.tiempo =0;
            return true;
        }
        this.tiempo++;
        return false;
    }
}
