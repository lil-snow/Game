#version 330 core

layout(location = 0) in vec3 a_Position;
layout(location = 1) in vec3 a_Normal;
layout(location = 2) in vec2 a_UV;

out vec3 f_Normal;
out vec2 f_UV;

uniform mat4 u_MVP;

void main() {

    f_Normal = a_Normal;
    f_UV = a_UV;
    vec4 pos = vec4(a_Position, 1.0f) * u_MVP;
    gl_Position = pos;

}