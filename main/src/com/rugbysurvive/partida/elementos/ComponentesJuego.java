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
 */
public class ComponentesJuego {
    private Campo campo;
    private Equipo equipo1;
    private Equipo equipo2;
    private Marcador marcador;
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


    private void generarEquipos()
    {


        this.equipo1.setLogo(this.equipo1Modelado.getEscudo());
        this.equipo1.setEstandarte(this.equipo1Modelado.getEscudo());

        if(this.equipo2.getColor() == Color.verde){
            this.equipo2.setLogo("banderas/logos/Logo5E4.png");
             this.equipo2.setEstandarte("banderas/logos/Logo5E4.png");
         }
        else if(this.equipo2.getColor() == Color.rojo)
        {
            this.equipo2.setLogo("banderas/logos/Logo3E1.png");
            this.equipo2.setEstandarte("banderas/logos/Logo3E1.png");

        }
        else if(this.equipo2.getColor() == Color.azul)
        {
            this.equipo2.setLogo("banderas/logos/Logo5E3.png");
            this.equipo2.setEstandarte("banderas/logos/Logo5E3.png");
        }

         else if(this.equipo2.getColor() == Color.amarillo)
        {
            this.equipo2.setLogo("banderas/logos/Logo4E2.png");
            this.equipo2.setEstandarte("banderas/logos/Logo4E2.png");

        }


            for(com.models.Jugador auxJugador : this.equipo1Modelado.getJugadores()){
           System.out.println("jugador equipo 1"+auxJugador.getPosX()+","+auxJugador.getPosY());
            List<Habilidad> hab = auxJugador.getHabilidades();
          //  System.out.println("jugador" + hab.get(0).getValor() + "'" + hab.get(1).getValor() + "'" + hab.get(2).getValor() + "'" + hab.get(3).getValor() + "'" + hab.get(4).getValor());
/*            if(auxJugador.getPowerups().size()>0){
                System.out.print("HAY OBJETOSSSSSSSSSSSSSSSs");
            }*/
            //
            Jugador jugadorReal;
            if(this.equipo1Modelado.getJugadores().indexOf(auxJugador) == 2 || this.equipo1Modelado.getJugadores().indexOf(auxJugador) == 4)
            {
                jugadorReal =  new Jugador(hab.get(0).getValor(),100,hab.get(1).getValor(),hab.get(2).getValor(),30,hab.get(4).getValor(), this.equipo1);
                PowerUP objeto3 = new PowerUP(1,10,Habilidades.resistencia,50,jugadorReal);
                ColocadorObjetosCampo objeto4 = new ColocadorObjetosCampo(2,10,"mina.png",jugadorReal,new MinaCampo("mina.png",jugadorReal));
                ColocadorObjetosCampo objeto2 = new ColocadorObjetosCampo(3,10,"objetos/agujero.png",jugadorReal,new Agujero("objetos/agujero.png",jugadorReal));
                ColocadorObjetosCampo objeto = new ColocadorObjetosCampo(4,10,"objetos/hielo.png",jugadorReal,new Hielo("objetos/hielo.png",jugadorReal));
                jugadorReal.añadirObjeto(objeto);
                jugadorReal.añadirObjeto(objeto2);
                jugadorReal.añadirObjeto(objeto3);
                jugadorReal.añadirObjeto(objeto4);
            }
            else{
               jugadorReal =  new Jugador(hab.get(0).getValor(),100,hab.get(1).getValor(),hab.get(2).getValor(),hab.get(3).getValor(),hab.get(4).getValor(), this.equipo1);
            }

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

        for(com.models.Jugador auxJugador : this.equipo2Modelado.getJugadores()){
           System.out.println("jugador equipo2"+auxJugador.getPosX()+","+auxJugador.getPosY());
            List<Habilidad> hab = auxJugador.getHabilidades();
            //System.out.println("jugador" + hab.get(0).getValor() + "'" + hab.get(1).getValor() + "'" + hab.get(2).getValor() + "'" + hab.get(3).getValor() + "'" + hab.get(4).getValor());
/*            if(auxJugador.getObjetos().size()>0){
                System.out.print("HAY OBJETOSSSSSSSSSSSSSSSs");
            }*/
            Jugador jugadorReal =  new Jugador(hab.get(0).getValor(),100,hab.get(1).getValor(),hab.get(2).getValor(),hab.get(3).getValor(),hab.get(4).getValor(), this.equipo2);
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
        // falta añadir los objetos a cada jugador
       /* Jugador jugador = new Jugador(80, 90, 100, 90, 80, 70, equipo1);

        jugador.setDireccion(DireccionJugador.derecha);

        PowerUP objeto3 = new PowerUP(1,10,Habilidades.defensa,50,jugador);
        ColocadorObjetosCampo objeto4 = new ColocadorObjetosCampo(2,10,"mina.png",jugador,new MinaCampo("mina.png",jugador));
        ColocadorObjetosCampo objeto2 = new ColocadorObjetosCampo(3,10,"objetos/agujero.png",jugador,new Agujero("objetos/agujero.png",jugador));
        ColocadorObjetosCampo objeto = new ColocadorObjetosCampo(4,10,"objetos/hielo.png",jugador,new Hielo("objetos/hielo.png",jugador));
        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);
        jugador.añadirObjeto(objeto3);
        jugador.añadirObjeto(objeto4);

        this.equipo1.añadirJugador(jugador,7,4);
        //this.equipo1.añadirJugador(jugador,17,22);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.izquierda);
        this.equipo1.añadirJugador(jugador, 6, 6);
        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.arriba);
        this.equipo1.añadirJugador(jugador, 7, 7);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo1);
        jugador.setDireccion(DireccionJugador.abajo);
        //jugador.setEstado(new ConPelota(jugador));
        this.equipo1.añadirJugador(jugador,9,4);


        this.equipo1.añadirJugador(new Jugador(80, 90, 34, 90, 80, 70, this.equipo1),9,9);
        this.equipo1.añadirJugador(new Jugador(80, 90, 12, 90, 80, 70, this.equipo1),11,25);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),11,11);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 13, 70, this.equipo1),12,12);
        this.equipo1.añadirJugador(new Jugador(80, 68, 100, 90, 80, 70, this.equipo1),13,13);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),14,14);
        this.equipo1.añadirJugador(new Jugador(80, 90, 51, 90, 80, 70, this.equipo1),15,15);
        this.equipo1.añadirJugador(new Jugador(80, 34, 56, 22, 80, 70, this.equipo1),16,16);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),17,17);
        this.equipo1.añadirJugador(new Jugador(80, 90, 56, 90, 80, 70, this.equipo1),12,12);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),4,4);


        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),3,12);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),4,13);
        this.equipo1.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo1),5,14);


        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),12,19);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),13,20);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),14,21);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),15,22);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),16,23);
        this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),17,24);

        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo2);
        //jugador.setEstado(new ConPelota(jugador));
        this.equipo2.añadirJugador(jugador,5,5);


        jugador = new Jugador(80, 90, 100, 90, 80, 70, this.equipo2);
        PowerUP objeto5 = new PowerUP(0,10,"casilla.png", Habilidades.vida,50,jugador);
       /* objeto2 = new ColocadorObjetosCampo(1,10,"mina.png",jugador,new MinaCampo("mina.png",jugador));

        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto2);
        jugador.añadirObjeto(objeto);
        jugador.añadirObjeto(objeto);*/

        //this.equipo2.añadirJugador(jugador,2,3);
       // this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),19,27);
        //this.equipo2.añadirJugador(new Jugador(80, 90, 100, 90, 80, 70, this.equipo2),21,21);


    }

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


