package games.mgd.archery.logic;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glClear;

import android.content.Context;

import games.mgd.archery.Game;

/**
 * Created by Michael on 22/07/2016.
 */
public class MainLogic implements Game {

    @Override
    public void inititalize(Context context) {
        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void clean() {

    }
}
