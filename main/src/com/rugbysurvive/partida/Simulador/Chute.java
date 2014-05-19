package com.rugbysurvive.partida.Simulador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
import com.rugbysurvive.partida.elementos.Marcador;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

/**
 * Created by Aleix on 31/03/14.
 */
public class Chute extends Accion {

    int posXObjetivo;
    int posYObjetivo;
    private Jugador jugador;
    private Sound soundChute;


    public Chute(Jugador jugador, int posX, int posY) {
        this.posXObjetivo = posX;
        this.posYObjetivo = posY;
        this.jugador = jugador;
        this.soundChute = Gdx.audio.newSound(Gdx.files.internal("sonido/acciones/Chute.mp3"));
    }

    @Override
    public boolean simular() {
        System.out.println("Chute hecho");

        //jugador.setEstado(new SinPelota());
        //return true;

        this.soundChute.play();
        this.precisionChute();

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
        System.out.println("__________________"+((jugador.getFuerza()/10)*64)+"---------"+DistanciaXute);
        System.out.println("InicialX "+InicialX+" FinalX "+FinalX);
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
            int contPrecision = 0;
            /*Obtenemos la posición de la matriz de la que se cogera el chute y validamos que la posición X y Y almacenada es correcta*/
            while(casillaChute < ((jugador.getHabilidad()/4)-1))
            {
                casillaChute = new Double(Math.random() * (24)).intValue();
                System.out.println("RANDOOOOM:"+casillaChute);
                casillaChute = casillaChute - (jugador.getHabilidad()/4);
                if(casillaChute >= 0)
                {
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
                if(contPrecision == 8)
                {
                    casillaChute = 24;
                }
                contPrecision = contPrecision + 1;
                System.out.println("Contador"+contPrecision);
            }

            Campo.getInstanciaCampo().colocarPelota(ejesDestinoPelota[casillaChute][1], ejesDestinoPelota[casillaChute][0]);

            comprobarPunto(ejesDestinoPelota, casillaChute);

            System.out.println("La pelota va a la posicion: "+ejesDestinoPelota[casillaChute][0]+"-"+ejesDestinoPelota[casillaChute][1]);
            jugador.setEstado(new SinPelota());
            return true;
        }
        return false;
    }

    public void comprobarPunto(int ejesDestinoPelota[][], int index)
    {
        if(ejesDestinoPelota[index][1] >= 8 && ejesDestinoPelota[index][1] <= 11)
        {
            if(this.jugador.getMiEquipo().getLado() == Lado.izquierda)
            {
                if(ejesDestinoPelota[index][0] >= 28)
                {
                    Marcador.getInstanceMarcador().sumarPuntuacion(2, this.jugador);
                }
            }
            else
            {
                if(ejesDestinoPelota[index][0] <= 2)
                {
                    Marcador.getInstanceMarcador().sumarPuntuacion(2, this.jugador);
                }
            }
        }
    }

    @Override
    public void simularAnimacion() {

    }
}