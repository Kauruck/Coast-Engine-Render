#version 400 core

in vec3 position;

out vec4 out_color;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform vec3 color;

void main(void){
    gl_Position =  projectionMatrix * transformationMatrix  * vec4(position, 1.0);
    out_color = vec4(color, 1.0);
}