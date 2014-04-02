package com.rugbysurvive.partida.gestores;

import com.rugbysurvive.partida.Dibujables.TipoDibujo;

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

    public Prueba(int posicionX,int posicionY,int contador,String textura){
        this.contador = contador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.current_contador = 0;
        this.ID = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);

        this.textura = textura;
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
