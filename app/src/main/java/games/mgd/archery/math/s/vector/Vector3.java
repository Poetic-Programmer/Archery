package games.mgd.archery.math.s.vector;

import android.util.Log;

import games.mgd.archery.math.s.matrix.Matrix44;
import games.mgd.archery.util.MathHelper;



public class Vector3
{
    private Vector3(){
        // Ensure no outside instantiation
    }

	public static final float [] X_AXIS = {1.0f, 0.0f, 0.0f};
	public static final float [] Y_AXIS = {0.0f, 1.0f, 0.0f};
	public static final float [] Z_AXIS = {0.0f, 0.0f,-1.0f};
    public static final float [] Z_AXIS_INV = {0.0f, 0.0f, 1.0f};
    public static float [] create(final float x, final float y, final float z){
        return new float [] {x, y, z};
    }

	public static void zero(float [] src)
	{
		src[0] = 0.0f;
		src[1] = 0.0f;
		src[2] = 0.0f;
	}
	public static void set(float [] dst, final float [] src)
	{
		dst[0] = src[0];
		dst[1] = src[1];
		dst[2] = src[2];
	}
	public static void set(float [] dst, final float x, final float y, final float z)
	{
		dst[0] = x;
		dst[1] = y;
		dst[2] = z;
	}

	public static float [] multiplyMatrix4(float [] vector, float [] matrix, boolean homogenous)
	{
		if(homogenous){
			return create(
					(matrix[0]*vector[0] + matrix[4]*vector[1]
							+ matrix[8]*vector[2] + matrix[12]),

					(matrix[1]*vector[0] + matrix[5]*vector[1]
							+ matrix[9]*vector[2] + matrix[13]),

					(matrix[2]*vector[0] + matrix[6]*vector[1]
							+ matrix[10]*vector[2] + matrix[14]));
		}
		else{
			// @ToDo: remove
			Log.d("Vector3", "no need for this matrix mutiplication");
			return create(0, 0, 0);
		}
	}
	public static void setElement(float [] dst, final int index, final float val)
	{
		dst[index] = val;
	}
	public static float length(final float [] src)
	{
		return (float) Math.sqrt(src[0]*src[0] + src[1]*src[1] + src[2]*src[2]);
	}
	public static float lengthSquared(final float [] src)
	{
		return (src[0]*src[0] + src[1]*src[1] + src[2]*src[2]);
	}
	public static float distance(final float [] p0,  final float [] p1 )
	{
		float x = p0[0] - p1[0];
		float y = p0[1] - p1[1];
		float z = p0[2] - p1[2];

		return (float) Math.sqrt( x*x + y*y + z*z );

	}
	public static float distanceSquared(final float [] p0, final float [] p1 )
	{
		float x = p0[0] - p1[0];
		float y = p0[1] - p1[1];
		float z = p0[2] - p1[2];

		return ( x*x + y*y + z*z );
	}
	public static boolean isEqual(final float [] p0, final float [] p1)
	{
		if (MathHelper.areEqual(p1[0], p0[0])
				&& MathHelper.areEqual(p1[1], p0[1])
				&& MathHelper.areEqual(p1[2], p0[2]))
			return true;

		return false;   
	}
	public static boolean notEqual(final float [] p0, final float [] p1)
	{
		if (MathHelper.areEqual(p1[0], p0[0])
				&& MathHelper.areEqual(p1[1], p0[1])
				&& MathHelper.areEqual(p1[2], p0[2]))
			return false;

		return true;   
	}
	public static boolean isZero(final float [] src)
	{
		return MathHelper.isZero(src[0]*src[0] + src[1]*src[1] + src[2]*src[2]);
	}
	public static boolean isUnit(final float [] src)
	{
		return MathHelper.isZero(1.0f - src[0]*src[0] - src[1]*src[1] - src[2]*src[2]);
	}
	public static void clean(float [] src)
	{
		if (MathHelper.isZero(src[0]))
			src[0] = 0.0f;
		if (MathHelper.isZero(src[1]))
			src[1] = 0.0f;
		if (MathHelper.isZero(src[2]))
			src[2] = 0.0f;
	}
	public static void normalize(float [] src)
	{
		float lengthsq = lengthSquared(src);

		if (MathHelper.isZero( lengthsq ) )
		{
			zero(src);
		}
		else
		{
			float factor = MathHelper.inverseSquareRoot( lengthsq );
			src[0] *= factor;
			src[1] *= factor;
			src[2] *= factor;
		}
	}
    public static float [] getNormalized(final float [] src)
    {
        float [] buffer = new float[3];
        set(buffer ,src);

        float lengthsq = lengthSquared(buffer);

        if (MathHelper.isZero( lengthsq ) )
        {
            zero(buffer);
        }
        else
        {
            float factor = MathHelper.inverseSquareRoot( lengthsq );
            buffer[0] *= factor;
            buffer[1] *= factor;
            buffer[2] *= factor;
        }

        return buffer;
    }
	public static void add(float [] dst, final float [] p0, final float [] p1)
	{
		dst[0] = p0[0] + p1[0];	
		dst[1] = p0[1] + p1[1];
		dst[2] = p0[2] + p1[2];
	}
	public static void sub(float [] dst, final float [] p0, final float [] p1)
	{
		dst[0] = p0[0] - p1[0];
		dst[1] = p0[1] - p1[1];
		dst[2] = p0[2] - p1[2];
	}
	public static float [] getSub(final float [] p0, final float [] p1)
	{
        float [] buffer = new float[3];
		buffer[0] = p0[0] - p1[0];
        buffer[1] = p0[1] - p1[1];
        buffer[2] = p0[2] - p1[2];
		return buffer;
	}
	public static void mul(float [] dst, final float [] src, final float scalar)
	{
		dst[0] = src[0] * scalar;
		dst[1] = src[1] * scalar;
		dst[2] = src[2] * scalar;
	}
	public static float [] mul(float [] src, final float scalar)
	{
		float [] temp = {0,0,0};
		temp[0] = src[0] * scalar;
		temp[1] = src[1] * scalar;
		temp[2] = src[2] * scalar;
		return temp;
	}
	public static float [] div(final float [] src, final float scalar)
	{
		float [] temp = {0,0,0};
		temp[0] = src[0] / scalar;
		temp[1] = src[1] / scalar;
		temp[2] = src[2] / scalar;
		return temp;
	}
	public static void div(float [] dst, final float [] src, final float scalar)
	{
		dst[0] = src[0] / scalar;
		dst[1] = src[1] / scalar;
		dst[2] = src[2] / scalar;
	}
	public static float dot(final float [] p0, final float [] p1)
	{
		return (p0[0]*p1[0] + p0[1]*p1[1] + p0[2]*p1[2]);
	}

