package games.mgd.archery.math.s.matrix;

public class Matrix33
{
    public static void SetTranslation(float [] dst, float [] xlate)
	{	    
	    dst[0] = 1.0f;
	    dst[1] = 0.0f;
	    dst[2] = xlate[0];
	    
	    dst[3] = 0.0f;
	    dst[4] = 1.0f;
	    dst[5] = xlate[1];
	    
	    dst[6] = 0.0f;
	    dst[7] = 0.0f;
	    dst[8] = 1.0f;
	}
    public static void RotationZ(float [] dst, float angle)
	{
	    float sintheta = (float) Math.sin(angle);
	    float costheta = (float) Math.cos(angle);

	    dst[0] = costheta;
	    dst[1] = sintheta;
	    dst[2] = 0.0f;
	    dst[3] = -sintheta;
	    dst[4] = costheta;
	    dst[5] = 0.0f;
	    dst[6] = 0.0f;
	    dst[7] = 0.0f;
	    dst[8] = 1.0f;
	}
    public static void Multiply(float [] dst, float [] mat1, float [] mat2)
	{
	    dst[0] = mat1[0]*mat2[0] + mat1[3]*mat2[1] + mat1[6]*mat2[2];
	    dst[1] = mat1[1]*mat2[0] + mat1[4]*mat2[1] + mat1[7]*mat2[2];
	    dst[2] = mat1[2]*mat2[0] + mat1[5]*mat2[1] + mat1[8]*mat2[2];
	    dst[3] = mat1[0]*mat2[3] + mat1[3]*mat2[4] + mat1[6]*mat2[5];
	    dst[4] = mat1[1]*mat2[3] + mat1[4]*mat2[4] + mat1[7]*mat2[5];
	    dst[5] = mat1[2]*mat2[3] + mat1[5]*mat2[4] + mat1[8]*mat2[5];
	    dst[6] = mat1[0]*mat2[6] + mat1[3]*mat2[7] + mat1[6]*mat2[8];
	    dst[7] = mat1[1]*mat2[6] + mat1[4]*mat2[7] + mat1[7]*mat2[8];
	    dst[8] = mat1[2]*mat2[6] + mat1[5]*mat2[7] + mat1[8]*mat2[8];
	}
    public static void MultiplyVector3(float [] dst, float [] vec, float [] mat) 
	{
    	dst[0] = (mat[0]*vec[0] + mat[1]*vec[1] + mat[2]*vec[2]);
    	dst[1] = (mat[3]*vec[0] + mat[4]*vec[1] + mat[5]*vec[2]);
    	dst[2] = (mat[6]*vec[0] + mat[7]*vec[1] + mat[8]*vec[2]);
	}
}
