package com.rugbysurvive.partida.Simulador;




/**
 * Created by Victor on 27/03/14.
 * Esqueleto para generar una accion que sea aceptada por el simulador
 * Toda clase que herede de accion debe implementar la funcion simular
 * que sera la base por la cual se generara la accion en el simulador.
 */
public abstract class Accion {

    protected boolean animacionParada = false;
    protected boolean finalizado = false;


    /**
     * Cada vez que se llama esta funcion se realiza un proceso de simulacion
     * Una accion puede contener tantas simulaciones como sean necesarias para
     * completar la accion.
     * Para finalizar la accion la funcion simular debe devolver cierto
     * en caso contrario devolvera false.
     */
    public abstract boolean simular();

    /**
     * Indica si la simulacion ha finalizado
     * @return cierto si la funcion ha finalizado
     */
    public boolean getFinalizado(){
        return this.finalizado;
    }

    /**
     * Prohibe el uso de procesos que requieren un tiempo
     * de procesado a lo largo del tiempo y que no se puedan
     * ejecutar de forma instantanea
     */
    public void  evitarAnimacion() {
        this.animacionParada = true;
    }

    /**
     * Permite el uso de procesos que requieren un tiempo
     * de procesado a lo largo del tiempo y que no se puedan
     * ejecutar de forma instantanea
     */
    public void permitirAnimacion() {
        this.animacionParada = false;
    }
}
