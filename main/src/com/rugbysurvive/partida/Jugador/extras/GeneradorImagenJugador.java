package com.rugbysurvive.partida.Jugador.extras;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.DireccionJugador;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aitor on 1/05/14.
 */
public class GeneradorImagenJugador {


    private static final int FRONTAL = 2;
    private static final int LATERAL_IZQUIERDO = 5;
    private static final int LATERAL_DERECHO = 1;
    private static final int FRONTAL_PARADO = 3;
    private static final int TRASERO = 4;

    private static final int ROJO = 1;
    private static final int AMARILLO = 2;
    private static final int AZUL = 3;
    private static final int VERDE = 4;


    public static ArrayList<ElementoDibujable> generarTexturas(Color color,int aspecto,DireccionJugador direccion){
       ArrayList<ElementoDibujable> texturas  = new ArrayList<ElementoDibujable>();
        texturas.add(generarRopa(color,direccion));
        texturas.add(generarAspecto(direccion,aspecto));
        texturas.add(generarPerfil(direccion));

        return texturas;
    }

    public static ArrayList<ElementoDibujable> generarTexturasIntefaz(Color color,int aspecto,DireccionJugador direccion){
        ArrayList<ElementoDibujable> texturas  = new ArrayList<ElementoDibujable>();
        texturas.add(generarRopaIntefaz(color,direccion));
        texturas.add(generarAspectoInterfaz(direccion,aspecto));
        texturas.add(generarPerfilInterfaz(direccion));

        return texturas;
    }


    private static ElementoDibujable generarRopaIntefaz(Color color,DireccionJugador direccion){
        String textura = "jugador/ropa/jugador"+generarDireccion(direccion)+"E"+generarColor(color)+".png";
        return new ElementoDibujable(TipoDibujo.interficieUsuario,textura);
    }

    private static ElementoDibujable generarAspectoInterfaz(DireccionJugador direccion,int tipoPiel){

        String textura = "jugador/piel/jugador"+generarDireccion(direccion)+"pell"+tipoPiel+".png";
        return new ElementoDibujable(TipoDibujo.interficieUsuario,textura);

    }

    public static int generarAspecto(){
        Random random = new Random();
        int tipoPiel = random.nextInt(2)+1;
        return tipoPiel;
    }


    private static ElementoDibujable generarRopa(Color color,DireccionJugador direccion){
        String textura = "jugador/ropa/jugador"+generarDireccion(direccion)+"E"+generarColor(color)+".png";
        return new ElementoDibujable(TipoDibujo.elementosJuego,textura);
    }

    private static ElementoDibujable generarAspecto(DireccionJugador direccion,int tipoPiel){
        String textura = "jugador/piel/jugador"+generarDireccion(direccion)+"pell"+tipoPiel+".png";
        return new ElementoDibujable(TipoDibujo.elementosJuego,textura);

    }

    private static int generarColor(Color color){
        switch (color){
            case amarillo:
                return 2;

            case rojo:
                return 1;

            case azul:
               return 3;

            case verde:
                return 4;

        }
        return 1;
    }


    private static int generarDireccion(DireccionJugador direccionJugador){

        switch (direccionJugador){
            case derecha:
                return LATERAL_DERECHO;
            case izquierda:
                return LATERAL_IZQUIERDO;
            case arriba:
                return TRASERO;
            case abajo:
                return FRONTAL;
            case frontal:
                return FRONTAL_PARADO;
        }
        return FRONTAL_PARADO;
    }


    private static ElementoDibujable generarPerfil (DireccionJugador direccion)
    {
        String textura ="";

        switch (direccion)
        {
            case arriba:
                textura = "jugador/jugador4.png";
                break;
            case abajo:
                textura = "jugador/jugador2.png";
                break;
            case izquierda:
                textura = "jugador/jugador5.png";
                break;
            case derecha:
                textura = "jugador/jugador1.png";
                break;
            case frontal:
                textura = "jugador/jugador3.png";
        }

        return new ElementoDibujable(TipoDibujo.elementosJuego,textura);
    }



    private static ElementoDibujable generarPerfilInterfaz (DireccionJugador direccion)
    {
        String textura ="";

        switch (direccion)
        {
            case arriba:
                textura = "jugador/jugador4.png";
                break;
            case abajo:
                textura = "jugador/jugador2.png";
                break;
            case izquierda:
                textura = "jugador/jugador5.png";
                break;
            case derecha:
                textura = "jugador/jugador1.png";
                break;
            case frontal:
                textura = "jugador/jugador3.png";
        }

        return new ElementoDibujable(TipoDibujo.interficieUsuario,textura);
    }
}
