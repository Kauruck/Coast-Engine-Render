package com.kauruck.coastEngine.render.systems;

import com.kauruck.coastEngine.centum.component.AbstractComponent;
import com.kauruck.coastEngine.centum.system.AbstractSystem;
import com.kauruck.coastEngine.core.input.Input;
import com.kauruck.coastEngine.core.resources.ResourceLocation;
import com.kauruck.coastEngine.render.Render;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.window.Window;
import org.lwjgl.opengl.GL11;

import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.glViewport;

public class RenderSystem extends AbstractSystem<RenderComponent> {
    public RenderSystem(float maxFps, Class<RenderComponent> renderComponentClass) {
        super(maxFps, renderComponentClass);
    }

    @Override
    public void process(AbstractComponent component, float deltaTime) {
        if(component instanceof RenderComponent){
            RenderComponent rc = (RenderComponent) component;
            rc.getRender().render((RenderComponent) component, component.getEntity());
        }
    }

    @Override
    public void pre() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

        if ( Window.isResized()) {
            glViewport(0, 0, Window.getWidth(), Window.getHeight());
            Render.recreateProjectionMatrix();
            Window.setResized(false);
        }

        List<Render.RenderThreadScheduleWithCallback> toRender = List.copyOf(Render.schedules);
        Render.schedules.clear();
        for(Render.RenderThreadScheduleWithCallback current : toRender){
            current.execute();
            current.callback();
        }
    }

    @Override
    public void post() {
        glfwSwapBuffers(Window.getId());
        Input.update();
    }

    @Override
    public ResourceLocation getID() {
        return new ResourceLocation("coastEngine","renderThread");
    }
}
