package games.mgd.archery.math.s.matrix;

public class Matrix44
{
	//private static float [] m_buffer = new float[16];
	//private static float [] m_v4Buffer = new float[4];
	//private static float [] m_v3Buffer = new float[3];
    
    public static void Set(float [] dst, float [] src) 
    { 
    	for(int i = 0; i < 16; ++i)
    		dst[i] = src[i];
    }
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static float GetElement(float [] src, int index)
//    {
//    	return src[index];
//    }
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static float GetElement(float [] src, int row, int col)
//    {
//    	return src[row * 4 + col];
//    }
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetElement(float [] dst, int index, float val)
//    {
//    	dst[index] = val;
//    }
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetElement(float [] dst, int col, int row, float val)
//    {
//    	dst[row * 4 + col] = val;
//    }
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static boolean IsEqual(float [] mat1, float [] mat2)
//	{
//	    for (int i = 0; i < 16; ++i)
//	    {
//	        if ( !MathHelper.AreEqual(mat1[i], mat2[i]) )
//	            return false;
//	    }
//	    return true;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static boolean NotEqual(float [] mat1, float [] mat2)
//	{
//	    for (int i = 0; i < 16; ++i)
//	    {
//	        if ( !MathHelper.AreEqual(mat1[i], mat2[i]) )
//	            return true;
//	    }
//	    return false;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static boolean IsZero(float [] src)
//	{
//	    for (int i = 0; i < 16; ++i)
//	    {
//	        if (!MathHelper.IsZero(src[i]))
//	            return false;
//	    }
//	    return true;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static boolean IsIdentity(float [] src)
//	{
//	    return  MathHelper.AreEqual(1.0f, src[0])
//	    	&&	MathHelper.AreEqual(1.0f, src[5])
//	        &&  MathHelper.AreEqual(1.0f, src[10])
//	        &&  MathHelper.AreEqual(1.0f, src[15])
//	        &&  MathHelper.IsZero(src[1])
//	        &&  MathHelper.IsZero(src[2])
//	        &&  MathHelper.IsZero(src[3])
//	        &&  MathHelper.IsZero(src[4])
//	        &&  MathHelper.IsZero(src[6])
//	        &&  MathHelper.IsZero(src[7])
//	        &&  MathHelper.IsZero(src[8])
//	        &&  MathHelper.IsZero(src[9])
//	        &&  MathHelper.IsZero(src[11])
//	        &&  MathHelper.IsZero(src[12])
//	        &&  MathHelper.IsZero(src[13])
//	        &&  MathHelper.IsZero(src[14]);
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void Clean(float [] dst)
//	{
//	    for (int i = 0; i < 16; ++i)
//	    {
//	        if(MathHelper.IsZero(dst[i]))
//	        	dst[i] = 0.0f;
//	    }
//
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
    public static void Identity(float [] dst)
	{
    	dst[0] = 1.0f;
    	dst[1] = 0.0f;
    	dst[2] = 0.0f;
    	dst[3] = 0.0f;
    	dst[4] = 0.0f;
    	dst[5] = 1.0f;
    	dst[6] = 0.0f;
    	dst[7] = 0.0f;
    	dst[8] = 0.0f;
    	dst[9] = 0.0f;
    	dst[10] = 1.0f;
    	dst[11] = 0.0f;
    	dst[12] = 0.0f;
	    dst[13] = 0.0f;
	    dst[14] = 0.0f;
	    dst[15] = 1.0f;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void Zero(float [] dst)
//	{
//    	dst[0] = 0.0f;
//    	dst[1] = 0.0f;
//    	dst[2] = 0.0f;
//    	dst[3] = 0.0f;
//    	dst[4] = 0.0f;
//    	dst[5] = 0.0f;
//    	dst[6] = 0.0f;
//    	dst[7] = 0.0f;
//    	dst[8] = 0.0f;
//    	dst[9] = 0.0f;
//    	dst[10] = 0.0f;
//    	dst[11] = 0.0f;
//    	dst[12] = 0.0f;
//	    dst[13] = 0.0f;
//	    dst[14] = 0.0f;
//	    dst[15] = 0.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void AffineInverse(float [] dst, float [] src)
//	{
//	    // compute upper left 3x3 matrix determinant
//	    float cofactor0 = src[5]*src[10] - src[6]*src[9];
//	    float cofactor4 = src[2]*src[9] - src[1]*src[10];
//	    float cofactor8 = src[1]*src[6] - src[2]*src[5];
//	    float det = src[0]*cofactor0 + src[4]*cofactor4 + src[8]*cofactor8;
//	    if (MathHelper.IsZero(det))
//	    {
//	        Set(dst, src);
//	    }
//	    // create adjunct matrix and multiply by 1/det to get upper 3x3
//	    float invDet = 1.0f/det;
//	    dst[0] = invDet*cofactor0;
//	    dst[1] = invDet*cofactor4;
//	    dst[2] = invDet*cofactor8;
//
//	    dst[4] = invDet*(src[6]*src[8] - src[4]*src[10]);
//	    dst[5] = invDet*(src[0]*src[10] - src[2]*src[8]);
//	    dst[6] = invDet*(src[2]*src[4] - src[0]*src[6]);
//
//	    dst[8] = invDet*(src[4]*src[9] - src[5]*src[8]);
//	    dst[9] = invDet*(src[1]*src[8] - src[0]*src[9]);
//	    dst[10] = invDet*(src[0]*src[5] - src[1]*src[4]);
//
//	    // multiply -translation by inverted 3x3 to get its inverse
//	    dst[12] = -src[0]*src[12] - src[4]*src[13] - src[8]*src[14];
//	    dst[13] = -src[1]*src[12] - src[5]*src[13] - src[9]*src[14];
//	    dst[14] = -src[2]*src[12] - src[6]*src[13] - src[10]*src[14];
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
   
    public static void SetTranslation(float [] dst, float [] xlate)
	{
    	
    	dst[0] = 1.0f;
    	dst[1] = 0.0f;
	    dst[2] = 0.0f;
	    dst[3] = 0.0f;
	    
	    dst[4] = 0.0f;
	    dst[5] = 1.0f;
	    dst[6] = 0.0f;
	    dst[7] = 0.0f;
	    
	    dst[8] = 0.0f;
	    dst[9] = 0.0f;
	    dst[10] = 1.0f;
	    dst[11] = 0.0f;
	    
	    dst[12] = xlate[0];
	    dst[13] = xlate[1];
	    dst[14] = xlate[2];
	    dst[15] = 1.0f;
	}
	public static void SetTranslation(float[] dst, float x, float y,
			float z) 
	{
    	dst[0] = 1.0f;
    	dst[1] = 0.0f;
	    dst[2] = 0.0f;
	    dst[3] = 0.0f;
	    
	    dst[4] = 0.0f;
	    dst[5] = 1.0f;
	    dst[6] = 0.0f;
	    dst[7] = 0.0f;
	    
	    dst[8] = 0.0f;
	    dst[9] = 0.0f;
	    dst[10] = 1.0f;
	    dst[11] = 0.0f;
	    
	    dst[12] = x;
	    dst[13] = y;
	    dst[14] = z;
	    dst[15] = 1.0f;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetQuaternionRotation(float [] dst, float [] rotate)
//	{
//	    float xs, ys, zs, wx, wy, wz, xx, xy, xz, yy, yz, zz;
//
//	    xs = rotate[1]+rotate[1];
//	    ys = rotate[2]+rotate[2];
//	    zs = rotate[3]+rotate[3];
//	    wx = rotate[0]*xs;
//	    wy = rotate[0]*ys;
//	    wz = rotate[0]*zs;
//	    xx = rotate[1]*xs;
//	    xy = rotate[1]*ys;
//	    xz = rotate[1]*zs;
//	    yy = rotate[2]*ys;
//	    yz = rotate[2]*zs;
//	    zz = rotate[3]*zs;
//
//
//	    dst[0] = 1.0f - (yy + zz);
//	    dst[4] = xy - wz;
//	    dst[8] = xz + wy;
//	    dst[12] = 0.0f;
//
//	    dst[1] = xy + wz;
//	    dst[5] = 1.0f - (xx + zz);
//	    dst[9] = yz - wx;
//	    dst[13] = 0.0f;
//
//	    dst[2] = xz - wy;
//	    dst[6] = yz + wx;
//	    dst[10] = 1.0f - (xx + yy);
//	    dst[14] = 0.0f;
//
//	    dst[3] = 0.0f;
//	    dst[7] = 0.0f;
//	    dst[11] = 0.0f;
//	    dst[15] = 1.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetMatrix3Rotation(float [] dst, float [] mat3)
//	{
//    	dst[0] = mat3[0];
//    	dst[1] = mat3[1];
//    	dst[2] = mat3[2];
//    	dst[3] = 0;
//    	dst[4] = mat3[3];
//    	dst[5] = mat3[4];
//    	dst[6] = mat3[5];
//    	dst[7] = 0;
//    	dst[8] = mat3[6];
//    	dst[9] = mat3[7];
//    	dst[10] = mat3[8];
//    	dst[11] = 0;
//    	dst[12] = 0;
//    	dst[13] = 0;
//    	dst[14] = 0;
//    	dst[15] = 1;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
    public static void SetRotation(float [] dst, float zRotation, float yRotation, float xRotation)
	{
	    // This is an "unrolled" contatenation of rotation matrices X Y  Z
	    float Sx = (float) Math.sin(xRotation);
	    float Cx = (float) Math.cos(xRotation);
	    
	    float Sy = (float) Math.sin(yRotation);
	    float Cy = (float) Math.cos(yRotation);

	    float Sz = (float) Math.sin(zRotation);
	    float Cz = (float) Math.cos(zRotation);

	    dst[0] =  (Cy * Cz);
	    dst[4] = -(Cy * Sz);  
	    dst[8] =  Sy;
	    dst[12] = 0.0f;

	    dst[1] =  (Sx * Sy * Cz) + (Cx * Sz);
	    dst[5] = -(Sx * Sy * Sz) + (Cx * Cz);
	    dst[9] = -(Sx * Cy); 
	    dst[13] = 0.0f;

	    dst[2] = -(Cx * Sy * Cz) + (Sx * Sz);
	    dst[6] =  (Cx * Sy * Sz) + (Sx * Cz);
	    dst[10] =  (Cx * Cy);
	    dst[14] = 0.0f;

	    dst[3] = 0.0f;
	    dst[7] = 0.0f;
	    dst[11] = 0.0f;
	    dst[15] = 1.0f;
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetAxisRotation(float [] dst, float [] axis, float angle)
//	{
//	    float c = (float) Math.cos(angle);
//	    float s = (float) Math.sin(angle);
//	    float t = 1.0f - c;
//
//	    float [] nAxis = {0,0,0};
//	    S_Vector3.Set(nAxis, axis);
//	    S_Vector3.Normalize(nAxis);
//
//	    // intermediate values
//	    float tx = t*nAxis[0];  float ty = t*nAxis[1];  float tz = t*nAxis[2];
//	    float sx = s*nAxis[0];  float sy = s*nAxis[1];  float sz = s*nAxis[2];
//	    float txy = tx*nAxis[1]; float tyz = tx*nAxis[2]; float txz = tx*nAxis[2];
//
//	    // set matrix
//	    dst[0] = tx*nAxis[0] + c;
//	    dst[4] = txy - sz;
//	    dst[8] = txz + sy;
//	    dst[12] = 0.0f;
//
//	    dst[1] = txy + sz;
//	    dst[5] = ty*nAxis[1] + c;
//	    dst[9] = tyz - sx;
//	    dst[13] = 0.0f;
//
//	    dst[2] = txz - sy;
//	    dst[6] = tyz + sx;
//	    dst[10] = tz*nAxis[2] + c;
//	    dst[14] = 0.0f;
//
//	    dst[3] = 0.0f;
//	    dst[7] = 0.0f;
//	    dst[11] = 0.0f;
//	    dst[15] = 1.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetScaling(float [] dst, float [] scaleFactors)
//	{
//    	dst[0] = scaleFactors[0];
//    	dst[1] = 0.0f;
//    	dst[2] = 0.0f;
//    	dst[3] = 0.0f;
//    	dst[4] = 0.0f;
//    	dst[5] = scaleFactors[1];
//    	dst[6] = 0.0f;
//    	dst[7] = 0.0f;
//    	dst[8] = 0.0f;
//    	dst[9] = 0.0f;
//    	dst[10] = scaleFactors[2];
//    	dst[11] = 0.0f;
//    	dst[12] = 0.0f;
//    	dst[13] = 0.0f;
//    	dst[14] = 0.0f;
//	    dst[15] = 1.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetRotationX(float [] dst, float angle)
//	{
//	    float sintheta = (float) Math.cos(angle);
//	    float costheta = (float) Math.sin(angle);
//
//	    dst[0] = 1.0f;
//	    dst[1] = 0.0f;
//	    dst[2] = 0.0f;
//	    dst[3] = 0.0f;
//	    dst[4] = 0.0f;
//	    dst[5] = costheta;
//	    dst[6] = sintheta;
//	    dst[7] = 0.0f;
//	    dst[8] = 0.0f;
//	    dst[9] = -sintheta;
//	    dst[10] = costheta;
//	    dst[11] = 0.0f;
//	    dst[12] = 0.0f;
//	    dst[13] = 0.0f;
//	    dst[14] = 0.0f;
//	    dst[15] = 1.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetRotationY(float [] dst, float angle )
//	{
//	    float sintheta = (float) Math.cos(angle);
//	    float costheta = (float) Math.sin(angle);
//
//	    sintheta = (float) Math.sin(angle);
//	    costheta = (float) Math.cos(angle);
//
//	    dst[0] = costheta;
//	    dst[1] = 0.0f;
//	    dst[2] = -sintheta;
//	    dst[3] = 0.0f;
//	    dst[4] = 0.0f;
//	    dst[5] = 1.0f;
//	    dst[6] = 0.0f;
//	    dst[7] = 0.0f;
//	    dst[8] = sintheta;
//	    dst[9] = 0.0f;
//	    dst[10] = costheta;
//	    dst[11] = 0.0f;
//	    dst[12] = 0.0f;
//	    dst[13] = 0.0f;
//	    dst[14] = 0.0f;
//	    dst[15] = 1.0f;
//
//	    Transpose(dst, dst);
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void SetRotationZ(float [] dst, float angle)
//	{
//	    float sintheta = (float) Math.cos(angle);
//	    float costheta = (float) Math.sin(angle);
//
//	    dst[0] = costheta;
//	    dst[1] = sintheta;
//	    dst[2] = 0.0f;
//	    dst[3] = 0.0f;
//	    dst[4] = -sintheta;
//	    dst[5] = costheta;
//	    dst[6] = 0.0f;
//	    dst[7] = 0.0f;
//	    dst[8] = 0.0f;
//	    dst[9] = 0.0f;
//	    dst[10] = 1.0f;
//	    dst[11] = 0.0f;
//	    dst[12] = 0.0f;
//	    dst[13] = 0.0f;
//	    dst[14] = 0.0f;
//	    dst[15] = 1.0f;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    /*
//    public static void GetFixedAngles(float zRotation, float yRotation, float xRotation)
//	{
//	    float Cx = 0.0f;
//	    float Sx = 0.0f;
//	    float Cy = 0.0f;
//	    float Sy = 0.0f;
//	    float Cz = 0.0f;
//	    float Sz = 0.0f;
//
//	    Sy = m_matrix[8];
//	    Cy =  MGDMathHelper.Sqrt( 1.0f - Sy*Sy );
//	    // normal case
//	    if ( !MGDMathHelper.IsZero( Cy ) )
//	    {
//	        float factor = 1.0f/Cy;
//	        Sx = -m_matrix[9]*factor;
//	        Cx = m_matrix[10]*factor;
//	        Sz = -m_matrix[4]*factor;
//	        Cz = m_matrix[0]*factor;
//	    }
//	    // x and z axes aligned
//	    else
//	    {
//	        Sz = 0.0f;
//	        Cz = 1.0f;
//	        Sx = m_matrix[6];
//	        Cx = m_matrix[5];
//	    }
//
//	    zRotation = (float) Math.atan2( Sz, Cz );
//	    yRotation = (float) Math.atan2( Sy, Cy );
//	    xRotation = (float) Math.atan2( Sx, Cx );
//	}
//
//    public static void GetAxisAngle(float [] axis, float [] mat44, float angle)
//	{
//	    float trace = mat44[0] + mat44[5] + mat44[10];
//	    float cosTheta = 0.5f*(trace - 1.0f);
//	    angle = (float)Math.acos( cosTheta );
//
//	    // angle is zero, axis can be anything
//	    if (MathHelper.IsZero(angle))
//	    {
//	        axis = S_Vector3.X_Axis;
//	    }
//	    // standard case
//	    else if ( angle < MathHelper.PI - MathHelper.Epsilon )
//	    {
//	        S_Vector3.Set(axis, mat44[6]-mat44[9], mat44[8]-mat44[2], mat44[1]-mat44[4]);
//	        S_Vector3.Normalize(axis);
//	    }
//	    // angle is 180 degrees
//	    else
//	    {
//	        int i = 0;
//	        if ( mat44[5] > mat44[0] )
//	            i = 1;
//	        if ( mat44[10] > mat44[i + 4*i] )
//	            i = 2;
//	        int j = (i+1)%3;
//	        int k = (j+1)%3;
//	        float s =  MathHelper.Sqrt(mat44[i + 4*i] - mat44[j + 4*j] - mat44[k + 4*k] + 1.0f);
//	        S_Vector3.SetElement(axis, i, 0.5f * s);
//	        float recip = 1.0f/s;
//	        S_Vector3.SetElement(axis, j, mat44[i + 4 * j] * recip);
//	        S_Vector3.SetElement(axis, k, mat44[k + 4 * i] * recip);
//	    }
//	}
//	*/
//    public static void Add(float [] dst, float [] mat1, float [] mat2)
//	{
//	    for(int i = 0; i < 16; ++i)
//	    {
//	        dst[i] = mat1[i] + mat2[i];
//	    }
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void Sub(float [] dst, float [] mat1, float [] mat2)
//	{
//	    for(int i = 0; i < 16; ++i)
//	    {
//	        dst[i] = mat1[i] - mat2[i];
//	    }
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void Negate(float [] mat)
//	{
//	    for (int i = 0; i < 16; ++i)
//	    {
//	    	mat[i] = -mat[i];
//	    }
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
    public static void MultiplyMatrix44(float [] dst, float [] src, float [] mat44)
	{
    	dst[0] = src[0]*mat44[0] + src[4]*mat44[1] + src[8]*mat44[2] 
	                    + src[12]*mat44[3];
    	dst[1] = src[1]*mat44[0] + src[5]*mat44[1] + src[9]*mat44[2] 
	                    + src[13]*mat44[3];
    	dst[2] = src[2]*mat44[0] + src[6]*mat44[1] + src[10]*mat44[2] 
	                    + src[14]*mat44[3];
    	dst[3] = src[3]*mat44[0] + src[7]*mat44[1] + src[11]*mat44[2] 
	                    + src[15]*mat44[3];

    	dst[4] = src[0]*mat44[4] + src[4]*mat44[5] + src[8]*mat44[6] 
	                    + src[12]*mat44[7];
    	dst[5] = src[1]*mat44[4] + src[5]*mat44[5] + src[9]*mat44[6] 
	                    + src[13]*mat44[7];
    	dst[6] = src[2]*mat44[4] + src[6]*mat44[5] + src[10]*mat44[6] 
	                    + src[14]*mat44[7];
    	dst[7] = src[3]*mat44[4] + src[7]*mat44[5] + src[11]*mat44[6] 
	                    + src[15]*mat44[7];

    	dst[8] = src[0]*mat44[8] + src[4]*mat44[9] + src[8]*mat44[10] 
	                    + src[12]*mat44[11];
    	dst[9] = src[1]*mat44[8] + src[5]*mat44[9] + src[9]*mat44[10] 
	                    + src[13]*mat44[11];
    	dst[10] = src[2]*mat44[8] + src[6]*mat44[9] + src[10]*mat44[10] 
	                    + src[14]*mat44[11];
    	dst[11] = src[3]*mat44[8] + src[7]*mat44[9] + src[11]*mat44[10] 
	                    + src[15]*mat44[11];

    	dst[12] = src[0]*mat44[12] + src[4]*mat44[13] + src[8]*mat44[14] 
	                    + src[12]*mat44[15];
    	dst[13] = src[1]*mat44[12] + src[5]*mat44[13] + src[9]*mat44[14] 
	                    + src[13]*mat44[15];
    	dst[14] = src[2]*mat44[12] + src[6]*mat44[13] + src[10]*mat44[14] 
	                    + src[14]*mat44[15];
    	dst[15] = src[3]*mat44[12] + src[7]*mat44[13] + src[11]*mat44[14] 
	                    + src[15]*mat44[15];
	}
    public static void MultiplyVector(float[] dst, float [] vector, float [] mat44) 
	{
    	/*
    	dst[0] = (mat44[0]*vector[0] + mat44[1]*vector[1] 
    			+ mat44[2]*vector[2] + mat44[3]*vector[3]);
    	dst[1] = (mat44[4]*vector[0] + mat44[5]*vector[1]
    			+ mat44[6]*vector[2] + mat44[7]*vector[3]);
    	dst[2] = (mat44[8]*vector[0] + mat44[9]*vector[1] 
    			+ mat44[10]*vector[2] + mat44[11]*vector[3]);
    	dst[3] = (mat44[12]*vector[0] + mat44[13]*vector[1] 
    			+ mat44[14]*vector[2] + mat44[15]*vector[3]); 
    	*/
    	dst[0] = (mat44[0]*vector[0] + mat44[4]*vector[1] 
    			+ mat44[8]*vector[2] + mat44[12]*vector[3]);
    	dst[1] = (mat44[1]*vector[0] + mat44[5]*vector[1]
    			+ mat44[9]*vector[2] + mat44[13]*vector[3]);
    	dst[2] = (mat44[2]*vector[0] + mat44[6]*vector[1] 
    			+ mat44[10]*vector[2] + mat44[14]*vector[3]);
    	dst[3] = (mat44[3]*vector[0] + mat44[7]*vector[1] 
    			+ mat44[11]*vector[2] + mat44[15]*vector[3]); 
	}
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void MultiplyTo(float [] src, float scalar)
//	{
//    	src[0] *= scalar;
//    	src[1] *= scalar;
//    	src[2] *= scalar;
//    	src[3] *= scalar;
//    	src[4] *= scalar;
//    	src[5] *= scalar;
//    	src[6] *= scalar;
//    	src[7] *= scalar;
//    	src[8] *= scalar;
//    	src[9] *= scalar;
//    	src[10] *= scalar;
//    	src[11] *= scalar;
//    	src[12] *= scalar;
//    	src[13] *= scalar;
//    	src[14] *= scalar;
//    	src[15] *= scalar;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static float [] Multiply(float [] mat44, float scalar)
//	{
//    	float [] temp = new float[16];
//    	temp[0] = mat44[0] * scalar;
//    	temp[1] = mat44[1] * scalar;
//    	temp[2] = mat44[2] * scalar;
//    	temp[3] = mat44[3] * scalar;
//    	temp[4] = mat44[4] * scalar;
//    	temp[5] = mat44[5] * scalar;
//    	temp[6] = mat44[6] * scalar;
//    	temp[7] = mat44[7] * scalar;
//    	temp[8] = mat44[8] * scalar;
//    	temp[9] = mat44[9] * scalar;
//    	temp[10] = mat44[10] * scalar;
//    	temp[11] = mat44[11] * scalar;
//    	temp[12] = mat44[12] * scalar;
//    	temp[13] = mat44[13] * scalar;
//    	temp[14] = mat44[14] * scalar;
//    	temp[15] = mat44[15] * scalar;
//
//	    return temp;
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)
// --Commented out by Inspection START (25/08/2014 03:28):
//    public static void TransformPoint(float [] dst, float [] vector3, float [] mat44)
//	{
//    	float [] temp = {0,0,0};
//    	temp = TransformPoint(vector3, mat44);
//    	dst[0] = temp[0];
//    	dst[1] = temp[1];
//    	dst[2] = temp[2];
//	}
// --Commented out by Inspection STOP (25/08/2014 03:28)

    /*
    public static void PrintRowMajor(String tag, float [] mat)
    {
    	(tag, "" + mat[0] + ", " + mat[1] + ", " + mat[2] + ", " + mat[3]);
    	(tag, "" + mat[4] + ", " + mat[5] + ", " + mat[6] + ", " + mat[7]);
    	(tag, "" + mat[8] + ", " + mat[9] + ", " + mat[10] + ", " + mat[11]);
    	(tag, "" + mat[12] + ", " + mat[13] + ", " + mat[14] + ", " + mat[15]);
    }
    public static void PrintColMajor(String tag, float [] mat)
    {
    	(tag, "" + mat[0] + ", " + mat[4] + ", " + mat[8] + ", " + mat[12]);
    	(tag, "" + mat[1] + ", " + mat[5] + ", " + mat[9] + ", " + mat[13]);
    	(tag, "" + mat[2] + ", " + mat[6] + ", " + mat[10] + ", " + mat[14]);
    	(tag, "" + mat[3] + ", " + mat[7] + ", " + mat[11] + ", " + mat[15]);
    }
    */
}
