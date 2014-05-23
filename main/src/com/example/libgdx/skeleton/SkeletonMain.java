package com.example.libgdx.skeleton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.models.Equipo;
import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;

import com.rugbysurvive.partida.IA.IA;

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


public class SkeletonMain extends Game {


    private InputMultiplexer multiplexer;
    private GestorGrafico gestorGrafico;

    private GestureDetector gestureDetector;

    private GestorEntrada gestorEntrada;

    private ArrayList<Boton> botons= new ArrayList <Boton>();
    private ConstantesJuego constantes;
    private Prueba prueba2;
    GestorObjetos gestorObjetos;
    int contador = 0;
    ComponentesJuego componentesJuego;
    Simulador simulador;
    Marcador marcador;
    GestorTurnos gestor;
    // VARIABLES DE ESTADO
    boolean calculandoEquipoInicio = false;
    boolean simular = false;
    Arbitro arbitro;
    com.models.Equipo equipo1;
    com.models.Equipo equipo2;
    boolean rival_IA = false;

    public SkeletonMain(Equipo equipo1, Equipo equipo2, boolean ia,boolean musica,CollBack collBack) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.rival_IA=ia;

    }

    public interface CollBack{
        public void finichMatch(int rEquipo1,int rEquipo2,Equipo equipo1, Equipo equipo2);
    }

    @Override
    public void create() {
        constantes.setRIVAL_IA(rival_IA);
        this.simulador = Simulador.getInstance();


        this.constantes = new ConstantesJuego();
        this.constantes.setResolucionPantalla(ResolucionPantalla.pequeña);
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

        this.gestorGrafico = new GestorGrafico(nombresTexturas,64);

        botons.add(new BotonInterfaz(ConstantesJuego.POSICION_BOTON_CHUTEPASE,0, Entrada.pase,"Menu/botoPassada.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonInterfaz(ConstantesJuego.POSICION_BOTON_OBJETOS,0, Entrada.objeto,"botonsPowerUp.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonInterfaz(ConstantesJuego.POSICION_BOTON_SUPLENTE,0, Entrada.cambiar,"Menu/botoSubstitucions.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonCambioTurno(ConstantesJuego.POSICION_BOTON_FINALIZAR,0, Entrada.finalizar,"botoCanviTorn.png",ConstantesJuego.ID_BOTON));

        // LIMITE ENTRE LOS BOTONES DE INTERFAZ NORMAL Y SIMULADOR
        botons.add(new BotonFinalizarAccion(ConstantesJuego.POSICION_BOTON_SUPLENTE,0, Entrada.cambiar,"Menu/botoPasarAccio.png",ConstantesJuego.ID_BOTON));
        botons.add(new BotonFinalizarSimulacion(ConstantesJuego.POSICION_BOTON_FINALIZAR,0, Entrada.finalizar,"Menu/botoAccelerar.png",ConstantesJuego.ID_BOTON));
        // COLOCAR EL RESTO DE BOTONES DEPSUES DE ESTOS



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
                //SaqueBanda saqueBanda = new SaqueBanda(0,19,ComponentesJuego.getComponentes().getEquipo2());
                //saqueBanda.arbitrar();

                IA ia = new IA();
            }

            this.simular = true;
            this.simulador.iniciarSimulacion();


        }


        if(simular) {

            int i=0;
            for(Boton boton : this.botons){
                if(i<4) {
                    boton.esconder();
                    i++;
                }
            }

            if(this.botons.get(0).isEscondido() && !this.botons.get(4).isProcesando()){
                this.botons.get(4).mostrar();
                this.botons.get(5).mostrar();
            }

            if(!this.botons.get(4).isEscondido() && !this.botons.get(4).isProcesando()){
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

       if(contador %100 == 0 ) {
           this.gestorObjetos.procesar();
          // this.componentesJuego.getMarcador().sumarPuntuacion(1, ComponentesJuego.getComponentes().getEquipo1().getJugadores().get(1));
       }

    this.gestorGrafico.dibujar();
    ProcesosContinuos.procesar();
    this.contador++;

       //this.prueba2.render();

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
}


