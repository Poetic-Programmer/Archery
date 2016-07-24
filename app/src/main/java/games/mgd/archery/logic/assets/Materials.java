package games.mgd.archery.logic.assets;

import games.mgd.archery.logic.lighting.Material;

/**
 * <h1> Material Singleton </h1>
 * <p> responsible for maintaining and extending materials used in the game </p>
 *
 * @author Michael
 * @since 24/07/2016.
 */
public enum Materials {
    INSTANCE;

    private Material wood;
    private Material darkWood;

    private Materials(){
        wood = new Material(
                new float [] {0.7f, 0.5f, 0.3f, 1.0f},
                new float [] {1.0f, 1.0f, 1.0f, 1.0f},
                new float [] {0.7f, 0.7f, 0.7f, 1.0f},
                0.0f
        );

        darkWood = new Material(
                new float [] {0.0f, 0.0f, 0.0f, 1.0f},
                new float [] {1.0f, 0.0f, 1.0f, 1.0f},
                new float [] {0.7f, 0.7f, 0.7f, 1.0f},
                20.0f
        );
    }

    public Material getWood() { return wood; }
    public Material getDarkWood() { return darkWood; }
}
