package com.rugbysurvive.partida.gestores.Procesos;

import java.util.ArrayList;

/**
 * Created by aitor on 23/04/14.
 * Permite a un proceso la continuada ejecucion a lo largo de
 * varios ciclos del juego.
 * El proceso ejecutado no necesita recibir ningun estimulo del
 * jugador por lo que se ejecutara y seguira la ejecucion
 * automaticamente hasta que considere que se debe finalizar.
 *
 * El conjunto de procesos continuos se ejecutan mediante una cola.
 */
public class ProcesosContinuos {

       private static ArrayList<Proceso> procesos = new ArrayList<Proceso>();

    /**
     * Se ejecutan todos los procesos de la cola , si alguno devuelve cierto
     * significa que ha finalizado por lo que se elimina.
     * Por cada llamada de la funcion se realiza una unica llamada a cada proceso.
     */
      public static void procesar() {

           ArrayList<Proceso> finalizados = new ArrayList<Proceso>();

           for(Proceso proceso : procesos){
                if(proceso.procesar()) {
                   finalizados.add(proceso);
                 }
           }

          eliminarProceso(finalizados);
       }

    /**
     * Añade un proceso a la cola de procesos
     * @param proceso proceso que se desea ejecutar de forma continua
     */
       public static void añadirProceso(Proceso proceso) {
           procesos.add(proceso);
       }

    /**
     * Elimina los procesos aunqeu esten a mitad de ejecucion
     * @param finalizados lista de procesos eliminados
     */
       private static void eliminarProceso(ArrayList<Proceso> finalizados){
            procesos.removeAll(finalizados);
       }
}
