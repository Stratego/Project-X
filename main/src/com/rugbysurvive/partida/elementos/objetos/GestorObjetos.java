package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.gestores.GestorGrafico;

import java.util.ArrayList;

/**
 * Created by aitor on 11/04/14.
 * Gestiona la vida de los objetos una vez activados
 * Cuando un objeto a agotado el tiempo de vida es eliminado
 * Esto permite centralizar todas las funcionalidades de los objetos
 */
public class GestorObjetos {
    protected ArrayList<Objeto> objectos;
    protected static GestorObjetos gestor;

    public GestorObjetos()
    {
        objectos = new ArrayList<Objeto>();
        gestor = this;
    }

    /**
     * Añade un nuevo objeto al gestor
     * A partir de este momento iniciara la cuenta atras de la vida
     * @param objeto elemento que se añade
     */
    public void añadirObjeto(Objeto objeto)
    {
        System.out.println("objeto añadido");
        this.objectos.add(objeto);
    }

    /**
     * Singleton del gestor
     * @return Gestor enviado de forma statica
     */
    public static GestorObjetos getGestor(){return gestor;}

    /**
     * Cada vez que se llama se quita una unidad de vida a
     * todos los objetos activos.
     * Si un objeto se queda sin vidas se revierten todos los efectos y
     * es elimiando definitivamente
     */
    public void procesar()
    {
        ArrayList<Objeto> objectosEliminar = new ArrayList<Objeto>();
        for(Objeto objeto : objectos)
        {
            if(objeto.pasarTurno()) {
                objectosEliminar.add(objeto);
            }
        }
        this.eliminarObjetos(objectosEliminar);

    }

    /**
     * Elimina un conjunto de objetos que ya han sido
     * desactivados sus efectos
     * @param objectosEliminar
     */
    private void eliminarObjetos(ArrayList<Objeto> objectosEliminar )
    {
        for(Objeto objeto : objectosEliminar)
        {
            this.objectos.remove(objeto);
        }
    }


}
