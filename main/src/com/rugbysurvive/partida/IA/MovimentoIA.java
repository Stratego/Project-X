package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Victor on 9/05/14.
 */
public class MovimentoIA {


    /**
     * Matriz con una serie de filas y una serie de columnas que representa el tablero sobre
     * el que se buscará el camino. Cada celda de la matriz es un nodo que puede
     * ser transitable o no y que tiene un coste particular (por ejemplo, sería
     * más difícil caminar por el barro que por tierra seca).
     */
    private Casilla[][] matriz;

    /**
     * Nodo de partida que indica una posición (x,y) en la matriz de celdas.
     * No importa si es un nodo intransitable y tampoco se considera el coste.
     */
    private Casilla casillaInicial;

    /**
     * Nodo de llegada que indica una posición (x,y) en la matriz de celdas.
     * Debe ser un nodo transitable.
     */
    private Casilla casillaFinal;



    /**
     * Constructor por defecto.

     * @param casillaInicial Casilla de partida que indica una posición (x,y) en la matriz de celdas.
     * @param casillaFinal Casilla de llegada que indica una posición (x,y) en la matriz de celdas.

     */
    public MovimentoIA (Casilla casillaInicial,Casilla casillaFinal)
    {
        this.matriz = ComponentesJuego.getComponentes().getCampo().getTablero();
        this.casillaInicial = casillaInicial;
        this.casillaFinal = casillaFinal;

    }

    /**
     * Ejecuta el algoritmo de A*, calculando el camino, si existe, desde el
     * punto de inicio hasta el punto final.
     * @return Devuelve una lista (no vacía) de nodos si existe el camino. Si no existe, devuelve null.
     */
    public ArrayList<Casilla> calcularCamino()
    {
        //ArrayList <Casilla> listaAbierta = new ArrayList<Casilla>();
        ArrayList <ArrayList<Casilla>> listaAbierta = new ArrayList<ArrayList<Casilla>>();
        ArrayList <Casilla> listaAuxiliar = new ArrayList<Casilla>();
        ArrayList <Casilla> listaCerrada = new ArrayList<Casilla>();

        Casilla casillaActual = null;
        boolean caminoEncontrado = false;

        int filas = matriz.length;
        int columnas = 0;
        if (filas > 0)
            columnas = matriz[0].length;

        // Añadimos el cuadro inicial a la lista abierta.
        listaAuxiliar.add(casillaInicial);

        listaAbierta.add(listaAuxiliar);

        //listaAuxiliar.clear();


        int iteraciones = 0;
        // Buscamos el camino mientras queden nodos candidatos y no lo hayamos encontrado.
        while (!listaAbierta.isEmpty() && !caminoEncontrado)
        {
            iteraciones++;
            // Extraemos el nodo de menor F desde la lista abierta hacia la lista cerrada.
            //casillaActual = (Casilla)listaAbierta.remove(0);
            System.out.println(listaAbierta.size());
            listaAuxiliar = listaAbierta.remove(0);

            //imprimirLista(listaAuxiliar);
            casillaActual = listaAuxiliar.get(listaAuxiliar.size()-1);
            listaCerrada.add(casillaActual);

            listaAuxiliar.clear();

            // Extraemos los nodos adyacentes al nodo actual.
            ArrayList<Casilla> casillasAdyacentes = new ArrayList<Casilla>();

            boolean derecha = false, izquierda = false, arriba = false, abajo = false;
            if (0 <= casillaActual.getPosX()+1 && casillaActual.getPosX()+1 < columnas && 0 <= casillaActual.getPosY() && casillaActual.getPosY() < filas)
            {
                if (matriz[(int)casillaActual.getPosY()][(int)casillaActual.getPosX()+1].casillaLibre())
                {
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY()][(int)casillaActual.getPosX() + 1]);
                    derecha = true;
                }
            }
            if (0 <= casillaActual.getPosX()-1 && casillaActual.getPosX()-1 < columnas && 0 <= casillaActual.getPosY() && casillaActual.getPosY() < filas)
            {
                if (matriz[(int)casillaActual.getPosY()][(int)casillaActual.getPosX()-1].casillaLibre())
                {
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY()][(int)casillaActual.getPosX() - 1]);
                    izquierda = true;
                }
            }
            if (0 <= casillaActual.getPosX() && casillaActual.getPosX() < columnas && 0 <= casillaActual.getPosY()-1 && casillaActual.getPosY()-1 < filas)
            {
                if (matriz[(int)casillaActual.getPosY()-1][(int)casillaActual.getPosX()].casillaLibre())
                {
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY() - 1][(int)casillaActual.getPosX()]);
                    arriba = true;
                }
            }
            if (0 <= casillaActual.getPosX() && casillaActual.getPosX() < columnas && 0 <= casillaActual.getPosY()+1 && casillaActual.getPosY()+1 < filas)
            {
                if (matriz[(int)casillaActual.getPosY()+1][(int)casillaActual.getPosX()].casillaLibre())
                {
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY() + 1][(int)casillaActual.getPosX()]);
                    abajo = true;
                }
            }
