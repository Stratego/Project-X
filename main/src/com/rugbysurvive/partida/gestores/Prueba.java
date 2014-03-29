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
    String textura;

    public Prueba(Dibujante dibujante,int posicionX,int posicionY,int contador,String textura){
        this.contador = contador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        ID =  dibujante.añadirDibujable(this);
        this.current_contador = 0;
        this.dibujante = dibujante;
        this.textura = textura;
    }


     public void render()
     {
          this.current_contador++;
          if(this.current_contador == contador)
          {
            this.dibujante.eliminarTextura(ID);
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
