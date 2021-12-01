package com.kauruck.coastEngine.render.systems;

import com.kauruck.coastEngine.centum.component.AbstractComponent;
import com.kauruck.coastEngine.centum.system.AbstractSystem;
import com.kauruck.coastEngine.render.components.RenderComponent;
import com.kauruck.coastEngine.render.window.Window;

public class RenderSystem extends AbstractSystem<RenderComponent> {
    public RenderSystem(float maxFps, Class<RenderComponent> renderComponentClass) {
        super(maxFps, renderComponentClass);
    }

    @Override
    public void process(AbstractComponent component, float deltaTime) {
        if(component instanceof RenderComponent){
            RenderComponent rc = (RenderComponent) component;
            rc.getRender().render(component.getEntity());
        }
    }
}
