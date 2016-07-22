package games.mgd.archery.math.c.curve;

import games.mgd.archery.math.s.vector.Vector3;

/**
 * Created by Michael on 22/07/2016.
 */
public class LinearCurve3D {
    private final float [] start;
    private final float [] end;

    private LinearCurve3D(float x0, float y0, float z0,
                          float x1, float y1, float z1)
    {
        start = Vector3.create(x0, y0, z0);
        end = Vector3.create(x1, y1, z1);
    }

    public static LinearCurve3D createFull(float x0, float y0, float z0, float x1, float y1, float z1){
        return new LinearCurve3D(
                x0, y0, z0, x1, y1, z1);
    }

    public float [] evaluate(float t)
    {
        float x = (1 - t) * start[0] + t * end[0];
        float y = (1 - t) * start[1] + t * end[1];
        float z = (1 - t) * start[2] + t * end[2];
        return Vector3.create(x, y, z);
    }
}
