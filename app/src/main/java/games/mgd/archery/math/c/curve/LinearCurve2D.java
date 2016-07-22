package games.mgd.archery.math.c.curve;

import games.mgd.archery.math.s.vector.Vector2;

/**
 * Created by Michael on 25/02/2016.
 */
public class LinearCurve2D {
    private final float [] start;
    private final float [] end;

    private LinearCurve2D(float x0, float y0, float x1, float y1)
    {
        start = Vector2.create(x0, y0);
        end = Vector2.create(x1, y1);
    }

    public static LinearCurve2D createFull(float x0, float y0, float x1, float y1){
        return new LinearCurve2D(
                x0, y0, x1, y1);
    }

    public float [] evaluate(float t)
    {
        float x = (1 - t) * start[0] + t * end[0];
        float y = (1 - t) * start[1] + t * end[1];

        return Vector2.create(x, y);
    }
}
