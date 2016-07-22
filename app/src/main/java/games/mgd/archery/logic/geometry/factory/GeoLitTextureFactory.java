package games.mgd.archery.logic.geometry.factory;

import games.mgd.archery.logic.geometry.structure.Structure;

/**
 * Created by Michael on 24/02/2016.
 */
public class GeoLitTextureFactory implements GeometryFactory {
    public static final int VERTEX_SIZE = 3 + 3 + 2; // 3D pos, normal and 2d tex coord

    public Structure build(GeometryType type) {
        Structure geo = null;
        switch(type){
            case CUBE:
                //geo = new CubeLitTexture();
                break;
            case BOW:
                //geo = new BowALitTexture();
                break;
            case NEW_BOW:
                //geo = new NewBowLitTexture();
                break;
            default:
                //geo = new CubeLitTexture();
                break;
        }

        geo.create();
        return geo;
    }
}
