#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texCoords;

out DATA {
	vec2 uvs;
} vs_out;


uniform vec2 translation;
uniform vec2 screenSize;

void main()
{
	vs_out.uvs = texCoords;
	gl_Position = ((position + vec4(translation, 0.0f, 0.0f)) / vec4(screenSize / 2.0, 1.0, 1.0)) - vec4(1.0, 1.0, 0.0, 0.0);
}