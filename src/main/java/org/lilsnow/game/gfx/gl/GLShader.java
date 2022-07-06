package org.lilsnow.game.gfx.gl;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lilsnow.game.Core;
import org.lilsnow.game.gfx.ColorRGBA;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

public class GLShader {

    private final int renderer_id;
    private final String vertex, fragment;

    private final HashMap<String, Integer> location_cache = new HashMap<>();

    public GLShader(String vertex, String fragment) {
        this.vertex = vertex;
        this.fragment = fragment;

        renderer_id = create_shader(vertex, fragment);
    }

    public void bind() {
        glUseProgram(renderer_id);
    }

    public void unbind() {
        glUseProgram(0);
    }
    
    public void set_uniform_4f(String name, Vector4f vector) {
        glUniform4f(get_uniform_loc(name), vector.x, vector.y, vector.z, vector.w);
    }
    
    public void set_uniform_4f(String name, ColorRGBA color) {
        glUniform4f (get_uniform_loc (name), color.r, color.g, color.b, color.a);
    }

    public void set_uniform_1i(String name, int value) {
        glUniform1i(get_uniform_loc(name), value);
    }

    public void set_uniform_m4f(String name, Matrix4f matrix) {
        float[] f = new float[16];
        matrix.get (f);
        glUniformMatrix4fv (get_uniform_loc (name), true, f);
    }
    
    public void set_uniform_bool(String name, boolean bool) {
        glUniform1i (get_uniform_loc (name), (bool) ? 1 : 0);
    }
    
    public void set_uniform_3f(String name, Vector3f v) {
        glUniform3f (get_uniform_loc (name), v.x, v.y, v.z);
    }
    
    public void delete() {
        glDeleteProgram(renderer_id);
    }

    private int get_uniform_loc(String name) {
        if (location_cache.containsKey(name)) {
            return location_cache.get(name);
        } else {
            int loc = glGetUniformLocation(renderer_id, name);
            if (loc == -1)
                Core.GAME_LOGGER.error("Uniform Error: Uniform '" + name + "' does not exist");
            else
                location_cache.put(name, loc);
            return loc;
        }
    }

    private int compile_shader(int type, String source) {
        int id = glCreateShader(type);
        glShaderSource(id, source);
        glCompileShader(id);

        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
            Core.GAME_LOGGER.error(
                    ((type == GL_VERTEX_SHADER) ? "Vertex" : "Fragment" ) + " Shader " +
                    glGetShaderInfoLog(id));
        }

        return id;
    }

    private int create_shader(String vertex_shader, String fragment_shader) {
        int program = glCreateProgram();
        int vs, fs;
        try {
            vs = compile_shader(GL_VERTEX_SHADER, new String(Files.readAllBytes(Paths.get(vertex_shader))));
            fs = compile_shader(GL_FRAGMENT_SHADER, new String(Files.readAllBytes(Paths.get(fragment_shader))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        glAttachShader(program, vs);
        glAttachShader(program, fs);

        glLinkProgram(program);

        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            Core.GAME_LOGGER.error(glGetProgramInfoLog(program));
        }

        glValidateProgram(program);
        glDeleteShader(vs);
        glDeleteShader(fs);

        return program;
    }

    public int get_id() {
        return renderer_id;
    }

    public String get_vertex_source() {
        try {
            return new String(Files.readAllBytes(Paths.get(vertex)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get_fragment_source() {
        try {
            return new String(Files.readAllBytes(Paths.get(fragment)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
