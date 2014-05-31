package com.rugbysurvive.partida.jugadores;

import com.partido.GestorTurnos;
import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.SinPelota;
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

    public static void generarSaquePredeterminado(Jugador jugador)
    {
        Jugador jugadorChute;
        Chute chute = null;

        if(jugador.getMiEquipo().getLado() == Lado.izquierda)
        {
            jugadorChute = recolocarIzquierda();
            int fuerzaDecimal = obtenerHabilidadValorDecimal(jugador.getFuerza());




            chute = new Chute(jugadorChute, jugadorChute.getPosicionX()-fuerzaDecimal, 9);

        }
        else
        {
            jugadorChute = recolocarDerecha();
            int fuerzaDecimal = obtenerHabilidadValorDecimal(jugador.getFuerza());


            chute = new Chute(jugadorChute, jugadorChute.getPosicionX()+fuerzaDecimal, 9);

        }

        Simulador.getInstance().eliminarAcciones();
        chute.simular();
    }

    private static  int obtenerHabilidadValorDecimal(int valor)
    {
        int valorDecimal = valor/10;

        if(valorDecimal <= 0)
        {
            valorDecimal = 1;
        }

        return valorDecimal;
    }

    private static Jugador recolocarIzquierda()
    {
        Jugador jugadorChuta = null;

        /*int posX = 13;
        int posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo1().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(Campo.getInstanciaCampo().getCasilla(posY, posX));
            jugador.setEstado(new SinPelota());

            posY += 1;
        }*/

        int cont = 1;
        for (PosicionInicial alineacion: ComponentesJuego.getComponentes().getEquipo1().getAlineacion()){
            alineacion.getJugador().getCasilla().setJugador(null);
            alineacion.getJugador().colocar(Campo.getInstanciaCampo().getCasilla(alineacion.getPosicionX(), alineacion.getPosicionY()));
            alineacion.getJugador().setEstado(new SinPelota());
           // alineacion.getJugador().setDireccion(DireccionJugador.derecha);
            cont += 1;

            if(cont == ConstantesJuego.JUGADORES_CAMPO)
                break;
        }


        int posX = 27;
        int posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(Campo.getInstanciaCampo().getCasilla(posY, posX));
           // jugador.setDireccion(DireccionJugador.izquierda);

            if(posX == 28)
            {
                jugador.setEstado(new ConPelota(jugador));
                jugadorChuta = jugador;
            }
            else
            {
                jugador.setEstado(new SinPelota());
            }

            if(posY == 12)
            {
                posX = 28;
                posY = 9;
            }
            else
            {
                posY += 1;
            }


        }
        return jugadorChuta;
    }

    private static  Jugador recolocarDerecha()
    {
        Jugador jugadorChuta = null;

        /*int posX = 16;
        int posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo2().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(Campo.getInstanciaCampo().getCasilla(posY, posX));
            jugador.setEstado(new SinPelota());
            posY += 1;

        }*/

        int cont = 1;
        for (PosicionInicial alineacion: ComponentesJuego.getComponentes().getEquipo2().getAlineacion()){
            alineacion.getJugador().getCasilla().setJugador(null);
            alineacion.getJugador().colocar(Campo.getInstanciaCampo().getCasilla((ConstantesJuego.LIMITE_CASILLAS_ANCHO_TABLERO - alineacion.getPosicionX()), (ConstantesJuego.LIMITE_CASILLAS_LARGO_TABLERO - alineacion.getPosicionY())));
            alineacion.getJugador().setEstado(new SinPelota());
            // alineacion.getJugador().setDireccion(DireccionJugador.izquierda);

            cont += 1;
            /*Este contador nos ayuda a evitar que se coloquen mas de 8 jugadores en el campo*/
            if(cont == ConstantesJuego.JUGADORES_CAMPO)
                break;
        }

        int posX = 2;
        int posY = 6;
        for (Jugador jugador : ComponentesJuego.getComponentes().getEquipo1().listaJugadoresCampo()){

            jugador.getCasilla().setJugador(null);
            jugador.colocar(Campo.getInstanciaCampo().getCasilla(posY, posX));
            //jugador.setDireccion(DireccionJugador.derecha);

            if(posX == 1)
            {
                jugador.setEstado(new ConPelota(jugador));
                jugadorChuta = jugador;
            }
            else
            {
                jugador.setEstado(new SinPelota());
            }

            if(posY == 12)
            {
                posX = 1;
                posY = 9;
            }
            else
            {
                posY += 1;
            }
        }
        return jugadorChuta;
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
        DireccionJugador direccion1 = DireccionJugador.derecha;
        DireccionJugador direccion2 = DireccionJugador.izquierda;
        if (jugadaequipo1.get(0).getMiEquipo().getLado()==Lado.derecha){

            ArrayList<Jugador> jugadaequipoaux = jugadaequipo1;
            jugadaequipo1 = jugadaequipo2;
            jugadaequipo2 = jugadaequipoaux;
            System.out.println("entra condicion 1");
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
        int y;
        Arbitro arbitro = Arbitro.getInstancia();
        DireccionJugador direccion1 = DireccionJugador.derecha;
        DireccionJugador direccion2 = DireccionJugador.izquierda;
        if (jugadaequipo1.get(0).getMiEquipo().getLado()==Lado.derecha){

            ArrayList<Jugador> jugadaequipoaux = jugadaequipo1;
            jugadaequipo1 = jugadaequipo2;
            jugadaequipo2 = jugadaequipoaux;
            System.out.println("entra condicion 1");
        }
        if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
            y = posY -3;
        }
        else{
            y = posY +3;
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
    }





    public static  void jugadoresCercanos (int posX,int posY){

        int posXAux=posX-1;
        int posYAux =posY+1;
        int rango = 3;
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
        for (Jugador jugador1:jugadaequipo1) {
            if(jugador1.getPosicionX() > 0 && jugador1.getPosicionY() >0) {
                campo.eliminarElemento(jugador1.getPosicionY(),jugador1.getPosicionX());
            }
            jugador1.setDireccion(direccion);
            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            campo.eliminarElemento( y, x);
            System.out.println("ELEMENTO COLOCADO:"+campo.añadirElemento(jugador1, y, x));

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
            if(jugador2.getPosicionX() > 0 && jugador2.getPosicionY() >0) {
                campo.eliminarElemento(jugador2.getPosicionY(),jugador2.getPosicionX());
            }
            jugador2.setDireccion(direccion);
            //jugador2.colocar(new Casilla((float) x, (float) y));

            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            campo.eliminarElemento(y, x);
            System.out.println("ELEMENTO COLOCADO:"+campo.añadirElemento(jugador2, y, x));

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
            if(jugador1.getPosicionX() > 0 && jugador1.getPosicionY() >0) {
                campo.eliminarElemento(jugador1.getPosicionY(),jugador1.getPosicionX());
            }
            jugador1.setDireccion(direccion);
            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            System.out.println("jugador colocado en equipo1:"+y+","+x);

            campo.eliminarElemento(y, x);
            campo.añadirElemento(jugador1, y, x);
            //jugador1.colocar(new Casilla((float)x,(float)y));

            if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                y +=1;
            }else if(y>0){
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

            if(jugador2.getPosicionX() > 0 && jugador2.getPosicionY() >0) {
                campo.eliminarElemento(jugador2.getPosicionY(),jugador2.getPosicionX());
            }
            jugador2.setDireccion(direccion);

            //jugador2.colocar(new Casilla((float) x, (float) y));

            if (arbitro.getPosicionX()==x && arbitro.getPosicionY()==y){
                arbitro.mover();
            }
            System.out.println("jugador colocado en equipo2:"+y+","+x);
            campo.eliminarElemento(y, x);
            campo.añadirElemento(jugador2, y, x);

            if (posY>=ConstantesJuego.POSICION_SAQUE_BANDA_SUPERIOR){
                y +=1;
            }else if (y>0){
                y -=1;
            }

            if (y==(posY)){
                x+=1;
                if (direccion==DireccionJugador.izquierda){
                    x-=2;
                }
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
