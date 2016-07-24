package games.mgd.archery.logic.geometry.factory;

import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.logic.geometry.structure.StructureType;
import games.mgd.archery.logic.geometry.structure.litCol.StructureBow;

/**
 * Created by Michael on 24/02/2016.
 */
public class GeoLitTextureFactory implements GeometryFactory {
    public static final int VERTEX_SIZE = 3 + 3 + 2; // 3D pos, normal and 2d tex coord

    public Structure build(StructureType type) {
        Structure structure = null;
        switch(type){
            case BOW:
                structure = new StructureBow();
                break;
            default:
                //geo = new CubeLitTexture();
                break;
        }

        structure.create();
        return structure;
    }
}
