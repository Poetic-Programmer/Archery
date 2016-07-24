package games.mgd.archery;

/**
 * <h1> game renderer </h1>
 * <p> this renderer updates all of the states of the game. It communicates with the
 * main activity as to properly deal with resources when the user interacts with other
 * features of their device. </p>
 *
 * @author Michael
 * @since 22/07/2016.
 */

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import games.mgd.archery.logic.MainLogic;
import games.mgd.archery.logic.World;

public class MainRenderer implements Renderer{
    private static final String TAG = "Renderer";

    // Get a reference to the context
    private Context context = null;

    private MainLogic game;
    private GameTimer timer;

    private int width, height;
    /**
     * creates all objects neccessary for interacting with the game.
     * creates the graphics object, which holds the gl view.
     *
     * @param context used to set up renderer, reference is saved for further use
     */
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
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // save state of thread at this call
        GameThread.INSTANCE.saveState();

        if(GameThread.INSTANCE.isInitialized()){
            game.inititalize(context);
            game.setupCamera(width, height);
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