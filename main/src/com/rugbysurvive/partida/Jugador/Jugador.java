package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.Jugador.extras.GeneradorImagenJugador;
import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.Entrada.GestionEntrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;

/**
 * Created by Victor on 27/03/14.
 */
public class Jugador implements GestionEntrada {

    private static final int MAXIMO_OBJETOS = 4;
    public static final int MAX_HABILIDAD = 100;
    public static final int MAX_DEFENSA = 100;
    public static final int MAX_ATAQUE = 100;
    public static final int MAX_FUERZA = 100;
    public static final int MAX_RESISTENCIA = 100;

    private String textura;
    private DireccionJugador direccion;
    private ElementoDibujable seleccion;
    private ElementoDibujable bloqueo;



    private Color color;
    private Estado estado;
    private Accion accion = null;
    /*Le asignamos aquí tambien la posicion en la que se encuentra el jugador?*/
    private Casilla casilla = null;

    /*Estas dos variables las ponemos aquí y no en estado, ya que si cambiamos el estado perdemos el valor de las variables*/
    private boolean seleccionado = false;
    private boolean bloqueado = false;

    private boolean lesionado;

    private ArrayList<ElementoDibujable> texturas;
    private ElementoDibujable indicador;
    private ArrayList<ObjetoJugador> powerup;

    private int Fuerza;
    private int Vida;
    private int Defensa;
    private int Habilidad;
    private int Resistencia;
    private int Ataque;

    private final int fuerzaOriginal;
    private final int defensaOriginal;
    private final int habilidadOriginal;
    private final int ataqueOriginal;
    public final int vidaOriginal;
    private final int resistenciaOriginal;

    /*contMovimientos indicara la cantidad de acciones movimiento que ha hecho hasta el momento, y de esta manera podremos ir haciendo que el jugador pierda resistencia segun interese*/
    public int contMovimientos;

    public int id;


    private boolean enJuego;

    public Equipo miEquipo;

    public Entrada paseOChute = Entrada.pase;

    public int aspecto;
    private boolean expulsado;
    /**
     * Constructor de jugador
     * @param fuerza Indica la fuerza de un jugador
     * @param vida Indica la vida de un jugador
     * @param defensa Indica la defensa de un jugador
     * @param equipo Indica a que equipo pertenece un jugador
     */
    public Jugador(int fuerza, int vida, int defensa, int habilidad, int resistencia, int ataque, Equipo equipo)
    {
        this.aspecto = GeneradorImagenJugador.generarAspecto();
        this.Fuerza= fuerza;
        this.Vida = vida;
        this.Defensa = defensa;
        this.Habilidad = habilidad;
        this.Resistencia = resistencia;
        this.Ataque = ataque;

        this.fuerzaOriginal = fuerza;
        this.vidaOriginal = vida;
        this.defensaOriginal = defensa;
        this.habilidadOriginal = habilidad;
        this.ataqueOriginal = ataque;
        this.resistenciaOriginal = resistencia;

        this.miEquipo = equipo;

        this.powerup= new ArrayList<ObjetoJugador>();

        this.estado = new SinPelota();

        this.estado.setBloqueado(false);
        this.setSeleccionado(false);
        this.id = -1;
        this.enJuego = false;
        this.textura = "jugador1.png";
        this.color = Color.azul;
        this.direccion = DireccionJugador.izquierda;
        this.texturas = GeneradorImagenJugador.generarTexturas(this.color,this.aspecto,DireccionJugador.izquierda);
        this.bloqueo =null;
        this.lesionado = false;
        this.contMovimientos = 0;
        this.expulsado = false;


    }

    public void cansancio()
    {
        if(this.contMovimientos == 3)
        {
            this.setResistencia(this.getResistencia() - 30);
            this.contMovimientos = 0;
        }
        else
        {
            this.contMovimientos += 1;
        }

        if(this.Resistencia <= this.resistenciaOriginal/2 && !this.lesionado){
            this.indicador  = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/estado/cansancio.png");
            this.indicador.dibujar(this.getPosicionX(), this.getPosicionY());
        }
    }

