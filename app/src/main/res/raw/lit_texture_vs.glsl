struct PositionLight
{
	vec3 Position;
	vec4 Ambient;
	vec4 Diffuse;
	vec4 Specular;
};

struct Material
{
	vec4 Ambient;
	vec4 Diffuse;
	vec4 Specular;
	float SpecularHighlight;
};

const float ZERO = 0.0;
const float ONE = 1.0;

uniform PositionLight u_PositionLight;
uniform Material u_Material;

uniform mat4 u_mv_Matrix;
uniform mat4 u_mvp_Matrix;

attribute vec3 a_Position3D;
attribute vec3 a_Normal3D;
attribute vec2 a_TexCoord;

varying vec2 v_TexCoord;
varying vec4 v_Colour;

vec4 CalculatePositionLight(vec3 position, vec3 normal)
{
	vec4 light = vec4(ZERO, ZERO, ZERO, ZERO);

    float distance = length(u_PositionLight.Position - position);
	vec3 direction = normalize(u_PositionLight.Position - position);
	float ndotd = max(ZERO, dot(normal, direction));
	vec3 reflection = (2.0 * ndotd * normal) - direction;

	float attenuation = ONE / (ONE + (0.005 * distance) + (0.0005 * distance * distance));

	float ndotr = max(ZERO, dot(normal, reflection));

	light += u_PositionLight.Ambient * u_Material.Ambient;
	light += u_PositionLight.Diffuse * u_Material.Diffuse * ndotd * attenuation;
	if(ndotd > ZERO){

		light += u_PositionLight.Specular * u_Material.Specular * pow(ndotr, u_Material.SpecularHighlight) * attenuation;
	}
	return light;
}

void main()
{
	gl_Position = u_mvp_Matrix * vec4(a_Position3D, ONE);

	vec3 viewPosition = vec3(u_mv_Matrix * vec4(a_Position3D, ONE));
	vec3 viewNormal   = vec3(u_mv_Matrix * vec4(a_Normal3D, ZERO));

	v_TexCoord = a_TexCoord;

	v_Colour = CalculatePositionLight(viewPosition, viewNormal);

	gl_PointSize = 10.0;
}