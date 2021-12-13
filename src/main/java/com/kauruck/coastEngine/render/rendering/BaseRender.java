package com.kauruck.coastEngine.render.rendering;

import com.kauruck.coastEngine.centum.entity.Entity;
import com.kauruck.coastEngine.render.components.RenderComponent;

public abstract class BaseRender{

    public abstract void create();

    public abstract void render(RenderComponent component, Entity entity);
}
