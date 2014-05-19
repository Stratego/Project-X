package com.rugbysurvive.partida.jugadores;

import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Simulador.Chute;
import com.rugbysurvive.partida.Simulador.Simulador;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aitor on 27/03/14.
 */
public class Posicionamiento {

    private static final int POS_LINIA_SALUDO_Y = 12;
    private static final int POS_LINIA_SALUDO_X_IZQUIERDA = 14;
    private static final int POS_LINIA_SALUDO_X_DERECHA = 15;

    public static ArrayList<Jugador> jugadaequipo1 = new ArrayList<Jugador>();
    public static ArrayList<Jugador> jugadaequipo2 = new ArrayList<Jugador>();
    public static ElementoDibujable casillaVision;

    private static Chute chute;

    private static Simulador simulador = Simulador.getInstance();

    /**
     * Coloca al equipo en posicion de saque elegido predeterminado
     * por el jugador en los menus de configuracion
     *
     * @param campo Zona de juego
     * @param equipo Conjunto de jugadores que seran situados en el campo
     * @param lado Mitad del campo donde seran situados los jugadores
     *
     */
    public static void generarSaqueCampo (Campo campo,Equipo equipo,Lado lado)
    {
        campo.borrarEquipo(equipo);
        Jugador jugador;
        int posicionX;
        int posicionY;

        for(int i =0 ;i<ConstantesJuego.JUGADORES_CAMPO;i++)
        {

            if(equipo.getAlineacion().size()>i){
                 jugador = equipo.getAlineacion().get(i).jugador;
                 posicionX = equipo.getAlineacion().get(i).posicionX;
                 posicionY = equipo.getAlineacion().get(i).posicionY;
                 if(lado == Lado.izquierda){
                        jugador.setDireccion(DireccionJugador.derecha);
                        campo.añadirElemento(jugador,posicionX,posicionY);
                        equipo.setLado(lado);
                 }
                 else
                 {
                     jugador.setDireccion(DireccionJugador.izquierda);
                     posicionY =ConstantesJuego.NUMERO_CASILLAS_LARGO_TABLERO - posicionY -1;
                     posicionX = ConstantesJuego.NUMERO_CASILLAS_ANCHO_TABLERO  -posicionX- 1;
                     campo.añadirElemento(jugador,posicionX,posicionY);
                     equipo.setLado(lado);
                 }

                // PRueba de peloteo
                if(i==0 && lado == Lado.izquierda){
                    jugador.setEstado(new ConPelota(jugador));
                }

            }
        }

    }


    public static void generarPosicionSaludo(Campo campo,Equipo equipo,Lado lado){

        campo.borrarEquipo(equipo);
        Jugador jugador;

        int posicionX = 0;
        int posicionY = POS_LINIA_SALUDO_Y;

        if(lado == Lado.izquierda){
           posicionX = POS_LINIA_SALUDO_X_IZQUIERDA;
        }
        else{
            posicionX = POS_LINIA_SALUDO_X_DERECHA;
        }




        for(int i =0 ;i<ConstantesJuego.JUGADORES_CAMPO;i++)
        {

            if(equipo.getAlineacion().size()>i){
                jugador = equipo.getAlineacion().get(i).jugador;
                jugador.setDireccion(DireccionJugador.frontal);
                if(lado == Lado.izquierda){

                    campo.añadirElemento(jugador,posicionY,posicionX);
                    posicionX--;
                }

                else
                {
                    campo.añadirElemento(jugador,posicionY,posicionX);
                    posicionX++;
                }

            }
        }

    }

    /**
     * Coloca al equipo en posicion de saque determinado por las reglas
     * de juego
     *
     * @param campo Zona de juego
     * @param equipo Conjunto de jugadores que seran situados en el campo
     * @param lado Mitad del campo donde seran situados los jugadores
     *
     */
     public static void generarSaquePredeterminado(Campo campo,Equipo equipo,Lado lado)
     {

     }

    /**
     * Coloca los dos equipos en posicion de mele en la posicion determinada
     * por el jugador
     *
     * @param posX posicion del eje x central de la mele
     * @param posY posicion del eje y central de lam ele
     */
    public static void  generarMele(int posX,int posY){


        Campo campo = ComponentesJuego.getComponentes().getCampo();
        ComponentesJuego.getComponentes().getEquipo1().quitarPelota();
        ComponentesJuego.getComponentes().getEquipo2().quitarPelota();
        jugadoresCercanos(posX, posY);
        int x = posX;
        if (posY<=ConstantesJuego.POSICION_SAQUE_BANDA_INFERIOR+1){
            posY+=1;
        }else if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR-1){
            posY-=1;
        }
        int y = posY +1;

