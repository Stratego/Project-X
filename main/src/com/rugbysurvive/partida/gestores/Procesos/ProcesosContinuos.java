package com.rugbysurvive.partida.gestores.Procesos;

import java.util.ArrayList;

/**
 * Created by aitor on 23/04/14.
 */
public class ProcesosContinuos {

       private static ArrayList<Proceso> procesos = new ArrayList<Proceso>();

       public static void procesar() {

           ArrayList<Proceso> finalizados = new ArrayList<Proceso>();

           for(Proceso proceso : procesos){
                if(proceso.procesar()) {
                   finalizados.add(proceso);
                 }
           }

          eliminarProceso(finalizados);
       }


       public static void añadirProceso(Proceso proceso) {
           procesos.add(proceso);
       }

       private static void eliminarProceso(ArrayList<Proceso> finalizados){
            procesos.removeAll(finalizados);
       }
}
