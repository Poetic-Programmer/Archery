package games.mgd.archery.logic.lighting;

import games.mgd.archery.math.c.view.Camera3D;
import games.mgd.archery.math.s.vector.Vector3;

/**
 * Created by Michael on 23/02/2016.
 */
public class PositionLight {
    private float [] position;
    private float [] viewPosition;
    private float [] ambient;
    private float [] diffuse;
    private float [] specular;

    private PositionLight(){
        // no default constructor
    }
    public PositionLight(float [] position, float [] ambient, float [] diffuse,
                         float [] specular){
        viewPosition = new float[3];

        this.position = position;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    public float [] getPosition() { return position; }
    public float [] getViewPosition() { return viewPosition; }
    public float [] getAmbient() { return ambient; }
    public float [] getDiffuse() { return diffuse; }
    public float [] getSpecular() { return specular; }

    public void setPosition(float x, float y, float z){
        position[0] = x;  position[1] = y;  position[2] = z;
    }
    public void setPositin(float [] position){
        setPosition(position[0], position[1], position[2]);
    }
    public void setViewPosition(Camera3D camera){
        Vector3.set(viewPosition, Vector3.multiplyMatrix4(position, camera.getView(), true));
    }
}
