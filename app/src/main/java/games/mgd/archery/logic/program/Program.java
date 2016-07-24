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
    public HashMap<String, Integer> getUniforms() { return uniformList; }
    protected abstract List<String> getAttributeList();
    protected abstract HashMap<String, Integer>  getUniformList();
    public abstract void setVertexAttribPointer(Structure structure);

    public void useProgram() {
        GLES20.glUseProgram(program);
    }
}
