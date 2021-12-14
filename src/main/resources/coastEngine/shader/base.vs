#version 400 core

in vec3 position;
in vec4 color;

out vec4 out_color;

void main(void){
    gl_Position = vec4(position, 1.0);
    out_color = color;
}