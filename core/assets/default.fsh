uniform sampler2D u_texture_0;

uniform vec2 u_size;

void main()
{
	vec2 texCoord = vec2(gl_FragCoord.x / u_size.x, 1.0 - (gl_FragCoord.y / u_size.y));

	gl_FragColor = texture2D(u_texture_0, texCoord);//vec4(1.0, 0.5, 0.2, 1.0); //* texture2D(u_texture, v_texCoords);
}