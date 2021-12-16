package com.kauruck.coastEngine.render;

import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.core.exception.NoSuchProcessException;
import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.resources.ResourceLoader;
import com.kauruck.coastEngine.core.threding.ThreadManger;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.rendering.BaseRender;
import com.kauruck.coastEngine.render.rendering.SquareRender;
import com.kauruck.coastEngine.render.rendering.TextureRender;
import com.kauruck.coastEngine.render.resources.TextureHandler;
import com.kauruck.coastEngine.render.shader.FragmentShader;
import com.kauruck.coastEngine.render.resources.FragmentShaderHandler;
import com.kauruck.coastEngine.render.shader.Shader;
import com.kauruck.coastEngine.render.shader.VertexShader;
import com.kauruck.coastEngine.render.resources.VertexShaderHandler;
import com.kauruck.coastEngine.render.systems.RenderSystem;
import com.kauruck.coastEngine.render.textures.Texture;
import com.kauruck.coastEngine.render.window.Window;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Render {

    public static final Logger LOGGER = LoggerFactory.getLogger("Render");

    private static List<BaseRender> renders = new ArrayList<>();

    public static void registerRenderHelper(BaseRender render){
        renders.add(render);
    }

    private static  final List<RenderThreadScheduleWithCallback> schedules = new ArrayList<>();

    public static void init(){
        //Register resource loaders
        ResourceLoader.registerResourceHandler(new TextureHandler(), Texture.class);
        ResourceLoader.registerResourceHandler(new FragmentShaderHandler(), FragmentShader.class);
        ResourceLoader.registerResourceHandler(new VertexShaderHandler(), VertexShader.class);
        //Register renders
        registerRenderHelper(new SquareRender());
        registerRenderHelper(new TextureRender());
        //Register Systems
        Centum.registerSystem(new RenderSystem(30, RenderComponent.class), (Centum.OnStartExecutor) () -> {
            glfwMakeContextCurrent(Window.getId());
            GL.createCapabilities();
            glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
            LOGGER.info("Adding " + renders.size() + " renders.");
            for(BaseRender current : renders){
                current.create();
            }

        }, (Centum.OnTickStartExecutor) Render::onTick,
        (Centum.OnTickEndExecutor) () -> {
            glfwSwapBuffers(Window.getId());
            Input.update();
        });
    }

    public static void scheduleOnRenderThread(RenderThreadSchedule schedule){
        Render.schedules.add(new RenderThreadScheduleWithCallback(schedule));
    }

    public static void scheduleOnRenderThreadBlocking(RenderThreadSchedule schedule){
        AtomicBoolean done = new AtomicBoolean(false);
        Render.schedules.add(new RenderThreadScheduleWithCallback(schedule, () -> {
            done.set(true);
        }));
        while (!done.get()){

        }

    }

    public static void onTick(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        List<RenderThreadScheduleWithCallback> toRender = List.copyOf(schedules);
        schedules.clear();
        for(RenderThreadScheduleWithCallback current : toRender){
            current.execute();
            current.callback();
        }

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

        glfwMakeContextCurrent(Window.getId());
        GL.createCapabilities();
        cleanUp();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(Window.getId());
        glfwDestroyWindow(Window.getId());

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


    public interface RenderThreadSchedule{
        void execute();
    }

    private interface RenderThreadCallback{
        void execute();
    }

    private static class RenderThreadScheduleWithCallback{
        private final RenderThreadSchedule schedule;
        private final RenderThreadCallback callback;


        public RenderThreadScheduleWithCallback(RenderThreadSchedule schedule) {
            this.schedule = schedule;
            this.callback = null;
        }

        public RenderThreadScheduleWithCallback(RenderThreadSchedule schedule, RenderThreadCallback callback) {
            this.schedule = schedule;
            this.callback = callback;
        }

        public void execute(){
            schedule.execute();
        }

        public void callback(){
            if(callback != null)
                callback.execute();
        }
    }

    public static void cleanUp(){
        Shader.cleanUp();
        Texture.cleanUp();
    }

}
