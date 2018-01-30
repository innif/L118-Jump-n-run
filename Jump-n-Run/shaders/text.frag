#version 330 core

in DATA {
	vec2 uvs;
} fs_in;

uniform sampler2D font;
uniform vec3 color;

const float width = 0.5;
const float edge = 0.1;

void main()
{
	float distance = 1.0 - texture(font, fs_in.uvs).a;

	gl_FragColor = vec4(color, 1.0 - smoothstep(width, width + edge, distance));
	//gl_FragColor = vec4(fs_in.uvs, 0.0, 1.0);
}