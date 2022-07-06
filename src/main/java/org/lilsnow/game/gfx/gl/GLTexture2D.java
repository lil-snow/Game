package org.lilsnow.game.gfx.gl;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.stb.STBImage.*;

public class GLTexture2D {

    private final int renderer_id;
    private final String filepath;
    private ByteBuffer texture;
    int width, height, channels;

    public GLTexture2D(String filepath) {
        this.filepath = filepath;
        texture = null;
        width = 0;
        height = 0;
        channels = 0;

        stbi_set_flip_vertically_on_load(true);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        texture = stbi_load(filepath, width, height, channels, 0);

        this.width = width.get(0);
        this.height = height.get(0);
        this.channels = channels.get(0);

        renderer_id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, renderer_id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, texture);
        glBindTexture(GL_TEXTURE_2D, 0);

        if (texture != null)
            stbi_image_free(texture);
    }

    public void bind(int slot) {
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, renderer_id);
    }

    public void bind() {
        bind(0);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int get_width() {
        return width;
    }

    public int get_height() {
        return height;
    }

    public void delete() {
        glDeleteTextures(renderer_id);
    }
    
    public String get_filepath() {
        return filepath;
    }
    
    public int get_id() {
        return renderer_id;
    }
    
}
