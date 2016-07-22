package games.mgd.archery;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import games.mgd.archery.logic.MainLogic;

/**
 * Created by Michael on 22/07/2016.
 */

public class MainRenderer implements Renderer{
    // Get a reference to the context
    private static Context context = null;

    private MainLogic game;
    private GameTimer timer;

    public MainRenderer(Context context){
        this.context = context;

        game = new MainLogic();
        timer = new GameTimer();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GameThread.INSTANCE.saveState();

        if (GameThread.INSTANCE.isIdle()) {
            // This is the first time the game is loaded.
            GameThread.INSTANCE.initialize();
        }
        else{
            // Game potentially resized or reloaded
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // save state of thread at this call
        GameThread.INSTANCE.saveState();

        if(GameThread.INSTANCE.isInitialized()){
            game.inititalize(context);

            GameThread.INSTANCE.run();
        }

        if(GameThread.INSTANCE.isRunning())
        {
            // Cap the frame rate
            timer.runLoop();

            game.update();
            game.render();
        }

        if(GameThread.INSTANCE.isPaused())
        {
            game.pause();
            synchronized(GameThread.INSTANCE.getLock())
            {
                GameThread.INSTANCE.idle();

                // let Activity know we are ready to pause
                GameThread.INSTANCE.getLock().notifyAll();
            }
        }

        if(GameThread.INSTANCE.isFinished())
        {
            game.pause();
            game.clean();

            //
            synchronized(GameThread.INSTANCE.getLock())
            {
                GameThread.INSTANCE.idle();

                // let Activity know we are ready to close
                GameThread.INSTANCE.getLock().notifyAll();
            }
        }
    }
}