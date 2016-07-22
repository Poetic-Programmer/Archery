package games.mgd.archery.math.c.curve;

import games.mgd.archery.math.s.vector.Vector2;

/**
 * Created by Michael on 25/02/2016.
 */

public class BezierCurve2D {
    private final float [] controlA;
    private final float [] controlB;
    private final float [] controlC;

    private BezierCurve2D(float [] a, float [] b, float [] c)
    {
        controlA = a;
        controlB = b;
        controlC = c;
    }


    public static BezierCurve2D createFull(
            float x0, float y0,
            float x1, float y1,
            float x2, float y2)
    {
        return new BezierCurve2D(
                Vector2.create(x0, y0),
                Vector2.create(x1, y1),
                Vector2.create(x2, y2)
        );
    }

    public float [] evaluate(float t)
    {
        float x = (1-t) * (1-t) * controlA[0] +
                2 * t * (1-t) * controlB[0] +
                t * t * controlC[0];

        float y = (1 - t) * (1 - t) * controlA[1] +
                2 * t * (1 - t) * controlB[1] +
                t * t * controlC[1];

        return Vector2.create(x, y);
    }
}
