package org.lilsnow.game.core;

import org.lilsnow.game.Core;
import org.lilsnow.game.gfx.gl.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private Window() { }

    private static long window = 0L;

    public static void init() {
        if (!glfwInit())
            Core.GAME_LOGGER.fatal("Failed to initialize GLFW");


        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        window = glfwCreateWindow(1920, 1080, Core.NAME, NULL, NULL);

        if (window == NULL)
            Core.GAME_LOGGER.fatal("Failed to create GLFW Window");

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    public static void run() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        float[] positions = {
                //Position          // Color
                -0.75f, -0.75f,       1.0f, 1.0f, 0.0f,       0.0f, 0.0f, // Bottom left
                 0.75f, -0.75f,       1.0f, 0.0f, 0.0f,       1.0f, 0.0f, // Bottom right
                 0.75f,  0.75f,       0.0f, 0.0f, 1.0f,       1.0f, 1.0f, // Top right
                -0.75f,  0.75f,       0.0f, 1.0f, 0.0f,       0.0f, 1.0f  // Top left
        };

        int[] indices = {
                0, 1, 2,
                2, 3, 0
        };

        GLVertexArray vao = new GLVertexArray();
        GLVertexBuffer vbo = new GLVertexBuffer(positions);

        GLBufferLayout layout = new GLBufferLayout();
        layout.push_float(2);
        layout.push_float(3);
        layout.push_int(2);
        vao.add_buffer(vbo, layout);

        GLIndexBuffer ibo = new GLIndexBuffer(indices);

        GLShader shader = new GLShader(
                "res/assets/shaders/vertex.glsl",
                "res/assets/shaders/fragment.glsl");
        shader.bind();

        GLTexture2D texture = new GLTexture2D("res/assets/textures/jan.jpg");
        texture.bind();
        shader.set_uniform_1i("u_Jan", 0);

        shader.unbind();
        vao.unbind();
        vbo.unbind();
        ibo.unbind();

        GLRenderer renderer = new GLRenderer();
        while (!glfwWindowShouldClose(window)) {

            update();

            renderer.clear();
            render();

            renderer.draw(vao, ibo, shader);

            glfwSwapBuffers(glfwGetCurrentContext());
            glfwPollEvents();
        }

        shader.delete();
        vbo.delete();
        vao.delete();
        ibo.delete();

        glfwTerminate();
    }

    public static void update() {

    }

    public static void render() {
    }

}
