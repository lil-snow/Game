package org.lilsnow.game.gfx.gl;

import static org.lwjgl.opengl.GL11.*;

public class GLRenderer {

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void draw(GLVertexArray vao, GLIndexBuffer ibo, GLShader shader) {
        vao.bind();
        ibo.bind();
        shader.bind();
        glDrawElements(GL_TRIANGLES, ibo.get_count(), GL_UNSIGNED_INT, 0);
    }

}
