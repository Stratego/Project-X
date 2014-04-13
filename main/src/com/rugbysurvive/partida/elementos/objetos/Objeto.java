package com.rugbysurvive.partida.elementos.objetos;

/**
 * Created by aitor on 11/04/14.
 * Estructura basica que debe cumplir un objeto
 * para ser gestionado en el proceso de activacion y
 * desactivacion por el gestor de objetos
 */
public abstract class Objeto extends ObjetoJugador {
        protected int vida;

        public Objeto(int vida,String imagen){
            super(imagen);
            this.vida = vida;
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

}
