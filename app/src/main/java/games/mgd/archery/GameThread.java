package games.mgd.archery;

import android.util.Log;

/**
 * Created by Michael on 22/07/2016.
 */
public enum GameThread {
    INSTANCE;

    private enum RendererStateDebug{
        INIT("Initialized"),
        RUNNING("Running"),
        PAUSED("Paused"),
        FINISHED("Finished"),
        IDLE("Idle");

        private String TAG = "RS DEBUG";
        private String echo;
        private RendererStateDebug(String state){
            echo = state;
        }

        public void print(){
            Log.d(TAG, echo);
        }
    };

    private enum RendererState{
        INIT,
        RUNNING,
        PAUSED,
        FINISHED,
        IDLE
    };

    private RendererState state;
    private RendererState savedState;
    private RendererStateDebug debugState;

    private Object lock;

    GameThread(){
        state = RendererState.IDLE;
        lock = new Object();
    }

    public Object getLock() { return lock; }

    public void initialize() { state = RendererState.INIT; }
    public void finish(){ state = RendererState.FINISHED; }
    public void pause(){ state = RendererState.PAUSED; }
    public void run() { state = RendererState.RUNNING; }
    public void idle() { state = RendererState.IDLE; }

    public boolean isInitialized() { return (savedState == RendererState.INIT); }
    public boolean isRunning() { return (savedState == RendererState.RUNNING); }
    public boolean isPaused() { return (savedState == RendererState.PAUSED); }
    public boolean isFinished() { return (savedState == RendererState.FINISHED); }
    public boolean isIdle() { return (savedState == RendererState.IDLE); }

    public void saveState(){
        // gain ownership of thread and save the state
        synchronized (lock){
            savedState = state;
        }
    }

}
