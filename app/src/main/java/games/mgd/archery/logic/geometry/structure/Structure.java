package games.mgd.archery.logic.geometry.structure;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.List;

import games.mgd.archery.logic.geometry.data.IndexBuffer;

/**
 * Created by Michael Dunleavy on 20/02/2016.
 *
 * This superclass provides an interface for the GeometryFactory to
 * create and set up basic geometric shapes.
 */
public abstract class Structure {
    // ToDo: move to separate class
    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_SHORT = 2;

    protected static interface DrawProcedure{
        void draw();
    }

    protected FloatBuffer vertexBuffer;
    protected int vertexBufferId;
    protected IndexBuffer indexBuffer;
    //protected int indexCount;
    protected List<DrawProcedure> drawProcedureList;

    public FloatBuffer getVertexBuffer() { return vertexBuffer; }
    public int getVertexBufferId() { return vertexBufferId; }
    
    /**
     *  creates the geometry using:
     *      - vertices
     *      - indices
     *      -drawing procedures
     *  defined by base classes.
     */
    public void create(){
        float [] vertices = createVertices();
        short [] indices = createIndices();

        indexBuffer = IndexBuffer.createWith(indices);

        vertexBuffer = createVertexBuffer(vertices);
        vertexBufferId = getVertexBufferId(vertices);
        //indexBufferId = getIndexBufferId(indices);
        drawProcedureList = createDrawProcedures();

        //printIndices(indices);
       // printVertices(vertices);
    }

    /**
     *  render the object using the list of draw procedures
     */
    public void draw() {
        for (DrawProcedure procedure : drawProcedureList) {
            procedure.draw();
        }
    }

    public void printVertices(float [] vertices){
        for(float f : vertices){
            Log.d("GEO", "" + f);
        }
    }
    public void printIndices(short [] indices){
        for(short s : indices){
            Log.d("GEO", "" + s);
        }
    }
    protected abstract float [] createVertices();
    protected abstract short [] createIndices();
    protected abstract List<DrawProcedure> createDrawProcedures();

    /**
     *
     * @param vertices
     * array of vertices to be stored in native memory
     * @return
     * vertex buffer
     */
    private FloatBuffer createVertexBuffer(float [] vertices){
        // move vertices to native memory
        FloatBuffer fb = ByteBuffer.allocateDirect(
                vertices.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertices);

        return fb;
    }
    private int getVertexBufferId(float [] vertices){
        final int [] id = new int[1];

        //create buffer name
        GLES20.glGenBuffers(id.length, id, 0);
        if(id[0] == 0){
            return 0;
        }

        // bind buffer name to target
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, id[0]);

        // move indices to native memory
        FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(
                vertices.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertices);
        vertexBuffer.position(0);

        // create buffer data store for indexBuffer, transfer memory to GPU
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexBuffer.capacity() * BYTES_PER_FLOAT,
                vertexBuffer, GLES20.GL_STATIC_DRAW);

        // release buffer
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        // return id
        return id[0];
    }
    /**
     * Create and bind buffers in native memory, transfer to
     * GPU, return address of buffer.
     *
     * @param indices
     * indexed data to be stored in native memory
     * @return
     * address of buffer
     */
    private int getIndexBufferId(short [] indices){
        final int [] id = new int[1];

        //create buffer name
        GLES20.glGenBuffers(id.length, id, 0);
        if(id[0] == 0){
            return 0;
        }

        // bind buffer name to target
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, id[0]);

        // move indices to native memory
        ShortBuffer indexBuffer = ByteBuffer.allocateDirect(
                indices.length * BYTES_PER_SHORT)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(indices);
        indexBuffer.position(0);

        // create buffer data store for indexBuffer, transfer memory to GPU
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity() * BYTES_PER_SHORT,
                indexBuffer, GLES20.GL_STATIC_DRAW);

        // release buffer
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        // return id
        return id[0];
    }
}
