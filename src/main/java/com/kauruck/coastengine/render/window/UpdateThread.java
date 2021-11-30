package com.kauruck.coastengine.render.window;

import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import com.kauruck.coastEngine.core.threding.Thread;
import com.kauruck.coastEngine.core.threding.ThreadManger;
import com.kauruck.coastengine.render.rendering.Renderable;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class UpdateThread extends Thread {

    public UpdateThread(float maxFps) {
        super(maxFps);
    }

    @Override
    public void onTick(float v) {
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        if(glfwWindowShouldClose(Window.getId())) {
            try {
                ThreadManger.stopThread(Window.getPid());
            } catch (NoSuchProcessException e) {
                e.printStackTrace();
            }
        }
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(Window.getId()); // swap the color buffers

        //TODO Threadsave
        for(Renderable current : Window.getRenderables()){
            current.execute();
        }

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }

    @Override
    public void onStart() {
        glfwMakeContextCurrent(Window.getId());
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void onEnd() {

    }
}
