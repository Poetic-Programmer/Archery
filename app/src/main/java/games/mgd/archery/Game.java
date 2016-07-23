package games.mgd.archery;

import android.content.Context;

/**
 * Created by Michael on 22/07/2016.
 */
public interface Game {
    void inititalize(Context context);
    void setupCamera(int width, int height);
    void pause();
    void update();
    void render();
    void clean();
}
