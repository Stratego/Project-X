package com.rugbysurvive.partida.gestores.Procesos;

/**
 * Created by aitor on 23/04/14.
 */
public interface Proceso {

    /**
     * Una vez cargado el proceso se llama en cada iteracion
     * uhasta que devuelva cierto.
     * @return cierto si finaliza el proceso ,falso en caso contrario
     */
    public boolean procesar();
}
