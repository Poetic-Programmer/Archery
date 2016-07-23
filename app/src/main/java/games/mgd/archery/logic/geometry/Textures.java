package games.mgd.archery.logic.geometry;

import android.content.Context;

import games.mgd.archery.R;
import games.mgd.archery.util.TextureLoader;

/**
 * Created by Michael on 23/07/2016.
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
