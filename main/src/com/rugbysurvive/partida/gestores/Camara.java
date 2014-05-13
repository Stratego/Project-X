package com.rugbysurvive.partida.gestores;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.rugbysurvive.partida.ConstantesJuego;

/**
 * Process the game's view.
 * The user can move the view using the touch dragged option.
 * The limit of view is because of the board bounds.
 *
 * @author aitor marco castro
 *
 */
public class Camara implements InputProcessor {

    private static final int MIN_POSITION_X = 200;
    private static final int MIN_POSITION_Y = 200;


    private OrthographicCamera camera;
    private float width;
    private float height;
    private int MAXIMO_ANCHURA = ConstantesJuego.ANCHO_TABLERO_CON_ZOOM- Gdx.graphics.getWidth()/2;
    private int MAXIMO_ALTURA = ConstantesJuego.ALTO_TABLERO_CON_ZOOM - Gdx.graphics.getHeight()/2;
    private int boardHeight;
    private int absoluteVariationY; // Number of pixels the camera have been moved.
    private int absoluteVariationX;
    private Rectangle glViewport;
    private boolean bloqueada;


    public Camara(int maxWidth, int maxHeight)
    {
        this.absoluteVariationX = 0;
        this.absoluteVariationY = 0;
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.camera = new OrthographicCamera(this.width, this.height);
        this.camera.position.set(this.width/2,this.height/2,0);
        this.bloqueada = false;
//        this.camera.apply(Gdx.graphics.getGL10());
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if(!bloqueada)
        {
            if(Gdx.input.isTouched(0) && !Gdx.input.isTouched(1))
             {
                    int variationX = Gdx.input.getDeltaX();
                    int variationY = Gdx.input.getDeltaY();

                     if(this.isCameraInsideBoard(variationX,variationY))
                     {
                         this.absoluteVariationX += variationX;
                         this.absoluteVariationY += variationY;

                         this.camera.translate(-variationX,variationY);
                        // this.camera.update();
                         // this.camera.apply(Gdx.graphics.getGL20());
               return true;
             }
            }
        }
        return false;
    }

    public void render(SpriteBatch batch)
    {
        this.camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(this.camera.combined);
    }

    public void variarPosicion(int posicionX,int posicionY)
    {

           posicionX = (int)(posicionX*ConstantesJuego.variables().getMultiplicador());
        posicionY = (int)(posicionY*ConstantesJuego.variables().getMultiplicador());


            if(posicionX>= this.camera.position.x +this.width/2 ||posicionX <= this.camera.position.x -this.width/2
                 || posicionY>= this.camera.position.y +this.height/2 ||posicionY <= this.camera.position.y -this.height/2)
            {
                 this.absoluteVariationX =  (int)(this.width/2) -posicionX;
                 this.absoluteVariationY =  posicionY - (int)(this.height/2);
                 this.camera.position.set(posicionX,posicionY,0);
            }
    }

    public void situarCamara(int posicionX,int posicionY)
    {

        posicionX = (int)(posicionX*ConstantesJuego.variables().getMultiplicador());
        posicionY = (int)(posicionY*ConstantesJuego.variables().getMultiplicador());


       this.absoluteVariationX =  (int)(this.width/2) -posicionX;
       this.absoluteVariationY =  posicionY - (int)(this.height/2);
       this.camera.position.set(posicionX,posicionY,0);

    }


    private boolean isCameraInsideBoard(int variationX,int variationY)
    {

        return true;

        /*return (variationY + this.camera.position.y > MIN_POSITION_Y
                && (-variationX)+ this.camera.position.x > MIN_POSITION_X
                && variationY + this.camera.position.y < MAXIMO_ANCHURA
                && (-variationX)+ this.camera.position.x < MAXIMO_ALTURA);*/
    }

    public void bloquear(){this.bloqueada = true;}
    public void desbloquear(){this.bloqueada = false;}


    public int getVariationX(){return this.absoluteVariationX;}
    public int getVariationY(){return this.absoluteVariationY;}

    public OrthographicCamera getOrthographicCamera(){return this.camera;}



//=================================================================================================0

    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}