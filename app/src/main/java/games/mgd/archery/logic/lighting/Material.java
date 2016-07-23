package games.mgd.archery.logic.lighting;

/**
 * Created by Michael on 23/02/2016.
 */
public class Material {
    private float [] ambient;
    private float [] diffuse;
    private float [] specular;
    private float specularHighlight;

    private Material(){
        // no default material allowed!
    }
    public Material(float [] ambient, float [] diffuse, float [] specular,
                     float specularHighlight){
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.specularHighlight = specularHighlight;
    }

    public float [] getAmbient() { return ambient; }
    public float [] getDiffuse() { return diffuse; }
    public float [] getSpecular() { return specular; }
    public float getSpecularHighlight() { return specularHighlight; }
}
