uniform sampler2D u_texture_0;
uniform sampler2D u_texture_1;

uniform vec2 u_size;

vec3 desaturate(vec3 color, float amount)
{
    vec3 gray = vec3(dot(vec3(0.2126, 0.7152, 0.0722), color));
    return vec3(mix(color, gray, amount));
}

void main()
{
	vec2 texCoord = vec2(gl_FragCoord.x / u_size.x, 1.0 - (gl_FragCoord.y / u_size.y));

	gl_FragColor = vec4(desaturate(texture2D(u_texture_0, texCoord).rgb, 1.0 - texture2D(u_texture_1, texCoord).r * 3.0), 1.0);
}