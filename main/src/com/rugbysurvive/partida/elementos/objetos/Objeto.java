package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.jugadores.Habilidades;
import com.rugbysurvive.partida.tablero.Campo;

/**
 * Created by aitor on 11/04/14.
 * Estructura basica que debe cumplir un objeto
 * para ser gestionado en el proceso de activacion y
 * desactivacion por el gestor de objetos
 */
public abstract class Objeto extends ObjetoJugador {

        protected int vida;
        protected int id;
        protected String imagen;
        protected Habilidades habilidad;
        protected int modificacion;
        protected Jugador jugador;

        protected int x;
        protected int y;




    public Objeto(int id,int vida,String imagen, Habilidades habilidad,int modificacion,Jugador jugador){
            super(id,imagen);
            this.id=id;
            this.vida = vida;
            this.imagen = imagen;
            this.habilidad = habilidad;
            this.modificacion = modificacion;

            this.jugador = jugador;
        }



    /**
         * Activa los efectos del objeto
         */
        @Override
        public void activar(){
            this.iniciar();
            GestorObjetos.getGestor().añadirObjeto(this);
        }

        /**
         * Inicia el efecto del objeto
         *
        */
        protected abstract void iniciar();

        /**
         * Desace el efecto del objeto
         */
        protected abstract void desactivar();

        /**
        *Cada vez que se llama esta funcion
        * el objeto pierde un turno de vida
        *
        * @return Cierto si el objeto ha finalizado
        * su vida , falso en caso contrario
        */
        public boolean pasarTurno()
        {
            this.vida = this.vida -1;
            if(this.vida == 0)
            {
                this.desactivar();
                return true;
            }
            return false;
        }



    public boolean controlPosicion(){

        x = jugador.getPosicionX();
        y = jugador.getPosicionY();
        boolean colocable = false;
        Campo campo = ComponentesJuego.getComponentes().getCampo();
        switch (jugador.getDireccion())
        {
            case derecha:
                if (x< ConstantesJuego.NUMERO_CASILLAS_LARGO_TABLERO-1){
                    x += 1;
                    colocable=true;
                }

                break;

            case izquierda:
                if (x>0 ){
                    x -= 1;
                    colocable=true;
                }
                break;

            case arriba:
                if (y<ConstantesJuego.NUMERO_CASILLAS_ANCHO_TABLERO-1){
                    y += 1;
                    colocable=true;
                }
                break;

            case abajo:
                if (y>0 ){
                    y -= 1;
                    colocable=true;
                }
                break;
        }
        if (colocable==true&&campo.getCasilla(x,y).sinObjeto()==true){
            return colocable;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Habilidades getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidades habilidad) {
        this.habilidad = habilidad;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getModificacion() {
        return modificacion;
    }

    public void setModificacion(int modificacion) {
        this.modificacion = modificacion;
    }
}
