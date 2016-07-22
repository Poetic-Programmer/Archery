package games.mgd.archery.game.audio;

import android.media.SoundPool;

public class SoundPlayer implements Sound {
    int soundId;
    SoundPool soundPool;

    public SoundPlayer(SoundPool soundPool,int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        soundPool.unload(soundId);
    }
}
