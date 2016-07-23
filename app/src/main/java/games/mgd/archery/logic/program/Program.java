package games.mgd.archery.logic.program;

import android.content.Context;
import android.opengl.GLES20;

import java.util.HashMap;
import java.util.List;

import games.mgd.archery.logic.geometry.structure.Structure;
import games.mgd.archery.logic.lighting.Material;
import games.mgd.archery.logic.lighting.PositionLight;
import games.mgd.archery.util.ShaderLoader;
import games.mgd.archery.util.TextResourceLoader;

/**
 * Created by Michael on 23/07/2016.
 */
public abstract class Program {
    // Uniform names
    public static final String U_MV_MATRIX = "u_mv_Matrix";
    public static final String U_MVP_MATRIX = "u_mvp_Matrix";
    public static final String U_COLOUR = "u_Colour";
    public static final String U_TEXTURE0 = "s_Texture0";
    public static final String U_POSITION_LIGHT_POSITION_3D = "u_PositionLight.Position";
    public static final String U_POSITION_LIGHT_AMBIENT = "u_PositionLight.Ambient";
    public static final String U_POSITION_LIGHT_DIFFUSE = "u_PositionLight.Diffuse";
    public static final String U_POSITION_LIGHT_SPECULAR = "u_PositionLight.Specular";
    public static final String U_MATERIAL_AMBIENT = "u_Material.Ambient";
    public static final String U_MATERIAL_DIFFUSE = "u_Material.Diffuse";
    public static final String U_MATERIAL_SPECULAR = "u_Material.Specular";
    public static final String U_MATERIAL_SPECULAR_HIGHLIGHT = "u_Material.SpecularHighlight";

    // Attribute names
    public static final String A_POSITION_3D = "a_Position3D";
    public static final String A_NORMAL_3D = "a_Normal3D";
    public static final String A_TEX_COORDS_2D = "a_TexCoord";

    protected int program;
    private List<String> attributeList;
    private HashMap<String, Integer> uniformList;

    /**
     * loads shaders, links program and creates attribute and uniform list. The subclasses 'decide'
     * how to fill the attribute and uniform lists.
     * @param context
     * application context
     * @param vsid
     * resource id of vertex shader
     * @param fsid
     * resource id of index shader
     */
    public void compileShaders(Context context, int vsid, int fsid){
        final int vs = ShaderLoader.compileShader(GLES20.GL_VERTEX_SHADER,
                TextResourceLoader.loadFromResource(context, vsid));
        final int fs = ShaderLoader.compileShader(GLES20.GL_FRAGMENT_SHADER,
                TextResourceLoader.loadFromResource(context, fsid));

        attributeList = getAttributeList();


        program = ShaderLoader.linkProgram(vs, fs, attributeList);

        uniformList = getUniformList();
    }

    public int getAttributeLocation(String attribute){
        return attributeList.indexOf(attribute);
    }

    protected abstract List<String> getAttributeList();
    protected abstract HashMap<String, Integer>  getUniformList();
    public abstract void setVertexAttribPointer(Structure structure);

    public void useProgram() {
        GLES20.glUseProgram(program);
    }

    /*
    The following methods are akin to utility methods, not all subclasses will make use of them.
    ToDo: consider moving to static utility class
     */
    public void setMVMatrix(float[] matrix) {
        GLES20.glUniformMatrix4fv(uniformList.get(U_MV_MATRIX), 1, false, matrix, 0);
    }
    public void setMVPMatrix(float[] matrix) {
        GLES20.glUniformMatrix4fv(uniformList.get(U_MVP_MATRIX), 1, false, matrix, 0);
    }
    public void setColour(float r, float g, float b, float a) {
        GLES20.glUniform4f(uniformList.get(U_COLOUR), r, g, b, a);
    }
    public void setTexture1(int texid){
        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texid);

        // Tell the texture uniform sampler to use this texture in the shader by
        // telling it to read from texture unit 0.
        GLES20.glUniform1i(uniformList.get(U_TEXTURE0), 0);
    }

    public void setPositionLight(PositionLight light){
        GLES20.glUniform3fv(uniformList.get(U_POSITION_LIGHT_POSITION_3D), 1,
                light.getViewPosition(), 0);
        GLES20.glUniform4fv(uniformList.get(U_POSITION_LIGHT_AMBIENT), 1, light.getAmbient(), 0);
        GLES20.glUniform4fv(uniformList.get(U_POSITION_LIGHT_DIFFUSE), 1, light.getDiffuse(), 0);
        GLES20.glUniform4fv(uniformList.get(U_POSITION_LIGHT_SPECULAR), 1, light.getSpecular(), 0);
    }

    public void setMaterial(Material material){
        GLES20.glUniform4fv(uniformList.get(U_MATERIAL_AMBIENT), 1, material.getAmbient(), 0);
        GLES20.glUniform4fv(uniformList.get(U_MATERIAL_DIFFUSE), 1, material.getDiffuse(), 0);
        GLES20.glUniform4fv(uniformList.get(U_MATERIAL_SPECULAR), 1, material.getSpecular(), 0);
        GLES20.glUniform1f(uniformList.get(U_MATERIAL_SPECULAR_HIGHLIGHT),
                material.getSpecularHighlight());
    }
}
