package com.rugbysurvive.partida.elementos.objetos.objetosCampo;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.Jugador;

import com.rugbysurvive.partida.elementos.objetos.ObjetoCampo;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;


/**
 * Created by Victor on 15/04/14.
 */
public class MinaCampo extends ObjetoCampo {
    private static final int TIEMPO_ENTRE_TEXTURAS = 5;
    private int tiempo;
    private ElementoDibujable animacion;
    private int contador;
    public boolean animando;


    public MinaCampo(String textura,Jugador jugador){
        super(textura,jugador);
        this.tiempo = 0;
        this.contador = 1;

    }
    @Override
    public void efecto(Jugador jugador) {
        jugador.setVida(jugador.getVida()-100);
        //System.out.println("vida jugador que ha pisado"+jugador.getVida());
        ProcesosContinuos.añadirProceso(this);
        this.animando = true;
        quitar();
    }

    @Override
    protected boolean animacion() {
        if(this.animando){
       if(tiempo%TIEMPO_ENTRE_TEXTURAS == 0 ) {
               this.animacion.borrar();
               this.animacion = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/explosion/explosio"+contador+".png");
               this.animacion.dibujar(this.getPosicionX(),this.getPosicionY());
            System.out.println("==================INICIANDO EXPLOSION=========================");

           this.contador++;
       }
       else if(tiempo == 0){
               this.animacion = new ElementoDibujable(TipoDibujo.interficieUsuario,"objetos/explosion/explosio"+contador+".png");
               this.animacion.dibujar(this.getPosicionX(),this.getPosicionY());
               this.contador++;
           System.out.println("==================EXPLOSION=========================");
       }

       if(this.tiempo%30 == 0) {
           this.animacion.borrar();
           return true;
       }
       this.tiempo++;
       return false;
    }
        return false;
    }



}
