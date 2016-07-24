package games.mgd.archery.logic.assets;

import games.mgd.archery.logic.geometry.*;
import games.mgd.archery.logic.geometry.factory.GeoLitTextureFactory;
import games.mgd.archery.logic.geometry.structure.StructureType;
import games.mgd.archery.logic.lighting.PositionLight;
import games.mgd.archery.logic.program.Shader;
import games.mgd.archery.math.c.view.Camera3D;
import games.mgd.archery.math.s.matrix.Matrix44;

/**
 * <h1> Geometry Singleton </h1>
 * <p>songleton responsible for maintaining and rendering all geometry used in the game</p>
 *
 * @author Michael Dunleavy
 * @since 24/07/2016.
 */
public enum Geometry {
    INSTANCE;

    private long startTime = System.nanoTime();

    GeoLitTextureFactory litTextureFactory;

    private GeoObject bow;

    private float [] m;
    private float [] mv;
    private float [] mvp;

    Geometry(){
        litTextureFactory = new GeoLitTextureFactory();

        bow = new GeoObject();

        m = Matrix44.createIdentity();
        mv = new float[16];
        mvp = new float[16];
    }

    public void initialize(){
        bow.setStructure(litTextureFactory.build(StructureType.BOW));
    }

    public void drawBow(Camera3D camera, PositionLight light){
        setMatrices(camera.getView(), camera.getProjection());

        Programs.INSTANCE.getLitTextureProgram().useProgram();
        Programs.INSTANCE.getLitTextureProgram().setVertexAttribPointer(bow.getGeometry());

        Shader.setMVMatrix(Programs.INSTANCE.getLitTextureProgram().getUniforms(), mv);
        Shader.setMVPMatrix(Programs.INSTANCE.getLitTextureProgram().getUniforms(), mvp);
        Shader.setPositionLight(Programs.INSTANCE.getLitTextureProgram().getUniforms(), light);
        Shader.setMaterial(Programs.INSTANCE.getLitTextureProgram().getUniforms(),
                Materials.INSTANCE.getDarkWood());

        bow.draw();
    }

    void setMatrices(float [] view, float [] projection){
        Matrix44.multiply(mv, view, m);
        Matrix44.multiply(mvp, projection, mv);
    }

    public void setModelMatrix(float x, float y, float z){
        m[12] = x; m[13] = y; m[14] = z;
    }
    public void setModelMatrix(float [] position){
        setModelMatrix(position[0], position[1], position[2]);
    }
}
