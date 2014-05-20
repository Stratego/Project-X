package com.partido;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.example.libgdx.skeleton.SkeletonMain;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;

import com.rugbysurvive.partida.Jugador.extras.GestorIndicadorMovimientos;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.gestores.Dibujable;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Procesos.Proceso;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.Random;

/**
 * Created by aitor on 21/04/14.
 *
 * Se encarga de procesar los turnos de cada equipo
 * Dibuja y gestiona las transiciones entre un
 * turno y el otro
 */
public class GestorTurnos implements Dibujable,Proceso {

    private static final int VELOCIDAD = 15;
    private static final int TIEMPO_PRESENTACION = 400;
    private static final int POSICION_CAMARA_INCIAL_X = 12*64;
    private static final int POSICION_CAMARA_INICIAL_Y = 11*64;
    private static final int TIEMPO_MUESTRA_ESCUDO = 50;


    private static int turno = 0;
    private int posicionTexturaX;
    private int posicionTexturaY;
    private static String estandarteEquipo1 ="Menu/CanviTorn.png";
    private static String estandarteEquipo2 = "banderas/logo4.png";
    private static int equipoCambiado = 0;
    private int id;
    private int tipoProceso;
    private int contadorTiempoPresentacion = 0;
    private float posicionCamaraX;
    private float posicionCamaraY;
    Music ambiente;
    private int tiempoMuestraEscudo;
    private ElementoDibujable escudo;

    private boolean animacionInicializadaAnteriormente;
    private boolean animacionInicialFinalizada;

    // Indica si el proceso inicial de carga de juego a finalizado
    private boolean procesoPrePartidoFinalizado;

    Arbitro arbitro=Arbitro.getInstancia();

    private Equipo equipoJugandoTurnoAnterior;

    private static boolean forzarCambioTurno = false;
    private SkeletonMain main;


    public GestorTurnos(SkeletonMain main){

         this.posicionTexturaX = Gdx.graphics.getWidth();
         this.posicionTexturaY = 0;
        this.tipoProceso = 0;
        this.posicionCamaraX = POSICION_CAMARA_INCIAL_X;
        this.posicionCamaraY = POSICION_CAMARA_INICIAL_Y;
        this.animacionInicialFinalizada = false;
        this.animacionInicializadaAnteriormente = false;
        this.ambiente = Gdx.audio.newMusic(Gdx.files.internal("sonido/ambiente/ambiente1.mp3"));
        this.tiempoMuestraEscudo =0;
        this.escudo = new ElementoDibujable(TipoDibujo.interficieUsuario,"banderas/peixetEscut.png");
        this.procesoPrePartidoFinalizado = false;
        this.main = main;
    }


    public void reiniciarCiclo(){
        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        if(equipo1.jugadorConPelota()){
            equipo1.desbloquear();
            equipo1.deseleccionar();
            equipo2.desbloquear();
        }
        else{
            equipo2.desbloquear();
            equipo2.bloquear();
        }
    }


    public void iniciarPresentacion(){
        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        Posicionamiento.generarPosicionSaludo(campo,equipo1, Lado.izquierda);
        Posicionamiento.generarPosicionSaludo(campo,equipo2, Lado.derecha);
        ProcesosContinuos.añadirProceso(this);
        this.animacionInicializadaAnteriormente = true;
        this.ambiente.play();


    }


    public void iniciarPartida(){
        this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
        ProcesosContinuos.añadirProceso(this);
    }
    /**
     * Inicializa el primer turno del partido
     */
    private void gestionarInicioPartido() {


        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();

        Random random = new Random();
        equipo1.bloquear();
        equipo2.bloquear();

       if(random.nextInt()%2 != 0) {

            equipo1.desbloquear();
            equipo1.setJugando(true);
       }

       else {
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
            this.tipoProceso = 1;
            equipo2.desbloquear();
            equipo2.setJugando(true);
            this.equipoJugandoTurnoAnterior = equipo2;
            ProcesosContinuos.añadirProceso(this);
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
            arbitro.mover();
            equipo1.bloquear();
            System.out.println("Cambiando turno");
            GestorIndicadorMovimientos.getInstancia().Borrar();
            return true;
        }

        else if (equipo2.bloqueado() && equipo2.isJugando()  && equipo1.bloqueado() && !equipo1.isJugando() ) {
            this.tipoProceso = 1;
            this.equipoJugandoTurnoAnterior = equipo1;
            equipo1.desbloquear();
            equipo1.setJugando(true);
            ProcesosContinuos.añadirProceso(this);
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
            equipo2.bloquear();
            System.out.println("Cambiando turno");
            GestorIndicadorMovimientos.getInstancia().Borrar();
            arbitro.mover();


            return true;
        }

        else if(forzarCambioTurno  && ((equipo2.isJugando()  && !equipo1.isJugando())
                || (!equipo2.isJugando()  && equipo1.isJugando())))
        {

            if(equipo2.isJugando()) {
                this.tipoProceso =1;
                equipo1.desbloquear();
                equipo1.deseleccionar();
                equipo2.deseleccionar();
                equipo1.setJugando(true);
                this.equipoJugandoTurnoAnterior = equipo1;
                ProcesosContinuos.añadirProceso(this);
                this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
                forzarCambioTurno = false;
                arbitro.mover();
                equipo2.bloquear();
                GestorIndicadorMovimientos.getInstancia().Borrar();

            }

            else{
                this.tipoProceso = 1;
                equipo2.desbloquear();
                equipo1.deseleccionar();
                equipo2.deseleccionar();
                equipo2.setJugando(true);
                this.equipoJugandoTurnoAnterior = equipo2;
                ProcesosContinuos.añadirProceso(this);
                this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
                forzarCambioTurno = false;
                arbitro.mover();
                equipo1.bloquear();
                GestorIndicadorMovimientos.getInstancia().Borrar();
            }


            return true;
        }

       return false;

    }


