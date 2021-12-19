#version 130

in vec2 out_uv;

out vec4 fragColor;

uniform sampler2D texture_sampler;

void main()
{
    fragColor = texture(texture_sampler, out_uv);
}
