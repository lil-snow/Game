#version 330 core

layout(location = 0) out vec4 color;

uniform sampler2D u_Texture;

in vec3 f_Normal;
in vec2 f_UV;

void main() {

    color = texture(u_Texture, f_UV);

}