        Arbitro arbitro = Arbitro.getInstancia();
        DireccionJugador direccion1 ;
        DireccionJugador direccion2 ;
        if (jugadaequipo1.get(0).getMiEquipo().getLado()==Lado.derecha){
            direccion1= DireccionJugador.izquierda;
            direccion2= DireccionJugador.derecha;
            System.out.println("entra condicion 1");
        }else{
            direccion1= DireccionJugador.derecha;
            direccion2= DireccionJugador.izquierda;
            System.out.println("entra condicion 2");
        }

        colocarMeeleEquipo1(x,y,posY,direccion1,arbitro,campo);


        x = posX+1;
        y = posY +1;

        colocarMeeleEquipo2(x, y, posY, direccion2, arbitro, campo);


        if (new Random().nextInt()%2 != 0){
            Jugador jugador = jugadaequipo1.get(jugadaequipo1.size() - 1);
            jugador.setEstado(new ConPelota(jugador));
            GestorTurnos.iniciarTurnoEquipo(ComponentesJuego.getComponentes().getEquipo2(),ComponentesJuego.getComponentes().getEquipo1());
        }else{
            Jugador jugador = jugadaequipo2.get(jugadaequipo2.size() - 1);
            jugador.setEstado(new ConPelota(jugador));
            GestorTurnos.iniciarTurnoEquipo(ComponentesJuego.getComponentes().getEquipo1(),ComponentesJuego.getComponentes().getEquipo2());
        }

        jugadaequipo1.clear();
        jugadaequipo2.clear();
        simulador.iniciarSimulacion();
    }

    public static void generarPenalty(Equipo equipo, int posX, int posY){
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        ComponentesJuego.getComponentes().getEquipo1().quitarPelota();
        ComponentesJuego.getComponentes().getEquipo2().quitarPelota();
        if (ComponentesJuego.getComponentes().getCampo().getCasilla(posY,posX).getJugador()==null){
            jugadoresCercanos(posX,posY);
            if (jugadaequipo1.get(0).getMiEquipo()==equipo){
                jugadaequipo1.get(0).setEstado(new ConPelota(jugadaequipo1.get(0)));
                if (jugadaequipo1.get(0).getMiEquipo().getLado()==Lado.derecha){
                    chute = new Chute(jugadaequipo1.get(0),new Random().nextInt()%2,(int)(Math.random()*(11-8+1)+8));
                    Simulador.getInstance().añadirAccion(chute);
                    System.out.println("chute izquierda jugador cercano jugador 1");
                }else{
                    chute = new Chute(jugadaequipo1.get(0),(int)(Math.random()*(29-28+1)+28),(int)(Math.random()*(11-8+1)+8));
                    Simulador.getInstance().añadirAccion(chute);
                    System.out.println("chute derecha jugador cercano jugador 1");
                }
            }else{
                jugadaequipo2.get(0).setEstado(new ConPelota(jugadaequipo2.get(0)));
                if (jugadaequipo2.get(0).getMiEquipo().getLado()==Lado.derecha){
                    chute = new Chute(jugadaequipo2.get(0),new Random().nextInt()%2,(int)(Math.random()*(11-8+1)+8));
                    Simulador.getInstance().añadirAccion(chute);
                    System.out.println("chute izquierda jugador cercano jugador 2");
                }else{
                    chute = new Chute(jugadaequipo2.get(0),(int)(Math.random()*(29-28+1)+28),(int)(Math.random()*(11-8+1)+8));
                    Simulador.getInstance().añadirAccion(chute);
                    System.out.println("chute derecha jugador cercano jugador 2");
                }
            }
        }else{
            Jugador jugador = ComponentesJuego.getComponentes().getCampo().getCasilla(posY,posX).getJugador();
            jugador.setEstado(new ConPelota(jugador));
            if (ComponentesJuego.getComponentes().getCampo().getCasilla(posY,posX).getJugador().getMiEquipo().getLado()==Lado.derecha){
                chute = new Chute(ComponentesJuego.getComponentes().getCampo().getCasilla(posY,posX).getJugador(),new Random().nextInt()%2,(int)(Math.random()*(11-8+1)+8));
                Simulador.getInstance().añadirAccion(chute);
                System.out.println("chute izquierda");
            }else{
                chute = new Chute(ComponentesJuego.getComponentes().getCampo().getCasilla(posY,posX).getJugador(),(int)(Math.random()*(29-28+1)+28),(int)(Math.random()*(11-8+1)+8));
                Simulador.getInstance().añadirAccion(chute);
                System.out.println("chute derecha");
            }
        }
        simulador.iniciarSimulacion();

    }

    /**
     * Coloca los dos equipos en posicion de saque de banda en la posicion determinada
     * por el jugador
     *
     * @param posX posicion del eje x central del saque
     * @param posY posicion del eje y central del saque
     * @param equipo equipo que saca la pelota
     */
    public static void  generarSaqueBanda(int posX,int posY,Equipo equipo){

        Campo campo = ComponentesJuego.getComponentes().getCampo();
        ComponentesJuego.getComponentes().getEquipo1().quitarPelota();
        ComponentesJuego.getComponentes().getEquipo2().quitarPelota();
        jugadoresCercanos(posX,posY);
        int x = posX-1;
        int y = posY +3;
        Arbitro arbitro = Arbitro.getInstancia();
        DireccionJugador direccion1 ;
        DireccionJugador direccion2 ;
        if (jugadaequipo1.get(0).getMiEquipo().getLado()==Lado.derecha){
            direccion1= DireccionJugador.izquierda;
            direccion2= DireccionJugador.derecha;
            System.out.println("entra condicion 1");
        }else{
            direccion1= DireccionJugador.derecha;
            direccion2= DireccionJugador.izquierda;
            System.out.println("entra condicion 2");
        }
        if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
            y = posY -3;
        }

        colocarSaqueEquipo1(x,y,posX,posY,direccion1,arbitro,campo,equipo);

        x = posX+1;

        if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
            y = posY -3;
        }else{
            y = posY +3;
        }

        colocarSaqueEquipo2(x, y, posX, posY, direccion2, arbitro, campo, equipo);

