package games.mgd.archery.logic.assets;

import android.content.Context;

import games.mgd.archery.R;
import games.mgd.archery.util.TextureLoader;

/**
 * <h1> Textures Singleton </h1>
 * <p> responsible for extending the list of textures used in the game </p>
 *
 * @author Michael Dunleavy
 * @since 24/07/2016.
 */

public enum Textures {
    INSTANCE;

    private int bowWoodTextureId;

    Textures(){

    }

    public void initialize(Context context){
        bowWoodTextureId = TextureLoader.loadTexture(context, R.drawable.stone_wall_256);
    }

    public int getBowWoodTextureId() { return bowWoodTextureId; }
}
