package com.rugbysurvive.partida.gestores;

/**
 * Created by aitor on 26/03/14.
 */
public class Prueba implements Dibujable{

    int posicionX;
    int posicionY;
    int ID;
    int contador;
    int current_contador;
    Dibujante dibujante;

    public Prueba(Dibujante dibujante,int posicionX,int posicionY,int contador){
        this.contador = contador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        ID =  dibujante.añadirDibujable(this);
        this.current_contador = 0;
        this.dibujante = dibujante;
    }


      public void render()
      {
          this.current_contador++;
          if(this.current_contador == contador)
          {
            this.dibujante.eliminarTextura(24);
          }
      }
    @Override
    public String getTextura() {
        return "jugador1.png";
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
