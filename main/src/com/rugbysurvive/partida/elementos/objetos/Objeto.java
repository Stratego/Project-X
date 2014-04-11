package com.rugbysurvive.partida.elementos.objetos;

/**
 * Created by aitor on 11/04/14.
 * Estructura basica que debe cumplir un objeto
 * para ser gestionado en el proceso de activacion y
 * desactivacion por el gestor de objetos
 */
public abstract class Objeto {
        protected int vida;

        public Objeto(int vida){
            this.vida = vida;
        }

        /**
         * Activa los efectos del objeto
         */
        public void activar(){
            this.iniciar();
            GestorObjetos.getGestor().añadirObjeto(this);
        }
        protected abstract void iniciar();
        protected abstract void desactivar();

        /**
        *
        * @return
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
