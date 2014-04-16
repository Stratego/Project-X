package com.rugbysurvive.partida.elementos.objetos;

import com.rugbysurvive.partida.Jugador.Jugador;
import com.rugbysurvive.partida.jugadores.Habilidades;

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
