package org.lilsnow.game.gfx.gl;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_INT;

public class GLBufferLayout {

    private int stride;

    private List<GLBufferElement> elements = new ArrayList<>();

    public void push_float(int count) {
        elements.add(new GLBufferElement(GL_FLOAT, count, false));
        stride += Float.BYTES * count;
    }

    public void push_int(int count) {
        elements.add(new GLBufferElement(GL_INT, count, false));
        stride += Integer.BYTES * count;
    }

    public GLBufferElement[] get_elements () {
        GLBufferElement[] arr = new GLBufferElement[elements.size()];
        elements.toArray(arr);
        return arr;
    }

    public int get_stride() {
        return stride;
    }

    public static class GLBufferElement {
        int count;
        int type;
        boolean normalized;

        public GLBufferElement(int type, int count, boolean normalized) {
            this.type = type;
            this.count = count;
            this.normalized = normalized;
        }

        public int get_size() {
            switch (type) {
                case GL_FLOAT -> { return Float.BYTES; }
                case GL_INT -> { return Integer.BYTES; }
                default -> { return 0; }
            }
        }
    }

}
