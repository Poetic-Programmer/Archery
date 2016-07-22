package com.engine.utility;

import static android.opengl.GLUtils.texImage2D;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;

public class Texture2D
{
    
	private final int m_resourceId;
	
	private final int[] m_textureId;
	private int minFilter;
	private int magFilter;
	public Texture2D(Context context, int resourceId)
	{
		this(context, resourceId, false);
	}

	private Texture2D(Context context, int resourceId, boolean getInfo)
	{
		m_resourceId = resourceId;
		m_textureId = new int[1];

		Load(context);
	}

	void Load(Context context)
	{
		 final int[] textureObjectIds = new int[1];
	        GLES20.glGenTextures(1, textureObjectIds, 0);
	        
	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inScaled = false;
	 
	        // Read in the resource
	        final Bitmap bitmap = BitmapFactory.decodeResource(
	            context.getResources(), m_resourceId, options);
	        
	        if (bitmap == null) {
	        	GLES20.glDeleteTextures(1, textureObjectIds, 0);
	            return;
	        } 
	        
	        // Bind to the texture in OpenGL
	        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObjectIds[0]);

	        // Set filtering: a default must be set, or the texture will be
	        // black.
			GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR );
			GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR );
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_CUBE_MAP, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE); 
	        // Load the bitmap into the bound texture.
	        texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

	        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);

	        // Recycle the bitmap, since its data has been loaded into
	        // OpenGL.
	        bitmap.recycle();

	        // Unbind from the texture.
	        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

	        m_textureId[0] = textureObjectIds[0];       
	}
    
	public void Reload(Context context) 
	{
		Load(context);
		SetFilters(minFilter, magFilter);
	}

	void SetFilters(int minFilter, int magFilter)
	{
		this.minFilter = minFilter;
		this.magFilter = magFilter;

		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,
				minFilter);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,
				magFilter);
	}

	public void Dispose() 
	{
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, m_textureId[0]);

		GLES20.glDeleteTextures(1, m_textureId, 0);
	}

	public int GetTextureId() 
	{
		return m_textureId[0];
	}
}
