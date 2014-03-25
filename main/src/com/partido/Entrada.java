package com.partido;

/**
 * Created by Victor on 24/03/14.
 */
public interface Entrada {

    public void accionEntrada(Imput imput,float posX, float posY);
    public void accionEntrada(Imput imput);
    public void accionArrastre(float posX, float posY);
    public boolean esSeleccionado(float posX, float posY);
}
