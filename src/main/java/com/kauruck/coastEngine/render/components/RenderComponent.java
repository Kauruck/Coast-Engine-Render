package com.kauruck.coastEngine.render.components;

import com.kauruck.coastEngine.centum.component.AbstractComponent;
import com.kauruck.coastEngine.render.rendering.BaseRender;
import com.kauruck.coastEngine.render.rendering.Mesh;

public class RenderComponent extends AbstractComponent {
    private final BaseRender render;
    private Mesh mesh;

    public RenderComponent(BaseRender render) {
        this.render = render;
    }

    public RenderComponent(BaseRender render, Mesh mesh) {
        this.render = render;
        this.mesh = mesh;
    }

    public BaseRender getRender() {
        return render;
    }


    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}
