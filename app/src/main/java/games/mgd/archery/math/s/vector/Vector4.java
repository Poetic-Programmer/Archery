package games.mgd.archery.math.s.vector;

/**
 * Created by Michael on 23/07/2016.
 */
public class Vector4 {
    public static float [] create(float x, float y, float z, float w){
        return new float [] {x, y, z, w};
    }

    public static float [] multiply (float [] vector, float [] matrix)
    {
        return create(
                (matrix[0]*vector[0] + matrix[4]*vector[1]
                        + matrix[8]*vector[2] + matrix[12]*vector[3]),

                (matrix[1]*vector[0] + matrix[5]*vector[1]
                        + matrix[9]*vector[2] + matrix[13]*vector[3]),

                (matrix[2]*vector[0] + matrix[6]*vector[1]
                        + matrix[10]*vector[2] + matrix[14]*vector[3]),

                (matrix[3]*vector[0] + matrix[7]*vector[1]
                        + matrix[11]*vector[2] + matrix[15]*vector[3]));
    }
}
