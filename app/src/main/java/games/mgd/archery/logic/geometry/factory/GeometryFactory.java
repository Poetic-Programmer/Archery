package games.mgd.archery.logic.geometry.factory;

import games.mgd.archery.logic.geometry.structure.Structure;

/**
 * Created by Michael on 20/02/2016.
 *
 * Each base of this factory will correspond to a particular vertex shader
 * attribute layout. This should keep any additions to the array of geometry
 * maintainable.
 *
 * Any additions to 'GeometryType' will have to be handled by any factories
 * inheriting from this interface.
 */

public interface GeometryFactory {
    public static enum GeometryType{
        CUBE,
        BOW,
        NEW_BOW,
        LINE,
        LINE_AND_CIRCLE,
        CIRCLE
    }

    public Structure build(GeometryType type);
}
