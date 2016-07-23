precision mediump float;

uniform sampler2D s_Texture0;

varying vec2 v_TexCoord;
varying vec4 v_Colour;

void main()
{
	gl_FragColor = v_Colour * texture2D(s_Texture0, v_TexCoord);
}