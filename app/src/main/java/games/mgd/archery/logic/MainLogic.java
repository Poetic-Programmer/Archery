package games.mgd.archery.logic;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glClear;

import android.content.Context;
import android.util.Log;

import games.mgd.archery.Game;
import games.mgd.archery.logic.assets.Geometry;
import games.mgd.archery.logic.assets.Materials;
import games.mgd.archery.logic.assets.Programs;
import games.mgd.archery.logic.assets.Textures;
import games.mgd.archery.logic.lighting.PositionLight;
import games.mgd.archery.logic.program.Program;
import games.mgd.archery.logic.program.Shader;
import games.mgd.archery.math.c.view.Camera3D;
import games.mgd.archery.util.MathHelper;

/**
 * Created by Michael on 22/07/2016.
 */
public class MainLogic implements Game {
    private static final String TAG = "MainLogic";

    private Camera3D camera;
    private PositionLight light;

    public MainLogic(){
        camera = new Camera3D();
    }


    @Override
    public void inititalize(Context context) {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        Textures.INSTANCE.initialize(context);
        Programs.INSTANCE.initialize(context);
        Geometry.INSTANCE.initialize();

    }

    @Override
    public void setupCamera(int width, int height){
        float a = (float) width / (float) height;
        float fov = 60.0f;

        // set up projection matrix
        camera.initialize(fov, a, 1.0f, 100.0f);

        // move the camera back until the lens width equals the game width
        float halfWidth = (float) width * 0.5f;
        float dist = (float) ((World.INSTANCE.getHalfWidth() / a) / (Math.tan(fov * 0.5f * MathHelper.DEGREES_TO_RADIANS)));
        camera.setPosition(new float [] {0, 0, -dist});

        // set the view matric
        camera.update();

        light = new PositionLight(
                new float[] { 0, World.INSTANCE.getCellRelHeight(16), 1},
                new float [] {0.7f, 0.7f, 0.7f, 1.0f},
                new float [] {0.7f, 0.7f, 0.7f, 1.0f},
                new float [] {0.7f, 0.7f, 0.7f, 1.0f}
        );
        light.setViewPosition(camera);
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

        Geometry.INSTANCE.drawBow(camera, light);
        //Log.d(TAG, "render");
    }

    @Override
    public void clean() {

    }
}
