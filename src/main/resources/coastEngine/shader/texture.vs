#version 450 core

layout (location =0) in vec3 position;
layout (location =1) in vec2 uvs;
layout (location =2) in vec3 color;

out vec2 pass_uvs;
out vec3 exColor;

void main(void){
	gl_Position = vec4(position, 1.0);
	pass_uvs = uvs;
	exColor = color;
}