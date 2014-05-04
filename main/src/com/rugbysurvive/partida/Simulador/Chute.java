package com.rugbysurvive.partida.Simulador;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;

/**
 * Created by Aleix on 31/03/14.
 */
public class Chute extends Accion {

    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;


    public Chute(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
    }

    @Override
    public boolean simular() {
        System.out.println("Chute hecho");

        //jugador.setEstado(new SinPelota());
        //return true;


        precisionChute();

        return true;
    }

    public boolean precisionChute (){
        int InicialX = jugador.getPosicionX();
        int InicialY = jugador.getPosicionY();
        int FinalX = this.posXObjetivo;
        int FinalY = this.posYObjetivo;
        int TotalCasillasX = FinalX - InicialX;
        int TotalCasillasY = FinalY - InicialY;
        int DistanciaXute  = ((int) Math.hypot (TotalCasillasX, TotalCasillasY))*64;
        if (((jugador.getFuerza()/10)*64) >= DistanciaXute)
        {
            int ejesDestinoPelota[][] = new int[25][2];
            ejesDestinoPelota[24][0] = FinalX;
            ejesDestinoPelota[24][1] = FinalY;

            boolean resto = true;
            boolean xoy = true;
            int contCambioXoY = 1;
            int cont = 0;


            //Como hay 25 casillas, empezamos desde la 25, que seria con precision 4 hasta la 0, que seria precision 100
            for(int i=23; i>=0; i--)
            {
                if(xoy == true)
                {
                    if(resto == true)
                    {
                        ejesDestinoPelota[i][0] = ejesDestinoPelota[i+1][0] - 1;
                        ejesDestinoPelota[i][1] = ejesDestinoPelota[i+1][1];
                        cont ++;
                    }
                    else
                    {
                        ejesDestinoPelota[i][0] = ejesDestinoPelota[i+1][0] + 1;
                        ejesDestinoPelota[i][1] = ejesDestinoPelota[i+1][1];
                        cont ++;
                    }

                    if(contCambioXoY == cont)
                    {
                        xoy = false;
                        cont = 0;
                    }
                }
                else
                {
                    if(resto == true)
                    {
                        ejesDestinoPelota[i][0] = ejesDestinoPelota[i+1][0];
                        ejesDestinoPelota[i][1] = ejesDestinoPelota[i+1][1] - 1;
                        cont ++;
                    }
                    else
                    {
                        ejesDestinoPelota[i][0] = ejesDestinoPelota[i+1][0];
                        ejesDestinoPelota[i][1] = ejesDestinoPelota[i+1][1] + 1;
                        cont ++;
                    }

                    if(contCambioXoY == cont)
                    {
                        xoy = true;
                        cont = 0;
                        contCambioXoY ++;
                        if(resto == true)
                        {
                            resto = false;
                        }
                        else
                        {
                            resto = true;
                        }
                    }
                }
            }

            int casillaChute = -1;
            /*Obtenemos la posición de la matriz de la que se cogera el chute y validamos que la posición X y Y almacenada es correcta*/
            while(casillaChute < jugador.getHabilidad()/10)
            {
                casillaChute = new Double(Math.random() * (25)).intValue();

                if(ejesDestinoPelota[casillaChute][0] < 0 || ejesDestinoPelota[casillaChute][0]>=30)
                {
                    casillaChute = -1;
                }
                else
                {
                    if(ejesDestinoPelota[casillaChute][1] < 0 || ejesDestinoPelota[casillaChute][1]>=20)
                    {
                        casillaChute = -1;
                    }
                }
            }

            // Campo.getInstanciaCampo().ponerPelota(ejesDestinoPelota[casillaChute][0], ejesDestinoPelota[casillaChute][1]);


           // Campo.getInstanciaCampo().ponerPelota(ejesDestinoPelota[casillaChute][0], ejesDestinoPelota[casillaChute][1]);

            System.out.println("La pelota va a la posicion: "+ejesDestinoPelota[casillaChute][0]+"-"+ejesDestinoPelota[casillaChute][1]);
            jugador.setEstado(new SinPelota());
            return true;
        }
        return false;
    }


    @Override
    public void simularAnimacion() {

    }
}