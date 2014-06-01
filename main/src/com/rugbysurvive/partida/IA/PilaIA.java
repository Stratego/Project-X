package com.rugbysurvive.partida.IA;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que implementa una pila de nodos que representara el camino que tiene
 * que hacer un jugador de IA de su posicion a su destino
 * Created by Victor on 12/05/14.
 */
public class PilaIA {
    /**
     * Vector interno que contiene los elementos del montículo.
     */
    private ArrayList elementos;

    /**
     * Constructor por defecto.
     * Deja el montículo vacío.
     */
    public PilaIA()
    {
        elementos = new ArrayList();
    }

    /**
     * Inserta un elemento en el montículo.
     * @param objeto Objeto que va a ser introducido. La clase del objeto
     * debe disponer del método "compareTo".
     * @return Devuelve verdadero si ha introducido el elemento.
     */
    public boolean push(Comparable objeto)
    {
        boolean insertado = false;
        if (objeto != null)
        {
            elementos.add(objeto);
            Collections.sort(elementos);
            insertado = true;
        }
        return insertado;
    }

    /**
     * Elimina el elemento máximo del montículo y lo devuelve.
     * @return Devuelve una referencia al objeto extraido del montículo. Si no
     * existe ningún elemento, devuelve null.
     */
    public Object popTop()
    {
        if (!elementos.isEmpty())
        {
            return elementos.remove(elementos.size()-1);
        }
        else
        {
            return null;
        }
    }

    /**
     * Elimina el elemento mínimo del montículo y lo devuelve.
     * @return Devuelve una referencia al objeto extraido del montículo. Si no
     * existe ningún elemento, devuelve null.
     */
    public Object popBottom()
    {
        if (!elementos.isEmpty())
        {
            return elementos.remove(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Obtiene una referencia al elemento máximo sin extraerlo del montículo.
     * @return Devuelve una referencia al objeto. Si no existe ningún elemento,
     * devuelve null.
     */
    public Object top()
    {
        if (!elementos.isEmpty())
        {
            return elementos.get(elementos.size()-1);
        }
        else
        {
            return null;
        }
    }

    /**
     * Obtiene una referencia al elemento mínimo sin extraerlo del montículo.
     * @return Devuelve una referencia al objeto. Si no existe ningún elemento,
     * devuelve null.
     */
    public Object bottom()
    {
        if (!elementos.isEmpty())
        {
            return elementos.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Comprueba si un objeto está en el montículo.
     * @param objeto Objeto que va a ser comprobado.
     * @return Devuelve verdadero si está en el montículo; falso en otro caso.
     */
    public boolean contains(Object objeto)
    {
        return elementos.contains(objeto);
    }

    /**
     * Fuerza la reordenación del montículo por si ese elemento ha dejado
     * de ser un máximo o un mínimo, o por si ahora es un máximo o un mínimo.
     */
    public void reordenar()
    {
        Collections.sort(elementos);
    }

    /**
     * Deja el montículo vacío.
     */
    public void clear()
    {
        elementos.clear();
    }

    /**
     * Obtiene el número de elementos que hay en el montículo.
     * @return Devuelve un valor natural includo el cero.
     */
    public int size()
    {
        return elementos.size();
    }

    /**
     * Comprueba si el montículo está vacío.
     * @return Devuelve verdadero si el montículo está vacío.
     */
    public boolean isEmpty()
    {
        return elementos.isEmpty();
    }

    /**
     * Obtiene una cadena de caracteres con el contenido del montículo.
     * @return Devuelve una cadena de caracteres con los elementos del montículo.
     */
    @Override
    public String toString()
    {
        return elementos.toString();
    }

    /**
     * Obtiene los elementos en un vector, ordenados de menor a mayor.
     * @return Devuelve un vector del tamaño del montículo.
     */
    public Object[] toArray()
    {
        int tamano = elementos.size();
        Object[] vector = new Object[tamano];
        for (int i = 0; i < tamano; i++)
        {
            vector[i] = elementos.get(i);
        }
        return vector;
    }
}
