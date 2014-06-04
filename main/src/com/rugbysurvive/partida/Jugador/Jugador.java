package com.rugbysurvive.partida.Jugador;

import com.rugbysurvive.partida.ConstantesJuego;
import com.rugbysurvive.partida.Dibujables.ElementoDibujable;
import com.rugbysurvive.partida.Dibujables.TipoDibujo;
import com.rugbysurvive.partida.Jugador.extras.Color;
import com.rugbysurvive.partida.Jugador.extras.GeneradorImagenJugador;
import com.rugbysurvive.partida.Simulador.Accion;
import com.rugbysurvive.partida.elementos.ComponentesJuego;
import com.rugbysurvive.partida.elementos.objetos.ObjetoJugador;
import com.rugbysurvive.partida.elementos.objetos.poweUps.PowerUP;
import com.rugbysurvive.partida.gestores.Entrada.Entrada;
import com.rugbysurvive.partida.gestores.GestorGrafico;
import com.rugbysurvive.partida.jugadores.Equipo;
import com.rugbysurvive.partida.tablero.Casilla;

import java.util.ArrayList;

/**
 * Created by Victor on 27/03/14.
 * Gestiona todos los elementos principales que debe tener
 * un jugador de rugby.
 * En primer termino gestiona todos los datos de entrada del usuario
 * y realiza los cambios de estado necesarios por los cuales se realizaran las
 * diferentes acciones.
 * Tambien contiene todos los atributos necesarios que lo caracterizan
 *
 * Cada jugador debe gestionar su proceso de dibujado y borrado asi como el dibujado
 * de sus diferentes indicadores de estado como herido o cansado.
 *
 *Gestiona tambien el conjunto de texturas que lo definen como el color y aspecto
 *
 */
public class Jugador  {

    //Limite superior que puede alcanzar los atributos de un jugador
    private static final int MAXIMO_OBJETOS = 4;
    public static final int MAX_HABILIDAD = 100;
    public static final int MAX_DEFENSA = 100;
    public static final int MAX_ATAQUE = 100;
    public static final int MAX_FUERZA = 100;
    public static final int MAX_RESISTENCIA = 100;
    public static final int MOVIMIENTOS_CANSANCIO = 6;
    public static final int CANSANCIO = 30;


    // Habilidades del jugador
    private int fuerza;
    private int vida;
    private int defensa;
    private int habilidad;
    private int resistencia;
    private int ataque;

    // Habilidades del jugador originales al iniciar el juego
    private final int fuerzaOriginal;
    private final int defensaOriginal;
    private final int habilidadOriginal;
    private final int ataqueOriginal;
    public final int vidaOriginal;
    private final int resistenciaOriginal;


    // Texturas del jugador tanto estados como aspecto
    private String textura;
    private DireccionJugador direccion;
    private ElementoDibujable seleccion;
    private ElementoDibujable bloqueo;
    private ArrayList<ElementoDibujable> texturas;
    private ElementoDibujable indicador;


    private Color color;
    private Accion accion = null;
    private Casilla casilla = null;

    //Estado del jugador
    private boolean seleccionado = false;
    private boolean bloqueado = false;
    private Estado estado;
    private boolean lesionado;


    private ArrayList<ObjetoJugador> powerup;


    public int contMovimientos;
    public int id;
    private boolean enJuego;
    public Equipo miEquipo;
    public Entrada paseOChute = Entrada.pase;
    public int aspecto;
    private boolean expulsado;

