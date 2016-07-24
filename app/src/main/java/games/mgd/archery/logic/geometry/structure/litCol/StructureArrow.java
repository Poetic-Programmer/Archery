package games.mgd.archery.logic.geometry.structure.litCol;

import java.util.List;

import games.mgd.archery.logic.World;
import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.math.s.vector.Vector3;
import games.mgd.archery.util.MathHelper;

/**
 * Created by Michael on 24/07/2016.
 */
public class StructureArrow extends Structure {
    /*
    float headRadius = 0.15f;
    float headHeight = World.INSTANCE.getCellRelWidth(0.8f);
    float headWidth = World.INSTANCE.getCellRelWidth(0.8f);
    float headDepth = World.INSTANCE.getCellRelWidth(0.1f);

    float baseHeight = World.INSTANCE.getCellRelWidth(2.0f);
    float baseRadius = World.INSTANCE.getCellRelHeight(0.12f);

    float flightHeight = World.INSTANCE.getCellRelWidth(0.6f);
    float flightRadius = flightHeight * 0.25f;

    float arrowLength = baseHeight + flightHeight - headHeight;

    int stacks = 8;
    int slices = 8;

    static int counter = 0;

    static{
        counter = 0;
    }

    public int appendHead(float [] vertices, int offset)
    {
        float halfWidth = arrow_head_width * 0.5f;
        float quarterHeight = height * 0.25f;
        float theta = MathHelper.TWO_PI / (float)slices;
        float du = 0;

        // Create arrow head base, a cone beginning at the tip of the base and
        // ending half way up the length of the head.
        // - Only 2 'rings' for the head base
        float [] normal = Vector3.create();
        float [] tangent = Vector3.create();
        float [] bitangent = Vector3.create();
        float [] triA = Vector3.create();
        float [] triB = Vector3.create();
        float [] triC = Vector3.create();
        float [] side1 = Vector3.create();
        float [] side2 = Vector3.create();

        for(int i = 0; i <= slices; ++i)
        {
            offset++;
            float c = (float) (Math.cos(i * theta));
            float s = (float) (Math.sin(i * theta));
            float dr = base_radius - 0;

            tangent.Set(-s, 0, c);
            bitangent.Set(dr*c, -(y + height * 0.5f), dr*s);
            normal.Set(tangent.Cross(bitangent));
            normal.Normalize();

            du = (float)i / slices;
            vertex_buffer.Add_Vertex(new float[]{
                    x + c * base_radius, y, z + s * base_radius,
                    normal.x, normal.y, normal.z,
                    du, 0
            });
        }
        offset++;
        vertex_buffer.Add_Vertex(new float[]{
                x, y + (height * 0.5f), z,
                0, 1, 0,
                0.5f, 1.0f
        });

        for(int i = 0; i < slices; ++i)
        {
            index_buffer.AddIndex(new short[]{
                    (short)(i+1), (short)(i), (short)(offset - 1)
            });
        }

        // Build Body of the arrow head, modelled after arrows in season one of 'Arrow'.
        // It will have 6 components, front-left, front-middle, front-right, back-right,
        // back-middle, back-left.
        //back
        tri_a.Set(x, y + height, z); //top-middle
        tri_b.Set(x - half_width, y + quarter_height, z); //left
        tri_c.Set(x, y, z-depth); // bottom-middle
        side_1.Set(tri_a.Sub(tri_c));
        side_2.Set(tri_c.Sub(tri_b));
        normal.SetCross(side_1, side_2);
        normal.Normalize();
        //normal.Print("left-back-normal");
        vertex_buffer.Add_Vertex(new float[]{
                tri_c.x, tri_c.y, tri_c.z,  normal.x, normal.y, normal.z,  0.5f, 0.0f, // bottom-middle
                tri_a.x, tri_a.y, tri_a.z,  normal.x, normal.y, normal.z,  0.5f, 1.0f, // top-middle
                tri_b.x, tri_b.y, tri_b.z,  normal.x, normal.y, normal.z,  0.0f, 0.0f, //left
        });

        tri_a.Set(x, y + height, z); //top-middle
        tri_b.Set(x + half_width, y + quarter_height, z); //right
        tri_c.Set(x, y, z-depth); // bottom-middle
        side_1.Set(tri_a.Sub(tri_c));
        side_2.Set(tri_c.Sub(tri_b));
        normal.SetCross(side_2, side_1);
        normal.Normalize();
        //normal.Print("right-back-normal");
        vertex_buffer.Add_Vertex(new float[]{
                tri_c.x, tri_c.y, tri_c.z,  normal.x, normal.y, normal.z,  0.5f, 0.0f, // bottom-middle
                tri_b.x, tri_b.y, tri_b.z,  normal.x, normal.y, normal.z,  0.0f, 0.0f, //right
                tri_a.x, tri_a.y, tri_a.z,  normal.x, normal.y, normal.z,  0.5f, 1.0f, // top-middle
        });

        index_buffer.AddIndex(new short[]{
                (short)(offset + 2), (short)(offset + 1), (short)(offset + 0),
                (short)(offset + 5), (short)(offset + 4), (short)(offset + 3),
        });
        offset += 6;

        // front
        tri_a.Set(x, y + height, z); //top-middle
        tri_b.Set(x + half_width, y + quarter_height, z); //right
        tri_c.Set(x, y, z+depth); // bottom-middle
        side_1.Set(tri_a.Sub(tri_c));
        side_2.Set(tri_c.Sub(tri_b));
        normal.SetCross(side_1, side_2);
        normal.Normalize();
        //normal.Print("right-front-normal");
        vertex_buffer.Add_Vertex(new float[]{
                tri_a.x, tri_a.y, tri_a.z,  normal.x, normal.y, normal.z,  0.5f, 1.0f, // top-middle
                tri_b.x, tri_b.y, tri_b.z,  normal.x, normal.y, normal.z,  0.0f, 0.0f, //right
                tri_c.x, tri_c.y, tri_c.z,  normal.x, normal.y, normal.z,  0.5f, 0.0f, // bottom-middle
        });

        tri_a.Set(x, y + height, z); //top-middle
        tri_b.Set(x - half_width, y + quarter_height, z); //left
        tri_c.Set(x, y, z+depth); // bottom-middle
        side_1.Set(tri_a.Sub(tri_c));
        side_2.Set(tri_c.Sub(tri_b));
        normal.SetCross(side_2, side_1);
        normal.Normalize();
        //normal.Print("left-front-normal");
        vertex_buffer.Add_Vertex(new float[]{
                tri_b.x, tri_b.y, tri_b.z,  normal.x, normal.y, normal.z,  0.0f, 0.0f, //left
                tri_a.x, tri_a.y, tri_a.z,  normal.x, normal.y, normal.z,  0.5f, 1.0f, // top-middle
                tri_c.x, tri_c.y, tri_c.z,  normal.x, normal.y, normal.z,  0.5f, 0.0f, // bottom-middle
        });

        index_buffer.AddIndex(new short[]{
                (short)(offset + 2), (short)(offset + 1), (short)(offset + 0),
                (short)(offset + 5), (short)(offset + 4), (short)(offset + 3),
        });
        offset += 6;

        // Plug the gaps at the bottom
        tri_a.Set(x, y, z-depth); //front-bottom-middle
        tri_b.Set(x - half_width, y + quarter_height, z); //left
        tri_c.Set(x, y, z+depth); // back-bottom-middle
        side_1.Set(tri_a.Sub(tri_b));
        side_2.Set(tri_a.Sub(tri_c));
        normal.SetCross(side_2, side_1);
        normal.Normalize();
        //normal.Print("bottom-left-gap-normal");
        vertex_buffer.Add_Vertex(new float[]{
                tri_b.x, tri_b.y, tri_b.z,  normal.x, normal.y, normal.z,  0.5f, 1.0f, //left
                tri_c.x, tri_c.y, tri_c.z,  normal.x, normal.y, normal.z,  0.0f, 0.0f, // back-bottom-middle
                tri_a.x, tri_a.y, tri_a.z,  normal.x, normal.y, normal.z,  1.0f, 0.0f, // front-bottom-middle
        });

        tri_a.Set(x + half_width, y + quarter_height, z); //right
        tri_b.Set(x, y, z-depth); //front-bottom-middle
        tri_c.Set(x, y, z+depth); // back-bottom-middle
        side_1.Set(tri_a.Sub(tri_b));
        side_2.Set(tri_a.Sub(tri_c));
        normal.SetCross(side_2, side_1);
        normal.Normalize();
        //normal.Print("bottom-right-gap-normal");
        vertex_buffer.Add_Vertex(new float[]{
                tri_a.x, tri_a.y, tri_a.z,  normal.x, normal.y, normal.z,  0.5f, 1.0f, //right
                tri_b.x, tri_b.y, tri_b.z,  normal.x, normal.y, normal.z,  1.0f, 0.0f, // front-bottom-middle
                tri_c.x, tri_c.y, tri_c.z,  normal.x, normal.y, normal.z,  0.0f, 0.0f, // back-bottom-middle
        });

        index_buffer.AddIndex(new short[]{
                (short)(offset + 2), (short)(offset + 1), (short)(offset + 0),
                (short)(offset + 5), (short)(offset + 4), (short)(offset + 3),
        });
        offset += 6;

        buffer.Set_Vertex_Buffer_ID(vertex_buffer.BuildVertexBuffer());
        buffer.Set_Index_Buffer_ID(index_buffer.BuildIndexBuffer());
        buffer.Set_Index_Count(index_buffer.Get_Index_Count());

        Close_Buffers();
        return buffer;
    }

    public Geometry_Buffer_Data Build_Body(float x, float y, float z,
                                           float height, float radius, int stacks, int slices)
    {
        Geometry_Buffer_Data buffer = new Geometry_Buffer_Data();
        Open_Buffers();

        float stackHeight = height / (float)stacks;
        float theta = MathHelper.TWO_PI / (float)slices;
        int ringCount = stacks + 1;

        Vector3 position = new Vector3();
        Vector3 normal = new Vector3();
        Vector3 tangent = new Vector3();
        Vector3 bitangent = new Vector3();
        Vector2 texCoord = new Vector2();

        for(int i = 0; i < ringCount; ++i)
        {
            float ypos = -0.5f*height + i*stackHeight;

            for(int j = 0; j <= slices; ++j)
            {
                float c = (float) Math.cos(j*theta);
                float s = (float) Math.sin(j*theta);

                position.Set(x + radius*c, y + ypos, z + radius*s);
                texCoord.x = (float)j/(float)slices;
                texCoord.y = 1.0f - (float)i/(float)stacks;
                tangent.Set(-s, 0.0f, c);
                bitangent.Set(0, -height, 0);
                normal.SetCross(tangent, bitangent);
                normal.Normalize();

                vertex_buffer.Add_Vertex(new float[]{
                        position.x, position.y, position.z,  normal.x, normal.y, normal.z,  texCoord.x, texCoord.y
                });
            }
        }
        // Add one because we duplicate the first and last vertex per ring
        // since the texture coordinates are different.
        int ringVertexCount = slices+1;

        // Compute indices for each stack.
        for(int i = 0; i < stacks; ++i)
        {
            for(int j = 0; j < slices; ++j)
            {
                index_buffer.AddIndex(new short[]{
                        (short) (i*ringVertexCount + j),
                        (short) ((i+1)*ringVertexCount + j),
                        (short) ((i+1)*ringVertexCount + j+1),

                        (short) (i*ringVertexCount + j),
                        (short) ((i+1)*ringVertexCount + j+1),
                        (short) (i*ringVertexCount + j+1),
                });
            }
        }

        buffer.Set_Vertex_Buffer_ID(vertex_buffer.BuildVertexBuffer());
        buffer.Set_Index_Buffer_ID(index_buffer.BuildIndexBuffer());
        buffer.Set_Index_Count(index_buffer.Get_Index_Count());

        Close_Buffers();
        return buffer;
    }

    public int appendFlight(float [] vertices, int offset)
    {
        Geometry_Buffer_Data buffer = new Geometry_Buffer_Data();
        Open_Buffers();

        float stackHeight = height / (float)stacks;
        float theta = MathHelper.TWO_PI / (float)slices;
        int ringCount = stacks + 1;

        Vector3 position = new Vector3();
        Vector3 normal = new Vector3();
        Vector3 tangent = new Vector3();
        Vector3 bitangent = new Vector3();
        Vector2 texCoord = new Vector2();

        for(int i = 0; i < ringCount; ++i)
        {
            float ypos = -(0.5f * height) + i*stackHeight;

            for(int j = 0; j <= slices; ++j)
            {
                offset++;

                float c = (float) Math.cos(j*theta);
                float s = (float) Math.sin(j*theta);

                position.Set(x + radius*c, y + ypos, z + radius*s);
                texCoord.x = (float)j/(float)slices;
                texCoord.y = 1.0f - (float)i/(float)stacks;
                tangent.Set(-s, 0.0f, c);
                bitangent.Set(0, -height, 0);
                normal.SetCross(tangent, bitangent);
                normal.Normalize();

                vertex_buffer.Add_Vertex(new float[]{
                        position.x, position.y, position.z,  normal.x, normal.y, normal.z,  texCoord.x, texCoord.y
                });
            }
        }
        // Add one because we duplicate the first and last vertex per ring
        // since the texture coordinates are different.
        int ringVertexCount = slices+1;

        // Compute indices for each stack.
        for(int i = 0; i < stacks; ++i)
        {
            for(int j = 0; j < slices; ++j)
            {
                index_buffer.AddIndex(new short[]{
                        (short) (i*ringVertexCount + j),
                        (short) ((i+1)*ringVertexCount + j),
                        (short) ((i+1)*ringVertexCount + j+1),

                        (short) (i*ringVertexCount + j),
                        (short) ((i+1)*ringVertexCount + j+1),
                        (short) (i*ringVertexCount + j+1),
                });
            }
        }

        // Add the bottom cap to the arrow base, the top cap will be covered by the arrow head
        int baseIndex = offset;
        position.y = y - (height * 0.5f);

        normal.x = 0;
        normal.y = -1.0f;
        normal.z = 0;
        // vertices of ring
        for(int i = 0; i <= slices; ++i)
        {
            offset++;

            position.x = (float) (radius * Math.cos(i*theta));
            position.z = (float) (radius * Math.sin(i*theta));

            // Scale down by the height to try and make top cap texture coord area
            // proportional to base.
            texCoord.x = position.x / height + 0.5f;
            texCoord.y = position.z / height + 0.5f;

            vertex_buffer.Add_Vertex(new float[]{
                    position.x, position.y, position.z,  normal.x, normal.y, normal.z,  texCoord.x, texCoord.y
            });
        }

        // Cap center vertex.
        position.x = 0;
        position.z = 0;
        texCoord.x = 0.5f;
        texCoord.y = 0.5f;

        offset++;
        vertex_buffer.Add_Vertex(new float[]{
                position.x, position.y, position.z,  normal.x, normal.y, normal.z,  texCoord.x, texCoord.y
        });

        // Cache the index of center vertex.
        int centerIndex = offset - 1;

        for(int i = 0; i < slices; ++i)
        {
            index_buffer.AddIndex(new short[]{
                    (short) centerIndex,
                    (short) (baseIndex + i),
                    (short) (baseIndex + i+1)
            });
        }

        // Build flight, two semi-circles attached as an 'X'.  Composed of 4 semi-circles
        // 2 overlapping with opposite normals
        // Top Facing
        baseIndex = offset;
        float phi = MathHelper.PI / (float)slices;
        for(int i = 0; i <= slices; ++i)
        {
            offset++;
            float c = (float) Math.cos(i * phi);
            float s = (float) Math.sin(i * phi);

            texCoord.x = (1.0f - c) / 2.0f;
            texCoord.y = (1.0f - s) / 2.0f;

            //texCoord.Print("FLIGHT TEX");
            vertex_buffer.Add_Vertex(new float[]{
                    c * flight_radius, (y + (height * -0.5f)) + s * flight_radius, 0,  0, 0, 1,  texCoord.x, texCoord.y
            });
        }
        offset++;
        texCoord.x = 0.5f;
        texCoord.y = 0.5f;
        vertex_buffer.Add_Vertex(new float[]{
                0, (y + (height * -0.5f)), 0,  0, 0, 1,  texCoord.x, texCoord.y
        });
        centerIndex = offset - 1;
        for(int i = 0; i < slices; ++i)
        {

            index_buffer.AddIndex(new short[]{
                    (short)(centerIndex), (short)(baseIndex+i), (short)(baseIndex+i+1)
            });

        }

        //bottom facing
        baseIndex = offset;
        for(int i = 0; i <= slices; ++i)
        {
            offset++;
            float c = (float) Math.cos(i * phi);
            float s = (float) Math.sin(i * phi);

            texCoord.x = (1.0f - c) / 2.0f;
            texCoord.y = (1.0f - s) / 2.0f;

            vertex_buffer.Add_Vertex(new float[]{
                    c * flight_radius,
                    (y + (height * -0.5f)) + s * flight_radius,
                    0,
                    0, 0, -1,
                    texCoord.x, texCoord.y
            });
        }
        offset++;
        texCoord.x = 0.5f;
        texCoord.y = 0.5f;
        vertex_buffer.Add_Vertex(new float[]{
                0, (y + (height * -0.5f)), 0,  0, 0, -1,  texCoord.x, texCoord.y
        });
        centerIndex = offset - 1;
        for(int i = 0; i < slices; ++i)
        {
            index_buffer.AddIndex(new short[]{
                    (short)(baseIndex+i+1), (short)(baseIndex+i), (short)(centerIndex)
            });
        }

        //left facing
        baseIndex = offset;
        for(int i = 0; i <= slices; ++i)
        {
            offset++;
            float c = (float) Math.cos(i * phi + (270.0f * MathHelper.DEGREES_TO_RADIANS));
            float s = (float) Math.sin(i * phi + (270.0f * MathHelper.DEGREES_TO_RADIANS));

            texCoord.x = (1.0f - c) / 2.0f;
            texCoord.y = (1.0f - s) / 2.0f;

            vertex_buffer.Add_Vertex(new float[]{
                    0, (y + (height * -0.5f)) + c * flight_radius, s * flight_radius,  1, 0, 0,  texCoord.x, texCoord.y
            });
        }
        offset++;
        texCoord.x = 0.5f;
        texCoord.y = 0.5f;
        vertex_buffer.Add_Vertex(new float[]{
                0, (y + (height * -0.5f)), 0,  1, 0, 0,  texCoord.x, texCoord.y
        });
        centerIndex = offset - 1;

        for(int i = 0; i < slices; ++i)
        {
            index_buffer.AddIndex(new short[]{
                    (short)(centerIndex), (short)(baseIndex+i), (short)(baseIndex+i+1)
            });
        }

        //right facing
        baseIndex = offset;
        for(int i = 0; i <= slices; ++i)
        {
            offset++;
            float c = (float) Math.cos(i * phi + (270.0f * MathHelper.DEGREES_TO_RADIANS));
            float s = (float) Math.sin(i * phi + (270.0f * MathHelper.DEGREES_TO_RADIANS));

            texCoord.x = (1.0f - c) / 2.0f;
            texCoord.y = (1.0f - s) / 2.0f;

            vertex_buffer.Add_Vertex(new float[]{
                    0, (y + (height * -0.5f)) + c * flight_radius, s * flight_radius,  -1, 0, 0,  texCoord.x, texCoord.y
            });
        }
        offset++;
        texCoord.x = 0.5f;
        texCoord.y = 0.5f;
        vertex_buffer.Add_Vertex(new float[]{
                0, (y + (height * -0.5f)), 0,  -1, 0, 0,  texCoord.x, texCoord.y
        });
        centerIndex = offset - 1;
        for(int i = 0; i < slices; ++i)
        {
            index_buffer.AddIndex(new short[]{
                    (short)(baseIndex+i+1),
                    (short)(baseIndex+i),
                    (short)(centerIndex)
            });
        }

        buffer.Set_Vertex_Buffer_ID(vertex_buffer.BuildVertexBuffer());
        buffer.Set_Index_Buffer_ID(index_buffer.BuildIndexBuffer());
        buffer.Set_Index_Count(index_buffer.Get_Index_Count());

        Close_Buffers();
        return buffer;
    }
    */

    @Override
    protected float[] createVertices() {
        return new float[0];
    }

    @Override
    protected short[] createIndices() {
        return new short[0];
    }

    @Override
    protected List<DrawProcedure> createDrawProcedures() {
        return null;
    }
}
