package com.kauruck.coastEngine.render.components;

import com.kauruck.coastEngine.centum.component.AbstractComponent;
import com.kauruck.coastEngine.render.rendering.BaseRender;

public class RenderComponent extends AbstractComponent {
    private final BaseRender render;

    public RenderComponent(BaseRender render) {
        this.render = render;
    }

    public BaseRender getRender() {
        return render;
    }
}
