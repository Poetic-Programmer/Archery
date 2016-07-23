package games.mgd.archery.logic.geometry.factory;

import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.logic.geometry.structure.StructureType;

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
    public Structure build(StructureType type);
}
