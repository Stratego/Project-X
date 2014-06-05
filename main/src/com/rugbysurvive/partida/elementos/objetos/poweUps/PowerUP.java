package com.rugbysurvive.partida.elementos.objetos.poweUps;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.arbitro.UsoObjetos;
import com.rugbysurvive.partida.elementos.objetos.Objeto;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Habilidades;


/**
 * Objeto que sera usado por el jugador sobre si mismo.
 * afectara una habilidad durante un tiempo limitado despues del cual se desactiva
 * ademas se efectuara una animacion para mostrar su uso.
 * Created by Victor on 11/04/14.
 */
public class PowerUP extends Objeto implements Proceso {


    UsoObjetos usoObjetos;

    /**
     * indica se esta efectuando una animacion
     */
    private boolean animacionIniciada;

    /**
     * representacion del la textura que se dibujara en la animacion
     */
    private ElementoDibujable flecha;

    /**
     * posicion y de la textura de la animacion
     */
    private int posicionYFlecha;

    /**
     * posicion x en la que se usara el objeto
     */
    protected int x;

    /**
     * posicion y en la que se usara el objeto
     */
    protected int y;

    /**
     * constructor del objeto power up
     * @param id identificador del objeto
     * @param vida vida util del objeto
     * @param imagen imagen que representa el objeto graficamente
     * @param habilidad habilidad a la que afecta el objeto
     * @param modificacion cantidad de habilidad dara el objeto
     * @param jugador jugador que usa el objeto
     */
    public PowerUP(int id,int vida,String imagen, Habilidades habilidad,int modificacion,Jugador jugador) {

        super(id, vida, imagen,jugador);
        this.habilidad = habilidad;
        this.modificacion = modificacion;
        this.animacionIniciada = false;
        this.flecha = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/flechaGrande.png");
    }

    /**
     * constructor del objeto power up
     * @param id identificador del objeto
     * @param vida vida util del objeto
     * @param habilidad habilidad a la que afecta el objeto
     * @param modificacion cantidad de habilidad dara el objeto
     * @param jugador jugador que usa el objeto
     */
    public PowerUP(int id,int vida, Habilidades habilidad,int modificacion,Jugador jugador){
        super(id,vida,generarTextura(habilidad),jugador);
        this.habilidad = habilidad;
        this.modificacion = modificacion;
        this.animacionIniciada = false;
        this.flecha = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/flechaGrande.png");
    }

    /**
     * genera una textura para la representacion grafica en funcion de la habilidad del objeto
     * @param habilidad habilidad del objeto
     * @return textura para la representacion grafica
     */
    private static String generarTextura(Habilidades habilidad){

        switch(habilidad){
            case fuerza:
                return "Menu/Habilidades/fuerza.png";
            case ataque:
                return "Menu/Habilidades/atac.png";
            case defensa:
                return "Menu/Habilidades/defensa.png";
            case habilidad:
                break;
            default:
               return  "Menu/Habilidades/resistencia.png";


        }
        return null;
    }


    @Override
    protected void iniciar() {
      //  usoObjetos = new UsoObjetos(this.jugador);
       // usoObjetos.arbitrar();
        //int valorActual = 0;
        ProcesosContinuos.añadirProceso(this);
        System.out.print("Jugador 2 "+this.jugador);
     switch (habilidad)
        {
            // en funcion de de la habilidad del power up se incrementa la habilidad del jugador
            case vida:

                jugador.setVida(jugador.getVida()+modificacion);
                break;

            case fuerza:

                jugador.setFuerza(jugador.getFuerza() + modificacion);
                break;

            case defensa:

                jugador.setDefensa(jugador.getDefensa() + modificacion);
                break;

            case resistencia:

                jugador.setResistencia(jugador.getResistencia() + modificacion);
                break;

            case habilidad:

                jugador.setHabilidad(jugador.getHabilidad() + modificacion);
                break;

            case ataque:

                jugador.setAtaque(jugador.getAtaque() + modificacion);
                break;
        }



        System.out.println("INICIAR");

    }

    @Override
    protected void desactivar() {
        switch (habilidad)
        {
            //cuando se desactiva el objeto la habilidad vuelve al estado anterior
            case vida:

                jugador.setVida(jugador.getVida()-modificacion);
                break;

            case fuerza:

                jugador.setFuerza(jugador.getFuerza()-modificacion);
                break;

            case defensa:

                jugador.setDefensa(jugador.getDefensa()-modificacion);
                break;

            case resistencia:

                jugador.setResistencia(jugador.getResistencia() - modificacion);
                break;

            case habilidad:

                jugador.setHabilidad(jugador.getHabilidad() - modificacion);
                break;

            case ataque:

                jugador.setAtaque(jugador.getAtaque() - modificacion);
                break;
        }

        //System.out.println("vida jugador al finalizar objeto "+ jugador.getVida());
        System.out.println("FINALIZAR");

    }


    @Override
    public boolean procesar() {
        if(!this.animacionIniciada){
            this.posicionYFlecha =  - ConstantesJuego.TAMAÑO_PUÑO;
            this.flecha.dibujar(ConstantesJuego.getHeight()/2- ConstantesJuego.TAMAÑO_PUÑO/2, - ConstantesJuego.TAMAÑO_PUÑO);
            this.animacionIniciada = true;
        }
        else if(this.posicionYFlecha >= ConstantesJuego.getWidth()/2 - ConstantesJuego.TAMAÑO_PUÑO/2){
            this.flecha.borrar();
            return true;
        }
        else{
            this.flecha.borrar();
            this.flecha.dibujar(ConstantesJuego.getHeight()/2- ConstantesJuego.TAMAÑO_PUÑO/2,this.posicionYFlecha);
        }
        this.posicionYFlecha = this.posicionYFlecha+15;
        return false;

    }
}