	public static void cross(float [] dst, final float [] p0, final float [] p1)
	{
		dst[0] = p0[1] * p1[2] - p0[2] * p1[1];
		dst[1] = p0[2] * p1[0] - p0[0] * p1[2];
		dst[2] = p0[0] * p1[1] - p0[1] * p1[0];
	}

    public static float [] getCross(final float [] p0, final float [] p1)
    {
        float [] buffer = new float[3];

        buffer[0] = p0[1] * p1[2] - p0[2] * p1[1];
        buffer[1] = p0[2] * p1[0] - p0[0] * p1[2];
        buffer[2] = p0[0] * p1[1] - p0[1] * p1[0];

        return buffer;
    }

	public static void rotate(float [] dst, final float [] src, final float angle,
							  final float axisX, final float axisY, final float axisZ)
	{
		float [] temp = {src[0], src[1], src[2], 1.0f};
		float [] mat = new float[16];
		Matrix44.identity(mat);
		Matrix44.SetRotation(mat, axisX * angle, axisY * angle, axisZ * angle);
		Matrix44.MultiplyVector(temp, temp, mat);
		dst[0] = temp[0];
		dst[1] = temp[1];
		dst[2] = temp[2];
	}

	public static void add(float[] dst, final float[] src, final float x,
                           final float y, final float z)
	{
		dst[0] = src[0] + x;
		dst[1] = src[1] + x;
		dst[2] = src[2] + x;
	}
	public static void midPoint(float [] dst, final float [] vec1, final float [] vec2)
	{
		dst[0] = (vec1[0] + vec2[0]) * 0.5f;
		dst[1] = (vec1[1] + vec2[1]) * 0.5f;
		dst[2] = (vec1[2] + vec2[2]) * 0.5f;
	}
}
