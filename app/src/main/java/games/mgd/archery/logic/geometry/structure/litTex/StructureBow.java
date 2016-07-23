package games.mgd.archery.logic.geometry.structure.litTex;

import android.opengl.GLES20;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import games.mgd.archery.logic.geometry.factory.GeoLitTextureFactory;
import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.math.c.curve.CubicBezierCurve3D;
import games.mgd.archery.math.c.curve.LinearCurve3D;
import games.mgd.archery.math.s.vector.Vector2;
import games.mgd.archery.math.s.vector.Vector3;

/**
 * Created by Michael on 20/03/2016.
 */
public class StructureBow  extends Structure {
    private static final int slices = 16;
    private static final int perLimbStacks = 16;

    float bowHeight = 9.0f;
    float halfBowHeight = bowHeight * 0.5f;

    float gripHeight = 1.0f;
    float halfGripHeight = gripHeight * 0.5f;

    float bowWidth = 4.0f;

    float radiusX = (1.0f / bowWidth) * (1.0f / 2.0f);
    float radiusZ = (1.0f / bowWidth) * (1.0f / 1.5f);

    int numVertices;
    static int counter = 0;

    static{
        counter = 0;
    }

    private int appendCircle(float [] vertices, int offset, float radius,
                             float [] origin, float [] axisA, float [] axisB, float v){
        counter++;
        float thetaStep = (float) (Math.PI * 2) / (float) slices;
        float [] position = new float[3];
        float [] normal = new float[3];
        float [] texCoord = new float[2];

        for(int i = 0; i < slices; ++i){
            float theta = thetaStep * i;

            position[0] = (float) (origin[0] +
                    Math.cos(theta) * axisA[0] * radius +
                    Math.sin(theta) * axisB[0] * radius);
            position[1] = (float) (origin[1] +
                    Math.cos(theta) * axisA[1] * radius +
                    Math.sin(theta) * axisB[1] * radius);
            position[2] = (float) (origin[2] +
                    Math.cos(theta) * axisA[2] * radius +
                    Math.sin(theta) * axisB[2] * radius);

            float u = (float) (Math.cos(theta) * 0.5f);

            Vector3.set(normal, position);
            Vector3.normalize(normal);

           // texCoord = Vector2.createFull(u, v);

            //position = position.clean();
            offset = addVertex(vertices, offset, position);
            offset = addVertex(vertices, offset, normal);
            offset = addVertex(vertices, offset, texCoord);

            //position.print();
        }

        return offset;
    }

    private int appendOval(float [] vertices, int offset, float radiusX, float radiusY,
                             float [] origin, float [] axisA, float [] axisB, float v){
        counter++;
        float thetaStep = (float) (Math.PI * 2) / (float) slices;
        float [] position = new float[3];
        float [] normal = new float[3];
        float [] texCoord = new float[2];
        for(int i = 0; i < slices; ++i){
            float theta = thetaStep * i;

            position[0] = (float) (origin[0] +
                    Math.cos(theta) * axisA[0] * radiusX +
                    Math.sin(theta) * axisB[0] * radiusY);
            position[1] = (float) (origin[1] +
                    Math.cos(theta) * axisA[1] * radiusX +
                    Math.sin(theta) * axisB[1] * radiusY);
            position[2] = (float) (origin[2] +
                    Math.cos(theta) * axisA[2] * radiusX +
                    Math.sin(theta) * axisB[2] * radiusY);

            float u = (float) (Math.cos(theta) * 0.5f);

            Vector3.set(normal, position);
            Vector3.normalize(normal);
            Vector2.set(texCoord, u, v);
            //position = position.clean();
            offset = addVertex(vertices, offset, position);
            offset = addVertex(vertices, offset, normal);
            offset = addVertex(vertices, offset, texCoord);

            //position.print();
        }

        return offset;
    }

    private int createGripBetween(float [] vertices, int offset,
                                      float [] a, float [] b, float radius){
        float [] line = Vector3.getSub(b, a);
        float [] axis = Vector3.getNormalized(line);
        float [] axisA = Vector3.getCross(axis, Vector3.Z_AXIS);
        float [] axisB = Vector3.getCross(axis, axisA);

        offset = appendCircle(vertices, offset, radius, a, axisA, axisB, 0);
        offset = appendCircle(vertices, offset, radius, b, axisA, axisB, 1);

        return offset;
    }

