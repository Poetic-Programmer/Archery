package games.mgd.archery.logic.geometry.factory;

import games.mgd.archery.logic.geometry.structure.Structure;

/**
 * Created by Michael on 20/02/2016.
 *
 * This factory is used to create geometry which corresponds to a shader program that takes
 * as attributes:
 *     - 3D position vector
 */

public class GeoBasicColourFactory implements GeometryFactory{
    public static final int VERTEX_SIZE = 3;

    public Structure build(GeometryType type) {
        Structure geo = null;
        switch(type){
            case CUBE:
                //geo = new BasicColourCube();
                break;
            case LINE:
                //geo = new BasicColourLine();
                break;
            case CIRCLE:
                //geo = new BasicColourCircleOutline();
                break;
            case LINE_AND_CIRCLE:
                //geo = new BasicColourLineCircleOutline();
                break;
            default:
                //geo = new BasicColourCube();
                break;
        }

        geo.create();
        return geo;
    }
}
