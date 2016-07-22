package games.mgd.archery;

/**
 * Created by Michael on 22/07/2016.
 */
public enum GameState {
    INIT, RUNNING, PAUSED, FINISHED, IDLE;

    private Object lock;

    private GameState(){
        lock = new Object();
    }

    public Object getLock() { return lock; }

}
