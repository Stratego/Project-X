package com.rugbysurvive.partida.elementos;

import com.models.Habilidad;
import com.rugbysurvive.partida.Jugador.ConPelota;
import com.rugbysurvive.partida.Jugador.DireccionJugador;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.arbitro.Arbitro;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.Agujero;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.Hielo;
import com.rugbysurvive.partida.elementos.objetos.objetosCampo.MinaCampo;
import com.rugbysurvive.partida.elementos.objetos.poweUps.ColocadorObjetosCampo;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.jugadores.Habilidades;
import com.rugbysurvive.partida.jugadores.Posicionamiento;
import com.rugbysurvive.partida.tablero.Campo;
import com.rugbysurvive.partida.tablero.Lado;

import java.util.List;
import java.util.Random;

/**
 * Created by aitor on 15/04/14.
 * Agrupa todos los elementos del juego mas importantes
 * Inicia la estructura basica y la conexion con la informacion
 * recibida de los menus.
 * Es por lo tanto el nexo de union entre los menus y la partida.
 */
public class ComponentesJuego {

    // Elementos de juego basicos
    private Campo campo;
    private Equipo equipo1;
    private Equipo equipo2;
    private Marcador marcador;

    // Datos recibidos desde los menus
    private com.models.Equipo equipo1Modelado;
    private com.models.Equipo equipo2Modelado;

    private static ComponentesJuego componentesJuego;



    public ComponentesJuego(com.models.Equipo equipo1,com.models.Equipo equipo2)
    {
        this.equipo1 = new Equipo();
        this.equipo2 = new Equipo();

        this.campo = new Campo();
        this.equipo1Modelado = equipo1;
        this.equipo2Modelado = equipo2;
        this.equipo1.setColor(generarColorEquipacion(this.equipo1Modelado.getEquipacion()));
        this.equipo2.setColor(generarColorEquipacion(this.equipo2Modelado.getEquipacion()));
        this.generarEquipos();
        this.marcador = new Marcador(this.equipo1,this.equipo2);
        componentesJuego = this;




    }
    public static ComponentesJuego getComponentes(){return componentesJuego;}

    /**
     * GEnera todos los datos provientes de los menus
     */
    private void generarEquipos()
    {


        // Carga los logos y estandartes del equipo
        this.equipo1.setLogo(this.equipo1Modelado.getEscudo());
        this.equipo1.setEstandarte(this.equipo1Modelado.getEscudo());

        if(this.equipo2.getColor() == Color.verde){
            this.equipo2.setLogo("banderas/logos/Logo5E4.png");
             this.equipo2.setEstandarte("banderas/logos/Logo5E4.png");
        }

        else if(this.equipo2.getColor() == Color.rojo) {
            this.equipo2.setLogo("banderas/logos/Logo3E1.png");
            this.equipo2.setEstandarte("banderas/logos/Logo3E1.png");

        }

        else if(this.equipo2.getColor() == Color.azul) {
            this.equipo2.setLogo("banderas/logos/Logo5E3.png");
            this.equipo2.setEstandarte("banderas/logos/Logo5E3.png");
        }

         else if(this.equipo2.getColor() == Color.amarillo)  {
            this.equipo2.setLogo("banderas/logos/Logo4E2.png");
            this.equipo2.setEstandarte("banderas/logos/Logo4E2.png");

        }



        // Carga todos los jugadores y sus posiciones  de los modelos
            for(com.models.Jugador auxJugador : this.equipo1Modelado.getJugadores()){

                   List<Habilidad> hab = auxJugador.getHabilidades();
                    Jugador jugadorReal;

                    // Para realizar pruebas de objetos
                    if(this.equipo1Modelado.getJugadores().indexOf(auxJugador) == 2 || this.equipo1Modelado.getJugadores().indexOf(auxJugador) == 4)
                    {
                        jugadorReal =  new Jugador(hab.get(0).getValor(),100,hab.get(1).getValor(),hab.get(2).getValor(),40,hab.get(4).getValor(), this.equipo1);
                         PowerUP objeto3 = new PowerUP(1,40,Habilidades.resistencia,50,jugadorReal);
                         ColocadorObjetosCampo objeto4 = new ColocadorObjetosCampo(2,10,"mina.png",jugadorReal,new MinaCampo("mina.png",jugadorReal));
                         ColocadorObjetosCampo objeto2 = new ColocadorObjetosCampo(3,10,"objetos/agujero.png",jugadorReal,new Agujero("objetos/agujero.png",jugadorReal));
                         ColocadorObjetosCampo objeto = new ColocadorObjetosCampo(4,10,"objetos/hielo.png",jugadorReal,new Hielo("objetos/hielo.png",jugadorReal));
                         jugadorReal.añadirObjeto(objeto);
                         jugadorReal.añadirObjeto(objeto2);
                         jugadorReal.añadirObjeto(objeto3);
                         jugadorReal.añadirObjeto(objeto4);
                    }

                    // carga del jugador con sus habilidades
                    else{
                        int velocidad = hab.get(3).getValor()+30;
                        if (velocidad>100){velocidad = 100;}
                            jugadorReal =  new Jugador(hab.get(0).getValor(),100,hab.get(1).getValor(),hab.get(2).getValor(),velocidad,hab.get(4).getValor(), this.equipo1);
                     }

                        // FIltrado de posiciones recibidas para reajustarlas a las necesidades de la partida
                         int posY = auxJugador.getPosY();
                         int posX = auxJugador.getPosX();
                         if(posY<14){
                             posX = translacionPosicionX(posX);
                          }

                        if(posY>14){
                             posY = this.translacionPosicion(posY);
                             posY = posY +1;
                         }
                         else{
                            posY = posY-1;
                         }

                        this.equipo1.añadirJugador(jugadorReal,posX,posY);
        }

         // carga la informacion de los menus del equipo 2
        for(com.models.Jugador auxJugador : this.equipo2Modelado.getJugadores()){
               System.out.println("jugador equipo2"+auxJugador.getPosX()+","+auxJugador.getPosY());
                List<Habilidad> hab = auxJugador.getHabilidades();

             int velocidad = hab.get(3).getValor()+30;
              if (velocidad>100){velocidad = 100;}
               Jugador jugadorReal =  new Jugador(hab.get(0).getValor(),100,hab.get(1).getValor(),hab.get(2).getValor(),velocidad,hab.get(4).getValor(), this.equipo2);
              int posY = auxJugador.getPosY();
              int posX = auxJugador.getPosX();
              if(posY<14){
                posX = translacionPosicionX(posX);
              }
              if(posY>14){
                  posY = this.translacionPosicion(posY);
                  posY = posY +1;
              }
              else{
                 posY = posY-1;
             }
              this.equipo2.añadirJugador(jugadorReal,posX,posY);
        }

    }

