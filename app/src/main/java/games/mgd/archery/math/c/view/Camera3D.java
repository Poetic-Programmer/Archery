package games.mgd.archery.math.c.view;

import games.mgd.archery.math.s.matrix.Matrix44;
import games.mgd.archery.math.s.vector.Vector3;
import games.mgd.archery.util.MathHelper;

/**
 * Created by Michael Dunleavy on 23/07/2016.
 *
 * This class handles the matrix transformations, from initial set up of the projection matrix
 * to the updating of the view matrix and the calculation of the model-view-projection matrix.
 *
 * This camera keeps both the view and projection as well as the viewProjection matrices. The
 * separate view and projection matrices are more efficient if the camera needs updated
 * regularly, however if the camera needs updated seldomly, then prefer the viewProjection matrix.
 *
 * @ToDo:
 *     - create movement machanics (possibly encapsulated)
 */
public class Camera3D {
    private float [] view;
    private float [] projection;
    private float [] viewProjection;

    private float [] position;
    private float xRotation;
    private float yRotation;

    public float [] getPosition() { return position; }
    public float [] getView () { return view; }
    public float [] getProjection () { return projection; }

    public Camera3D(){
        view = new float[16];
        projection = new float[16];
        position = new float[3];
    }

    public void initialize(float fov, float ratio, float near, float far){
        setProjection(fov, ratio, near, far);
        setView();
    }

    public void setPosition(float [] position){
        Vector3.set(this.position, position);
    }
    public void update(){
        //yRotation++;
        setView();
    }

    private void setView(){
        float [] rotation = Matrix44.createRotation(
                -yRotation * MathHelper.DEGREES_TO_RADIANS,
                -xRotation * MathHelper.DEGREES_TO_RADIANS,
                0);
        float [] translation = Matrix44.createTranslation(position);

        Matrix44.multiply(view, rotation, translation);
    }

    private void setProjection(float fov, float aspectRatio, float near, float far){
        final float angleInRadians = (float) (fov * Math.PI / 180.0);
        final float a = (float) (1.0 / Math.tan(angleInRadians / 2.0));

        projection = Matrix44.create(
                a / aspectRatio, 0, 0, 0,
                0, a, 0, 0,
                0, 0, -((far + near) / (far - near)), -1.0f,
                0, 0, -((2f * far * near) / (far - near)), 0
        );
    }
}
