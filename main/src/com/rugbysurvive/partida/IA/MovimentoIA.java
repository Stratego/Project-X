package com.rugbysurvive.partida.IA;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;

/**
 * Created by Victor on 9/05/14.
 */
public class MovimentoIA {


    /**
     * Matriz con una serie de filas y una serie de columnas que representa el tablero sobre
     * el que se buscará el camino. Cada celda de la matriz es un nodo que puede
     * ser transitable o no y que tiene un coste particular ).
     */
    private NodoIA[][] matriz;

    /**
     * Nodo de partida que indica una posición (x,y) en la matriz de celdas.
     * No importa si es un nodo intransitable y tampoco se considera el coste.
     */
    private NodoIA nodoInicial;

    /**
     * Nodo de llegada que indica una posición (x,y) en la matriz de celdas.
     * Debe ser un nodo transitable.
     */
    private NodoIA nodoFinal;

    private ArrayList caminoFinal = new ArrayList<NodoIA>();

    /**
     * Constructor por defecto.

     * @param casillaInicial Casilla de partida que indica una posición (x,y) en la matriz de celdas.
     * @param casillaFinal Casilla de llegada que indica una posición (x,y) en la matriz de celdas.

     */
    public MovimentoIA (Casilla casillaInicial,Casilla casillaFinal)
    {
        this.matriz = comversorMatriz(ComponentesJuego.getComponentes().getCampo().getTablero());
        this.nodoInicial = conversorNodo(casillaInicial);
        this.nodoFinal = conversorNodo(casillaFinal);
        FinalCamino();


    }


    public ArrayList calcularCamino()
    {
        PilaIA listaAbierta = new PilaIA();
        ArrayList listaCerrada = new ArrayList<NodoIA>();
        NodoIA nodoActual = null;
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
            nodoActual = (NodoIA) listaAbierta.popBottom();
            listaCerrada.add(nodoActual);

            // Extraemos los nodos adyacentes al nodo actual.
            ArrayList nodosAdyacentes = new ArrayList<NodoIA>();

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

            // Para cada nodo encontrado, comprobamos si hemos llegado al punto de destino.
            while (!nodosAdyacentes.isEmpty() && !caminoEncontrado)
            {
                NodoIA nodoAdyacente = (NodoIA) nodosAdyacentes.remove(0);
                if (!listaCerrada.contains(nodoAdyacente))
                {
                    if (!listaAbierta.contains(nodoAdyacente))
                    {
                        nodoAdyacente.setNodoPadre(nodoActual);
                        //System.out.println(nodoAdyacente.getNodoPadre().getCoste());
                        listaAbierta.push(nodoAdyacente);
                        //System.out.println(listaAbierta.toString());

                        if ((nodoFinal.getX() == nodoAdyacente.getX()) && (nodoFinal.getY() == nodoAdyacente.getY()) )
                        {
                            caminoEncontrado = true;
                            ArrayList camino = new ArrayList<NodoIA>();
                            NodoIA nodoAuxiliar = nodoAdyacente;

                            while (nodoAuxiliar != null)
                            {
                                camino.add(0, nodoAuxiliar);
                                nodoAuxiliar = nodoAuxiliar.getNodoPadre();
                                caminoFinal=camino;
                            }
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
            return caminoFinal;
        }
        else
        {
            return null;
        }
    }


    public void imprimirLista(ArrayList <NodoIA> lista){
        System.out.print("imprimiendo lista");
        for (NodoIA casilla: lista){
            System.out.println("["+casilla.getY()+"] ["+casilla.getX()+"], ");
        }

    }
    public NodoIA conversorNodo(Casilla casilla){

        NodoIA nodo = new NodoIA();
        nodo.setX((int)casilla.getPosX());
        nodo.setY((int) casilla.getPosY());
        nodo.setTransitable(true);
        if (casilla.hayPelota()){
            nodo.setCoste(0);
        }else if (casilla.sinJugador()==false && casilla.sinObjeto()==false && casilla.sinArbitro()==false){
            nodo.setCoste(4);
        }else{
            nodo.setCoste(1);
        }
        return nodo;
    }

    public NodoIA [][] comversorMatriz(Casilla[][] campo){

        NodoIA[][] matriz = new NodoIA[20][30] ;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                matriz[i][j]=conversorNodo(campo[i][j]);
            }
        }
        return matriz;

    }

    public void FinalCamino(){

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                matriz[i][j].setNodoFinal(nodoFinal);
            }
        }
    }

    public int[][] arraymovimento(Jugador jugador){

        int iteraciones = 0;
        ArrayList <NodoIA> lista = calcularCamino();

        int cantidadMovimientos = jugador.getResistencia()/10;

        if(cantidadMovimientos <= 2)
        {
            cantidadMovimientos = 3;
        }

        int[][] movimiento = new int [cantidadMovimientos][2];

        for (NodoIA nodo :  lista){

            if(iteraciones < cantidadMovimientos)
            {
                movimiento[iteraciones][0]= nodo.getX();
                movimiento[iteraciones][1]= nodo.getY();
            }
            iteraciones++;
        }
        int [][] movimientosAux = new int[cantidadMovimientos][2];

        for(int i=0; i<cantidadMovimientos; i++)
        {

            if (movimiento[i][0]!=0 && movimiento[i][1]!=0 ){
                System.out.println("matriz movimiento:");

                movimientosAux[i][0] = movimiento[i][0];

                System.out.println(movimientosAux[i][0]);
                movimientosAux[i][1] = movimiento[i][1];
                System.out.println(movimientosAux[i][1]);
            }
        }


        return movimientosAux;
    }

}
