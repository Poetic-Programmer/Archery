package games.mgd.archery.logic.geometry.data;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Michael on 26/02/2016.
 */
public class VertexBuffer {
    private final int id;

    private VertexBuffer(int id){
        this.id = id;
    }

    public static VertexBuffer createWith(float [] vertices){
        return new VertexBuffer(
                getId(vertices)
        );
    }

    private static final int getId(float [] vertices){
        final int bytes_per_float = 4;

        // create buffer names
        final int [] bufferId = new int[1];
        GLES20.glGenBuffers(1, bufferId, 0);
        if(bufferId[0] == 0){
            return 0;
        }

        //bind target to array buffer
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, bufferId[0]);

        // move vertices to client memory
        FloatBuffer buffer = ByteBuffer.allocateDirect(vertices.length * bytes_per_float)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertices);

        // create buffer data store for vertex buffer, transfer to GPU memory
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.capacity() * bytes_per_float, buffer,
                GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        return bufferId[0];
    }
}