//jugadaequipo1.size()-2
        if (new Random().nextInt()%2 != 0){
            Jugador jugador = jugadaequipo1.get(jugadaequipo1.size() - 2);
            jugador.setEstado(new ConPelota(jugador));
            GestorTurnos.iniciarTurnoEquipo(ComponentesJuego.getComponentes().getEquipo2(),ComponentesJuego.getComponentes().getEquipo1());
        }else{
            Jugador jugador = jugadaequipo2.get(jugadaequipo2.size() - 2);
            jugador.setEstado(new ConPelota(jugador));
            GestorTurnos.iniciarTurnoEquipo(ComponentesJuego.getComponentes().getEquipo1(),ComponentesJuego.getComponentes().getEquipo2());
        }

        jugadaequipo1.clear();
        jugadaequipo2.clear();
        simulador.iniciarSimulacion();
    }





    public static  void jugadoresCercanos (int posX,int posY){

        int posXAux=posX-1;
        int posYAux =posY+1;
        int rango = 2;
        boolean salida=false;

        while (salida==false){

            for (int x=0; x<=rango; x++){
                for (int y=0; y<=rango; y++){

                    if (controlPosicion(posXAux+x,posYAux-y)==true){
                        //casillaVision = new ElementoDibujable(TipoDibujo.elementosJuego,"casilla.png");
                        //casillaVision.dibujar(posXAux+x,posYAux-y);
                        if (ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador()!=null){
                            //System.out.println("entra selecion jugador");
                            if (ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador().getMiEquipo()==ComponentesJuego.getComponentes().getEquipo1()){
                            //if (ComponentesJuego.getComponentes().getEquipo1().jugadorEnEquipo(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==true){

                                if (jugadaequipo1.contains(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==false && jugadaequipo1.size()<4){
                                    jugadaequipo1.add(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador());
                                    System.out.println("añadido jugador equipo1: " + jugadaequipo1.size());
                                }
                            }else{
                                if (jugadaequipo2.contains(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador())==false  && jugadaequipo2.size()<4){
                                    jugadaequipo2.add(ComponentesJuego.getComponentes().getCampo().getCasilla(posYAux-y,posXAux+x).getJugador());
                                    System.out.println("añadido jugador equipo2 :" + jugadaequipo2.size());
                                }
                            }
                    }

                }

            }
        }
            if ( jugadaequipo1.size()>3 && jugadaequipo2.size()>3){
                salida=true;
                break;
            }
            posYAux+=1;
            posXAux-=2;
            rango +=3;
        }
    }


    public static   boolean controlPosicion(int x, int y){

        boolean colocable = false;

        if (x>=0 && x<=ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO && y<=ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO && y>=0){

            colocable=true;
        }

        return colocable;

    }

    /**
     * coloca los jugadores del equipo 1 en la meele
     * @param x pos x
     * @param y pos y
     * @param posY posicion y inicial
     * @param direccion direccion del jugador
     * @param arbitro arbitro del juego
     * @param campo campo de juego
     */
    public static void colocarMeeleEquipo1(int x, int y,int posY, DireccionJugador direccion, Arbitro arbitro, Campo campo){
        for (Jugador jugador1:jugadaequipo1){
            campo.eliminarElemento(jugador1.getPosicionY(),jugador1.getPosicionX());
            jugador1.setDireccion(direccion);
            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            campo.añadirElemento(jugador1, y, x);
            System.out.println("estado casilla"+campo.getCasilla(y,x).sinArbitro());

            y -=1;
            if (y==(posY-2)){
                x-=1;
                y=posY;
            }
        }
    }

    /**
     * coloca los jugadores del equipo 2 en la meele
     * @param x pos x
     * @param y pos y
     * @param posY posicion y inicial
     * @param direccion direccion del jugador
     * @param arbitro arbitro del juego
     * @param campo campo de juego
     */
    public static void colocarMeeleEquipo2 (int x, int y,int posY, DireccionJugador direccion, Arbitro arbitro, Campo campo){

        for (Jugador jugador2:jugadaequipo2){
            campo.eliminarElemento(jugador2.getPosicionY(),jugador2.getPosicionX());
            jugador2.setDireccion(direccion);
            //jugador2.colocar(new Casilla((float) x, (float) y));

            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            campo.añadirElemento(jugador2, y, x);

            y -=1;
            if (y==(posY-2)){
                x+=1;
                y=posY;
            }
        }

    }

    /**
     * coloca los jugadores del equipo 1 en saque
     * @param x pos x
     * @param y pos y
     * @param posY posicion y inicial
     * @param direccion direccion del jugador
     * @param arbitro arbitro del juego
     * @param campo campo de juego
     */
    public static void colocarSaqueEquipo1(int x, int y,int posX, int posY, DireccionJugador direccion, Arbitro arbitro, Campo campo, Equipo equipo){

        for (Jugador jugador1:jugadaequipo1){
            campo.eliminarElemento(jugador1.getPosicionY(),jugador1.getPosicionX());
            jugador1.setDireccion(direccion);
            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }

            campo.añadirElemento(jugador1, y, x);
            //jugador1.colocar(new Casilla((float)x,(float)y));


            if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                y +=1;
            }else{
                y -=1;
            }
            if (y==(posY)){
                x=posX;
                y=posY;
                if (equipo!=jugador1.getMiEquipo()){
                    break;
                }else{
                    if (y>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                        direccion= DireccionJugador.abajo;
                    }else{
                        direccion= DireccionJugador.arriba;
                    }
                }
            }
        }
    }


    /**
     * coloca los jugadores del equipo 2 en saque
     * @param x pos x
     * @param y pos y
     * @param posY posicion y inicial
     * @param direccion direccion del jugador
     * @param arbitro arbitro del juego
     * @param campo campo de juego
     * @param equipo
     */
    public static void colocarSaqueEquipo2(int x, int y,int posX, int posY, DireccionJugador direccion, Arbitro arbitro, Campo campo, Equipo equipo){

        for (Jugador jugador2:jugadaequipo2){

            campo.eliminarElemento(jugador2.getPosicionY(),jugador2.getPosicionX());
            jugador2.setDireccion(direccion);

            //jugador2.colocar(new Casilla((float) x, (float) y));

            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            campo.añadirElemento(jugador2, y, x);

            if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                y +=1;
            }else{
                y -=1;
            }

            if (y==(posY)){
                x+=1;
                y=posY;
                if (equipo!=jugador2.getMiEquipo()){
                    break;
                }else{
                    if (y>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                        direccion= DireccionJugador.abajo;
                    }else{
                        direccion= DireccionJugador.arriba;
                    }
                }
            }

        }
    }
}
