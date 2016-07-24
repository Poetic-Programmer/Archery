package games.mgd.archery.game.audio;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

/**
 * <h1> Audio Player </h1>
 * <p>All music and sounds are created through this class</p>
 *
 * @author Michael Dunleavy
 * @since 24/07/2016
 * @ToDo: update soundpool
 */
public class AudioPlayer implements Audio{
    AssetManager assets;
    SoundPool soundPool;

    /**
     * creates an audio player for all audio files to be created
     *
     * @param activity used to get the audio stream to read volume key presses from the user
     */
    public AudioPlayer(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            return new MusicPlayer(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor, 0);
            return new SoundPlayer(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}
