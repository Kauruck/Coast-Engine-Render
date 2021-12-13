#version 450 core

in vec3 exColour;
in vec2 pass_uvs;

out vec4 fragColor;

void main()
{
    fragColor = vec4(exColour, 1.0);
}
