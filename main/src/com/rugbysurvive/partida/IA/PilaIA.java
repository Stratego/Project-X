package com.rugbysurvive.partida.IA;

import java.util.ArrayList;
import java.util.Collections;

/**
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
                        /*
                         * Evidentemente, ésta no es la implementación de inserción de
                         * un montículo doble ya que lo único que hace es ordenar todos
                         * los elementos.
                         * Cuando se inserta un elemento en un montículo, se realiza una
                         * ordenación parcial que mantiene el elemento máximo y mínimo
                         * en la cima del montículo. En este caso, se realiza una ordenación
                         * total (con su correspondiente pérdida de eficiencia).
                         * El borrado también debería reestructurar el montículo cuando se
                         * mejore este método.
                         */
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
     * Fuerza la reordenación del montículo. Si algún elemento del montículo
     * sufre una modificación, será necesario forzar la reordenación por si
     * ese elemento ha dejado de ser un máximo o un mínimo, o por si ahora
     * es un máximo o un mínimo.
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
     * El formato coincide con el de un ArrayList (corchetes al principio y
     * al final y una coma y un espacio entre cada elemento). Los objetos
     * introducidos en el montículo deben sobreescribir el método toString.
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
