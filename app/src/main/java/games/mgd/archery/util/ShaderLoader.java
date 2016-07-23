package games.mgd.archery.util;

import android.opengl.GLES20;
import android.util.Log;

import java.util.List;

/**
 * Created by Michael on 14/02/2016.
 */
public class ShaderLoader {
    private static final String TAG = "Shader Loader";

    public static int compileShader(int type, String shader){
        // Create the shader.
        final int id = GLES20.glCreateShader(type);

        if (id == 0){
            Log.v(TAG, "could not create shader");
            return 0;
        }

        // Let Opengl see the source (string containing shader).
        GLES20.glShaderSource(id, shader);

        // Compile the shader.
        GLES20.glCompileShader(id);

        // Error check.
        final int [] status = new int[1];
        GLES20.glGetShaderiv(id, GLES20.GL_COMPILE_STATUS, status, 0);

        if(status[0] == 0){
            Log.v(TAG, "could not compile shader");
            GLES20.glDeleteShader(id);
            return 0;
        }

        return id;
    }

    public static int linkProgram(int vs, int fs,
                                   List<String> attributes){
        // Create the Progeram
        final int id = GLES20.glCreateProgram();

        if(id == 0){
            Log.v(TAG, "failed to create program");
            return 0;
        }

        // Attach both shaders
        GLES20.glAttachShader(id, vs);
        GLES20.glAttachShader(id, fs);

        // Bind all attributes
        int offset = 0;
        for(String s : attributes){
            GLES20.glBindAttribLocation(id, offset++, s);
        }

        // Link the program
        GLES20.glLinkProgram(id);

        // Error check
        final int [] status = new int[1];
        GLES20.glGetProgramiv(id, GLES20.GL_LINK_STATUS, status, 0);
        if(status[0] == 0){
            Log.v(TAG, "failed to link program");
            GLES20.glDeleteProgram(id);
            return 0;
        }

        return id;
    }
}
