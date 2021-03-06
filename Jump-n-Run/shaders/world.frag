#version 330 core

in DATA {
	vec2 uvs;
	float tid;
} fs_in;

uniform sampler2D textures[32];

void main()
{
	int index = int(fs_in.tid + 0.5);
	gl_FragColor = texture(textures[index], fs_in.uvs);
	//gl_FragColor = vec4(fs_in.uvs, 0.0, 1.0);
}