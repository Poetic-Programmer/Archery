package games.mgd.archery.logic.program;

import java.util.Map;
import android.opengl.GLES20;

import games.mgd.archery.logic.lighting.Material;
import games.mgd.archery.logic.lighting.PositionLight;

/**
 * static class responsible for holding and maintaining all things needed to interact with shader
 * programs.
 *
 * @author Michael
 * @since 14/02/2016.
 *
 * @ToDo: revise passing methods, get rid of Map
 */

public class Shader {
    // Uniform names
    static final String U_MV_MATRIX = "u_mv_Matrix";
    static final String U_MVP_MATRIX = "u_mvp_Matrix";
    static final String U_COLOUR = "u_Colour";
    static final String U_TEXTURE0 = "s_Texture0";
    static final String U_POSITION_LIGHT_POSITION_3D = "u_PositionLight.Position";
    static final String U_POSITION_LIGHT_AMBIENT = "u_PositionLight.Ambient";
    static final String U_POSITION_LIGHT_DIFFUSE = "u_PositionLight.Diffuse";
    static final String U_POSITION_LIGHT_SPECULAR = "u_PositionLight.Specular";
    static final String U_MATERIAL_AMBIENT = "u_Material.Ambient";
    static final String U_MATERIAL_DIFFUSE = "u_Material.Diffuse";
    static final String U_MATERIAL_SPECULAR = "u_Material.Specular";
    static final String U_MATERIAL_SPECULAR_HIGHLIGHT = "u_Material.SpecularHighlight";

    // Attribute names
    static final String A_POSITION_3D = "a_Position3D";
    static final String A_NORMAL_3D = "a_Normal3D";
    static final String A_TEX_COORDS_2D = "a_TexCoord";

    private Shader() {
        // stop outside class instantiation
    }

    public static void setMVMatrix(Map<String, Integer> map, float[] matrix) {
        GLES20.glUniformMatrix4fv(map.get(Shader.U_MV_MATRIX), 1, false, matrix, 0);
    }
    public static void setMVPMatrix(Map<String, Integer> map, float[] matrix) {
        GLES20.glUniformMatrix4fv(map.get(Shader.U_MVP_MATRIX), 1, false, matrix, 0);
    }
    public static void setColour(Map<String, Integer> map, float r, float g, float b, float a) {
        GLES20.glUniform4f(map.get(Shader.U_COLOUR), r, g, b, a);
    }
    public static void setTexture1(Map<String, Integer> map, int texid){
        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texid);

        // Tell the texture uniform sampler to use this texture in the shader by
        // telling it to read from texture unit 0.
        GLES20.glUniform1i(map.get(Shader.U_TEXTURE0), 0);
    }

    public static void setPositionLight(Map<String, Integer> map, PositionLight light){
        GLES20.glUniform3fv(map.get(Shader.U_POSITION_LIGHT_POSITION_3D), 1,
                light.getViewPosition(), 0);
        GLES20.glUniform4fv(map.get(Shader.U_POSITION_LIGHT_AMBIENT), 1, light.getAmbient(), 0);
        GLES20.glUniform4fv(map.get(Shader.U_POSITION_LIGHT_DIFFUSE), 1, light.getDiffuse(), 0);
        GLES20.glUniform4fv(map.get(Shader.U_POSITION_LIGHT_SPECULAR), 1, light.getSpecular(), 0);
    }

    public static void setMaterial(Map<String, Integer> map, Material material){
        GLES20.glUniform4fv(map.get(Shader.U_MATERIAL_AMBIENT), 1, material.getAmbient(), 0);
        GLES20.glUniform4fv(map.get(Shader.U_MATERIAL_DIFFUSE), 1, material.getDiffuse(), 0);
        GLES20.glUniform4fv(map.get(Shader.U_MATERIAL_SPECULAR), 1, material.getSpecular(), 0);
        GLES20.glUniform1f(map.get(Shader.U_MATERIAL_SPECULAR_HIGHLIGHT),
                material.getSpecularHighlight());
    }
}
