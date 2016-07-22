package games.mgd.archery.util;


public class MathHelper
{
	private static final float Epsilon = 1.0e-6f;
	public static final float DEGREES_TO_RADIANS = 0.017453292f;
	public static final float RADIANS_TO_DEGREES = 57.29577951f;
	public static final float PI = 3.141592654f;
	// --Commented out by Inspection (25/08/2014 03:28):public static final float HALF_PI = 1.570796327f;
	public static final float TWO_PI =  6.283185307f;
	// --Commented out by Inspection (25/08/2014 03:28):public static final float FLT_MAX = 1.0e+37f;
	// --Commented out by Inspection (25/08/2014 03:28):public static final float FLT_MIN = 1.0e-37f;
	
	public static float clean(float val)
	{
		float cpy = val;
		//("MathHelper", "startVal: " + val);
		
		if(isZero(val))
		{
			//("MathHelper", "MODIFY");
			cpy = 0;
		}
		
		//("MathHelper", "endVal: " + val);
		return cpy;
	}
	
	public static float sqrt(float val)
	{
		return (float) Math.sqrt(val);
	}
	
	public static boolean isZero(float x)
	{
		return(Math.abs(x) < Epsilon);
	}
	
	public static boolean areEqual(float a, float b)
	{
		return(isZero(a - b));
	}
	
	public static float inverseSquareRoot(float val)
	{
		return (float) (1.0f / Math.sqrt(val));
	}
	
// --Commented out by Inspection START (25/08/2014 03:28):
//	public static float Abs(float f)
//	{
//		return Math.abs(f);
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
}
