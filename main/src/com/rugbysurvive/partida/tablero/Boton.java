package com.rugbysurvive.partida.tablero;

        import com.badlogic.gdx.Gdx;
        import com.rugbysurvive.partida.ConstantesJuego;
        import com.rugbysurvive.partida.Dibujables.TipoDibujo;
        import com.rugbysurvive.partida.Jugador.Jugador;
        import com.rugbysurvive.partida.elementos.ComponentesJuego;
        import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
        import com.rugbysurvive.partida.gestores.Dibujable;
        import com.rugbysurvive.partida.gestores.Entrada.Entrada;
        import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
        import com.rugbysurvive.partida.gestores.GestorGrafico;

        import java.util.ArrayList;

/**
 * Clase que define la posicion y comportamiento de un boron dentro del tablero de juego
 * Created by Victor on 24/03/14.
 */
public abstract class Boton implements GestionEntrada,Dibujable{

    /**
     * posicion x en el tablero
     */
    private float posX;

    /**
     * posicion y en el tablero
     */
    private float posY;

    public Entrada entrada;
    /**
     * indicara si el elemento esta selecionado
     */
    private boolean selecionado;

    /**
     * identificador del boton para el gestor grafico
     */
    int ID;

    /**
     * Textura que representara al boton el la interficie grafica
     */
    String textura;

    /**
     * identificador que se usara  para identidificar elementos en las listas de objetos o suplentes
     */
    public int posicion;


    /**
     * Constructor del elemento boton
     *
     * @param posX posicion x en el tablero
     * @param posY posicion y en el tablero
     * @param entrada tipo de boton que sera
     */
    public Boton(float posX, float posY, Entrada entrada,String textura, int posicion) {
        this.posY = posY;
        this.posX = posX;
        this.entrada = entrada;
        this.textura = textura;
        this.posicion = posicion;
        ID=GestorGrafico.generarDibujante().añadirDibujable(this, TipoDibujo.interficieUsuario);
    }



    /**
     * indicamos que el elemento se ha seleccionado y su posicion en el tablero
     * @param posX eje x donde se ha realizado la accion /entrada
     * @param posY eje y donde se ha realizado la accion /entrada
     */
    public boolean esSeleccionado(float posX, float posY) {
        //System.out.println("X: " + this.posX + " Y: " + this.posY);
        int anchoBoton=0;
        int altoBoton=0;

        if (this.posicion == ConstantesJuego.ID_BOTON){
            anchoBoton=ConstantesJuego.variables().getAnchoBoton();
            altoBoton=ConstantesJuego.variables().getAnchoBoton();

        }else if(this.obtenerEntrada()==Entrada.listasuplente){
            anchoBoton=ConstantesJuego.getAnchoBotonSuplentes();
            altoBoton=ConstantesJuego.getAltoBotonSuplentes();

        }else {
            anchoBoton=ConstantesJuego.getAnchoBotonObjetos();
            altoBoton=ConstantesJuego.getAltoBotonObjetos();
        }

        if (posX >= this.posX && posX <= this.posX+anchoBoton){
            if (posY >= Gdx.graphics.getHeight() - this.posY -altoBoton && posY <= Gdx.graphics.getHeight()  -this.posY){
                accionEntrada(this.entrada);
                selecionado=true;
            }else{
                selecionado=false;
            }

        }else {
            selecionado=false;
        }

        return selecionado;
    }

    public Entrada obtenerEntrada()
    {
        return this.entrada;
    }

    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {



    }


    public void borrar()
    {
        GestorGrafico.generarDibujante().eliminarTextura(this.getID());
    }

    @Override
    public abstract void accionEntrada(Entrada entrada);

    public int getID(){
        return ID;
    }

    public int getPosicion(){
        return this.posicion;
    }

    @Override
    public String getTextura() {
        return this.textura;
    }

    @Override
    public int getPosicionX() {
        return (int)this.posX;
    }

    @Override
    public int getPosicionY() {
        return (int)this.posY;
    }
/*
    @Override
    public abstract void accionEntrada(Entrada entrada) {


        //System.out.println("posicion: " + posicion);
        // intercambio entre entrada pase o chute
        if (entrada == Entrada.pase){
            //introducir accion pase
            this.entrada = Entrada.chute;
<<<<<<< HEAD
        this.entrada = Entrada.pase;

            } else {
                if (entrada == Entrada.chute){
                    //introducir accion chute
                    this.entrada = Entrada.pase;

=======
        } else {
            if (entrada == Entrada.chute){
                //introducir accion chute
                this.entrada = Entrada.pase;
>>>>>>> 3ba40cfb80f2b91a8478fe860c99b6c6a291ebed
            }
        }

        Campo.getInstanciaCampo().accionEntrada(this.entrada,0,0);


        //obtenemos el elemento de la lista mediante la posicion le dimos al crear el boton
        if (entrada == Entrada.listaobjetos){
            Jugador jugador = ComponentesJuego.getComponentes().getEquipo1().getJugadorActivo();
            ArrayList<ObjetoJugador> objetos = jugador.getPowerUP();
            System.out.println("vida jugador antes objeto "+jugador.getVida());
            //activamos y eliminamos el objeto de la lista
            for (ObjetoJugador iter: objetos){
                if (iter.getId()==this.posicion){
                    iter.activar();
                    System.out.println("vida jugador despues objeto "+ jugador.getVida());
                    jugador.getPowerUP().remove(iter);
                    break;
                }
            }
            //obteniendo la instansacion de equipo obtener la de objetos de jugador activo y activar objeto

        }

        if (entrada == Entrada.listasuplente){
            //obteniendo la instansacion de equipo y realizar cambio en la lista de jugadores
            ComponentesJuego.getComponentes().getEquipo1().intercambioJugadores(posicion);
        }

        if (entrada==Entrada.finalizar){
            //introducir accion finalizar

        }

    }
*/

}