    private int createTopLimb(float [] vertices, int offset){
        float curveStep = 1.0f / (float) perLimbStacks;

        float step = 1.0f / (bowWidth) * 2;
        float [] cp0 = Vector3.create(0, halfGripHeight, 0);
        float [] cp1 = Vector3.create(step * 2, halfGripHeight + (step * 7), 0);
        float [] cp2 = Vector3.create(step * 4, halfGripHeight + (step * 4), 0);
        float [] cp3 = Vector3.create(bowWidth, halfBowHeight, 0);

        CubicBezierCurve3D topCurve = CubicBezierCurve3D.createWith(
                cp0, cp1, cp2, cp3
        );

        for(int i = 0; i < perLimbStacks; ++i){
            float curveStart = curveStep * i;
            float curveEnd = curveStep * (i+1);

            float [] pointA = topCurve.evaluate(curveStart);
            float [] pointB = topCurve.evaluate(curveEnd);

            float [] line = Vector3.getSub(pointB, pointA);
            float [] axis = Vector3.getNormalized(line);
            float [] axisA = Vector3.getCross(axis, Vector3.Z_AXIS);
            float [] axisB = Vector3.getCross(axis, axisA);

            //pointB.print();
            offset = appendOval(vertices, offset, radiusX, radiusZ, pointB, axisA, axisB, curveStart);
        }
        return offset;
    }

    private int createBottomLimb(float [] vertices, int offset){
        float curveStep = 1.0f / (float) perLimbStacks;

        float step = 1.0f / (bowWidth) * 2;
        float [] cp0 = Vector3.create(0, -halfGripHeight, 0);
        float [] cp1 = Vector3.create(step, -halfGripHeight - (step * 4), 0);
        float [] cp2 = Vector3.create(step * 4, -halfGripHeight - (step * 7), 0);
        float [] cp3 = Vector3.create(bowWidth, -halfBowHeight, 0);

        CubicBezierCurve3D topCurve = CubicBezierCurve3D.createWith(
                cp0, cp1, cp2, cp3
        );

        for(int i = 0; i < perLimbStacks; ++i){
            float curveStart = curveStep * i;
            float curveEnd = curveStep * (i+1);

            float [] pointA = topCurve.evaluate(curveStart);
            float [] pointB = topCurve.evaluate(curveEnd);

            float [] line = Vector3.getSub(pointB, pointA);
            float [] axis = Vector3.getNormalized(line);
            float [] axisA = Vector3.getCross(axis, Vector3.Z_AXIS_INV);
            float [] axisB = Vector3.getCross(axisA, axis);

            offset = appendOval(vertices, offset, radiusX, radiusZ, pointB, axisA, axisB, 1 - curveStart);
        }
        return offset;
    }

    @Override
    protected float[] createVertices()
    {
        int offset = 0;
        int gripVertexCount = slices * 2 * GeoLitTextureFactory.VERTEX_SIZE;
        int topLimbVertexCount = (slices * perLimbStacks) * GeoLitTextureFactory.VERTEX_SIZE;
        int bottomLimbVertexCount = (slices * perLimbStacks) * GeoLitTextureFactory.VERTEX_SIZE;
        float [] vertices = new float[gripVertexCount + topLimbVertexCount + bottomLimbVertexCount];

        // Build Grip
        LinearCurve3D gripCurve = LinearCurve3D.createFull(
                0.0f, -halfGripHeight, 0.0f,
                0.0f, halfGripHeight, 0.0f
        );

        offset = createGripBetween(vertices, offset,
                gripCurve.evaluate(0), gripCurve.evaluate(1), radiusX);
        offset = createTopLimb(vertices, offset);
        offset = createBottomLimb(vertices, offset);

        int printOffset = 0;
        for(float f : vertices){
            //Log.d("print " + printOffset++, "<" + f + ">");
        }

        numVertices = vertices.length;

        //printVertices(vertices);
        return vertices;
    }

