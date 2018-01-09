#version 330 core

in DATA {
	vec2 uvs;
} fs_in;

uniform vec3 color;

void main()
{
	gl_FragColor = vec4(color, 1.0);
}