    /**
     * Inicializa todos los atributos que caracterizan al jugador y lo une
     * al equipo que pertenece.
     *
     * @param fuerza Indica la fuerza de un jugador
     * @param vida Indica la vida de un jugador
     * @param defensa Indica la defensa de un jugador
     * @param ataque Indica que el ataque te tendra originalmente el jugador
     * @param habilidad Indica que la habiliadd te tendra originalmente el jugador
     * @param resistencia Indica la resistencia  te tendra originalmente el jugador
     * @param equipo Indica a que equipo pertenece un jugador
     */
    public Jugador(int fuerza, int vida, int defensa, int habilidad, int resistencia, int ataque, Equipo equipo)
    {
        // Caracteristicas del equipo que afectan al jugador
        this.color = Color.azul;
        this.direccion = DireccionJugador.izquierda;


        // Inicializacion de las texturas
        this.aspecto = GeneradorImagenJugador.generarAspecto();
        this.bloqueo =null;
        this.indicador = null;
        this.id = -1;
        this.textura = "jugador1.png";
        this.texturas = GeneradorImagenJugador.generarTexturas(this.color,this.aspecto,DireccionJugador.izquierda);

        // Inicializacion de las habilidades
        this.fuerza = fuerza;
        this.vida = vida;
        this.defensa = defensa;
        this.habilidad = habilidad;
        this.resistencia = resistencia;
        this.ataque = ataque;

        this.fuerzaOriginal = fuerza;
        this.vidaOriginal = vida;
        this.defensaOriginal = defensa;
        this.habilidadOriginal = habilidad;
        this.ataqueOriginal = ataque;
        this.resistenciaOriginal = resistencia;


        // Inicializacion de los estados
        this.estado = new SinPelota();
        this.lesionado = false;
        this.contMovimientos = 0;
        this.expulsado = false;
        this.estado.setBloqueado(false);
        this.setSeleccionado(false);
        this.enJuego = false;


        // Elementos que contiene el jugador
        this.miEquipo = equipo;
        this.powerup= new ArrayList<ObjetoJugador>();
    }


    /**
     * Cada vez que se llama a la funcion el jugador se va cansando,
     * Eso implica que llegado a un punto perdera habilidad de resistencia
     * y sera indicado por pantalla.
     *
     */
    public void cansancio()
    {
        if(this.contMovimientos == MOVIMIENTOS_CANSANCIO) {
            this.setResistencia(this.getResistencia() - CANSANCIO);
            this.contMovimientos = 0;
            if(this.indicador == null) {
                this.indicador  = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/estado/cansancio.png");
                this.indicador.dibujar(this.getPosicionX(), this.getPosicionY());
            }
        }

        else {
            this.contMovimientos += 1;
        }


    }

