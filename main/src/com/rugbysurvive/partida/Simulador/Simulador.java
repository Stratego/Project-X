package com.rugbysurvive.partida.Simulador;


import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aitor on 27/03/14.
 * Gestiona todas las acciones realizadas durante los turnos de los jugadores.
 * En primer termino se deben guardar  el conjunto de acciones que se deben realizar.
 * Cada accion que se quiera añadir debe cumplir heredar de la clase Accion e implementar
 * sus metodos.
 * El simulador mezclara las acciones segun sean de un jugador o otro y seran ejecutadas
 * de la siguiente forma :
 *    ejemplo
 *      4 acciones {jugador1,jugador2,jugador1,jugador2 ...}
 *
 * La animacion no se realiza de forma instantanea a menos que se especifique por lo que habra
 * un tiempo de retardo indicado por sus atributos.
 * Ademas añade metodos auxiliares para gestionar el proceso de simulacion con animaciones
 * aceleraciones o bloqueos.
 */

public class Simulador implements Proceso{

   // Limitadores de tiempo para realizar la simulacion de forma visible para el usuario
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

    /**
     * Inicializa el simulador por metodo privado ya que se construye
     * mediante el patron singleton .
     */
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

    /**
     * Obtiene de forma estatica la instancia del simulador
     * @return instancia del simulador
     */
    public static Simulador getInstance()
    {
        if(instance == null) {
            instance = new Simulador();
        }

        return instance;
    }

    /**
     * Añade una nueva accion al simulador colocada en la cola siguiendo
     * el orden descrito en la descripcion de la clase.
     * Toda accion debe tener implementada la funcion simular.
     *
     *
     * @param accion accion que debe ser ejecutada
     */
    public void añadirAccion(Accion accion)
    {
        Equipo equipo1= ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2= ComponentesJuego.getComponentes().getEquipo2();
        /*En caso de no existir ninguna lista se crea una*/
        if(this.acciones == null) {
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


    /**
     * Prepara todos los elementos para realizar la simulacion
     * Se debe ejecutar antes de llamar a la funcion simular
     * para su correcto funcionamiento.
     */
    public void iniciarSimulacion()
    {

         Equipo equipo1= ComponentesJuego.getComponentes().getEquipo1();
         Equipo equipo2= ComponentesJuego.getComponentes().getEquipo2();
         int posicion =1;

         if(this.accionesEquipo1.size() == 0 && this.accionesEquipo2.size() == 0){
             this.equipoInicial = equipo1;
         }

         if(this.equipoInicial.equals(equipo1)) {
               posicion = this.añadirAccionEquipo1(posicion);
         }


         else {
               posicion = this.añadirAccionEquipo2(posicion);
         }

        this.simulando = true;
    }


    /**
     * Realiza el proceso de simulacion.
     * Cada vez que se llama a la funcion realiza una llamada a la
     * accion que tiene asignada para simular.
     * Cada vez que una accion finaliza es eliminada y se simula la siguiente.
     * La simulacion no finaliza hasta que todas las acciones no hayan finalizado.
     * La funcion tambien controla todos los procesos de parada , finalizacion instantanea
     * o bloqueo con reinicio.
     *
     * @return indicador de simulacion finalizada.
     *
     */
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

                if(this.eliminarAcciones) {
                    this.eliminarAcciones = false;
                    this.acciones = new ArrayList<Accion>();
                    this.accionesEquipo1 = new ArrayList<Accion>();
                    this.accionesEquipo2 = new ArrayList<Accion>();
                }

                if(this.acciones.size() == 0) {
                    this.accionesEquipo1 = new ArrayList<Accion>();
                    this.accionesEquipo2 = new ArrayList<Accion>();
                }
            }
            else if(this.simulando && this.acciones.size() == 0) {

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


    /**
     * Realiza el proceso de simulacion instantaneamente
     * elimina cualquier proceso de animacion , sonido
     * o cualquier otro que requiere de un tiempo de procesado
     * que no sea instantaneo.
     */
    public void forzarFinal() {

        if(!(this.parado || this.iniciarParado)) {
            this.evitarAnimaciones();
            while(this.acciones.size()>0){
                simular();
            }
            this.permitirAnimaciones();
        }
    }

    /**
     *Bloquea todos los procesos que no se realizan
     * de forma instantanea.
     */
    private void evitarAnimaciones() {
        for(Accion accion : this.acciones) {
            accion.evitarAnimacion();
        }
    }

    /**
     * Permite todos los procesos que requieran de un tiempo
     * para ser realizadas.
     */
    private void permitirAnimaciones() {
        for(Accion accion : this.acciones) {
            accion.permitirAnimacion();
        }
    }


    /**
     * Finaliza instaneamente la funcion que se esta ejecutando
     */
    public void finalizarAccion(){
        if(!(this.parado || this.iniciarParado))
        {
            this.evitarAnimaciones();
            while(!accionFinalizada){
                simular();
            }
            this.permitirAnimaciones();
        }
    }


    /**
     * Reinicia el simulador eliminando todas las acciones
     * de la cola
     */
    public void reiniciar() {
        this.acciones = new ArrayList<Accion>();
    }

    /**
     * Reinicia el simulador eliminando todas las acciones de la cola
     * pudiendose usar en medio del proceso de simulacion.
     */
    public void eliminarAcciones() {
       this.eliminarAcciones = true;
    }

    /**
     * Obtiene la cantidad de acciones que quedan para ejecutarse
     * @return cantidad de acciones por ejecutar
     */
    public int listSize(){
        return this.acciones.size();
    }

    /**
     * Indica si la simulacion esta parada o no
     * @return devuelve cierto en caso de parada
     */
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


    /**
     * Añade todas las acciones del equipo 2 sobre el equipo 1
     * Las mezcla de la siguiente manera :
     *   {accion equipo 1, accion equipo 2 , accion equipo1 ...}
     *
     *@param posicion posicion inicial de mezclado
     * @return posicion final de mezclado
     */
    private int añadirAccionEquipo1(int posicion) {
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
        return posicion;
    }

    /**
     * Añade todas las acciones del equipo 1 sobre el equipo 2
     * Las mezcla de la siguiente manera :
     *   {accion equipo 2, accion equipo 1 , accion equipo 2 ...}
     *
     *@param posicion posicion inicial de mezclado
     * @return posicion final de mezclado
     */
    private int añadirAccionEquipo2(int posicion) {
        this.acciones.addAll(this.accionesEquipo1);

        this.acciones.addAll(this.accionesEquipo2);

        for(Accion accion : this.accionesEquipo1) {
            if(this.acciones.size()>= posicion){
                this.acciones.add(posicion,accion);
                posicion = posicion +2;
            }
            else {
                this.acciones.add(accion);
            }

        }
        return posicion;
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
