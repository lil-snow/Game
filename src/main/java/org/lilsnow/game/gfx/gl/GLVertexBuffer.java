package org.lilsnow.game.gfx.gl;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class GLVertexBuffer {

    public GLVertexBuffer(float[] data) {
        renderer_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, renderer_id);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, renderer_id);
    }

    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void delete() {
        glDeleteBuffers(renderer_id);
    }

    private int renderer_id;

}