    /*Le va quitando vida al jugador hasta que se lesiona*/
    public void lesionar(int daño)
    {
        if(this.Vida > 0)
        {
            this.setVida(this.getVida() - daño);
            if(this.Vida <= 0)
            {
                this.setVida(0);
                this.setResistencia(0);
                this.setFuerza(0);
                if(!this.lesionado){
                    this.indicador  = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/estado/vida.png");
                    this.indicador.dibujar(this.getPosicionX(),this.getPosicionY());
                }
                this.lesionado = true;
            }

            else if(this.Vida <= this.vidaOriginal/2){
                 if(this.Resistencia > this.resistenciaOriginal /2) {
                    this.setResistencia(this.resistenciaOriginal/2);
                 }
                if(this.Fuerza > this.fuerzaOriginal /2) {
                    this.setFuerza(this.resistenciaOriginal/2);
                }
                if(!this.lesionado){
                    this.indicador  = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/estado/vida.png");
                    this.indicador.dibujar(this.getPosicionX(), this.getPosicionY());
                }
                this.lesionado = true;
            }

        }
    }

    /**
     * Devuelve a que equipo pertenece un jugador.
     * @return Equipo
     */
    public Equipo getMiEquipo()
    {
        return this.miEquipo;
    }

    /**
     * Añade a la lista de objetos del jugador un nuevo objeto.
     * @param objeto Objeto a añadir a un jugador
     */
    public void añadirObjeto(ObjetoJugador objeto){
       if(this.powerup.size() < MAXIMO_OBJETOS)
       {
            this.powerup.add(objeto);
       }
    }

    /**
     * Coloca un jugador en una casilla determinada e inicia
     * el proceso de dibujado.
     * @param casilla casilla donde se situa el jugador
     */
    public void colocar(Casilla casilla){

        casilla.setJugador(this);
        this.setCasilla(casilla);

        if(this.casilla != null)
        {
            this.enJuego = true;
            for(ElementoDibujable textura: this.texturas){
                textura.dibujar(this.getPosicionX(),this.getPosicionY());

            }
            if(this.indicador!= null){
                this.indicador.dibujar(this.getPosicionX(),this.getPosicionY());
            }

            this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");

            if(this.estado instanceof ConPelota){
                ((ConPelota) this.estado).actualizarTexturas();
            }
        }
    }

    /**
     * Elimina el jugador de la casilla y finaliza el proceso de dibujado
     */
    public void quitar(){
        this.casilla = null;
        this.seleccion.borrar();
        this.seleccion = null;
        id = -1;
        this.enJuego = false;
        this.seleccionado = false;
        this.bloqueado = false;
        for(ElementoDibujable elemento : this.texturas){
            elemento.borrar();
        }
        this.bloqueo = null;
    }

    /**
     *
     * @return PowerUp
     */
    public ArrayList<ObjetoJugador> getPowerUP()
    {
        return this.powerup;
    }

    /**
     * Devolvemos el PowerUp deseado
     * @param index Posición en la que se encuentra un PowerUp
     * @return PowerUp
     */
    public ObjetoJugador getPowerUP(int index)
    {
        if(index>=0 && index<4)
        {
            return this.powerup.get(index);
        }

        return null;
    }

    /**
     * Añadimos un PowerUp a jugador
     * @param powerup
     */
    public void setPowerUP(ObjetoJugador powerup)
    {
        if(this.powerup.size() <= 4)
        {
            this.powerup.add(powerup);
        }
    }

    /**
     * Añadimos un PowerUp a jugador en el índice deseado
     * @param powerup
     * @param index
     */
    public void setPowerUP(PowerUP powerup, int index)
    {
        if(index>=0 && index<4)
        {
            this.powerup.add(index, powerup);
        }
    }


    /**
     * Modifica la direccion del jugador
     * @param direccion
     */
    public void setDireccion(DireccionJugador direccion)
    {
        this.direccion = direccion;
        for(ElementoDibujable dibujo: this.texturas)
        {
            dibujo.borrar();
        }
        this.texturas = GeneradorImagenJugador.generarTexturas(this.color,this.aspecto,direccion);

        if(this.bloqueado && this.bloqueo != null){
                this.texturas.remove(this.bloqueo);
                this.generarTexturaBloqueado();
        }
    }


    /**
     * Generamos una acción
     * @param posX
     * @param posY
     */
    public void generarAccion(int posX, int posY)
    {
        this.estado.generarAccion(this, (int)posX, (int)posY);
    }

    /**
     * Devuelve el estado del jugador
     * @return Estado
     */
    public Estado getEstado()
    {
        return this.estado;
    }

    /**
     * Modifica el estado del jugador
     * @param estado Nuevo estado
     */
    public void setEstado(Estado estado)
    {
        if(this.estado != null) {
            if(this.estado instanceof ConPelota && estado instanceof SinPelota){
                ((ConPelota)this.estado).borrarTexturas();
            }
        }
        this.estado = estado;
    }

    /**
     * Devuelve la acción assignada a un Jugador
     * @return Accion
     */
    public Accion getAccion()
    {
        return this.accion;
    }

