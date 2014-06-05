package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.models.Equipo;
import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;

import com.rugbysurvive.partida.IA.IA;

import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.ResolucionPantalla;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.arbitro.SaqueBanda;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.Marcador;
import com.rugbysurvive.partida.elementos.objetos.GestorObjetos;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestorEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.gestores.Procesos.ProcesosContinuos;
import com.rugbysurvive.partida.gestores.Prueba;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Boton;
import com.rugbysurvive.partida.tablero.Botones.BotonCambioTurno;
import com.rugbysurvive.partida.tablero.Botones.BotonFinalizarAccion;
import com.rugbysurvive.partida.tablero.Botones.BotonFinalizarSimulacion;
import com.rugbysurvive.partida.tablero.Botones.BotonInterfaz;

import java.util.ArrayList;


/**
 * Clase base del proceso de partida.
 * Carga todos los elementos del juego , realiza la interconexion
 * con los menus y mantiene el bucle principal.
 *
 * Es por lo tanto el inicio de todos los elementos de la partida y el
 * punto base donde se deben generar todos los elementos.
 */
public class SkeletonMain extends Game {

    static final int NUMERO_TURNOS_FINAL =15;

    // gestores
    private GestureDetector gestureDetector;
    private GestorEntrada gestorEntrada;
    private GestorObjetos gestorObjetos;
    private InputMultiplexer multiplexer;
    private GestorGrafico gestorGrafico;

    // elementos
    private ArrayList<Boton> botons= new ArrayList <Boton>();
    private ConstantesJuego constantes;
    private Arbitro arbitro;
    private com.models.Equipo equipo1;
    private com.models.Equipo equipo2;
    private ComponentesJuego componentesJuego;
    private Simulador simulador;
    private Marcador marcador;
    private GestorTurnos gestor;

    // VARIABLES DE ESTADO
    private boolean calculandoEquipoInicio = false;
    private boolean simular = false;
    private int contador = 0;
    private boolean rival_IA = false;
    private CollBack regreso;

