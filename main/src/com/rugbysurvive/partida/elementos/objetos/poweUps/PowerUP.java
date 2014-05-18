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
 * Created by aitor on 11/04/14.
 */
public class PowerUP extends Objeto implements Proceso {


    UsoObjetos usoObjetos;
    private boolean animacionIniciada;
    private ElementoDibujable flecha;
    private int posicionYFlecha;


    protected int x;
    protected int y;

    public PowerUP(int id,int vida,String imagen, Habilidades habilidad,int modificacion,Jugador jugador) {

        super(id, vida, imagen,jugador);
        this.habilidad = habilidad;
        this.modificacion = modificacion;
        this.animacionIniciada = false;
        this.flecha = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/flechaGrande.png");
    }

    public PowerUP(int id,int vida, Habilidades habilidad,int modificacion,Jugador jugador){
        super(id,vida,generarTextura(habilidad),jugador);
        this.habilidad = habilidad;
        this.modificacion = modificacion;
        this.animacionIniciada = false;
        this.flecha = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/flechaGrande.png");
    }

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
