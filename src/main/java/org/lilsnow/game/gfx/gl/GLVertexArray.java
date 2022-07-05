package org.lilsnow.game.gfx.gl;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class GLVertexArray {

    private int renderer_id;

    public GLVertexArray() {
        renderer_id = glGenVertexArrays();
    }

    public void add_buffer(GLVertexBuffer vbo, GLBufferLayout layout) {
        bind();
        vbo.bind();
        GLBufferLayout.GLBufferElement[] elements = layout.get_elements();
        int offset = 0;
        for (int i = 0; i < elements.length; i++) {
            GLBufferLayout.GLBufferElement element = elements[i];
            glEnableVertexAttribArray(i);
            glVertexAttribPointer(i, element.count, element.type, element.normalized,
                    layout.get_stride(), offset);
            offset += element.count * element.get_size();
        }

    }

    public void bind() {
        glBindVertexArray(renderer_id);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void delete() {
        glDeleteVertexArrays(renderer_id);
    }

}
