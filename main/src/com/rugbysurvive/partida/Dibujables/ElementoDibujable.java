package com.rugbysurvive.partida.Dibujables;

import com.rugbysurvive.partida.elementos.objetos.GestorObjetos;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;

/**
 * Created by aitor on 15/04/14.
 *
 * Realiza el proceso de dibujado de una textura concreta .
 * Permite definir un conjunto de texturas sobre una misma clase
 * teniendo calla una de ellas vida propia para ser dibujada
 * o desdibujada cuando sera necesario
 */
public class ElementoDibujable implements Dibujable {

    private TipoDibujo tipoDibujo;
    private String textura ;
    private int posicionX;
    private int posicionY;
    private int id;

    /**
     * @param tipoDibujo caracteristicas de la textura dentro del juego
     * @param textura direccion donde esta guardada la textura
     */
    public ElementoDibujable(TipoDibujo tipoDibujo,String textura) {
        this.tipoDibujo = tipoDibujo;
        this.textura = textura;
        this.id = -1;
    }

    /**
     * Realiza el proceso de dibujado en la pantalla en la posicion determinada.
     *
     * @param posicionX posicion del eje x donde sera dibujado el elemento
     * @param posicionY posicion del eje y donde sera dibujado el elemento
     */
    public void dibujar(int posicionX,int posicionY) {
        this.posicionY = posicionY;
        this.posicionX = posicionX;

        if(id == -1) {
            id = GestorGrafico.generarDibujante().añadirDibujable(this,tipoDibujo);
        }
    }

    /**
     * Realiza el proceso de borrado, en el preciso instante
     * dejara de mostrarse por pantalla.
     */
    public void borrar()
    {
       if(id != -1) {
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
