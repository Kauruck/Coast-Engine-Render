package com.kauruck.coastengine.render.systems;

import com.kauruck.coastEngine.centum.Centum;
import com.kauruck.coastEngine.centum.component.AbstractComponent;
import com.kauruck.coastEngine.centum.system.AbstractSystem;
import com.kauruck.coastengine.render.components.RenderComponent;
import com.kauruck.coastengine.render.window.Window;

public class RenderSystem extends AbstractSystem<RenderComponent> {
    public RenderSystem(float maxFps, Class<RenderComponent> renderComponentClass) {
        super(maxFps, renderComponentClass);
    }

    @Override
    public void process(AbstractComponent component, float deltaTime) {
        if(component instanceof RenderComponent){
            RenderComponent rc = (RenderComponent) component;
            Window.scheduleRendering(rc.getRender().render(component.getEntity()));
        }
    }
}
