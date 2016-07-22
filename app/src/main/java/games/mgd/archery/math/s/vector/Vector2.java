package games.mgd.archery.math.s.vector;

import games.mgd.archery.util.MathHelper;

public class Vector2
{
    private Vector2(){
    }

    public static float [] create(final float x, final float y){
        return new float [] {x, y};
    }

	public static void midPoint(float [] dst, float [] vec1, float [] vec2)
	{
		dst[0] = (vec1[0] + vec2[0]) * 0.5f;
		dst[1] = (vec1[1] + vec2[1]) * 0.5f;
	}
	
	public static void zero(float [] dst)
	{
		dst[0] = 0.0f;
		dst[1] = 0.0f;
	}
	public static void set(float [] dst, final float [] src)
	{
		dst[0] = src[0];
		dst[1] = src[1];
	}
	public static void set(float [] dst, final float x, final float y)
	{
		dst[0] = x;
		dst[1] = y;
	}
	public static void clean(float [] src)
	{
		if (MathHelper.isZero(src[0]))
			src[0] = 0.0f;
		if (MathHelper.isZero(src[1]))
			src[1] = 0.0f;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void SetElement(float [] dst, int index, float val)
//	{
//		dst[index] = val;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
	public static float length(final float [] src)
	{
		return (float) Math.sqrt(src[0]*src[0] + src[1]*src[1]);
	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static float lengthSquared(final float [] src)
	{
		return (src[0]*src[0] + src[1]*src[1]);
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static float Distance(float [] p0,  float [] p1 )
//	{
//		float x = p0[0] - p1[0];
//		float y = p0[1] - p1[1];
//
//		return (float) Math.sqrt( x*x + y*y);
//
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
	public static float distanceSquared(final float [] p0,  final float [] p1)
	{
		float x = p0[0] - p1[0];
		float y = p0[1] - p1[1];

		return (x*x + y*y);

	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static boolean IsEqual(float [] p0,  float [] p1)
//	{
//		if (MathHelper.AreEqual(p1[0], p0[0])
//				&& MathHelper.AreEqual(p1[1], p0[1]))
//			return true;
//
//		return false;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static boolean NotEqual(float [] p0,  float [] p1)
//	{
//		if (MathHelper.AreEqual(p1[0], p0[0])
//				&& MathHelper.AreEqual(p1[1], p0[1]))
//			return false;
//
//		return true;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static boolean IsZero(float [] src)
//	{
//		return MathHelper.IsZero(src[0]*src[0] + src[1]*src[1]);
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static boolean IsUnit(float [] src)
//	{
//		return MathHelper.IsZero(1.0f - src[0]*src[0] - src[1]*src[1]);
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void Clean(float [] src)
//	{
//		if (MathHelper.IsZero(src[0]))
//			src[0] = 0.0f;
//		if (MathHelper.IsZero(src[1]))
//			src[1] = 0.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
	public static void normalize(final float [] src)
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
		}
	}

	public static float [] getNormalized(final float [] src)
	{
        float [] buffer = new float[2];
        set(buffer, src);

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
		}
		return buffer;
	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static float [] Add(float [] p0, float [] p1)
//	{
//		m_buffer[0] = p0[0] + p1[0];
//		m_buffer[1] = p0[1] + p1[1];
//
//		return m_buffer;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static void add(float [] dst, final float [] p0, final float [] p1)
	{
		dst[0] = p0[0] + p1[0];	
		dst[1] = p0[1] + p1[1];
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void AddTo(float [] dst, float [] src)
//	{
//		dst[0] += src[0];
//		dst[1] += src[1];
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static float [] getSub(final float [] p0, final float [] p1)
	{
        float [] buffer = new float[2];
		buffer[0] = p0[0] - p1[0];
		buffer[1] = p0[1] - p1[1];
		return buffer;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void SubTo(float [] dst,  float [] src)
//	{
//		dst[0] -= src[0];
//		dst[1] -= src[1];
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void Negate(float [] src)
//	{
//		src[0] = -src[0];
//		src[1] = -src[1];
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
	public static void mul(float [] dst, final float [] src, final float scalar)
	{
		dst[0] = src[0] * scalar;
		dst[1] = src[1] * scalar;
	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static float [] getMul(final float [] p, final float scalar)
	{
        float [] buffer = new float[2];
		buffer[0] = p[0] * scalar;
		buffer[1] = p[1] * scalar;
		return buffer;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
	public static float [] getDiv(final float [] src, final float scalar)
	{
        float [] buffer = new float[2];
		buffer[0] = src[0] / scalar;
		buffer[1] = src[1] / scalar;
		return buffer;
	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
	public static void div(float [] dst, final float [] src, final float scalar)
	{
		dst[0] = src[0] / scalar;
		dst[1] = src[1] / scalar;
	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static float dot(final float [] p0, final float [] p1)
	{
		return (p0[0]*p1[0] + p0[1]*p1[1]);
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void Perpendicular(float [] dst, float [] src)
//	{
//		dst[0] = src[1];
//		dst[1] = -src[0];
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static float [] GetReflect(float [] vector, float [] normal, float coeficient)
//	{
//		float dot = Dot(vector, normal);
//
//		m_buffer[0] = coeficient * (-2 * (dot * normal[0] + vector[0]));
//		m_buffer[1] = coeficient * (-2 * (dot * normal[1] + vector[1]));
//
//		return m_buffer;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static void Reflect(float [] vector, float [] normal, float coeficient)
//	{
//		float dot = Dot(vector, normal);
//
//		vector[0] = coeficient * (-2 * (dot * normal[0] + vector[0]));
//		vector[1] = coeficient * (-2 * (dot * normal[1] + vector[1]));
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static float getAngle(final float [] vec1, final float [] vec2)
	{
		float angle = (float) Math.toDegrees(Math.atan2(vec2[0] - vec1[0], vec2[1] - vec1[1]));



		return angle;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static final float GetAngleX(float [] src)
//	{
//		float angle = (float) Math.toDegrees(Math.atan2(src[0] - X_Axis[0], src[1] - X_Axis[1]));
//
//		return angle;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
	public static void sub(float[] dst, final float[] vec1, final float[] vec2)
	{
		dst[0] = vec1[0] - vec2[0];
		dst[1] = vec1[1] - vec2[1];
	}
}