    public void reiniciarFases(){

        Equipo equipo1 = ComponentesJuego.getComponentes().getEquipo1();
        Equipo equipo2 = ComponentesJuego.getComponentes().getEquipo2();

        if(equipoJugandoTurnoAnterior.equals(equipo2)) {
            this.tipoProceso =1;
            equipo2.setJugando(false);
            equipo1.setJugando(true);
            equipo1.deseleccionar();
            equipo2.deseleccionar();
            this.equipoJugandoTurnoAnterior = equipo1;
            ProcesosContinuos.añadirProceso(this);
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
            forzarCambioTurno = false;
            arbitro.mover();
            equipo2.bloquear();
            equipo1.desbloquear();
            GestorIndicadorMovimientos.getInstancia().Borrar();

        }

        else{
            this.tipoProceso = 1;
            equipo2.desbloquear();
            equipo2.setJugando(true);
            equipo1.setJugando(false);
            equipo1.deseleccionar();
            equipo2.deseleccionar();
            this.equipoJugandoTurnoAnterior = equipo2;
            ProcesosContinuos.añadirProceso(this);
            this.id = GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
            forzarCambioTurno = false;
            arbitro.mover();
            equipo1.bloquear();
            GestorIndicadorMovimientos.getInstancia().Borrar();
        }
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
            GestorIndicadorMovimientos.getInstancia().Borrar();
            return true;
        }
        else if(equipo1.isJugando()  && equipo2.isJugando()  && forzarCambioTurno ){
            forzarCambioTurno = false;
            equipo1.bloquear();
            equipo2.bloquear();
            equipo1.deseleccionar();
            equipo2.deseleccionar();
            GestorIndicadorMovimientos.getInstancia().Borrar();

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

        // GESTION CAMBIO TURNO
        if(this.tipoProceso ==1) {

            if(this.posicionTexturaX == Gdx.graphics.getHeight()){
                this.ambiente = Gdx.audio.newMusic(Gdx.files.internal("sonido/cambioTurno.wav"));
                this.ambiente.play();
            }
            if(this.posicionTexturaX > 0) {
                this.posicionTexturaX = this.posicionTexturaX - VELOCIDAD;
                return false;
            }
            else {
                if(this.tiempoMuestraEscudo >= TIEMPO_MUESTRA_ESCUDO ) {
                    GestorGrafico.generarDibujante().eliminarTextura(this.id);
                    this.escudo.borrar();
                    this.posicionTexturaX = Gdx.graphics.getHeight();
                    this.tiempoMuestraEscudo =0;
                    return true;
                }
                else if(this.tiempoMuestraEscudo == 0){
                    this.ambiente = Gdx.audio.newMusic(Gdx.files.internal("sonido/golpe.wav"));
                    this.ambiente.play();
                    this.main.mostrarBotones();
                    this.escudo.dibujar(ConstantesJuego.POS_BANDERA_CAMBIO_TURNO_X,ConstantesJuego.POS_BANDERA_CAMBIO_TURNO_Y);
                    if(!this.procesoPrePartidoFinalizado){
                        ComponentesJuego.getComponentes().generarSaque();
                        this.gestionarInicioPartido();
                        this.procesoPrePartidoFinalizado = true;
                    }
                }
                this.tiempoMuestraEscudo++;
               return false;
            }
        }

        // GESTION PRESENTACION PARTIDO
        else if(this.tipoProceso ==0) {

            if(this.contadorTiempoPresentacion >= TIEMPO_PRESENTACION){
                this.posicionCamaraX = POSICION_CAMARA_INCIAL_X;
                this.posicionCamaraY = POSICION_CAMARA_INICIAL_Y;
                this.animacionInicialFinalizada = true;
                this.ambiente.pause();
                this.ambiente.dispose();
                this.tipoProceso =1;
                return true;
            }

            else {
                if(contadorTiempoPresentacion%2==0){
                    GestorGrafico.getCamara().situarCamara((int)this.posicionCamaraX,(int)this.posicionCamaraY);
                    this.posicionCamaraX++;
                    this.posicionCamaraY = this.posicionCamaraY + (float)0.5;
                }
            }
            this.contadorTiempoPresentacion++;

        }
        return false;
    }

    public static void cambiarTurno()
    {
        forzarCambioTurno = true;
    }

    public boolean isAnimacionInicialFinalizada() {
        return animacionInicialFinalizada;
    }
    public boolean isAnimacionInicializadaAnteriormente() {
        return animacionInicializadaAnteriormente;
    }


    public static int getTurno() {
        return turno;
    }
    public static void sumarTurno(){turno++;}


}
