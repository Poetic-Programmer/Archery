package games.mgd.archery.game.audio;

/**
 * Created by Michael on 22/07/2016.
 */
public interface Music {
    void play();

    void stop();

    void pause();

    void setLooping(boolean looping);

    void setVolume(float volume);

    boolean isPlaying();

    boolean isStopped();

    boolean isLooping();

    void dispose();
}
