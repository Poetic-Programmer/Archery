package games.mgd.archery.logic.geometry.factory;

import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.logic.geometry.structure.StructureType;

/**
 * Created by Michael on 20/02/2016.
 *
 * This factory is used to create geometry which corresponds to a shader program that takes
 * as attributes:
 *     - 3D position vector
 */

public class GeoBasicColourFactory implements GeometryFactory{
    public static final int VERTEX_SIZE = 3;

    public Structure build(StructureType type) {
        Structure structure = null;
        switch(type){
            case BOW:
                //geo = new BasicColourCube();
                break;
            default:
                //geo = new BasicColourCube();
                break;
        }

        structure.create();
        return structure;
    }
}
