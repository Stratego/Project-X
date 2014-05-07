package com.partido;

import com.badlogic.gdx.Gdx;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;

import java.util.Random;

/**
 * Created by aitor on 21/04/14.
 *
 * Se encarga de procesar los turnos de cada equipo
 * Dibuja y gestiona las transiciones entre un
 * turno y el otro
 */
public class GestorTurnos implements Dibujable,Proceso {

    private static final int VELOCIDAD = 10;
    private int posicionTexturaX;
    private int posicionTexturaY;
    private static String estandarteEquipo1 ="Menu/CanviTorn.png";
    private static String estandarteEquipo2 = "banderas/logo4.png";
    private static int equipoCambiado = 0;
    private int id;

    Arbitro arbitro=Arbitro.getInstancia();

    private static boolean forzarCambioTurno = false;


    public GestorTurnos(){
         this.posicionTexturaX = Gdx.graphics.getWidth();
         this.posicionTexturaY = 0;
    }


    /**
     * Inicializa el primer turno del partido
     */
    public static void iniciarPartido() {

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        equipo1.bloquear();
        equipo2.bloquear();

        Random random = new Random();



       if(true){//random.nextInt()%2 != 0) {
            equipo1.desbloquear();
            equipo1.setJugando(true);
            equipo2.bloquear();
        }

        else {
            equipo1.bloquear();
            equipo2.desbloquear();
            equipo2.setJugando(true);
        }

    }

    /**
     * Cambia el turno al siguiente equipo , si los
     * equipos estan bloqueados devolvera false indicando
     * que no puede cambiar de turno
     * @return si ha habido cambio de turno o no
     */
    public boolean CambiarTurno() {

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();


        if(equipo1.bloqueado() && equipo1.isJugando()  && equipo2.bloqueado() && !equipo2.isJugando() ) {
            equipo2.desbloquear();
            equipo2.setJugando(true);
            ProcesosContinuos.añadirProceso(this);
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
            arbitro.mover();
            return true;
        }

        else if (equipo2.bloqueado() && equipo2.isJugando()  && equipo1.bloqueado() && !equipo1.isJugando() ) {
            equipo1.desbloquear();
            equipo1.setJugando(true);
            ProcesosContinuos.añadirProceso(this);
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);

            arbitro.mover();


            return true;
        }

        else if(forzarCambioTurno  && ((equipo2.isJugando()  && !equipo1.isJugando())
                || (!equipo2.isJugando()  && equipo1.isJugando())))
        {
            if(equipo2.isJugando()) {
                equipo1.desbloquear();
                equipo1.setJugando(true);
                ProcesosContinuos.añadirProceso(this);
                this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
                forzarCambioTurno = false;
                arbitro.mover();
                equipo2.bloquear();
            }

            else{
                equipo2.desbloquear();
                equipo2.setJugando(true);
                ProcesosContinuos.añadirProceso(this);
                this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
                forzarCambioTurno = false;
                arbitro.mover();
                equipo1.bloquear();
            }


            return true;
        }

       return false;

    }

    /**
     * Indica si los dos equipos han finalizado el turno.
     * Por lo que se considera que la ronda ha acabado
     * @return cierto si ha finalizado la ronda de turnos
     */

    public static boolean finTurnoJugadores() {

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();


        if(equipo1.bloqueado() && equipo1.isJugando()  && equipo2.bloqueado() && equipo2.isJugando() ) {
            return true;
        }
        else if(equipo1.isJugando()  && equipo2.isJugando()  && forzarCambioTurno ){
            forzarCambioTurno = false;
            equipo1.bloquear();
            equipo2.bloquear();
           return true;
        }
       return false;
    }

    public static void iniciarTurnoEquipo(Equipo equipo1, Equipo equipo2){
        equipo1.bloquear();
        equipo2.desbloquear();
        equipo2.setJugando(true);

    }

    @Override
    public String getTextura() {
        return estandarteEquipo1;
    }

    @Override
    public int getPosicionX() {
        return posicionTexturaX;
    }

    @Override
    public int getPosicionY() {
        return posicionTexturaY;
    }

    @Override
    public boolean procesar() {

        if(this.posicionTexturaX > 0) {
            this.posicionTexturaX = this.posicionTexturaX - VELOCIDAD;
            return false;
        }
        else {
            GestorGrafico.generarDibujante().eliminarTextura(this.id);
            return true;
        }
    }

    public static void cambiarTurno()
    {
        forzarCambioTurno = true;
    }

}
