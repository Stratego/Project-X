package com.rugbysurvive.partida.gestores;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;

/**
 * Created by aitor on 26/03/14.
 */
public class Prueba implements Dibujable{

    int posicionX;
    int posicionY;
    int ID;
    int contador;
    int current_contador;
    String textura;
    ObjetoJugador objetoJugador;

    public Prueba(int posicionX,int posicionY,int contador,String textura){
        this.contador = contador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.current_contador = 0;
        this.ID = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.texto);
        //objetoJugador = new PowerUP(5);
        this.textura = "holaaaaaaaa";
        objetoJugador.activar();
    }


     public void render()
     {
          this.current_contador++;
          if(this.current_contador == contador)
          {
            GestorGrafico.generarDibujante().eliminarTextura(ID);
          }
     }
    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return this.posicionX;
    }

    @Override
    public int getPosicionY() {
        return this.posicionY;
    }
}
