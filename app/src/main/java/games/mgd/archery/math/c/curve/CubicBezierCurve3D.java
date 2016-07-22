package games.mgd.archery.math.c.curve;

import games.mgd.archery.math.s.vector.Vector3;

/**
 * Created by Michael on 27/07/2016.
 */

public class CubicBezierCurve3D
{
    private final float [] controlA;
    private final float [] controlB;
    private final float [] controlC;
    private final float [] controlD;

    private CubicBezierCurve3D(
            float aX, float aY, float aZ,
            float bX, float bY, float bZ,
            float cX, float cY, float cZ,
            float dX, float dY, float dZ)
    {
        controlA = Vector3.create(aX, aY, aZ);
        controlB = Vector3.create(bX, bY, bZ);
        controlC = Vector3.create(cX, cY, cZ);
        controlD = Vector3.create(dX, dY, dZ);
    }

    public static CubicBezierCurve3D createFull(
            float aX, float aY, float aZ,
            float bX, float bY, float bZ,
            float cX, float cY, float cZ,
            float dX, float dY, float dZ)
    {
        return new CubicBezierCurve3D(
                aX, aY, aZ,
                bX, bY, bZ,
                cX, cY, cZ,
                dX, dY, dZ
        );
    }

    public static CubicBezierCurve3D createWith(
            float [] cp0, float [] cp1, float [] cp2, float [] cp3)
    {
        return new CubicBezierCurve3D(
                cp0[0], cp0[1], cp0[2],
                cp1[0], cp1[1], cp1[2],
                cp2[0], cp2[1], cp2[2],
                cp3[0], cp3[1], cp3[2]
        );
    }

    public float [] evaluate(float t)
    {
        return Vector3.create(
                (((1-t)*(1-t)*(1-t)) * controlA[0]   +
                        3 * t * ((1-t)*(1-t)) * controlB[0] +
                        3 * (t*t)*(1-t) * controlC[0]       +
                        (t*t*t) * controlD[0]),

                (((1-t)*(1-t)*(1-t)) * controlA[1]   +
                        3 * t * ((1-t)*(1-t)) * controlB[1] +
                        3 * (t*t)*(1-t) * controlC[1]       +
                        (t*t*t) * controlD[1]),

                (((1-t)*(1-t)*(1-t)) * controlA[2]   +
                        3 * t * ((1-t)*(1-t)) * controlB[2] +
                        3 * (t*t)*(1-t) * controlC[2]       +
                        (t*t*t) * controlD[2])
        );
    }
}
