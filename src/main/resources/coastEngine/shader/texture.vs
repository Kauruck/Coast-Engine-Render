#version 130

in vec3 position;
in vec2 uv;

out vec2 out_uv;

uniform mat4 projectionMatrix;
uniform mat4 transformationMatrix;

void main(void){
	gl_Position = projectionMatrix * transformationMatrix * vec4(position, 1.0);
	out_uv = uv;
}