    @Override
    protected short[] createIndices() {
        int gripIndexCount = 3 * 2 * slices;
        int topLimbIndexCount = 3 * 2 * slices * perLimbStacks;
        int bottomLimbIndexCount = 3 * 2 * slices * (perLimbStacks);
        short [] indices = new short[
                gripIndexCount + topLimbIndexCount + bottomLimbIndexCount];

        int offset = 0;
        int startVertexOffset = 0;
        int vertexOffset = 0;  // this is the offset to the vertex
        offset = createGripIndices(indices, offset, vertexOffset, false);

        for(int i = 0; i < perLimbStacks; ++i){
            vertexOffset = slices * (i + 1);
            offset = createGripIndices(indices, offset, vertexOffset, false);
        }

        vertexOffset = (2 + perLimbStacks) * slices;
        offset = createGripToBottomIndices(indices, offset, vertexOffset);

        for(int i = 1; i < perLimbStacks; ++i){
            vertexOffset = (slices) + (perLimbStacks * slices) +
                    slices * (i);

            offset = createGripIndices(indices, offset, vertexOffset, true);
        }

        return indices;
    }
    private int createGripToBottomIndices(short [] indices, int offset, final int vertexOffset){
        int a, b, c, d, e, f;


/*
        offset = addIndex3(indices, offset,
                a = 2,
                b = 1,
                c = 17);

        offset = addIndex3(indices, offset,
                d = 18,
                e = 2,
                f = 17);/**/
        //Log.d("HERE WE GO", "<" + a + ", " + b + ", " + c + ">");
        //Log.d("HERE WE GO AGAIN", "<" + d + ", " + e + ", " + f + ">");

        for(int i = 0; i < slices - 1; ++i){
            offset = addIndex3(indices, offset,
                    a = (i + 1),
                    b = (i),
                    c = (vertexOffset + i));

            offset = addIndex3(indices, offset,
                    d = vertexOffset + 1 + i,
                    e = i + 1,
                    f = vertexOffset + i);

        }

        offset = addIndex3(indices, offset,
                a = 0,
                b = slices - 1,
                c = vertexOffset + (slices-1));

        offset = addIndex3(indices, offset,
                d = vertexOffset,
                e = 0,
                f = vertexOffset + (slices - 1));

        //Log.d("HERE WE GO", "<" + a + ", " + b + ", " + c + ">");
        //Log.d("HERE WE GO AGAIN", "<" + d + ", " + e + ", " + f + ">");

        return offset;
    }
    private int createGripIndices(short [] indices, int offset, final int vertexOffset, final boolean invert){
        int stacks = 2;

        if(invert){
            for (int i = 0; i < slices - 1; ++i) {
                offset = addIndex3(indices, offset,
                        vertexOffset + ((i)),
                        vertexOffset + (slices + i),
                        vertexOffset + (slices + i + 1)
                );

                offset = addIndex3(indices, offset,
                        vertexOffset + (i),
                        vertexOffset + (slices + i + 1),
                        vertexOffset + (i + 1)
                );
            }

            offset = addIndex3(indices, offset,
                    vertexOffset + ((slices - 1)),
                    vertexOffset + ((slices - 1) * 2 + 1),
                    vertexOffset + (slices)
            );

            offset = addIndex3(indices, offset,
                    vertexOffset + (slices - 1),
                    vertexOffset + (slices),
                    vertexOffset + (0)
            );
        }
        else {
            for (int i = 0; i < slices - 1; ++i) {
                offset = addIndex3(indices, offset,
                        vertexOffset + (slices + i + 1),
                        vertexOffset + (slices + i),
                        vertexOffset + ((i))
                );

                offset = addIndex3(indices, offset,
                        vertexOffset + (i + 1),
                        vertexOffset + (slices + i + 1),
                        vertexOffset + (i)
                );
            }

            offset = addIndex3(indices, offset,
                    vertexOffset + (slices),
                    vertexOffset + ((slices - 1) * 2 + 1),
                    vertexOffset + ((slices - 1))
            );

            offset = addIndex3(indices, offset,
                    vertexOffset + (0),
                    vertexOffset + (slices),
                    vertexOffset + (slices - 1)
            );
        }
        return offset;
    }
    protected List<DrawProcedure> createDrawProcedures(){
        List<DrawProcedure> drawList = new ArrayList<>();

        drawList.add(new DrawProcedure() {
            @Override
            public void draw() {
                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.getId());
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexBuffer.getSize(), GLES20.GL_UNSIGNED_SHORT, 0);
                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
            }
        });

        return drawList;
    }

    private int addVertex(float [] vertices, int offset,
                          final float [] vertex){
        int len = vertex.length;

        if(len == 2) {
            vertices[offset++] = vertex[0];
            vertices[offset++] = vertex[1];
        }
        else if(len == 3){
            vertices[offset++] = vertex[0];
            vertices[offset++] = vertex[1];
            vertices[offset++] = vertex[2];
        }

        return offset;
    }

    private int addIndex3(short [] indices, int offset,
                          final int a, final int b, final int c){
        indices[offset++] = (short)a;
        indices[offset++] = (short)b;
        indices[offset++] = (short)c;

        return offset;
    }
}
