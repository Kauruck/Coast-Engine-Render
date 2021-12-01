package com.kauruck.coastEngine.render;

import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import com.kauruck.coastEngine.core.threding.ThreadManger;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.systems.RenderSystem;
import com.kauruck.coastEngine.render.window.Window;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Render {

    public static void init(){
        Centum.registerSystem(new RenderSystem(30, RenderComponent.class), (Centum.OnStartExecutor) () -> {
            glfwMakeContextCurrent(Window.getId());
            GL.createCapabilities();
            glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        }, (Centum.OnTickStartExecutor) () -> {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        }, (Centum.OnTickEndExecutor) () -> {
            glfwSwapBuffers(Window.getId());

        });
    }

    public static void showWindow(Runnable setup) {
        Thread thread = new Thread(setup);
        thread.start();
        while (!glfwWindowShouldClose(Window.getId())){
            glfwPollEvents();
        }

        try {
            ThreadManger.stopAllThreads();
        } catch (NoSuchProcessException e) {
            e.printStackTrace();
        }

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(Window.getId());
        glfwDestroyWindow(Window.getId());

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


}
