package com.rugbysurvive.partida.IA;

/**
 * Created by Victor on 12/05/14.
 */
/**
 * Esta clase representa un nodo del mapa de celdas del algoritmo del A*.
 * Indica cuál es su posición (x,y) en ese mapa, así como su valor F, G, H.
 */
public class NodoIA implements Comparable
{
    /**
     * Componente x (columna) de la posición del nodo en el mapa.
     */
    private int x;

    /**
     * Componente y (fila) de la posición del nodo en el mapa.
     */
    private int y;

    /**
     * Indica si el nodo puede ser visitado o accedido.
     */
    private boolean transitable;

    /**
     * Coste extra del nodo. Por ejemplo, sería más costoso caminar sobre el
     * barro que sobre tierra firme.
     */
    private int coste;

    /**
     * Valor total del nodo.
     * F = G + H + coste
     */
    private int F;

    /**
     * Valor desde el nodo hasta el nodo inicial.
     * Las diagonales suman 14 y las ortogonales 10.
     */
    private int G;

    /**
     * Valor desde el nodo hasta el nodo final.
     * Se considera el peor caso, en el no se pueden hacer diagonales, por lo
     * tanto, H + 10*(diferencia de filas + diferencia de columnas entre el nodo
     * y el nodo final).
     */
    private int H;

    /**
     * Referencia al nodo padre. Necesario para calcular G.
     */
    private NodoIA nodoPadre;

    /**
     * Referencia al nodo final. Necesario para calcular H.
     */
    private NodoIA nodoFinal;

    /**
     * Constructor por defecto.
     * Inicializa a unos valores neutros del nodo.
     */
    public NodoIA()
    {
        x = -1;
        y = -1;

        transitable = true;
        coste = 0;

        F = 0;
        G = 0;
        H = 0;

        nodoPadre = null;
        nodoFinal = null;
    }

    /**
     * Compara dos nodos según su valor de F.
     * @param objeto Nodo con el que se va a comparar el nodo que invocó el
     * método.
     * @return Devuelve 1 si el nodo que invocó el método tiene menor F,
     * devuelve 0 si son iguales o -1 en otro caso.
     */
    public int compareTo(Object objeto)
    {
        if (F > ((NodoIA) objeto).F) return 1;
        else if (F < ((NodoIA) objeto).F) return -1;
        else return 0;
    }

    /**
     * Obtiene la componente x de la posición del nodo.
     * @return Devuelve un valor entero con el número de columna de la posición
     * del nodo.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Establece un nuevo valor para la componente x (columna).
     * @param x Nuevo valor para la componente x.
     * @return Devuelve verdadero si se ha establecido el nuevo valor.
     */
    public boolean setX(int x)
    {
        if (x >= 0)
        {
            this.x = x;
            return true;
        }
        return false;
    }

    /**
     * Obtiene la componente y de la posición del nodo.
     * @return Devuelve un valor entero con el número de fila de la posición
     * del nodo.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Establece un nuevo valor para la componente y (fila).
     * @param y Nuevo valor para la componente y.
     * @return Devuelve verdadero si se ha establecido el nuevo valor.
     */
    public boolean setY(int y)
    {
        if (y >= 0)
        {
            this.y = y;
            return true;
        }
        return false;
    }

    /**
     * Recalcula el valor de F. Normalmente, cuando G, H o coste han cambiado.
     */
    private void recalcularF()
    {
        F = G + H + coste;
    }

    /**
     * Recalcula el valor de G. Normalmente, cuando el padre se ha modificado.
     */
    private void recalcularG()
    {
        G = nodoPadre.G;
        if (x==nodoPadre.x || y==nodoPadre.y)
            G += 10;
        else
            G += 14;
        recalcularF();
    }

    /**
     * Recalcula el valor de H. Normalmente, cuando el nodo final ha cambiado.
     */
    private void recalcularH()
    {
        if (nodoFinal != null)
        {
            H = (Math.abs(x-nodoFinal.x) + Math.abs(y-nodoFinal.y))*10;
        }
        else
        {
            H = 0;
        }
        recalcularF();
    }

    /**
     * Obtiene el valor F.
     * @return Devuelve un número entero que es el valor F del nodo.
     */
    public int getF()
    {
        return F;
    }

    /**
     * Obtiene el valor G.
     * @return Devuelve un número entero que es el valor G del nodo.
     */
    public int getG()
    {
        return G;
    }

    /**
     * Obtiene el valor H.
     * @return Devuelve un número entero que es el valor H del nodo.
     */
    public int getH()
    {
        return H;
    }

    /**
     * Obtiene el nodo padre.
     * @return Devuelve una referencia al nodo padre.
     */
    public NodoIA getNodoPadre()
    {
        return nodoPadre;
    }

    /**
     * Establece un nuevo nodo padre. Recalcula G (y F) forzadamente.
     * @param nodoPadre Referencia al nodo padre que se va a asignar.
     */
    public void setNodoPadre(NodoIA nodoPadre)
    {
        this.nodoPadre = nodoPadre;
        recalcularG();
    }

    /**
     * Obtiene el nodo final.
     * @return Devuelve una referencia al nodo final.
     */
    public NodoIA getNodoFinal()
    {
        return nodoFinal;
    }

    /**
     * Establece un nuevo nodo final. Recalcula H (y F) forzadamente.
     * @param nodoFinal Referencia al nodo final que se va a asignar.
     */
    public void setNodoFinal(NodoIA nodoFinal)
    {
        this.nodoFinal = nodoFinal;
        recalcularH();
    }

    /**
     * Comprueba si el nodo es transitable.
     * @return Devuelve verdadero si es transitable o falso en otro caso.
     */
    public boolean getTransitable()
    {
        return transitable;
    }

    /**
     * Establece si el nodo es transitable.
     * @param transitable Nuevo valor para la transitibilidad del nodo.
     */
    public void setTransitable(boolean transitable)
    {
        this.transitable = transitable;
    }

    /**
     * Obtiene el coste del nodo.
     * @return Devuelve un valor entero con el coste del nodo.
     */
    public int getCoste()
    {
        return coste;
    }

    /**
     * Establece un nuevo valor para el coste. Recalcula F forzadamente.
     * @param coste Nuevo valor para el coste del nodo.
     * @return Devuelve verdadero si ha podido modificar el coste. Sólo si es mayor o igual que 0.
     */
    public boolean setCoste(int coste)
    {
        if (coste >= 0)
        {
            this.coste = coste;
            recalcularF();
            return true;
        }
        return false;
    }

    /**
     * Genera una cadena de caracteres con la posición del nodo (fila, columna).
     * @return Devuelve una cadena con la posición (fila, columna).
     */
    @Override
    public String toString()
    {
        return "(" + y + ", " + x + ")";
    }
}