    /**
     * Genera el saque inicial y da la pelota a un jugador aleatoriamente
     */
    public void generarSaque(){

        Posicionamiento.generarSaqueCampo(this.campo,this.equipo1, Lado.izquierda);
        Posicionamiento.generarSaqueCampo(this.campo,this.equipo2, Lado.derecha);

        Random random = new Random();
        Equipo equipo = this.equipo1;
        if(random.nextInt()%2 != 0) {
            equipo = this.equipo2;
        }
        Jugador jugador = equipo.getJugadores().get(Math.abs(random.nextInt() % 6));
        jugador.setEstado(new ConPelota(jugador));

    }

    /**
     * A partir de la informacion recibida de los menus se define
     * el color del equipo
     * @param equipacion referencia a la equipacion del equipo recibida desde los
     *                   menus
     * @return colo de la equipacion definida en partida
     */
    public Color generarColorEquipacion(String equipacion) {

        if(equipacion.equals("Jugador3E1.png")){
            return Color.rojo;
        }
        else if(equipacion.equals("Jugador3E2.png")){
            return Color.amarillo;
        }
        else if(equipacion.equals("Jugador3E3.png")){
            return Color.azul;
        }
        else{
            return Color.verde;
        }
    }


    /**
     * Ajustes de posicion para adecuarlas al tablero
     * @param posicion posicion real
     * @return posicion ajustada
     */
    private int translacionPosicion(int posicion){

        switch (posicion){
            case 29:
                return 0;
            case 28:
                return 1;
            case 27:
                return 2;
            case 26:
                return 3;
            case 25:
                return 4;
            case 24:
                return 5;
            case 23:
                return 6;
            case 22:
                return 7;
            case 21:
                return 8;
            case 20:
                return 9;
            case 19:
                return 10;
            case 18:
                return 11;
            case 17:
                return 12;
            case 16:
                return 13;
            case 15:
                return 14;
        }
        return 0;
    }

    /**
     * Ajusta de posicion en el eje x
     * @param posicion posicion menus
     * @return posicion ajustada para partida
     */
    private int translacionPosicionX(int posicion){
        if(posicion<=9){
            int diferencia = 9-posicion;
            posicion = 9+diferencia;
            return posicion;
        }
        else{
            int diferencia = posicion -9;
            posicion = 9- diferencia;
            return posicion;
        }
    }
    public Campo getCampo(){
        return this.campo;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }
    public Equipo getEquipo2()
    {
        return equipo2;
    }
    public Marcador getMarcador(){return this.marcador;}

}


