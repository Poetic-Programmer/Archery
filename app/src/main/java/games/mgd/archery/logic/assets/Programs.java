package games.mgd.archery.logic.assets;

import android.content.Context;

import games.mgd.archery.R;
import games.mgd.archery.logic.program.LitTextureProgram;
import games.mgd.archery.logic.program.Program;

/**
 * <h1> Programs Singleton </h1>
 * <p> responsible for extending the list of programs used in the game </p>
 *
 * @author Michael Dunleavy
 * @since 24/07/2016.
 */
public enum Programs {
    INSTANCE;

    private Program litTextureProgram;

    Programs(){
        litTextureProgram = new LitTextureProgram();
    }

    public void initialize(Context context){
        litTextureProgram.compileShaders(context, R.raw.lit_texture_vs, R.raw.lit_texture_fs);
    }

    public Program getLitTextureProgram() { return litTextureProgram; }
}