    /**
     * Modifica la acción del jugador
     * @param accion
     */
    public void setAccion(Accion accion)
    {
        this.accion = accion;
    }

    /**
     * Devuelve si un jugador esta o no seleccionado
     * @return Boolean
     */
    public boolean getSeleccionado()
    {
        return this.seleccionado;
    }

    /**
     * Selecciona a nu jugador
     * @param seleccionado
     */
    public void setSeleccionado(boolean seleccionado)
    {
        this.seleccionado = seleccionado;

        if(this.seleccionado )
        {
            if(this.seleccion == null) {
                this.seleccion= new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            }
            this.seleccion.dibujar((int)this.casilla.getPosX(),(int)this.casilla.getPosY());
            // Obligamos a dibjar la textura del jugador encima

            for(ElementoDibujable texturas : this.texturas) {
                texturas.borrar();
            }

            for(ElementoDibujable texturas : this.texturas) {
                texturas.dibujar(this.getPosicionX(),this.getPosicionY());
            }

        }
        else
        {
            if(this.seleccion == null) {
                this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            }
            this.seleccion.borrar();
            GestorGrafico.getCamara().desbloquear();
        }
    }

    /**
     * Devuelve si un jugador se encuentra seleccionado
     * @return Boolean
     */
    public boolean getBloqueado()
    {
        return this.bloqueado;
    }

    /**
     * Modifica el estado de bloqueo de un jugador
     * @param bloqueado
     */
    public void setBloqueado(boolean bloqueado)
    {
        this.bloqueado = bloqueado;

        if(this.bloqueado)  {
            if(this.bloqueo != null && this.texturas.contains(this.bloqueo)){
                ElementoDibujable texturaBloqueado = this.texturas.get(this.texturas.indexOf(this.bloqueo));
                texturaBloqueado.borrar();
                this.texturas.remove(texturaBloqueado);

            }
            this.generarTexturaBloqueado();
        }

        else {
             if(this.bloqueo != null && this.texturas.contains(this.bloqueo)){
                    ElementoDibujable texturaBloqueado = this.texturas.get(this.texturas.indexOf(this.bloqueo));
                     texturaBloqueado.borrar();
                     this.texturas.remove(texturaBloqueado);
                     this.bloqueo = null;
            }
        }
    }

    private void generarTexturaBloqueado(){

    if(this.getPosicionY() >= 0  && this.getPosicionX() >= 0)
    {
        ElementoDibujable elemento;
        switch (this.direccion){
            case izquierda:
                elemento = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/bloqueado/jugador5Congelat.png");
                this.texturas.add(elemento);
                this.bloqueo = elemento;
                break;
            case derecha:
                elemento =new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/bloqueado/jugador1Congelat.png");
                this.texturas.add( elemento);
                this.bloqueo = elemento;
                break;
            case arriba:
                elemento =new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/bloqueado/jugador4Congelat.png");
                this.texturas.add(elemento );
                this.bloqueo = elemento;
                break;
            case abajo:
                elemento =new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/bloqueado/jugador2Congelat.png");
                this.texturas.add(elemento);
                this.bloqueo = elemento;
                break;

        }


            this.texturas.get(this.texturas.size()-1).dibujar(this.getPosicionX(),this.getPosicionY());
            System.out.println("Dibujando textura: "+this.getPosicionX()+","+this.getPosicionY());

        }
    }

