package org.lilsnow.game.core;

import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lilsnow.game.Core;
import org.lilsnow.game.core.input.InputCallbacks;
import org.lilsnow.game.core.input.Mouse;
import org.lilsnow.game.gfx.gl.*;
import org.lilsnow.game.obj.dim3.Box3D;
import org.lilsnow.game.obj.dim3.Camera3Dv;
import org.lilsnow.game.obj.dim3.GameObject3D;
import org.lilsnow.game.obj.dim3.Plane3D;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private Window() { }

    private static long window = 0L;
    private static final ImGuiImplGl3 imguigl = new ImGuiImplGl3 ();
    private static final ImGuiImplGlfw imguiglfw = new ImGuiImplGlfw ();
    
    private static final String glslVersion = null;

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

        glfwSetCursorPosCallback (window, InputCallbacks::mouse_pos_callback);
        glfwSetMouseButtonCallback (window, InputCallbacks::mouse_button_callback);
        glfwSetScrollCallback (window, InputCallbacks::mouse_scroll_callback);
        
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    public static void run() {
        GL.createCapabilities();
        
        ImGui.createContext ();
    
        imguiglfw.init (window, true);
        imguigl.init (glslVersion);
        
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    
        GameObject3D object = new GameObject3D (
                new Box3D (new Vector3f (2.0f, 2.0f, 2.0f)),
                new Vector3f (0.0f, 0.0f, 0.0f),
                new Vector3f (0.0f, 0.0f, 0.0f),
                new Vector3f (1.0f, 1.0f, 1.0f)
        );
        
        GameObject3D object2 = new GameObject3D (
                new Plane3D (new Vector2f (1.0f, 1.0f)),
                new Vector3f (10.0f, 10.0f, 10.0f),
                new Vector3f (45.0f, 0.0f, 0.0f),
                new Vector3f (0.5f, 0.5f, 0.5f)
        );
    
    
        GLVertexArray vao = new GLVertexArray();
        GLVertexBuffer vbo = new GLVertexBuffer(object.get_model().get_vertices ());
    
        GLBufferLayout layout = new GLBufferLayout();
        layout.push_float(3);
        layout.push_float(3);
        layout.push_float (2);
        vao.add_buffer(vbo, layout);
    
        GLIndexBuffer ibo = new GLIndexBuffer(object.get_model().get_indices ());
    
        GLShader shader = new GLShader(
                "res/assets/shaders/vertex.glsl",
                "res/assets/shaders/fragment.glsl");
        shader.bind();
        
        GLTexture2D texture = new GLTexture2D ("res/assets/textures/arrow.png");
        texture.bind ();
        shader.set_uniform_1i ("u_Texture", 0);
        
        GLVertexArray vao2 = new GLVertexArray ();
        GLVertexBuffer vbo2 = new GLVertexBuffer (object2.get_model ().get_vertices ());
        
        vao2.add_buffer (vbo2, layout);
        
        GLIndexBuffer ibo2 = new GLIndexBuffer (object2.get_model ().get_indices ());
        
        GLShader shader2 = new GLShader (
                "res/assets/shaders/light_vert.glsl",
                "res/assets/shaders/light_frag.glsl"
        );
        shader2.bind ();
        
        GLTexture2D texture2 = new GLTexture2D (
                "res/assets/textures/jan.png"
        );
        texture2.bind (1);
        shader2.set_uniform_1i ("u_Texture", 1);
        
        Camera3Dv cam = new Camera3Dv ();
    
        texture.unbind ();
    
        shader.unbind ();
        
        vao.unbind ();
        
        vbo.unbind ();
        
        ibo.unbind ();

        GLRenderer renderer = new GLRenderer();
        
        cam.move (new Vector3f (0.0f, 0.0f, 5.0f));
        while (!glfwWindowShouldClose(window)) {

            update();

            if (Mouse.is_dragging ()) {
                if (Mouse.isButton (GLFW_MOUSE_BUTTON_1)) {
                    cam.move (new Vector3f (-Mouse.get_dx () * 0.01f, -Mouse.get_dy () * 0.01f, 0.0f));
                }
                
                if (Mouse.isButton (GLFW_MOUSE_BUTTON_2)) {
                    cam.rotate (new Vector3f (Mouse.get_dy () * 0.01f, -Mouse.get_dx () * 0.01f, 0.0f));
                }
            }
            
            cam.move (new Vector3f (0.0f, 0.0f, -Mouse.get_scroll_y ()));
            
            object2.move (new Vector3f (-4.0f, 4.0f, -4.0f));
            
            renderer.clear();
            imguiglfw.newFrame ();
            ImGui.newFrame ();
    
            render();
    
            Matrix4f mvp = new Matrix4f();
            
//            texture.bind ();
            cam.get_vp ().mul(object.get_model_matrix (), mvp);
            shader.set_uniform_m4f ("u_MVP", mvp);
            
            renderer.draw(vao, ibo, shader);
            
//            texture2.bind (1);
            cam.get_vp ().mul (object2.get_model_matrix (), mvp);
            shader2.set_uniform_m4f ("u_MVP", mvp);

            renderer.draw (vao2, ibo2, shader2);
            
            gui_render ();
            
            ImGui.render();
            imguigl.renderDrawData (ImGui.getDrawData ());
    
            Mouse.end ();
            
            glfwSwapBuffers(glfwGetCurrentContext());
            glfwPollEvents();
        }
        
        shader.delete ();
        vbo.delete ();
        vao.delete ();
        ibo.delete ();

        imguigl.dispose ();
        imguiglfw.dispose ();
        ImGui.destroyContext ();
        glfwFreeCallbacks (window);
        
        glfwDestroyWindow (window);
        glfwTerminate();
    }

    public static void update() {

    }

    public static void render() {
    
    }
    
    public static void gui_render() {
    
    }

}
