package games.mgd.archery.logic.geometry;

import games.mgd.archery.logic.geometry.structure.Structure;

/**
 * Created by Michael on 23/07/2016.
 */
public class GeoObject {
    private Structure structure;

    public Structure getGeometry() { return structure; }

    public GeoObject(){
    }

    public void setStructure(Structure structure){
        // Structure is created by a geometry factory in the Geometry enum
        this.structure = structure;
    }


    public void draw(){
        structure.draw();
    }

    public void draw(int offset){
        structure.draw(offset);
    }
}
