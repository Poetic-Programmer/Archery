package games.mgd.archery.logic.geometry.data;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/**
 * Created by Michael on 26/02/2016.
 */
public class IndexBuffer {
    private final int id;
    private final int size;
    private IndexBuffer(int id, int size){
        this.id = id;
        this.size = size;
    }

    public int getId() { return id; }
    public int getSize() { return size; }
    public static IndexBuffer createWith(short [] indices){
        return new IndexBuffer(
                getId(indices), indices.length
        );
    }

    private static final int getId(short [] indices){
        final int bytes_per_short = 2;

        // get buffer name
        final int [] bufferId = new int[1];
        GLES20.glGenBuffers(1, bufferId, 0);

        if(bufferId[0] == 0){
            return 0;
        }

        // bind buffer to target
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, bufferId[0]);

        // move data to native memory
        ShortBuffer indexBuffer = ByteBuffer.allocateDirect(indices.length * bytes_per_short)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(indices);
        indexBuffer.position(0);

        // create data store for index buffer, move to GPU memory
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity() * bytes_per_short,
                indexBuffer, GLES20.GL_STATIC_DRAW);

        // release buffer
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        return bufferId[0];
    }
}
