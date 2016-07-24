package games.mgd.archery.logic.program;

import android.opengl.GLES20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.logic.program.Program;

/**
 * @author Michael Dunleavy
 * @since 24/07/2016.
 */

public class LitTextureProgram extends Program {
    private static final int POSITION_COMPONENT_COUNT = 3;
    private static final int NORMAL_COMPONENT_COUNT = 3;
    private static final int TEXTURE_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT +
            NORMAL_COMPONENT_COUNT + TEXTURE_COMPONENT_COUNT) *
            Structure.BYTES_PER_FLOAT;

    @Override
    protected List<String> getAttributeList() {
        List<String> arrayList = new ArrayList<>();

        arrayList.add(Shader.A_POSITION_3D);
        arrayList.add(Shader.A_NORMAL_3D);
        arrayList.add(Shader.A_TEX_COORDS_2D);

        return arrayList;
    }

    @Override
    protected HashMap<String, Integer> getUniformList() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put(Shader.U_MV_MATRIX, GLES20.glGetUniformLocation(this.program, Shader.U_MV_MATRIX));
        map.put(Shader.U_MVP_MATRIX, GLES20.glGetUniformLocation(this.program,
                Shader.U_MVP_MATRIX));
        map.put(Shader.U_POSITION_LIGHT_POSITION_3D, GLES20.glGetUniformLocation(this.program,
                Shader.U_POSITION_LIGHT_POSITION_3D));
        map.put(Shader.U_POSITION_LIGHT_AMBIENT, GLES20.glGetUniformLocation(this.program,
                Shader.U_POSITION_LIGHT_AMBIENT));
        map.put(Shader.U_POSITION_LIGHT_DIFFUSE, GLES20.glGetUniformLocation(this.program,
                Shader.U_POSITION_LIGHT_DIFFUSE));
        map.put(Shader.U_POSITION_LIGHT_SPECULAR, GLES20.glGetUniformLocation(this.program,
                Shader.U_POSITION_LIGHT_SPECULAR));
        map.put(Shader.U_MATERIAL_AMBIENT, GLES20.glGetUniformLocation(this.program,
                Shader.U_MATERIAL_AMBIENT));
        map.put(Shader.U_MATERIAL_DIFFUSE, GLES20.glGetUniformLocation(this.program,
                Shader.U_MATERIAL_DIFFUSE));
        map.put(Shader.U_MATERIAL_SPECULAR, GLES20.glGetUniformLocation(this.program,
                Shader.U_MATERIAL_SPECULAR));
        map.put(Shader.U_MATERIAL_SPECULAR_HIGHLIGHT, GLES20.glGetUniformLocation(this.program,
                Shader.U_MATERIAL_SPECULAR_HIGHLIGHT));
        map.put(Shader.U_TEXTURE0, GLES20.glGetUniformLocation(this.program, Shader.U_TEXTURE0));
        return map;
    }

    @Override
    public void setVertexAttribPointer(Structure structure) {
        structure.getVertexBuffer().position(0);

        GLES20.glVertexAttribPointer(
                getAttributeLocation(Shader.A_POSITION_3D),
                POSITION_COMPONENT_COUNT,
                GLES20.GL_FLOAT, false,
                STRIDE,
                structure.getVertexBuffer());
        GLES20.glEnableVertexAttribArray(getAttributeLocation(Shader.A_POSITION_3D));

        structure.getVertexBuffer().position(POSITION_COMPONENT_COUNT);
        GLES20.glVertexAttribPointer(
                getAttributeLocation(Shader.A_NORMAL_3D),
                NORMAL_COMPONENT_COUNT,
                GLES20.GL_FLOAT, false,
                STRIDE,
                structure.getVertexBuffer()
        );
        GLES20.glEnableVertexAttribArray(getAttributeLocation(Shader.A_NORMAL_3D));

        structure.getVertexBuffer().position(
                POSITION_COMPONENT_COUNT + NORMAL_COMPONENT_COUNT
        );
        GLES20.glVertexAttribPointer(
                getAttributeLocation(Shader.A_TEX_COORDS_2D),
                TEXTURE_COMPONENT_COUNT,
                GLES20.GL_FLOAT, false,
                STRIDE,
                structure.getVertexBuffer()
        );

        GLES20.glEnableVertexAttribArray(getAttributeLocation(Shader.A_TEX_COORDS_2D));

        structure.getVertexBuffer().position(0);
    }
}
