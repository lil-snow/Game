package org.lilsnow.game.gfx.gl;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class GLIndexBuffer {

    private int renderer_id;
    private int count;

    public GLIndexBuffer(int[] data) {
        this.count = data.length;
        renderer_id = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, renderer_id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, renderer_id);
    }

    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void delete() {
        glDeleteBuffers(renderer_id);
    }

    public int get_count() {
        return count;
    }

}
