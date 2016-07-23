package games.mgd.archery.logic.geometry;

import android.content.Context;

import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.logic.lighting.Material;
import games.mgd.archery.math.s.matrix.Matrix44;

/**
 * Created by Michael on 23/07/2016.
 */
public class GeoBow {
    private Structure structure;
    private Material material;

    private float [] modelMatrix;

    public Structure getGeometry() { return structure; }
    public Material getMaterial() { return material; }
    public float [] getModelMatrix() { return modelMatrix; }

    public GeoBow(){
        material = new Material(
                new float [] {0.7f, 0.7f, 0.7f, 1.0f},
                new float [] {1.0f, 1.0f, 1.0f, 1.0f},
                new float [] {0.7f, 0.7f, 0.7f, 1.0f},
                0.01f
        );

        modelMatrix = Matrix44.createIdentity();
    }

    public void setStructure(Structure structure){
        // Structure is created by a geometry factory in the Geometry enum
        this.structure = structure;
    }

    public void setModelMatrix(float x, float y, float z){
        modelMatrix[12] = x; modelMatrix[13] = y; modelMatrix[14] = z;
    }
    public void setModelMatrix(float [] position){
        setModelMatrix(position[0], position[1], position[2]);
    }

    public void draw(){
        structure.draw();
    }
}
