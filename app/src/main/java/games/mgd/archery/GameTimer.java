package games.mgd.archery;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Michael on 22/07/2016.
 */
public class GameTimer {
    // Game Loop Variables
    private static final int FPS = 25;
    private static final long SKIP_TICKS = 1000 / FPS;
    private long nextGameTick;
    private long sleepTime;

    public GameTimer(){
        nextGameTick = System.currentTimeMillis();
    }

    public void runLoop(){
        nextGameTick += SKIP_TICKS;
        sleepTime = nextGameTick - System.currentTimeMillis();
        if(sleepTime >= 0){
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
