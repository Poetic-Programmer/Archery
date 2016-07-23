package games.mgd.archery.logic.geometry;

import android.util.Log;

import games.mgd.archery.logic.geometry.factory.GeoLitTextureFactory;
import games.mgd.archery.logic.geometry.factory.GeometryFactory;
import games.mgd.archery.logic.geometry.structure.StructureType;
import games.mgd.archery.logic.lighting.PositionLight;
import games.mgd.archery.logic.program.Shader;
import games.mgd.archery.math.c.view.Camera3D;
import games.mgd.archery.math.s.matrix.Matrix44;

/**
 * Created by Michael on 23/07/2016.
 */
public enum Geometry {
    INSTANCE;

    private long startTime = System.nanoTime();

    GeoLitTextureFactory litTextureFactory;

    private GeoBow bow;
    private float [] mv;
    private float [] mvp;

    Geometry(){
        litTextureFactory = new GeoLitTextureFactory();

        bow = new GeoBow();

        mv = new float[16];
        mvp = new float[16];
    }

    public void initialize(){
        bow.setStructure(litTextureFactory.build(StructureType.BOW));
    }

    public void drawBow(Camera3D camera, PositionLight light){
        setMatrices(bow.getModelMatrix(), camera.getView(), camera.getProjection());

        Shader.INSTANCE.getLitTextureProgram().useProgram();
        Shader.INSTANCE.getLitTextureProgram().setMVMatrix(mv);
        Shader.INSTANCE.getLitTextureProgram().setMVPMatrix(mvp);
        Shader.INSTANCE.getLitTextureProgram().setPositionLight(light);
        Shader.INSTANCE.getLitTextureProgram().setMaterial(bow.getMaterial());
        Shader.INSTANCE.getLitTextureProgram().setTexture1(Textures.INSTANCE.getBowWoodTextureId());
        Shader.INSTANCE.getLitTextureProgram().setVertexAttribPointer(bow.getGeometry());

        if(System.nanoTime() - startTime >= 1000000000)
        {
            Matrix44.printRowMajor("M", bow.getModelMatrix());
            Matrix44.printRowMajor("V", camera.getView());
            Matrix44.printRowMajor("P", camera.getProjection());
            Matrix44.printRowMajor("MV", mv);
            Matrix44.printRowMajor("MVP", mvp);

            startTime = System.nanoTime();
        }

        bow.draw();
    }

    void setMatrices(float [] model, float [] view, float [] projection){
        Matrix44.multiply(mv, view, model);
        Matrix44.multiply(mvp, projection, mv);
    }
}
