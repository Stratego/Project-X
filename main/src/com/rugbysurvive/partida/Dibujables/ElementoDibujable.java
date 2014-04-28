package com.rugbysurvive.partida.Dibujables;

import com.rugbysurvive.partida.elementos.objetos.GestorObjetos;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;

/**
 * Created by aitor on 15/04/14.
 */
public class ElementoDibujable implements Dibujable {

    TipoDibujo tipoDibujo;
    String textura ;
    int posicionX;
    int posicionY;
    int id;

    public ElementoDibujable(TipoDibujo tipoDibujo,String textura)
    {
        this.tipoDibujo = tipoDibujo;
        this.textura = textura;
        this.id = -1;
    }

    public void dibujar(int posicionX,int posicionY)
    {
        this.posicionY = posicionY;
        this.posicionX = posicionX;
        if(id == -1 )
        {
            id = GestorGrafico.generarDibujante().añadirDibujable(this,tipoDibujo);
        }
    }
    public void borrar()
    {
       if(id != -1)
       {
           GestorGrafico.generarDibujante().eliminarTextura(id);
           this.id = -1;
       }
    }
    @Override
    public String getTextura() {
        return textura;
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
