#version 330 core

layout(location = 0) out vec4 color;

in vec2 f_TexCoord;
in vec4 f_Color;

uniform sampler2D u_Jan;

void main() {

    color = texture(u_Jan, f_TexCoord);

}