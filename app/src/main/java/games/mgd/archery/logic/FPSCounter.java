package games.mgd.archery.logic;

import android.util.Log;

/**
 * Created by Michael on 23/07/2016.
 */
public class FPSCounter {
	private long startTime = System.nanoTime();
	private int frames = 0;

	public void logFrame() {
		frames++;
		if(System.nanoTime() - startTime >= 1000000000)
		{
			Log.d("FPSCounter", "fps: " + frames);
			frames = 0;
			startTime = System.nanoTime();
		}
	}
}
