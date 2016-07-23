package games.mgd.archery.logic.program;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.opengl.GLES20;

import games.mgd.archery.R;

/**
 * Created by Michael on 14/02/2016.
 */
public enum Shader {
    INSTANCE;

    Program litTextureProgram;

    Shader(){
        litTextureProgram = new LitTextureProgram();
    }

    public void initialize(Context context){
        litTextureProgram.compileShaders(context,
                R.raw.lit_texture_vs,
                R.raw.lit_texture_fs);
    }

    public Program getLitTextureProgram() { return litTextureProgram; }
}
