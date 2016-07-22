package games.mgd.archery.logic.geometry.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Michael on 26/02/2016.
 */
public class VertexArray {
    private final FloatBuffer vertexBuffer;

    private VertexArray(FloatBuffer vertexBuffer){
        this.vertexBuffer = vertexBuffer;
    }

    public FloatBuffer getVertexBuffer() { return vertexBuffer; }

    public static VertexArray createWith(float [] vertices){
        return new VertexArray(
                getVertexBuffer(vertices)
        );
    }

    private static FloatBuffer getVertexBuffer(float [] vertices){
        final int bytes_per_float = 4;
        // move vertices to native memory
        return ByteBuffer.allocateDirect(vertices.length * bytes_per_float)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertices);
    }
}
