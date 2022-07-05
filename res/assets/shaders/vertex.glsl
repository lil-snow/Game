#version 330 core

layout(location = 0) in vec2 position;
layout(location = 1) in vec3 color;
layout(location = 2) in vec2 texCoord;

out vec4 f_Color;
out vec2 f_TexCoord;

void main() {
    f_Color = vec4(color, 1.0);
    f_TexCoord = texCoord;
    gl_Position = vec4(position, 0.0, 1.0);
}