    /**
     *Reduce la vida con la cantidad indicada por daño
     * Si la vida es muy baja sera indicado con una textura de lesion.
     * @param daño cantidad de vida que se reduce al jugador
     */
    public void lesionar(int daño)
    {
        if(this.vida > 0)
        {
            this.setVida(this.getVida() - daño);
            if(this.vida <= 0)
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

            else if(this.vida <= this.vidaOriginal/2){
                 if(this.resistencia > this.resistenciaOriginal /2) {
                    this.setResistencia(this.resistenciaOriginal/2);
                 }
                if(this.fuerza > this.fuerzaOriginal /2) {
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
        // La casilla recibida debe exisiter
        if(this.casilla != null)
        {
            // Se generan todas las texturas para su correcto dibujado
            this.enJuego = true;
            for(ElementoDibujable textura: this.texturas){
                textura.dibujar(this.getPosicionX(),this.getPosicionY());

            }
            if(this.indicador!= null){
                this.indicador.dibujar(this.getPosicionX(),this.getPosicionY());
            }

            this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            // Si el jugador tiene pelota hay que actualizar su indicador de que tiene pelota
            if(this.estado instanceof ConPelota){
                ((ConPelota) this.estado).actualizarTexturas();
            }
        }
    }

    /**
     * Elimina el jugador de la casilla y finaliza el proceso de dibujado.
     * Se borran todas las textura.
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
     * Modifica la direccion del jugador
     * @param direccion
     */
    public void setDireccion(DireccionJugador direccion)
    {
        this.direccion = direccion;
        for(ElementoDibujable dibujo: this.texturas) {
            dibujo.borrar();
        }
        this.texturas = GeneradorImagenJugador.generarTexturas(this.color,this.aspecto,direccion);

        if(this.bloqueado && this.bloqueo != null) {
                this.texturas.remove(this.bloqueo);
                this.generarTexturaBloqueado();
        }
    }


    /**
     * Cada estado tiene la capacidad de generar una accion para el simulador.
     * Una vez llamada la funcion el estado indicado generara esa accion y la
     * añadira al simulador.
     * @param posX posicion afectada por la accion
     * @param posY posicion en el eje Y afectada por la accion
     */
    public void generarAccion(int posX, int posY)
    {
        this.estado.generarAccion(this, (int)posX, (int)posY);
    }


    /**
     * Genera todas las texturas necesarias para el estado bloqueado.
     * Eso implica un dibujado segun la direccion de visualizacion
     */
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
            //System.out.println("Dibujando textura: "+this.getPosicionX()+","+this.getPosicionY());

        }
    }

    /**
     * Toda accion de entrada de datos realizada por un usuario sera enviado
     * al jugador.
     * Cada vez que el jugador reciba una entrada pueden pasar dos cosas:
     *
     * - Si el jugador no esta seleccionado :
     *      Reaccionara solo si recibe una entrada click encima de el
     *
     *  - Si el jugador esta seleccionado :
     *        Dependiendo de la entrada de la entrada de datos ,su estado
     *        o la posicion reaccionara una manera otra .
     *        Cualquier proceso del jugador partira desde esta funcion
     *
     * @param posX Posicion del tablero en el eje X donde ha sucedido la entrada de datos
     * @param posY Posicion del tablero en el eje y donde ha sucedidod la entrada de datos
     * @param casillas Tablero del juego
     */
    public void accionEntrada(Entrada entrada, float posX, float posY, Casilla[][] casillas)
    {
        boolean accionGenerada = false;

        /**
         * - Recorremos todas las casillas y verificamos si en alguna de ellas hay un jugador con estado seleccionado == true
         * - Si hay un jugador seleccionado, procedemos a verificar cual de ellos es
         * - Una vez localizado se le asigna el pase
         */

        // Indica al jugador si se esta en posicion de pase o chute (indicado por el interfaz de usuario)
        if (entrada == Entrada.pase || entrada == Entrada.chute) {
              this.paseOChute = entrada;
        }

        else
        {
            //Se comprueba que el jugador no esta bloqueado
            if(!this.getBloqueado())
            {
                /* Se Comprueba que el jugador el destinatario del mensaje
                * Todos los jugadores del campo deben recibir la entrada por
                * una posible entrada de seleccion o deseleccion
               */

                for (int i = 0; i < 20; i++)
                {
                    for (int j = 0; j < 30; j++)
                    {
                        //MIramos que el jugador esta seleccionado
                        if(this.getSeleccionado())
                        {
                            /*
                            * Identificamos la casilla que ha lanzado el evento comparando con la X y la Y
                            */
                            if(casillas[i][j].getPosX() == posX && casillas[i][j].getPosY() == posY)
                            {
                                // Depuramos la entrada de datos para asegurarnos que implique una accion del jugador
                                if(entrada == Entrada.clicklargo || entrada == Entrada.arrastrar)
                                {

                                    if(this.getSeleccionado())
                                    {   // Generamos la accion determinada segun el estado del jugador
                                        accionGenerada = this.getEstado().generarAccion(this,(int)posX,(int)posY, entrada);
                                        i=20;
                                        j=30;
                                    }
                                }
                            }
                        }
                    }
                }
                /*
                 *Procesado de seleccion y deseleccion de un jugador .
                 * El jugador debe estar desbloqueado y no seleccionado .
                 * Ade,as debe ser el objetivo de la accion del jugador
                 */
                if(!this.getBloqueado()) {
                    if(!this.getSeleccionado()) {

                        if(this.casilla.getPosX() == posX && this.casilla.getPosY() == posY) {

                            if(entrada == Entrada.clic)
                            {
                                if (!ConstantesJuego.RIVAL_IA){
                                    this.setSeleccionado(true);
                                } else if (this.miEquipo== ComponentesJuego.getComponentes().getEquipo1() ){
                                    this.setSeleccionado(true);
                                }
                                // bloquep de la camara para facilitar la accion del jugador
                                GestorGrafico.getCamara().bloquear();
                            }
                        }
                    }
                    // El jugador recibe una peticion de deseleccion y se desbloquea la camara
                    else {
                        if(entrada == Entrada.clic)  {
                            this.setSeleccionado(false);
                            this.estado = estado.getEstado();
                            GestorGrafico.getCamara().desbloquear();
                        }
                    }
                }
                else {
                    GestorGrafico.getCamara().desbloquear();
                }
            }
        }
    }


    /**
     * Devuelve el estado del jugador actual
     * @return Estado del jugador actual
     */
    public Estado getEstado()
    {
        return this.estado;
    }

    /**
     * Modifica el estado del jugador por otro deseado
     * @param estado Nuevo estado del jugador hasta nuevo cambio
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
     * Devuelve si un jugador esta o no seleccionado
     * @return Boolean
     */
    public boolean getSeleccionado()
    {
        return this.seleccionado;
    }

    /**
     * Indica si un jugador esta seleccionado o no
     * - El jugador indicado pasa a estado seleccionado:
     *      Esto signfica el dibujado de las texturas que indican seleccion.
     *      El jugador seleccionado pasa a ser el objetivo de toda accion
     *      indicada por el usuario.
     *   - El jugador indicado pasa a estado deseleccionado:
     *      Se borraran todas las texturas que indican seleccion
     *      y dejara de recibir cualquier indicadion del usuario.
     *
     *    @param seleccionado indicador si esta seleccionado
     */
    public void setSeleccionado(boolean seleccionado)
    {

        this.seleccionado = seleccionado;

        if(this.seleccionado ) {
            if(this.seleccion == null) {
                this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
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

        else {
            if(this.seleccion == null) {
                this.seleccion = new ElementoDibujable(TipoDibujo.elementosJuego,"jugador/seleccionado.png");
            }
            this.seleccion.borrar();
            GestorGrafico.getCamara().desbloquear();
        }
    }



    /**
     * Indica si un jugador esta bloqueado o no.
     *  - Si el jugador esta bloqueado no podra ser objeto de seleccion
     *    y por lo tanto estara inerte a cualquier entrada de datos hasta
     *    que sea desbloqueado
     *
     * - Si el jugador esta desbloqueado puede recibir cualquier entrada de datos
     *   cuyo objetivo sea el jugador
     *
     * @param bloqueado indicador si esta bloqueado
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


    /**
     *Devuelve todos los objetos que dispone el jugador
     * @return PowerUp objetos que dispone el jugador
     */
    public ArrayList<ObjetoJugador> getPowerUP()
    {
        return this.powerup;
    }

    /**
     * Devolvemos el PowerUp deseado a partir de su posicion.
     * Si no contiene ningun power up la funcion devolvera un nulo.
     * @param index Posición en la que se encuentra un PowerUp
     * @return Power up encontrado
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
     * Se añade un PowerUp a jugador.
     * Si el jugador contiene mas de 4 elementos no se incluira
     * @param powerup objeto que se desea añadir
     */
    public void setPowerUP(ObjetoJugador powerup)
    {
        if(this.powerup.size() <= 4)
        {
            this.powerup.add(powerup);
        }
    }

    /**
     * Añadimos un PowerUp a jugador en el índice deseado.
     *  EL indice no debe superar el maximo de objetos permitidos
     * @param powerup objeto que se desea añadir
     * @param index
     */
    public void setPowerUP(PowerUP powerup, int index)
    {
        if(index>=0 && index<4) {
            this.powerup.add(index, powerup);
        }
    }

    /**
     * Indica si el jugador puede realizar un pase o un chute
     * @return  indicacion de pase o chute
     */
    public Entrada getPaseOChute() {
        return this.paseOChute;
    }



    /**
     * Obtenemos la posición X del jugador dentro del campo de juego.
     * La posicion viene determinada por la casilla donde esta colocado el jugador.
     * Si el jugador no esta colocado la funcion devuelve un -1
     * @return  Posicion de la casilla en el eje x del tablero donde esta situado el jugador
     *
     */

    public int getPosicionX() {
        if(this.casilla != null) {
            return (int)this.casilla.getPosX();
        }
        else return -1;
    }


     /**
     * Obtenemos la posición Y del jugador dentro del campo de juego.
     * La posicion viene determinada por la casilla donde esta colocado el jugador.
     * Si el jugador no esta colocado la funcion devuelve un -1
     * @return  Posicion de la casilla en el eje xYdel tablero donde esta situado el jugador
     *
     */

    public int getPosicionY() {
        if(this.casilla != null) {
            return (int)this.casilla.getPosY();
        }
        else {
            return -1;
        }
    }

    /**
     * Se obtiene la habilidad de fuerza que dispone el jugador en el
     * momento prceso
     * @return fuerza del jugador
     */
    public int getFuerza() {
        return this.fuerza;
    }

    /**
     * Modifica la fuerza del jugador al valor indicado
     * @param fuerza fuerza del jugador
     */
    public void setFuerza(int fuerza) {
          this.fuerza = fuerza;
    }


    public int getVida() { return this.vida; }


    /**
     * Modifica la vida del jugador al valor indicado
     * @param vida del jugador
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDefensa() {
        return this.defensa;
    }

    /**
     * Modifica la defensa del jugador al valor indicado
     * @param defensa  del jugador
     */
    public void setDefensa(int defensa) { this.defensa = defensa; }


    /**
     * Obtiene la  habilidad que tiene el jugador en el preciso momento.
     * @return habilidad del jugador
     */
     public int getHabilidad() { return this.habilidad; }


    /**
     * Modifica la habilidad del jugador al valor indicado
     * @param habilidad del jugador
     */
    public void setHabilidad(int habilidad) { this.habilidad = habilidad; }

    /**
     * Obtiene la resistencia que tiene el jugador en el preciso momento.
     * @return resistencia del jugador
     */
    public int getResistencia() {
        return this.resistencia;
    }

    /**
     * Modifica la resistencia del jugador al valor indicado
     * @param resistencia  del jugador
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getAtaque() {return this.ataque; }

    /**
     * Modifica la fuerza del jugador al valor indicado
     * @param ataque  del jugador
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public Casilla getCasilla(){ return this.casilla;}

    public void setCasilla(Casilla casilla) { this.casilla = casilla;}

    /**
     * Indica si el jugador esta jugando en ese momento
     * Esto indica que esta en alguna de las casillas del tableero
     * @return indicacion de si esta en el tablero o no
     */
    public boolean getEnJuego(){
        return enJuego;
    }


    /**
     * Devuelve el conjunto de texturas por capas del jugador en posicion de muestreo
     * Esta posicion corresponde al frontal sin jugar.
     * @return Conjunto de texturas que corresponden al dibujo del jugador por capas
     */
    public ArrayList<ElementoDibujable> getTexturasMuestreo(){

        return GeneradorImagenJugador.generarTexturasIntefaz(this.color,this.aspecto, DireccionJugador.frontal);
    }

    /**
     * Color de la equipacion del jugador en este momento
     * @return color de la equipacion del jugador
     */
    public Color getColor() {
        return color;
    }

    /**
     * Modica la equipacion del jugador en caso necesario
     * @param color nuevo color de la equipacion
     */
    public void setColor(Color color) {
        this.color = color;
        System.out.println("color equipacion jugador"+this.color);
        this.texturas = GeneradorImagenJugador.generarTexturas(this.color,this.aspecto,this.direccion);
    }

    /**
     * Indica si el jugador ha sido expulsado del campo
     * @return Si ha sido expulsado del campo o no
     */
    public boolean isExpulsado() {
        return expulsado;
    }

    /**
     * Situa al jugador en posicion de expulsado
     * Por lo tanto deja de reacciona cualqueir entrada de datos
     * Es una posicion parecida al bloqueo pero implica tambien
     * que no este en el campo
     * @param expulsado
     */
    public void setExpulsado(boolean expulsado) {
        this.expulsado = expulsado;
    }

    /**
     * Devuelve si un jugador se encuentra bloqueado o no
     * @return Indicacion si esta bloqueado o no
     */
    public boolean getBloqueado()
    {
        return this.bloqueado;
    }

    /**
     * Devuelve la acción assignada a un Jugador
     * @return accion asignada al jugador
     */
    public Accion getAccion() {
        return this.accion;
    }

    /**
     * Modifica la acción del jugador
     * @param accion accion que sea desea añadir
     */
    public void setAccion(Accion accion)
    {
        this.accion = accion;
    }

    /**
     * Indica la direccion por la cual esta mirando el jugador en el campo
     * @return direccion de posicion del jugador
     */
    public DireccionJugador getDireccion(){return this.direccion;}
}