/*
            // Sólo incluidos las diagonales si las ortogonales se han incluido previamente ya que para ser 8-conectado primero debe ser 4-conectado.
            if (0 <= casillaActual.getPosX()+1 && casillaActual.getPosX()+1 < columnas && 0 <= casillaActual.getPosY()-1 && casillaActual.getPosY()-1 < filas && (forzar8conectado || (arriba && derecha)))
                if (matriz[(int)casillaActual.getPosY()-1][(int)casillaActual.getPosX()+1].casillaLibre())
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY()-1][(int)casillaActual.getPosX()+1]);

            if (0 <= casillaActual.getPosX()-1 && casillaActual.getPosX()-1 < columnas && 0 <= casillaActual.getPosY()-1 && casillaActual.getPosY()-1 < filas && (forzar8conectado || (arriba && izquierda)))
                if (matriz[(int)casillaActual.getPosY()-1][(int)casillaActual.getPosX()-1].casillaLibre())
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY()-1][(int)casillaActual.getPosX()-1]);

            if (0 <= casillaActual.getPosX()+1 && casillaActual.getPosX()+1 < columnas && 0 <= casillaActual.getPosY()+1 && casillaActual.getPosY()+1 < filas && (forzar8conectado || (abajo && derecha)))
                if (matriz[(int)casillaActual.getPosY()+1][(int)casillaActual.getPosX()+1].casillaLibre())
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY()+1][(int)casillaActual.getPosX()+1]);

            if (0 <= casillaActual.getPosX()-1 && casillaActual.getPosX()-1 < columnas && 0 <= casillaActual.getPosY()+1 && casillaActual.getPosY()+1 < filas && (forzar8conectado || (abajo && izquierda)))
                if (matriz[(int)casillaActual.getPosY()+1][(int)casillaActual.getPosX()-1].casillaLibre())
                    casillasAdyacentes.add(matriz[(int)casillaActual.getPosY()+1][(int)casillaActual.getPosX()-1]);
*/
            // Para cada nodo encontrado, comprobamos si hemos llegado al punto de destino.
            while (!casillasAdyacentes.isEmpty() && !caminoEncontrado)
            {
                Casilla nodoAdyacente = casillasAdyacentes.remove(casillasAdyacentes.size()-1);
                if (!listaCerrada.contains(nodoAdyacente))
                {
                    ArrayList <Casilla> listaaux2 = new ArrayList<Casilla>();
                    if (listaAbierta.size()>0)
                    {
                        System.out.println("la lista es 0");
                        listaaux2 = listaAbierta.get(0);
                    }
                    //if (!listaAbierta.contains(nodoAdyacente))
                    if (!listaaux2.contains(nodoAdyacente))
                    {
                        //nodoAdyacente.setNodoPadre(casillaActual);

                        listaaux2.add(casillaActual);
                        listaaux2.add(nodoAdyacente);



                        listaAbierta.add(listaaux2);

                        //listaAuxiliar.clear();

                        if (casillaFinal == nodoAdyacente)
                        {
                            caminoEncontrado = true;
                        }
                    }
                    else
                    {
                       /* int nuevoG = casillaActual.getG();
                        if (nodoAdyacente.getX()==casillaActual.getX() || nodoAdyacente.getY()==casillaActual.getY())
                            nuevoG += 10;
                        else
                            nuevoG += 14;

                        if (nuevoG < nodoAdyacente.getG())
                        {
                            nodoAdyacente.setNodoPadre(casillaActual);
                            listaAbierta.reordenar();
                        }*/

                        int costecasilla = casillaActual.getCoste();

                        if (costecasilla<nodoAdyacente.getCoste()){
                            listaaux2.add(casillaActual);
                            listaaux2.add(nodoAdyacente);



                            listaAbierta.add(listaaux2);
                            reordenar(listaAbierta);

                        }
                    }
                }
                for (ArrayList<Casilla> lista: listaAbierta){
                    System.out.println("elemento lista abierta");
                    imprimirLista(lista);
                }

            }
        }




        // Si hemos llegado al nodo final, volvemos hacia atrás desde ese nodo extrayendo el camino hasta el nodo inicial.
        if (caminoEncontrado)
        {
            /*ArrayList camino = new ArrayList<Casilla>();
            NodoAstar nodoAuxiliar = casillaFinal;
            while (nodoAuxiliar != null)
            {
                camino.add(0, nodoAuxiliar);
                nodoAuxiliar = nodoAuxiliar.getNodoPadre();
            }
            return camino;*/



            return listaAbierta.get(0);
        }
        else
        {
            return null;
        }


    }

  public void reordenar(ArrayList <ArrayList <Casilla>> lista){


      /*Collections.sort(lista, new Comparator() {
          @Override
          public int compare(ArrayList <Casilla> lista1, ArrayList <Casilla> lista2) {
              return new Integer(lista1.size()).compareTo(new Integer(lista2.size()));
          }
      });*/

      /*BeanComparator fieldComparator = new BeanComparator(
              "fruitName");
      Collections.sort(fruits, fieldComparator);*/

      if (lista.size()>1){
          int auxilar = lista.get(0).size();
          for (ArrayList <Casilla> lista2: lista){
              if (lista2.size()<auxilar){
                ArrayList <Casilla> listaAuxiliar = lista2;
                lista.remove(lista2);
                lista.add(0,listaAuxiliar);
              }
          }
      }

  }

  public void imprimirLista(ArrayList <Casilla> lista){
      System.out.print("imprimiendo lista");
      for (Casilla casilla: lista){
          System.out.print("["+casilla.getPosicionX()+"] ["+casilla.getPosicionY()+"], ");
      }

  }


    /*public ArrayList calcularCamino()
    {
        Deap listaAbierta = new Deap();
        ArrayList listaCerrada = new ArrayList<NodoAstar>();
        NodoAstar nodoActual = null;
        boolean caminoEncontrado = false;

        int filas = matriz.length;
        int columnas = 0;
        if (filas > 0)
            columnas = matriz[0].length;

        // Añadimos el cuadro inicial a la lista abierta.
        listaAbierta.push(nodoInicial);

        int iteraciones = 0;
        // Buscamos el camino mientras queden nodos candidatos y no lo hayamos encontrado.
        while (!listaAbierta.isEmpty() && !caminoEncontrado)
        {
            iteraciones++;
            // Extraemos el nodo de menor F desde la lista abierta hacia la lista cerrada.
            nodoActual = (NodoAstar) listaAbierta.popBottom();
            listaCerrada.add(nodoActual);

            // Extraemos los nodos adyacentes al nodo actual.
            ArrayList nodosAdyacentes = new ArrayList<NodoAstar>();

            boolean derecha = false, izquierda = false, arriba = false, abajo = false;
            if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
            {
                if (matriz[nodoActual.getY()][nodoActual.getX()+1].getTransitable())
                {
                    nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()+1]);
                    derecha = true;
                }
            }
            if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY() && nodoActual.getY() < filas)
            {
                if (matriz[nodoActual.getY()][nodoActual.getX()-1].getTransitable())
                {
                    nodosAdyacentes.add(matriz[nodoActual.getY()][nodoActual.getX()-1]);
                    izquierda = true;
                }
            }
            if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas)
            {
                if (matriz[nodoActual.getY()-1][nodoActual.getX()].getTransitable())
                {
                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()]);
                    arriba = true;
                }
            }
            if (0 <= nodoActual.getX() && nodoActual.getX() < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas)
            {
                if (matriz[nodoActual.getY()+1][nodoActual.getX()].getTransitable())
                {
                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()]);
                    abajo = true;
                }
            }

            // Sólo incluidos las diagonales si las ortogonales se han incluido previamente ya que para ser 8-conectado primero debe ser 4-conectado.
            if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (forzar8conectado || (arriba && derecha)))
                if (matriz[nodoActual.getY()-1][nodoActual.getX()+1].getTransitable())
                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()+1]);

            if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()-1 && nodoActual.getY()-1 < filas && (forzar8conectado || (arriba && izquierda)))
                if (matriz[nodoActual.getY()-1][nodoActual.getX()-1].getTransitable())
                    nodosAdyacentes.add(matriz[nodoActual.getY()-1][nodoActual.getX()-1]);

            if (0 <= nodoActual.getX()+1 && nodoActual.getX()+1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (forzar8conectado || (abajo && derecha)))
                if (matriz[nodoActual.getY()+1][nodoActual.getX()+1].getTransitable())
                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()+1]);

            if (0 <= nodoActual.getX()-1 && nodoActual.getX()-1 < columnas && 0 <= nodoActual.getY()+1 && nodoActual.getY()+1 < filas && (forzar8conectado || (abajo && izquierda)))
                if (matriz[nodoActual.getY()+1][nodoActual.getX()-1].getTransitable())
                    nodosAdyacentes.add(matriz[nodoActual.getY()+1][nodoActual.getX()-1]);

            // Para cada nodo encontrado, comprobamos si hemos llegado al punto de destino.
            while (!nodosAdyacentes.isEmpty() && !caminoEncontrado)
            {
                NodoAstar nodoAdyacente = (NodoAstar) nodosAdyacentes.remove(0);
                if (!listaCerrada.contains(nodoAdyacente))
                {
                    if (!listaAbierta.contains(nodoAdyacente))
                    {
                        nodoAdyacente.setNodoPadre(nodoActual);
                        listaAbierta.push(nodoAdyacente);

                        if (nodoFinal == nodoAdyacente)
                        {
                            caminoEncontrado = true;
                        }
                    }
                    else
                    {
                        int nuevoG = nodoActual.getG();
                        if (nodoAdyacente.getX()==nodoActual.getX() || nodoAdyacente.getY()==nodoActual.getY())
                            nuevoG += 10;
                        else
                            nuevoG += 14;

                        if (nuevoG < nodoAdyacente.getG())
                        {
                            nodoAdyacente.setNodoPadre(nodoActual);
                            listaAbierta.reordenar();
                        }
                    }
                }
            }
        }


        // Si hemos llegado al nodo final, volvemos hacia atrás desde ese nodo extrayendo el camino hasta el nodo inicial.
        if (caminoEncontrado)
        {
            ArrayList camino = new ArrayList<NodoAstar>();
            NodoAstar nodoAuxiliar = nodoFinal;
            while (nodoAuxiliar != null)
            {
                camino.add(0, nodoAuxiliar);
                nodoAuxiliar = nodoAuxiliar.getNodoPadre();
            }
            return camino;
        }
        else
        {
            return null;
        }
    }*/

}
