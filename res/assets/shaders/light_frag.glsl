#version 330 core

layout(location = 0) out vec4 color;

in vec2 f_UV;

uniform sampler2D u_Texture;

void main() {

    color = texture(u_Texture, f_UV);

}