    public SkeletonMain(Equipo equipo1, Equipo equipo2, boolean ia,boolean musica,CollBack collBack) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.rival_IA=ia;
        regreso = collBack;

    }

    public interface CollBack{
        public void finichMatch(int rEquipo1,int rEquipo2,Equipo equipo1, Equipo equipo2);
    }

    @Override
    public void create() {
        /**
         * Carga de todas las estructuras basicas para iniciar el proyecto
         * Los gestores, los elementos de juego y todas las clases auxiliares.
         */

         // indica la existencia de IA
        constantes.setRIVAL_IA(rival_IA);

        // Iinica las estructuras basicas
        this.simulador = Simulador.getInstance();
        this.constantes = new ConstantesJuego();
        this.gestorGrafico = new GestorGrafico(generarTexturas(),64);

        // añade los botones de la interfaz
        botons.add(new BotonInterfaz(ConstantesJuego.POSICION_BOTON_CHUTEPASE,0, Entrada.pase,"Menu/botoPassada.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonInterfaz(ConstantesJuego.POSICION_BOTON_OBJETOS,0, Entrada.objeto,"botonsPowerUp.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonInterfaz(ConstantesJuego.POSICION_BOTON_SUPLENTE,0, Entrada.cambiar,"Menu/botoSubstitucions.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonCambioTurno(ConstantesJuego.POSICION_BOTON_FINALIZAR,0, Entrada.finalizar,"botoCanviTorn.png",ConstantesJuego.ID_BOTON));

        // LIMITE ENTRE LOS BOTONES DE INTERFAZ NORMAL Y SIMULADOR
        botons.add(new BotonFinalizarAccion(ConstantesJuego.POSICION_BOTON_SUPLENTE,0, Entrada.cambiar,"Menu/botoPasarAccio.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonFinalizarSimulacion(ConstantesJuego.POSICION_BOTON_FINALIZAR,0, Entrada.finalizar,"Menu/botoAccelerar.png",ConstantesJuego.ID_BOTON));



        this.componentesJuego = new ComponentesJuego(equipo1,equipo2);
        this.gestorEntrada = new GestorEntrada(this.gestorGrafico.getCamara().getOrthographicCamera(),botons,this.gestorGrafico);

        this.multiplexer = new InputMultiplexer();
        this.gestorObjetos = new GestorObjetos();
        gestureDetector = new GestureDetector(20, 0.5f, 1, 0.5f,this.gestorEntrada);
        this.marcador = new Marcador(ComponentesJuego.getComponentes().getEquipo1()
                                        ,ComponentesJuego.getComponentes().getEquipo2());


        this.calculandoEquipoInicio = true;
        this.simular = false;
        this.gestor = new GestorTurnos(this);


    }

    @Override
    public void dispose() {
        this.gestorGrafico.dispose();
    }

    @Override
    public void render() {
        /**
         * Bucle del juego
         * Es la base de la estructura
         */



   // La presentacion se realiza unicamente una vez
        if(!this.gestor.isAnimacionInicializadaAnteriormente()){
            this.gestor.iniciarPresentacion();
        }

    // Bucle principal del juego , se inicia una vez finalizada la animacion
       if(this.gestor.isAnimacionInicialFinalizada())
       {
           // Proceso por el cual se inicia el partido
            if(this.calculandoEquipoInicio) {
                multiplexer.addProcessor(gestureDetector);
                multiplexer.addProcessor(this.gestorGrafico.getCamara());
                Gdx.input.setInputProcessor(multiplexer);

                this.gestor.iniciarPartida();
                this.calculandoEquipoInicio = false;
            }
            this.gestor.CambiarTurno();


           if(GestorTurnos.finTurnoJugadores() && !this.simular){

                System.out.println("Iniciando simulacion");
                GestorTurnos.sumarTurno();

                 if (ConstantesJuego.RIVAL_IA ==true){
                 IA ia = new IA();
             }

            this.simular = true;
            this.simulador.iniciarSimulacion();


           }

          // Proceso de simulacion desde el inicio hasta el final
          if(simular) {

            int i=0;
            // Esconder botones partida
            for(Boton boton : this.botons){
                if(i<4) {
                    boton.esconder();
                    i++;
                }
            }
            //Mostrar botones simulacion
            if(this.botons.get(0).isEscondido() && !this.botons.get(4).isProcesando()){
                this.botons.get(4).mostrar();
                this.botons.get(5).mostrar();
            }

            // Una vez esconcidos los botones se inica la simulacion
            if(!this.botons.get(4).isEscondido() && !this.botons.get(4).isProcesando()){

                // Se realiza la simulacion hasta que finaliza y se vuelve a reinicar todo el proceso
                if(this.simulador.simular()){
                    this.simular = false;
                    this.gestor.reiniciarFases();
                    this.botons.get(0).mostrar();
                    this.botons.get(1).mostrar();
                    this.botons.get(2).mostrar();
                    this.botons.get(3).mostrar();

                    this.botons.get(4).esconder();
                    this.botons.get(5).esconder();
                }
            }


        }


       }

       //Calcula la vida de los objetos y los elimina en caso necesario
       if(contador %100 == 0 ) {
           this.gestorObjetos.procesar();
       }

         this.gestorGrafico.dibujar();
        // Gestiona la vida de los procesos continuos (animaciones o procesos con tiempo de vida
        // a o largo de dieferentes iteraciones.
          ProcesosContinuos.procesar();
         this.contador++;

        // Fin de partida
        if(Gdx.input.isKeyPressed(Input.Keys.BACK) || GestorTurnos.turno() == NUMERO_TURNOS_FINAL){
            regreso.finichMatch(Marcador.resultadoEquipo1(), Marcador.resultadoEquipo2(), equipo1, equipo2);
        }


    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void mostrarBotones(){
        this.botons.get(0).mostrar();
        this.botons.get(1).mostrar();
        this.botons.get(2).mostrar();
        this.botons.get(3).mostrar();
    }


    private ArrayList<String> generarTexturas(){

        ArrayList<String> nombresTexturas = new ArrayList<String>();
        nombresTexturas.add("jugador1.png");
        nombresTexturas.add("tablero/campo.png");
        nombresTexturas.add("casellalila.png");
        nombresTexturas.add("boto.png");
        nombresTexturas.add("listaprueba.png");
        nombresTexturas.add("casilla.png");
        nombresTexturas.add("jugador/seleccionado.png");
        nombresTexturas.add("jugador/jugador1.png");
        nombresTexturas.add("jugador/jugador2.png");
        nombresTexturas.add("jugador/jugador4.png");
        nombresTexturas.add("jugador/jugador3.png");
        nombresTexturas.add("jugador/jugador5.png");
        nombresTexturas.add("plantillaobjetos.png");
        nombresTexturas.add("pelota.png");
        nombresTexturas.add("jugador/extras/caution.png");

        nombresTexturas.add("indicadoresMovimiento/cruzDiagonal.png");
        nombresTexturas.add("indicadoresMovimiento/curvaAbajoDerecha.png");
        nombresTexturas.add("indicadoresMovimiento/curvaArribaDerecha.png");
        nombresTexturas.add("indicadoresMovimiento/curvaIzquierdaAbajo.png");
        nombresTexturas.add("indicadoresMovimiento/curvaIzquierdaArriba.png");
        nombresTexturas.add("indicadoresMovimiento/rectaHorizontal.png");
        nombresTexturas.add("indicadoresMovimiento/rectaVertical.png");
        nombresTexturas.add("indicadoresMovimiento/pilotaPosessio.png");
        nombresTexturas.add("Menu/botoSubstitucions.png");
        nombresTexturas.add("Menu/xutBoto.png");
        nombresTexturas.add("banderas/logo1.png");
        nombresTexturas.add("banderas/logo4.png");
        nombresTexturas.add("Menu/marcador.png");


        nombresTexturas.add("botoCanviTorn.png");
        nombresTexturas.add("botonsPowerUp.png");
        nombresTexturas.add("Menu/botoPassada.png");
        nombresTexturas.add("Menu/xutBoto.png");
        nombresTexturas.add("mina.png");
        nombresTexturas.add("taulellObjectes.png");
        nombresTexturas.add("TauloCanviJugadors.png");
        nombresTexturas.add("Menu/Habilidades/nivel1.png");
        nombresTexturas.add("Menu/Habilidades/nivel2.png");
        nombresTexturas.add("Menu/Habilidades/nivel3.png");

        // BANDERAS


        nombresTexturas.add(this.equipo1.getEscudo());
        String[] split = this.equipo1.getEscudo().split("/");
        nombresTexturas.add("banderas/cambioTurno/"+split[split.length-1]);

        String bandera = "banderas/logos/Logo5E4.png";
        nombresTexturas.add(bandera);
        split = bandera.split("/");
        nombresTexturas.add("banderas/cambioTurno/"+split[split.length-1]);

        bandera ="banderas/logos/Logo3E1.png";
        nombresTexturas.add(bandera);
        split = bandera.split("/");
        nombresTexturas.add("banderas/cambioTurno/"+split[split.length-1]);


        bandera ="banderas/logos/Logo5E3.png";
        nombresTexturas.add(bandera);
        split = bandera.split("/");
        nombresTexturas.add("banderas/cambioTurno/"+split[split.length-1]);


        bandera ="banderas/logos/Logo4E2.png";
        nombresTexturas.add(bandera);
        split = bandera.split("/");
        nombresTexturas.add("banderas/cambioTurno/"+split[split.length-1]);


        nombresTexturas.add("Menu/Habilidades/fuerza.png");


        nombresTexturas.add("jugador/piel/jugador1pell1.png");
        nombresTexturas.add("jugador/piel/jugador1pell2.png");
        nombresTexturas.add("jugador/piel/jugador1pell3.png");
        nombresTexturas.add("jugador/piel/jugador2pell1.png");
        nombresTexturas.add("jugador/piel/jugador2pell2.png");
        nombresTexturas.add("jugador/piel/jugador2pell3.png");
        nombresTexturas.add("jugador/piel/jugador3pell1.png");
        nombresTexturas.add("jugador/piel/jugador3pell2.png");
        nombresTexturas.add("jugador/piel/jugador3pell3.png");
        nombresTexturas.add("jugador/piel/jugador4pell1.png");
        nombresTexturas.add("jugador/piel/jugador4pell2.png");
        nombresTexturas.add("jugador/piel/jugador4pell3.png");
        nombresTexturas.add("jugador/piel/jugador5pell1.png");
        nombresTexturas.add("jugador/piel/jugador5pell2.png");
        nombresTexturas.add("jugador/piel/jugador5pell3.png");


        nombresTexturas.add("jugador/ropa/jugador1E1.png");
        nombresTexturas.add("jugador/ropa/jugador1E2.png");
        nombresTexturas.add("jugador/ropa/jugador1E3.png");
        nombresTexturas.add("jugador/ropa/jugador1E4.png");
        nombresTexturas.add("jugador/ropa/jugador2E1.png");
        nombresTexturas.add("jugador/ropa/jugador2E2.png");
        nombresTexturas.add("jugador/ropa/jugador2E3.png");
        nombresTexturas.add("jugador/ropa/jugador2E4.png");
        nombresTexturas.add("jugador/ropa/jugador3E1.png");
        nombresTexturas.add("jugador/ropa/jugador3E1.png");
        nombresTexturas.add("jugador/ropa/jugador3E2.png");
        nombresTexturas.add("jugador/ropa/jugador3E3.png");
        nombresTexturas.add("jugador/ropa/jugador3E4.png");
        nombresTexturas.add("jugador/ropa/jugador4E1.png");
        nombresTexturas.add("jugador/ropa/jugador4E2.png");
        nombresTexturas.add("jugador/ropa/jugador4E3.png");
        nombresTexturas.add("jugador/ropa/jugador4E4.png");
        nombresTexturas.add("jugador/ropa/jugador5E1.png");
        nombresTexturas.add("jugador/ropa/jugador5E2.png");
        nombresTexturas.add("jugador/ropa/jugador5E3.png");
        nombresTexturas.add("jugador/ropa/jugador5E4.png");
        nombresTexturas.add("Menu/CanviTorn.png");
        nombresTexturas.add("Menu/fletxaAmunt.png");
        nombresTexturas.add("Menu/fletxaAvall.png");
        nombresTexturas.add("Menu/botoPasarAccio.png");
        nombresTexturas.add("Menu/botoAccelerar.png");
        nombresTexturas.add("Menu/Habilidades/taulohabilitats.png");
        nombresTexturas.add("Menu/Habilidades/atac.png");
        nombresTexturas.add("Menu/Habilidades/defensa.png");
        nombresTexturas.add("Menu/Habilidades/fuerza.png");
        nombresTexturas.add("Menu/Habilidades/resistencia.png");
        nombresTexturas.add("Menu/tauloCanvi.png");
        nombresTexturas.add("arbitro/arbitre1.png");
        nombresTexturas.add("arbitro/arbitre2.png");
        nombresTexturas.add("arbitro/arbitre3.png");
        nombresTexturas.add("arbitro/arbitre4.png");

        nombresTexturas.add("jugador/bloqueado/jugador1Congelat.png");
        nombresTexturas.add("jugador/bloqueado/jugador2Congelat.png");
        nombresTexturas.add("jugador/bloqueado/jugador4Congelat.png");
        nombresTexturas.add("jugador/bloqueado/jugador5Congelat.png");
        nombresTexturas.add("banderas/peixetEscut.png");
        nombresTexturas.add("simulacion/izquierda.png");
        nombresTexturas.add("simulacion/derecha.png");
        nombresTexturas.add("jugador/estado/vida.png");
        nombresTexturas.add("jugador/estado/cansancio.png");
        nombresTexturas.add("jugador/estado/conPelota.png");
        nombresTexturas.add("objetos/fondo.png");
        nombresTexturas.add("objetos/positivo.png");
        nombresTexturas.add("objetos/flechaGrande.png");
        nombresTexturas.add("simulacion/rebrePassada.png");
        nombresTexturas.add("objetos/explosion/explosio1.png");
        nombresTexturas.add("objetos/explosion/explosio2.png");
        nombresTexturas.add("objetos/explosion/explosio3.png");
        nombresTexturas.add("objetos/explosion/explosio4.png");
        nombresTexturas.add("objetos/explosion/explosio5.png");
        nombresTexturas.add("objetos/explosion/explosio6.png");
        nombresTexturas.add("objetos/agujero.png");
        nombresTexturas.add("objetos/hielo.png");
        nombresTexturas.add("Menu/Habilidades/habilidad.png");
        return nombresTexturas;

    }
}