    /**
     * Controla todo lo relacionado con el jugador
     * @param entrada
     * @param posX
     * @param posY
     * @param casillas
     */
    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla[][] casillas)
    {
        boolean accionGenerada = false;
        //  GestorGrafico.getCamara().bloquear();
        //Falta hacer una comprobacion de seleccionados para ver si un jugador hace un pase o otra accion
        /**
         * - Recorremos todas las casillas y verificamos si en alguna de ellas hay un jugador con estado seleccionado == true
         * - Si hay un jugador seleccionado, procedemos a verificar cual de ellos es
         * - Una vez localizado se le asigna el pase
         */


        if (entrada == Entrada.pase || entrada == Entrada.chute)
        {
              this.paseOChute = entrada;
        }
        else
        {
            if(this.getBloqueado() == false)
            {
                //System.out.println(posX+"-"+posY+"-------"+this.getPosicionX()+"-"+this.getPosicionY());
                for (int i = 0; i < 20; i++)
                {
                    for (int j = 0; j < 30; j++)
                    {
                        if(this.getSeleccionado() == true)
                        {
                            /*
                            * Identificamos la casilla que ha lanzado el evento comparando con la X y la Y
                            */
                            if(casillas[i][j].getPosX() == posX && casillas[i][j].getPosY() == posY)
                            {
                                if(entrada == Entrada.clicklargo || entrada == Entrada.arrastrar)
                                {
                                    //System.out.println("entrada:"+entrada);
                                    if(this.getSeleccionado() == true)
                                    {
                                        accionGenerada = this.getEstado().generarAccion(this,(int)posX,(int)posY, entrada);
                                        i=20;
                                        j=30;
                                    }
                                }
                            }
                        }
                    }
                }
                if(accionGenerada == true)
                {
                    //this.setEstado(new SinPelota());
                    //System.out.println("me quedo sin pelota");
                }
                if(this.getBloqueado() == false)
                {
                    if(this.getSeleccionado() == false)
                    {
                        if(this.casilla.getPosX() == posX && this.casilla.getPosY() == posY)
                        {
                            if(entrada == Entrada.clic)
                            {
                                this.setSeleccionado(true);
                                System.out.println("ESTADO:"+this.getEstado());
                                System.out.println(">---------Me seleccionan-------------<"+this.getEstado());
                                GestorGrafico.getCamara().bloquear();
                            }
                        }
                    }
                    else
                    {
                        if(entrada == Entrada.clic)
                        {
                            this.setSeleccionado(false);
                            this.estado = estado.getEstado();
                            System.out.println("<---------Me deseleccionan------------->");
                            GestorGrafico.getCamara().desbloquear();
                        }
                    }
                }
                else
                {
                    System.out.println("<---------Estoy bloqueado-1------------>");
                    GestorGrafico.getCamara().desbloquear();
                }
            }
           /* else
            {
                System.out.println("<---------Estoy bloqueado-2------------>");
            }*/
        }
    }

    /**
     * Devuelve si pase o chute
     * @return Entrada
     */
    public Entrada getPaseOChute()
    {
        return this.paseOChute;
    }



    public DireccionJugador getDireccion(){return this.direccion;}


    /**
     * @param entrada tipo de entrada
     * @param posX eje x donde se ha realizado la acciion /entrada
     * @param posY eje y donde se ha realizado la acciion /entrada
     */
    @Override
    public void accionEntrada(Entrada entrada, float posX, float posY) {

    }

    /**
     *
     * @param entrada tipo de entrada
     */
    @Override
    public void accionEntrada(Entrada entrada) {
        System.out.println("---------------------------------Tipo entrada:"+entrada);
    }





    /**
     * Obtenemos la posición X del jugador
     * @return int Posición X
     */

    public int getPosicionX() {
        if(this.casilla != null) {
            return (int)this.casilla.getPosX();
        }
        else return -1;
    }

    /**
     * Obtenemos la posición Y del jugador
     * @return int Posición Y
     */

    public int getPosicionY() {
        if(this.casilla != null)
        {
            return (int)this.casilla.getPosY();
        }
        else return -1;
    }

    public int getFuerza() { return this.Fuerza; }

    public void setFuerza(int fuerza) {
        this.Fuerza = fuerza;
    }

    public int getVida() { return this.Vida; }

    public void setVida(int vida) { this.Vida = vida;}

    public int getDefensa() {
        return this.Defensa;
    }

    public void setDefensa(int defensa) { this.Defensa = defensa; }

    public int getHabilidad() { return this.Habilidad; }

    public void setHabilidad(int habilidad) { this.Habilidad = habilidad; }

    public int getResistencia() {
        return this.Resistencia;
    }

    public void setResistencia(int resistencia) {
        this.Resistencia = resistencia;
    }

    public int getAtaque() {return this.Ataque; }

    public void setAtaque(int ataque) {
        this.Ataque = ataque;
    }

    public Casilla getCasilla(){ return this.casilla;}

    public void setCasilla(Casilla casilla) { this.casilla = casilla;}

    /**
     *
     * @return boolean enJuego
     */
    public boolean getEnJuego(){return enJuego;}


    /**
     *
     * @return ArrayList texturas
     */
    public ArrayList<ElementoDibujable> getTexturasMuestreo(){

        return GeneradorImagenJugador.generarTexturasIntefaz(this.color,this.aspecto, DireccionJugador.frontal);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.texturas = GeneradorImagenJugador.generarTexturas(this.color,this.aspecto,this.direccion);
    }

    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
    }

    public boolean isExpulsado() {
        return expulsado;
    }

    public void setExpulsado(boolean expulsado) {
        this.expulsado = expulsado;
    }
}
