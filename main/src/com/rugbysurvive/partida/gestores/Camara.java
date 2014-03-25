package com.rugbysurvive.partida.gestores;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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
    private int boardWidth;
    private int boardHeight;
    private int absoluteVariationY; // Number of pixels the camera have been moved.
    private int absoluteVariationX;
    private Rectangle glViewport;


    public Camara(int maxWidth, int maxHeight)
    {
        this.absoluteVariationX = 0;
        this.absoluteVariationY = 0;
        this.boardHeight =  maxHeight;
        this.boardWidth = maxWidth;
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        this.camera = new OrthographicCamera(this.width, this.height);
        glViewport = new Rectangle(0, 0, this.width, this.height);
        this.camera.position.set(this.width,this.height,0);
//        this.camera.apply(Gdx.graphics.getGL10());
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        int variationX = Gdx.input.getDeltaX();
        int variationY = Gdx.input.getDeltaY();

        if(this.isCameraInsideBoard(variationX,variationY))
        {
            this.absoluteVariationX += variationX;
            this.absoluteVariationY += variationY;

            this.camera.translate(-variationX,variationY);
            this.camera.update();
            this.camera.apply(Gdx.graphics.getGL10());
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch)
    {

        /*GL10 gl = Gdx.graphics.getGL10();
        System.out.println(gl.toString());

        // Camera --------------------- /
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glViewport((int) glViewport.x, (int) glViewport.y,
                (int) glViewport.width, (int) glViewport.height);*/

        batch.setProjectionMatrix(this.camera.combined);
        this.camera.update();
        this.camera.apply(Gdx.graphics.getGL10());
    }

    private boolean isCameraInsideBoard(int variationX,int variationY)
    {
        return (variationY + this.camera.position.y > MIN_POSITION_Y
                && (-variationX)+ this.camera.position.x > MIN_POSITION_X
                && variationY + this.camera.position.y < this.boardHeight
                && (-variationX)+ this.camera.position.x < this.boardWidth);
    }


    public int getVariationX(){return this.absoluteVariationX;}
    public int getVariationY(){return this.absoluteVariationY;}





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