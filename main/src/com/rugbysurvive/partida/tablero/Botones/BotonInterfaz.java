package com.rugbysurvive.partida.tablero.Botones;



import com.rugbysurvive.partida.ConstantesJuego;

import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.tablero.Boton;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by Victor on 24/04/14.
 */
public class BotonInterfaz extends Boton{
    private Entrada entradaBoton;
    /**
     * Constructor del elemento boton
     *
     * @param posX     posicion x en el tablero
     * @param posY     posicion y en el tablero
     * @param entrada  tipo de boton que sera
     * @param textura
     * @param posicion
     */
    public BotonInterfaz(float posX, float posY, Entrada entrada, String textura, int posicion) {
        super(posX, posY, entrada, textura, posicion,ConstantesJuego.ANCHO_BOTON,ConstantesJuego.ALTO_BOTON);
        this.entradaBoton = entrada;
        this.posY = ConstantesJuego.POSICION_BOTON_ESCONDIDO;
        this.setEscondido(true);



    }

    @Override
    public void accionEntrada(Entrada entrada) {

        if (this.entradaBoton == Entrada.pase){
            //introducir accion pase
            this.entradaBoton = Entrada.chute;
            this.textura = "Menu/xutBoto.png";


        } else {
            if (this.entradaBoton == Entrada.chute){
                //introducir accion chute
                this.entradaBoton = Entrada.pase;
                this.textura = "Menu/botoPassada.png";
            }
        }


        Campo.getInstanciaCampo().accionEntrada(this.entradaBoton,0,0);

    }
}
