package games.mgd.archery.game.graphics;

import android.opengl.GLSurfaceView;

public class GLGraphics 
{
    private final GLSurfaceView glView;
    
    public GLGraphics(GLSurfaceView glView) 
    {
        this.glView = glView;
    }

    public int GetWidth()
    {
        return glView.getWidth();
    }
    
    public int GetHeight()
    {
        return glView.getHeight();
    }
}
