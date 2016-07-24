package games.mgd.archery.game.audio;

/**
 * <h1> Audio Interface </h1>
 * <p> interface for creating all audio </p>
 *
 * @author Michael Dunleavy
 * @since 24/07/2016
 */
public interface Audio{
    /**
     * Creates a new MusicPlayer
     * @param filename File path
     * @return MusicPlayer accessed through Music interface
     */
    Music newMusic(String filename);

    /**
     * Creates a new SoundPlayer
     * @param filename File path
     * @return SoundPlayer accessed through Sound interface
     */
    Sound newSound(String filename);
}
